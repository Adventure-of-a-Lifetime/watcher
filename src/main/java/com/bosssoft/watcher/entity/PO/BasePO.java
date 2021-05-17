package com.bosssoft.watcher.entity.PO;

public class BasePO {
    protected String create_time;
    protected String create_user;
    protected String update_time;
    protected String last_time;
    protected String last_user;
    protected String last_user_name;
    protected String version;
    protected String is_deleted;

    protected String getCreate_time() {
        return create_time;
    }

    protected void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    protected String getCreate_user() {
        return create_user;
    }

    protected void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    protected String getUpdate_time() {
        return update_time;
    }

    protected void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    protected String getLast_time() {
        return last_time;
    }

    protected void setLast_time(String last_time) {
        this.last_time = last_time;
    }

    protected String getLast_user() {
        return last_user;
    }

    protected void setLast_user(String last_user) {
        this.last_user = last_user;
    }

    protected String getLast_user_name() {
        return last_user_name;
    }

    protected void setLast_user_name(String last_user_name) {
        this.last_user_name = last_user_name;
    }

    protected String getVersion() {
        return version;
    }

    protected void setVersion(String version) {
        this.version = version;
    }

    protected String getIs_deleted() {
        return is_deleted;
    }

    protected void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public String toString() {
        return "BasePO{" +
                "create_time='" + create_time + '\'' +
                ", create_user='" + create_user + '\'' +
                ", update_time='" + update_time + '\'' +
                ", last_time='" + last_time + '\'' +
                ", last_user='" + last_user + '\'' +
                ", last_user_name='" + last_user_name + '\'' +
                ", version='" + version + '\'' +
                ", is_deleted='" + is_deleted + '\'' +
                '}';
    }
}