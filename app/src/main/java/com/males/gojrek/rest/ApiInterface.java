package com.males.gojrek.rest;

import com.males.gojrek.model.CashResponse;
import com.males.gojrek.model.GopayResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by LAZYCAT on 4/14/2018.
 */

public interface ApiInterface {
    //mengambil data cash
    @GET("cash")
    Call<CashResponse> getCash();

    //mengambil data gopay
    @GET("gopay")
    Call<GopayResponse> getGopay();

    // POST cash
    @FormUrlEncoded
    @POST("cash")
    Call<ResponseBody> cashRequest(@Field("tanggal") String tanggal,
                                   @Field("orderan") String orderan,
                                   @Field("nominal") Integer nominal,
                                   @Field("keterangan") String keterangan);

    // POST gopay
    @FormUrlEncoded
    @POST("gopay")
    Call<ResponseBody> gopayRequest(@Field("tanggal") String tanggal,
                                    @Field("saldo") Integer saldo,
                                    @Field("keterangan") String keterangan);
}
