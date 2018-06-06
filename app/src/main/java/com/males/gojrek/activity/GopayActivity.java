package com.males.gojrek.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.males.gojrek.R;
import com.males.gojrek.adapter.CashAdapter;
import com.males.gojrek.adapter.GopayAdapter;
import com.males.gojrek.model.Gopay;
import com.males.gojrek.model.GopayResponse;
import com.males.gojrek.rest.ApiClient;
import com.males.gojrek.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GopayActivity extends AppCompatActivity {
    private ProgressDialog pDialog;

    private static final String TAG = GopayActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Gopay");
        setContentView(R.layout.activity_gopay);

        //tambahan sendiri
        // Showing progress dialog
        pDialog = new ProgressDialog(GopayActivity.this);
        pDialog.setMessage("Tunggu bentar...");
        pDialog.setCancelable(false);
        pDialog.show();

        //start recycle view (tambahkan setelah membuat recycler view (desain layout item dan recycler nya & adapter)
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gopay_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //stop recycler view

        //variabel apiservice -> memanggil base URL dan memasukkan endpoint API
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        //variable call -> memanggil API getCash dari ApiInterface.java
        Call<GopayResponse> call = apiService.getGopay();

        //method untuk memanggil api dan menerima callback/responya dari API
        call.enqueue(new Callback<GopayResponse>() {
            @Override
            public void onResponse(Call<GopayResponse>call, Response<GopayResponse> response) {
                // Dismiss the progress dialog
                if (pDialog.isShowing())
                    pDialog.dismiss();

                List<Gopay> gopays = response.body().getGopayresult();
                recyclerView.setAdapter(new GopayAdapter(gopays, getApplicationContext()));
                Log.d(TAG, "Number of cashResult received: " + gopays.size());

            }

            @Override
            public void onFailure(Call<GopayResponse>call, Throwable t) {
                // Dismiss the progress dialog
                if (pDialog.isShowing())
                    pDialog.dismiss();
                //toast (tambahan sendiri)
                Toast.makeText(GopayActivity.this, "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                //stop
                // Log error here since request failed
                Log.e(TAG, t.toString());

            }
        });

        //memunculkan back button pada action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
