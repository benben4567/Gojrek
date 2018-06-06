package com.males.gojrek.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by LAZYCAT on 4/15/2018.
 */

public class GopayResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("gopayresult")
    @Expose
    private List<Gopay> gopayresult;

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

    public List<Gopay> getGopayresult() {
        return gopayresult;
    }

    public void setGopayresult(List<Gopay> gopayresult) {
        this.gopayresult = gopayresult;
    }

}
