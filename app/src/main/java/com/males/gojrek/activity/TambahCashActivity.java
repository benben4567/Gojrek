package com.males.gojrek.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.fxn.cue.Cue;
import com.fxn.cue.enums.Type;
import com.males.gojrek.R;
import com.males.gojrek.rest.ApiClient;
import com.males.gojrek.rest.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahCashActivity extends AppCompatActivity {
    EditText etTanggal, etNominal, etKeterangan;
    Spinner spOrderan;
    Button btnSimpan;
    SimpleDateFormat dateFormatter;
    ProgressDialog loading;
    Context mContext;

    //variabel apiservice -> memanggil base URL dan memasukkan endpoint API
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Input Cash");
        setContentView(R.layout.activity_tambah_cash);

        mContext = this;

        initComponent();

        //memunculkan back button pada action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void initComponent() {
        //komponen
        etTanggal = (EditText) findViewById(R.id.et_tanggal);
        etNominal = (EditText) findViewById(R.id.et_nominal);
        etKeterangan = (EditText) findViewById(R.id.et_keterangan);
        spOrderan = (Spinner) findViewById(R.id.sp_orderan);
        btnSimpan = (Button) findViewById(R.id.btn_simpan);

        //setting tanggal
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        long date = System.currentTimeMillis();
        String dateString = dateFormatter.format(date);
        //set text etTanggal otomatis hari ini (default)
        etTanggal.setInputType(InputType.TYPE_NULL);
        etTanggal.setText(dateString);



        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext,String.valueOf(spinner.getSelectedItem()), Toast.LENGTH_SHORT).show();

                loading = new ProgressDialog(TambahCashActivity.this);
                loading.setMessage("Tunggu bentar...");
                loading.setCancelable(false);
                int length = etNominal.getText().length();
                if (length > 0) {
                    requestCash();
                    loading.show();
                } else {
                    Cue.init()
                            .with(getBaseContext())
                            .setMessage("Isi kolom nominal")
                            .setGravity(Gravity.CENTER_VERTICAL)
                            .setType(Type.DANGER)
                            .setTextSize(14)
                            .show();
                }
            }
        });

    }

    private void requestCash() {
        apiService.cashRequest(etTanggal.getText().toString(), String.valueOf(spOrderan.getSelectedItem()),
                Integer.parseInt(String.valueOf(etNominal.getText())), etKeterangan.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals(false)){
                                    String success_message = jsonRESULTS.getString("message");
                                    Cue.init()
                                            .with(mContext)
                                            .setMessage(success_message)
                                            .setGravity(Gravity.CENTER_VERTICAL)
                                            .setType(Type.SUCCESS)
                                            .setTextSize(14)
                                            .show();
                                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    String error_message = jsonRESULTS.getString("message");
                                    //Toast.makeText(getBaseContext(), error_message, Toast.LENGTH_SHORT).show();
                                    Cue.init()
                                            .with(getBaseContext())
                                            .setMessage(error_message)
                                            .setGravity(Gravity.CENTER_VERTICAL)
                                            .setType(Type.DANGER)
                                            .setTextSize(14)
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "onResponse: GA BERHASIL");
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        loading.dismiss();
                        //Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                        Cue.init()
                                .with(getBaseContext())
                                .setMessage("Koneksi Internet Bermasalah")
                                .setGravity(Gravity.CENTER_VERTICAL)
                                .setType(Type.DANGER)
                                .setTextSize(14)
                                .show();
                    }
                });


    }

    private void showDateDialog() {
        //Calendar untuk mendapatkan tanggal sekarang
        Calendar newCalendar = Calendar.getInstance();
        //Initiate DatePicker dialog
        DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                //Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                //Set Calendar untuk menampung tanggal yang dipilih
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                etTanggal.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        //Tampilkan DatePicker dialog
        datePicker.show();
    }

    //method back button (gatau isi nya, dari sono nya gitu)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
