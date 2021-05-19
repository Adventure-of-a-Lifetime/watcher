package com.bosssoft.watcher.module.observe.observed;

import org.springframework.stereotype.Component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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
