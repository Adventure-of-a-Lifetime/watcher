package com.bosssoft.watcher.module.observe.observed;

import com.bosssoft.watcher.module.observe.inject.RuleInject;
import com.bosssoft.watcher.module.observe.observer.ProvinceRuleObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Map;

@Component
public class ProvinceRuleObserved {

    private final PropertyChangeSupport observed = new PropertyChangeSupport(this);

    public void insertErrorRule(String event, Object oldValue, Object newValue) {
        this.observed.firePropertyChange(event,oldValue,newValue);
    }

    public void addObserver(PropertyChangeListener propertyChangeListener) {
        this.observed.addPropertyChangeListener(propertyChangeListener);
    }
}
