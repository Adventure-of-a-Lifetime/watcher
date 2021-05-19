package com.bosssoft.watcher.module.observe;

import com.bosssoft.watcher.module.observe.inject.RuleInject;
import com.bosssoft.watcher.module.observe.observed.ProvinceRuleObserved;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.beans.PropertyChangeListener;
import java.util.List;

@Component
public class ObserverConfigure implements CommandLineRunner {

    @Resource
    private List<RuleInject> observers;

    @Resource
    private ProvinceRuleObserved observed;

    @Override
    public void run(String... args) {
        for(RuleInject observer : observers) {
            observed.addObserver((PropertyChangeListener) observer);
        }
    }
}
