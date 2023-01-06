package com.ubk.simplifai;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;

public class QuestionType4Activity extends AppCompatActivity {

    ImageView ivBack, ivNext;
    TextView tvQuestion, tvQuestionNumber, tvOption1, tvOption2, tvOption3, tvOption4, tvSubmit;
    //RecyclerView rvQuestionType4;

    String _id, questionTypeID, group_name, question_type, questionGroupId;
    String option1, option2, option3, option4;
    String question, answer;
    int quesNo = 0;
    int totalQuestion;

    /*String auth_token, username;
    private RequestQueue questionType4RequestQueue;
    private StringRequest questionType4StringRequest;
    private List<HashMap> questionList;

    boolean lodingstatus=false;*/

    private List<HashMap> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_type4);

        ivBack = findViewById(R.id.ivBack);
        ivNext = findViewById(R.id.ivNext);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvOption1 = findViewById(R.id.tvOption1);
        tvOption2 = findViewById(R.id.tvOption2);
        tvOption3 = findViewById(R.id.tvOption3);
        tvOption4 = findViewById(R.id.tvOption4);
        tvSubmit = findViewById(R.id.tvSubmit);

        //rvQuestionType4 = findViewById(R.id.rvQuestionType4);
        //rvQuestionType4.setLayoutManager(new LinearLayoutManager(this));

        _id = getIntent().getStringExtra("_id");
        questionTypeID = getIntent().getStringExtra("questionTypeID");
        group_name = getIntent().getStringExtra("group_name");
        question_type = getIntent().getStringExtra("question_type");
        totalQuestion = getIntent().getIntExtra("totalQuestion", 0);
        //intTotalQuestion = Integer.parseInt(totalQuestion);
        questionGroupId = _id;
        Log.e("dfsddfttgff", "dzxfc" + totalQuestion);

        try {
            String vbd = getIntent().getStringExtra("questionList");

            JSONArray jsonArray = new JSONArray(vbd);
            questionList = new ArrayList<HashMap>(jsonArray.length());
            Log.e("dfsdf", "dzxfc" + vbd);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object1 = jsonArray.getJSONObject(i);

                Log.e("dfsdf", "dzxfc" + object1.getString("question"));
                HashMap<String, String> map = new HashMap<>();
                map.put("id", object1.getString("_id"));
                map.put("question", object1.getString("question"));
                map.put("answer", object1.getString("answer"));
                questionList.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // String[] strArr = new String[jsonArray.length()];

        setQuestion(quesNo);

//        option1 = getIntent().getStringExtra("option1");
//        option2 = getIntent().getStringExtra("option2");
//        option3 = getIntent().getStringExtra("option3");
//        option4 = getIntent().getStringExtra("option4");
//        question = getIntent().getStringExtra("question");

        // tvOption1.append(option1);
        // tvOption2.append(option2);
        //  tvOption3.append(option3);
        //  tvOption4.append(option4);
        //  tvQuestion.setText(question);

        /*SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        auth_token = sp.getString("auth_token", "");
        username = sp.getString("username", "");*/

        //processGetQuestionType4(questionGroupId);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionType4Activity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //for (int i = 0; i<Integer.valueOf(totalQuestion); i++) {
                if (quesNo < totalQuestion - 1) {
                    quesNo++;
                    setQuestion(quesNo);
                } else if (quesNo == totalQuestion - 1) {
                    tvSubmit.setVisibility(View.VISIBLE);
                    ivNext.setVisibility(View.GONE);
                    quesNo++;
                    setQuestion(quesNo);
                }
                    /*else {
                    Intent intent = new Intent(QuestionType4Activity.this, SubmitActivity.class);
                    startActivity(intent);
                }*/
            }
        });

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionType4Activity.this, SubmitActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setQuestion(int id) {
        Log.e("sdfsdfs", "====" + questionList.get(id));

        tvQuestion.setText("" + questionList.get(id).get("question"));

        tvOption1.setBackgroundResource(R.drawable.style_grey_curved_outline2);
        tvOption2.setBackgroundResource(R.drawable.style_grey_curved_outline2);
        tvOption3.setBackgroundResource(R.drawable.style_grey_curved_outline2);
        tvOption4.setBackgroundResource(R.drawable.style_grey_curved_outline2);

        try {
            String ques = (String) questionList.get(id).get("answer");
            //Log.e("sfdgsdfs","ques"+ques);
            JSONArray array = new JSONArray(ques);

            //Log.e("sfdgsdfs","ques--"+array.get(0));
            tvOption1.setText("1   " + array.get(0));
            tvOption2.setText("2   " + array.get(1));
            tvOption3.setText("3   " + array.get(2));
            tvOption4.setText("4   " + array.get(3));

            tvOption1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer = tvOption1.getText().toString();
                    tvOption1.setBackgroundResource(R.drawable.style_blue_curved_outline);
                    tvOption2.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    tvOption3.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    tvOption4.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                }
            });
            tvOption2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer = tvOption1.getText().toString();
                    tvOption2.setBackgroundResource(R.drawable.style_blue_curved_outline);
                    tvOption1.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    tvOption3.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    tvOption4.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                }
            });
            tvOption3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer = tvOption1.getText().toString();
                    tvOption3.setBackgroundResource(R.drawable.style_blue_curved_outline);
                    tvOption1.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    tvOption2.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    tvOption4.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                }
            });
            tvOption4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer = tvOption1.getText().toString();
                    tvOption4.setBackgroundResource(R.drawable.style_blue_curved_outline);
                    tvOption1.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    tvOption2.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                    tvOption3.setBackgroundResource(R.drawable.style_grey_curved_outline2);
                }
            });

            Log.e("sfdgsdfs", "Ans: " + answer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
