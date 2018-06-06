package com.males.gojrek.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.males.gojrek.R;
import com.males.gojrek.model.Cash;
import com.males.gojrek.model.Gopay;

import java.util.List;

/**
 * Created by LAZYCAT on 4/15/2018.
 */

public class GopayAdapter extends RecyclerView.Adapter<GopayAdapter.GopayViewHolder> {

    private List<Gopay> gopays;
    private Context context;

    public class GopayViewHolder extends RecyclerView.ViewHolder {

        LinearLayout gopayLayout;
        TextView tanggal;
        TextView saldo;
        TextView keterangan;

        public GopayViewHolder(View v) {
            super(v);

            gopayLayout = (LinearLayout) v.findViewById(R.id.cash_layout);
            tanggal = (TextView) v.findViewById(R.id.txttanggal);
            saldo = (TextView) v.findViewById(R.id.txtsaldo);
            keterangan = (TextView) v.findViewById(R.id.txtketerangan);
        }
    }

    public GopayAdapter(List<Gopay> gopays, Context context) {
        this.gopays = gopays;
        this.context = context;
    }

    @Override
    public GopayAdapter.GopayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_gopay, parent, false);
        return new GopayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GopayViewHolder holder, int position) {
        holder.tanggal.setText(gopays.get(position).getTanggal());
        holder.saldo.setText(gopays.get(position).getSaldo());
        holder.keterangan.setText(gopays.get(position).getKeterangan());

    }

    @Override
    public int getItemCount() {
        return gopays.size();
    }
}
