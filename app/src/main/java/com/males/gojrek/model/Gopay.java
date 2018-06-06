package com.males.gojrek.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LAZYCAT on 4/15/2018.
 */

public class Gopay {

    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("saldo")
    @Expose
    private String saldo;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    public Gopay(String tanggal, String saldo, String keterangan){
        this.tanggal = tanggal;
        this.saldo = saldo;
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
