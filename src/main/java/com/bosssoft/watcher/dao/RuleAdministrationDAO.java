package com.bosssoft.watcher.dao;

import com.bosssoft.watcher.entity.PO.ErrorDataPO;
import com.bosssoft.watcher.entity.PO.ErrorRulePO;
import com.bosssoft.watcher.entity.PO.RuleSetPO;

import java.util.List;
import java.util.Map;

public interface RuleAdministrationDAO {

    List<RuleSetPO> retrieveAllRule();

    List<ErrorRulePO> retrieveProvinceRuleError();

    void insertErrorRule(List<ErrorRulePO> errorRulePOList);

    List<String> retrieveRuleId();

    List<Map<String, Object>> retrieveRuleInfo();

    List<Map<String, Object>> retrieveFunction(String id, String mof_div_code);

    void executeRuleSql(String sql);

    String retrieveRuleName(String sql, Object[] arg);

    List<String> retrieveRegionCode();

    List<ErrorDataPO> retrieveErrorData(List<String> regionCode);
}

