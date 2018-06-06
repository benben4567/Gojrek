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

import java.util.List;

/**
 * Created by LAZYCAT on 4/14/2018.
 */

public class CashAdapter extends RecyclerView.Adapter<CashAdapter.CashViewHolder> {

    private List<Cash> cashes;
    private int rowLayout;
    private Context context;

    public class CashViewHolder extends RecyclerView.ViewHolder {

        //LinearLayout cashLayout;
        TextView tanggal;
        TextView orderan;
        TextView nominal;
        TextView keterangan;

        public CashViewHolder(View v) {
            super(v);

            //cashLayout = (LinearLayout) v.findViewById(R.id.cash_layout);
            tanggal = (TextView) v.findViewById(R.id.txttanggal);
            orderan = (TextView) v.findViewById(R.id.txtorderan);
            nominal = (TextView) v.findViewById(R.id.txtnominal);
            keterangan = (TextView) v.findViewById(R.id.txtketerangan);
        }
    }

    public CashAdapter(List<Cash> cashes, Context context) {
        this.cashes = cashes;
        //this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public CashAdapter.CashViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cash, parent, false);
        return new CashViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CashViewHolder holder, int position) {
        holder.tanggal.setText(cashes.get(position).getTanggal());
        holder.orderan.setText(cashes.get(position).getOrderan());
        holder.nominal.setText(cashes.get(position).getNominal());
        holder.keterangan.setText(cashes.get(position).getKeterangan());
    }

    @Override
    public int getItemCount() {
        return cashes.size();
    }
}
