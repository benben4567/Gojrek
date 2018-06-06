package com.males.gojrek.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LAZYCAT on 4/14/2018.
 */

public class Cash {
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("orderan")
    @Expose
    private String orderan;
    @SerializedName("nominal")
    @Expose
    private String nominal;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    public Cash(String tanggal, String orderan, String nominal, String keterangan){
        this.tanggal = tanggal;
        this.orderan = orderan;
        this.nominal = nominal;
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getOrderan() {
        return orderan;
    }

    public void setOrderan(String orderan) {
        this.orderan = orderan;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
