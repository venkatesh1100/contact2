package com.example.redsun.contact2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<List_View> item;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView heading, desc;

        public MyViewHolder(View view) {
            super(view);
            heading = (TextView) view.findViewById(R.id.titles);
            desc = (TextView) view.findViewById(R.id.desc);

        }
    }


    public MyAdapter(List<List_View> item, SecondActivity secondActivity) {

        this.item = item;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        List_View v = item.get(position);
        holder.heading.setText(v.getHead());
        holder.desc.setText(v.getDesc());


    }

        @Override
        public int getItemCount()
        {
            return item.size();
        }
}

