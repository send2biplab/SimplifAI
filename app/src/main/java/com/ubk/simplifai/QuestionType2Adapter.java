package com.ubk.simplifai;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionType2Adapter extends BaseAdapter {
    private Context context;
    private List<HashMap> list;
    LayoutInflater inflter;

    public QuestionType2Adapter(Context context, List<HashMap> list) {
        this.context = context;
        this.list = list;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflter.inflate(R.layout.list_question_type2, null);

        HashMap qdata = new HashMap(list.get(position));

        Log.e("sdfsf", "sdfs" + qdata.get("question"));

        TextView textView = view.findViewById(R.id.tvQues2);
        textView.setText("" + qdata.get("question"));

        Log.d("test", "Position: " + position + "\nConvert View: " + convertView + "\nParent: " + parent);
        return view;
    }
}
