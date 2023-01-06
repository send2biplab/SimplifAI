package com.ubk.simplifai;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class QuestionType4Adapter extends RecyclerView.Adapter<QuestionType4Adapter.ViewHolder> {
    JSONArray data;
    Context context;
    String question;
    int i;

    public QuestionType4Adapter(Context context, JSONArray data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public QuestionType4Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_question_type4, parent, false);
        QuestionType4Adapter.ViewHolder viewHolder = new QuestionType4Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionType4Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        for (i = 0; i < data.length(); i++) {
            try {
                JSONObject object1 = data.getJSONObject(i);
                JSONArray answer = object1.getJSONArray("answer");

                Log.i("response", "QuestionType4AdapterGroup_object1: " + object1);
                question = object1.getString("question");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.i("response", "QuestionType4AdapterGroup_position: " + position);
            //int pos = i + 1;
            holder.tvQuestion.setText(question);
            //holder.tvQuestionNumber.setText(pos);
            //holder.tvOption1.append();
            holder.tvOption2.append("Yellow");
            holder.tvOption3.append("Green");
            holder.tvOption4.append("White");
            Log.e("response", "QuestionType4AdapterGroup_response: " + question);

            holder.tvOption1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.tvOption1.setBackgroundResource(R.drawable.style_blue_curved_outline);
                    holder.tvOption2.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    holder.tvOption3.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    holder.tvOption4.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                }
            });
            holder.tvOption2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.tvOption2.setBackgroundResource(R.drawable.style_blue_curved_outline);
                    holder.tvOption1.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    holder.tvOption3.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    holder.tvOption4.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                }
            });
            holder.tvOption3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.tvOption3.setBackgroundResource(R.drawable.style_blue_curved_outline);
                    holder.tvOption1.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    holder.tvOption2.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    holder.tvOption4.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                }
            });
            holder.tvOption4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.tvOption4.setBackgroundResource(R.drawable.style_blue_curved_outline);
                    holder.tvOption1.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    holder.tvOption2.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    holder.tvOption3.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                }
            });
            holder.ivNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //i++;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion, tvQuestionNumber, tvOption1, tvOption2, tvOption3, tvOption4;
        ImageView ivNext;

        public ViewHolder(View view) {
            super(view);
            tvQuestion = view.findViewById(R.id.tvQuestion);
            tvQuestionNumber = view.findViewById(R.id.tvQuestionNumber);
            tvOption1 = view.findViewById(R.id.tvOption1);
            tvOption2 = view.findViewById(R.id.tvOption2);
            tvOption3 = view.findViewById(R.id.tvOption3);
            tvOption4 = view.findViewById(R.id.tvOption4);
            ivNext = view.findViewById(R.id.ivNext);
        }
    }
}
