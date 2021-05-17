package com.bosssoft.watcher.entity.PO;

import java.util.Objects;

public class RuleSetPO extends BasePO {

    private String di_rule_id;
    private int fi_rule_code;
    private String fi_rule_name;
    private String rule_template_id;
    private String db_id;
    private String menu_id;
    private String monitoring_mode;
    private int warn_level;
    private int control_type;
    private String process_flow;
    private String contact_person;
    private String contact_information;
    private int management_level;
    private String rule_remark;
    private int is_enabled;
    private int fiscal_year;
    private int mof_div_code;
    private String trigger_cond;

    public String getDi_rule_id() {
        return di_rule_id;
    }

    public void setDi_rule_id(String di_rule_id) {
        this.di_rule_id = di_rule_id;
    }

    public int getFi_rule_code() {
        return fi_rule_code;
    }

    public void setFi_rule_code(int fi_rule_code) {
        this.fi_rule_code = fi_rule_code;
    }

    public String getFi_rule_name() {
        return fi_rule_name;
    }

    public void setFi_rule_name(String fi_rule_name) {
        this.fi_rule_name = fi_rule_name;
    }

    public String getRule_template_id() {
        return rule_template_id;
    }

    public void setRule_template_id(String rule_template_id) {
        this.rule_template_id = rule_template_id;
    }

    public String getDb_id() {
        return db_id;
    }

    public void setDb_id(String db_id) {
        this.db_id = db_id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getMonitoring_mode() {
        return monitoring_mode;
    }

    public void setMonitoring_mode(String monitoring_mode) {
        this.monitoring_mode = monitoring_mode;
    }

    public int getWarn_level() {
        return warn_level;
    }

    public void setWarn_level(int warn_level) {
        this.warn_level = warn_level;
    }

    public int getControl_type() {
        return control_type;
    }

    public void setControl_type(int control_type) {
        this.control_type = control_type;
    }

    public String getProcess_flow() {
        return process_flow;
    }

    public void setProcess_flow(String process_flow) {
        this.process_flow = process_flow;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getContact_information() {
        return contact_information;
    }

    public void setContact_information(String contact_information) {
        this.contact_information = contact_information;
    }

    public int getManagement_level() {
        return management_level;
    }

    public void setManagement_level(int management_level) {
        this.management_level = management_level;
    }

    public String getRule_remark() {
        return rule_remark;
    }

    public void setRule_remark(String rule_remark) {
        this.rule_remark = rule_remark;
    }

    public int getIs_enabled() {
        return is_enabled;
    }

    public void setIs_enabled(int is_enabled) {
        this.is_enabled = is_enabled;
    }

    public int getFiscal_year() {
        return fiscal_year;
    }

    public void setFiscal_year(int fiscal_year) {
        this.fiscal_year = fiscal_year;
    }

    public int getMof_div_code() {
        return mof_div_code;
    }

    public void setMof_div_code(int mof_div_code) {
        this.mof_div_code = mof_div_code;
    }

    public String getTrigger_cond() {
        return trigger_cond;
    }

    public void setTrigger_cond(String trigger_cond) {
        this.trigger_cond = trigger_cond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleSetPO ruleSetPO = (RuleSetPO) o;
        return fi_rule_code == ruleSetPO.fi_rule_code && warn_level == ruleSetPO.warn_level && control_type == ruleSetPO.control_type && management_level == ruleSetPO.management_level && is_enabled == ruleSetPO.is_enabled && fiscal_year == ruleSetPO.fiscal_year && mof_div_code == ruleSetPO.mof_div_code && di_rule_id.equals(ruleSetPO.di_rule_id) && fi_rule_name.equals(ruleSetPO.fi_rule_name) && rule_template_id.equals(ruleSetPO.rule_template_id) && db_id.equals(ruleSetPO.db_id) && menu_id.equals(ruleSetPO.menu_id) && monitoring_mode.equals(ruleSetPO.monitoring_mode) && process_flow.equals(ruleSetPO.process_flow) && contact_person.equals(ruleSetPO.contact_person) && contact_information.equals(ruleSetPO.contact_information) && rule_remark.equals(ruleSetPO.rule_remark) && trigger_cond.equals(ruleSetPO.trigger_cond);
    }

    @Override
    public int hashCode() {
        return Objects.hash(di_rule_id, fi_rule_code, fi_rule_name, rule_template_id, db_id, menu_id, monitoring_mode, warn_level, control_type, process_flow, contact_person, contact_information, management_level, rule_remark, is_enabled, fiscal_year, mof_div_code, trigger_cond);
    }

    @Override
    public String toString() {
        return "RuleSetPO{" +
                "create_time='" + create_time + '\'' +
                ", create_user='" + create_user + '\'' +
                ", update_time='" + update_time + '\'' +
                ", last_time='" + last_time + '\'' +
                ", last_user='" + last_user + '\'' +
                ", last_user_name='" + last_user_name + '\'' +
                ", version='" + version + '\'' +
                ", is_deleted='" + is_deleted + '\'' +
                ", id='" + di_rule_id + '\'' +
                ", fi_rule_code=" + fi_rule_code +
                ", fi_rule_name='" + fi_rule_name + '\'' +
                ", rule_template_id='" + rule_template_id + '\'' +
                ", db_id='" + db_id + '\'' +
                ", menu_id='" + menu_id + '\'' +
                ", monitoring_mode='" + monitoring_mode + '\'' +
                ", warn_level=" + warn_level +
                ", control_type=" + control_type +
                ", process_flow='" + process_flow + '\'' +
                ", contact_person='" + contact_person + '\'' +
                ", contact_information='" + contact_information + '\'' +
                ", management_level=" + management_level +
                ", rule_remark='" + rule_remark + '\'' +
                ", is_enabled=" + is_enabled +
                ", fiscal_year=" + fiscal_year +
                ", mof_div_code=" + mof_div_code +
                ", trigger_cond='" + trigger_cond + '\'' +
                '}';
    }
}
