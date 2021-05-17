package com.bosssoft.watcher.entity.PO;

import java.util.Date;

public class ErrorDataPO {

    private String id;
    private String pay_app_no;
    private String mof_div_code;
    private String type;
    private String solve;
    private Date discover_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPay_app_no() {
        return pay_app_no;
    }

    public void setPay_app_no(String pay_app_no) {
        this.pay_app_no = pay_app_no;
    }

    public String getMof_div_code() {
        return mof_div_code;
    }

    public void setMof_div_code(String mof_div_code) {
        this.mof_div_code = mof_div_code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSolve() {
        return solve;
    }

    public void setSolve(String solve) {
        this.solve = solve;
    }

    public Date getDiscover_date() {
        return discover_date;
    }

    public void setDiscover_date(Date discover_date) {
        this.discover_date = discover_date;
    }

    @Override
    public String toString() {
        return "ErrorDataPO{" +
                "id='" + id + '\'' +
                ", pay_app_no='" + pay_app_no + '\'' +
                ", mof_div_code='" + mof_div_code + '\'' +
                ", type='" + type + '\'' +
                ", solve='" + solve + '\'' +
                ", discover_date=" + discover_date +
                '}';
    }
}
