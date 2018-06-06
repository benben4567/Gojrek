package com.males.gojrek.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by LAZYCAT on 4/14/2018.
 */

public class CashResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cashresult")
    @Expose
    private List<Cash> cashresult;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Cash> getCashresult() {
        return cashresult;
    }

    public void setCashresult(List<Cash> cashresult) {
        this.cashresult = cashresult;
    }
}
