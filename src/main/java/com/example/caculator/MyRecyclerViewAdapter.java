package com.example.caculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyHolder> {

    private List<MyObject> mList;

    class MyHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        EditText mEditText;

        MyHolder(View view){
            super(view);
            mTextView = (TextView)view.findViewById(R.id.sub_textview);
            mEditText = (EditText)view.findViewById(R.id.sub_edittext);
        }
    }

    MyRecyclerViewAdapter(List<MyObject> list){
        mList = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_view,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position){
        MyObject object = mList.get(position);
        holder.mTextView.setText(object.getString());
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }
}
