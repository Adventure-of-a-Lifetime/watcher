package com.bosssoft.watcher.module.observe.observer;

import com.bosssoft.watcher.entity.PO.ErrorRulePO;
import com.bosssoft.watcher.module.observe.inject.RuleInject;
import com.bosssoft.watcher.service.RuleAdministrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Optional;

@Component
public class ProvinceRuleObserver implements PropertyChangeListener, RuleInject {

    private static final Logger logger = LoggerFactory.getLogger(ProvinceRuleObserver.class);

    @Resource
    private RuleAdministrationService ruleAdministrationService;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        logger.info("收到数据" + evt.getPropertyName());
        Optional<List<ErrorRulePO>> errorRulePOList;
        errorRulePOList = Optional.ofNullable((List<ErrorRulePO>) evt.getNewValue());
        errorRulePOList.ifPresent(errorList -> ruleAdministrationService.insertErrorRule(errorList));
    }
}
