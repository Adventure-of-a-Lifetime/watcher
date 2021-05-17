package com.bosssoft.watcher.module.fork;


import com.bosssoft.watcher.dao.RuleAdministrationDAO;
import com.bosssoft.watcher.entity.PO.ErrorRulePO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class CheckRuleForkJoin extends RecursiveTask<List<ErrorRulePO>> {

    private static final Logger logger = LoggerFactory.getLogger(CheckRuleForkJoin.class);

    private final RuleAdministrationDAO ruleAdministrationDAO;

    private final List<Map<String, Object>> list;

    public CheckRuleForkJoin(List<Map<String, Object>> list, RuleAdministrationDAO ruleAdministrationDAO) {
        this.list = list;
        this.ruleAdministrationDAO = ruleAdministrationDAO;
    }

    @Override
    protected List<ErrorRulePO> compute() {
        StringBuffer joinSql = new StringBuffer();
        StringBuffer sql = new StringBuffer();

        List<ErrorRulePO> rule_list = new ArrayList<>();
        if (list.size() > 1) {
            CheckRuleForkJoin joinOne = new CheckRuleForkJoin(list.subList(0, list.size() / 2), ruleAdministrationDAO);
            CheckRuleForkJoin joinTwo = new CheckRuleForkJoin(list.subList(list.size() / 2, list.size()), ruleAdministrationDAO);

            invokeAll(joinOne, joinTwo);

            try {
                rule_list.addAll(joinOne.get());
                rule_list.addAll(joinTwo.get());
            } catch (InterruptedException | ExecutionException e) {
                logger.error(e.getMessage());
            }
        } else {
            Map<String, Object> map = list.get(0);
            List<Map<String, Object>> functionList = ruleAdministrationDAO.retrieveFunction(map.get("id").toString(), map.get("mof_div_code").toString());
            sql = new StringBuffer(" where 1 = 1");
            joinSql = new StringBuffer("");
            String querySql = "";
            for (Map<String, Object> functionMap : functionList) {
                if (functionMap.get("function_name").toString().charAt(0) == ',') {
                    joinSql.append(functionMap.get("function_name")).append(" ");
                    concatSql(joinSql, functionMap);
                    joinSql.append(" AND to_char(current_date::TIMESTAMP,'yyyy') = to_char(create_date ::TIMESTAMP,'yyyy')");
                } else {
                    sql.append(" and ").append(functionMap.get("function_name")).append(" ");
                    concatSql(sql, functionMap);
                    sql.append(" AND to_char(current_date::TIMESTAMP,'yyyy') = to_char(create_date ::TIMESTAMP,'yyyy')");
                }
            }
            if (joinSql.length() != 0) {
                querySql = "select * from pay_fi_voucher v " + joinSql;
            } else {
                querySql = "select * from pay_fi_voucher v " + sql;
            }
            try {
                ruleAdministrationDAO.executeRuleSql(querySql);
            } catch (Exception e) {
                logger.info(querySql);
                querySql = "select name from pay_fi_rule_set where id = ? and mof_div_code = ? and is_enabled = '1' and is_deleted = '0'";
                ErrorRulePO errorRulePO = new ErrorRulePO();
                errorRulePO.setError(2);
                errorRulePO.setId(map.get("id").toString());
                errorRulePO.setMof_div_code(map.get("mof_div_code").toString());
                errorRulePO.setName(ruleAdministrationDAO.retrieveRuleName(querySql, new Object[]{errorRulePO.getId(), errorRulePO.getMof_div_code()}));
                rule_list.add(errorRulePO);
            }
        }
        return rule_list;
    }

    private void concatSql(StringBuffer sql, Map<String, Object> functionMap) {
        if (functionMap.get("operation_symbol").toString().equals("like")) {
            String[] values = functionMap.get("value").toString().split(";");
            sql.append("in (");
            for (String value : values) {
                sql.append(value).append(",");
            }
            sql.delete(sql.length() - 1, sql.length());
            sql.append(") ");
        } else {
            sql.append(functionMap.get("operation_symbol")).append(" ").append(functionMap.get("value"));
        }
    }
}

