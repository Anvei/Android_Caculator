package com.example.caculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<MyObject> {

    private int mResourceId;

    MyAdapter(Context context, int textViewResourceId, List<MyObject> objects){
        super(context, textViewResourceId, objects);
        mResourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        MyObject object = getItem(position);
        View view;
        Holder holder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(mResourceId, parent, false);
            holder = new Holder();
            holder.mTextView = (TextView)view.findViewById(R.id.sub_textview);
            holder.mEditText = (EditText)view.findViewById(R.id.sub_edittext);
            view.setTag(holder);
        }else{
            view = convertView;
            holder = (Holder)view.getTag();
        }
        holder.mTextView.setText(object.getString());
        return view;
    }

    class Holder{
        private TextView mTextView;
        private EditText mEditText;
    }
}
