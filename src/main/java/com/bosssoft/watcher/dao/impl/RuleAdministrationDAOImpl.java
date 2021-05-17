package com.bosssoft.watcher.dao.impl;

import com.bosssoft.watcher.dao.RuleAdministrationDAO;
import com.bosssoft.watcher.entity.PO.ErrorDataPO;
import com.bosssoft.watcher.entity.PO.ErrorRulePO;
import com.bosssoft.watcher.entity.PO.RuleSetPO;
import com.bosssoft.watcher.entity.enumerate.CreateKeyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RuleAdministrationDAOImpl implements RuleAdministrationDAO {

    private static final Logger logger = LoggerFactory.getLogger(RuleAdministrationDAOImpl.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RuleSetPO> retrieveAllRule() {
        String retrieveAllRuleSql = "select * from pay_fi_rule_set";
        return jdbcTemplate.query(retrieveAllRuleSql, new BeanPropertyRowMapper<>(RuleSetPO.class));
    }

    @Override
    public List<ErrorRulePO> retrieveProvinceRuleError() {
        String retrieveProvinceRuleError = "SELECT mof_div_code,name FROM pay_fi_rule_set WHERE is_deleted = '0' AND is_enabled = '0' AND management_level = '1'";
        List<ErrorRulePO> errorRulePOList = jdbcTemplate.query(retrieveProvinceRuleError, new BeanPropertyRowMapper<>(ErrorRulePO.class));
        for (ErrorRulePO errorRulePO : errorRulePOList) {
            errorRulePO.setError(1);
        }
        return errorRulePOList;
    }

    @Override
    public void insertErrorRule(List<ErrorRulePO> errorRulePOList) {
        String sql = "insert into pay_fi_error_rule(id,mof_div_code,name,error,time) values(?,?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            final LocalDateTime localDateTime = LocalDateTime.now();
            final DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, String.valueOf(CreateKeyEnum.getInstance().getUtils().nextId()));
                preparedStatement.setString(2, errorRulePOList.get(i).getMof_div_code());
                preparedStatement.setString(3, errorRulePOList.get(i).getName());
                preparedStatement.setInt(4, errorRulePOList.get(i).getError());
                preparedStatement.setString(5, localDateTime.format(ofPattern));
            }

            @Override
            public int getBatchSize() {
                return errorRulePOList.size();
            }
        });
    }

    @Override
    public List<String> retrieveRuleId() {
        String sql = "select distinct(id) from pay_fi_rule_set where is_enabled = '1' and is_deleted = '0'";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    @Override
    public List<Map<String, Object>> retrieveRuleInfo() {
        String sql = "select id,mof_div_code from pay_fi_rule_set where is_enabled = '1' and is_deleted = '0'";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> retrieveFunction(String id, String mof_div_code) {
        String sql = "select function_name,operation_symbol,value from pay_fi_rule_set_function where rule_set_id =" +
                "? and mof_div_code = ?";
        return jdbcTemplate.queryForList(sql, new Object[]{id, mof_div_code});
    }

    @Override
    public void executeRuleSql(String sql) {
        jdbcTemplate.execute(sql);
    }

    @Override
    public String retrieveRuleName(String sql, Object[] arg) {
        return jdbcTemplate.queryForMap(sql, arg).get("name").toString();
    }

    @Override
    public List<String> retrieveRegionCode() {
        List<String> regionCode = new ArrayList<>(64);
        String sql = "select distinct(mof_div_code) from pay_di_rule where is_deleted = 2 and is_enabled = 1";
       List<Map<String,Object>> codeMap = jdbcTemplate.queryForList(sql);
       for(Map<String,Object> map : codeMap) {
           regionCode.add(map.get("mof_div_code").toString());
       }
       return regionCode;
    }

    @Override
    public List<ErrorDataPO> retrieveErrorData(List<String> regionCode) {
        String sql = "select fi_vou_no,mof_div_code from pay_di_bill where is_deleted <> 1 and (fi_vou_no,mof_div_code) not in (select pay_app_no," +
                "mof_div_code from pay_fi_voucher where is_deleted <> 1)";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ErrorDataPO.class));
    }
}

