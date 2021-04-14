package src.Classes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.src.R;

import java.util.ArrayList;

import src.Utils.Common_utils;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Transaction> data;
    private LayoutInflater mInflater;
    Common_utils common_utils = Common_utils.getInstance();

    // data is passed into the constructor
    public RecyclerViewAdapter(Context context, ArrayList<Transaction> data) {
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_transactionitem, parent, false);
        return new ViewHolder(view);
    }

    // binds the data in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaction dataItem = data.get(position);
        // set fields of transaction
        String amount = "$" + dataItem.getAmount();
        if(common_utils.checkDate(dataItem.getDate()))
            holder.TV_time.setText(dataItem.getTime());
        else
            holder.TV_time.setText(dataItem.getDate());

        holder.TV_from.setText(dataItem.toString());
        holder.TV_type.setText(dataItem.getType());
        holder.TV_amount.setText(amount);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return data.size();
    }

    // get data by position
    public Transaction getItem(int position) {
        return data.get(position);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TV_from, TV_type, TV_amount, TV_time;
        ViewHolder(View itemView) {
            super(itemView);
            TV_from = itemView.findViewById(R.id.Custom_TV_from);
            TV_type = itemView.findViewById(R.id.Custom_TV_type);
            TV_amount = itemView.findViewById(R.id.Custom_TV_amount);
            TV_time = itemView.findViewById(R.id.Custom_TV_time);
        }
    }
}
