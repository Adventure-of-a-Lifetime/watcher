package com.bosssoft.watcher.entity.PO;

public class ErrorRulePO {

    private String id;
    private String mof_div_code;
    private String name;
    private int error;
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMof_div_code() {
        return mof_div_code;
    }

    public void setMof_div_code(String mof_div_code) {
        this.mof_div_code = mof_div_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ErrorRulePO{" +
                "id='" + id + '\'' +
                ", mof_div_code='" + mof_div_code + '\'' +
                ", name='" + name + '\'' +
                ", error=" + error +
                ", time='" + time + '\'' +
                '}';
    }
}

