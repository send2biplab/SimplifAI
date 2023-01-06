package com.ubk.simplifai;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    //ArrayList images;
    JSONArray data;
    Context context;
    String _id, questionTypeID, group_name, question_type, questionGroupIcon;

    // Constructor for initialization
    public ListAdapter(Context context, JSONArray data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the Layout(Instantiates list_item.xml layout file into View object)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_queston_group, parent, false);

        // Passing view to ViewHolder
        ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
        return viewHolder;
    }

    // Binding data to the into specified position
    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // TypeCast Object to int type
        //int res = (int) images.get(position);
        //holder.images.setImageResource(res);
        try {
            JSONObject object1 = data.getJSONObject(position);
            Log.e("response", "ListAdapterGroup_name2: " + object1);

            //_id = object1.getString("_id");
            questionTypeID = object1.getString("questionTypeID");
            group_name = object1.getString("groupName");
            question_type = object1.getString("question_type");

            if (questionTypeID == "63ac4c034d9adfa4be0f523c") {
                holder.ivQuestionGroupIcon.setImageResource(R.drawable.list_images_man);
            } else if (questionTypeID == "63ac4c4d4d9adfa4be0f523e") {
                holder.ivQuestionGroupIcon.setImageResource(R.drawable.list_text_man);
            }
            /*else if (questionTypeID == "63ac4c7a4d9adfa4be0f523f")
            {
                holder.ivQuestionGroupIcon.setImageResource(R.drawable.list_polygon_man);
            }*/
            else if (questionTypeID == "63ac4d404d9adfa4be0f5240") {
                holder.ivQuestionGroupIcon.setImageResource(R.drawable.list_audio_man);
            }
            if (position % 4 == 0 || position % 4 == 3) {
                holder.clLayout.setBackgroundResource(R.drawable.style_blue_curved);
                //holder.clLayout.setBackgroundColor(Color.parseColor("#967444"));
            } else {
                holder.clLayout.setBackgroundResource(R.drawable.style_yellow_curved);
                //holder.clLayout.setBackgroundColor(Color.parseColor("#06116C"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        holder.tvGroupName.setText(group_name);
        Log.e("response", "ListAdapterGroup_name2: " + group_name);

        holder.clLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject object1 = null;
                try {
                    object1 = data.getJSONObject(position);
                    _id = object1.getString("_id");
                    questionTypeID = object1.getString("questionTypeID");
                    group_name = object1.getString("groupName");
                    question_type = object1.getString("question_type");
                    Log.e("response", "ListAdapterGroup_name: " + group_name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(context, TaskInformationActivity.class);
                intent.putExtra("_id", _id);
                intent.putExtra("questionTypeID", questionTypeID);
                intent.putExtra("group_name", group_name);
                intent.putExtra("question_type", question_type);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Returns number of items currently available in CustomAdapter
        return data.length();
    }

    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        //ImageView images;
        TextView tvGroupName;
        ConstraintLayout clLayout;
        ImageView ivQuestionGroupIcon;

        public ViewHolder(View view) {
            super(view);
            //images = (ImageView) view.findViewById(R.id.imageview);
            tvGroupName = (TextView) view.findViewById(R.id.tvGroupName);
            clLayout = (ConstraintLayout) view.findViewById(R.id.clLayout);
            ivQuestionGroupIcon = (ImageView) view.findViewById(R.id.ivQuestionGroupIcon);
        }
    }
}
