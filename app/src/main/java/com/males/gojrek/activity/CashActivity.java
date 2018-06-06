package com.males.gojrek.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.males.gojrek.R;
import com.males.gojrek.adapter.CashAdapter;
import com.males.gojrek.model.Cash;
import com.males.gojrek.model.CashResponse;
import com.males.gojrek.rest.ApiClient;
import com.males.gojrek.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CashActivity extends AppCompatActivity {
    private ProgressDialog pDialog;

    private static final String TAG = CashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Cash");
        setContentView(R.layout.activity_cash);

        //tambahan sendiri
        // Showing progress dialog
        pDialog = new ProgressDialog(CashActivity.this);
        pDialog.setMessage("Tunggu bentar...");
        pDialog.setCancelable(false);
        pDialog.show();

        //start recycle view (tambahkan setelah membuat recycler view (desain layout item dan recycler nya & adapter)
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cash_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //stop recycler view

        //variabel apiservice -> memanggil base URL dan memasukkan endpoint API
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        //variable call -> memanggil API getCash dari ApiInterface.java
        Call<CashResponse> call = apiService.getCash();

        //method untuk memanggil api dan menerima callback/responya dari API
        call.enqueue(new Callback<CashResponse>() {
            @Override
            public void onResponse(Call<CashResponse>call, Response<CashResponse> response) {
                // Dismiss the progress dialog
                if (pDialog.isShowing())
                    pDialog.dismiss();

                List<Cash> cashes = response.body().getCashresult();
                recyclerView.setAdapter(new CashAdapter(cashes, getApplicationContext()));
                Log.d(TAG, "Number of cashResult received: " + cashes.size());

            }

            @Override
            public void onFailure(Call<CashResponse>call, Throwable t) {
                // Dismiss the progress dialog
                if (pDialog.isShowing())
                    pDialog.dismiss();
                //toast (tambahan sendiri)
                Toast.makeText(CashActivity.this, "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
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
