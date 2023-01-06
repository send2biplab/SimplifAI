package com.ubk.simplifai;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

public class TaskInformationActivity extends AppCompatActivity {

    ImageView ivBack;
    Button btTagging;
    LinearLayout llControlInstructions, llTaskTypeTutorial;
    String question_type;

    String auth_token, username;
    String questionTypeID, questionGroupId;
    String option1, option2, option3, option4;
    String question;
    private RequestQueue questionType4RequestQueue;
    private StringRequest questionType4StringRequest;
    private List<HashMap> questionList;
    boolean lodingstatus = false;

    JSONArray data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_information);

        ivBack = findViewById(R.id.ivBack);
        btTagging = findViewById(R.id.btTagging);
        llControlInstructions = findViewById(R.id.llControlInstructions);
        llTaskTypeTutorial = findViewById(R.id.llTaskTypeTutorial);

        question_type = getIntent().getStringExtra("question_type");
        questionTypeID = getIntent().getStringExtra("questionTypeID");
        questionGroupId = getIntent().getStringExtra("_id");

        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        auth_token = sp.getString("auth_token", "");
        username = sp.getString("username", "");

        processGetQuestionType4(questionGroupId);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskInformationActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        btTagging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("response", "TaskInformationActivity_question_type: " + question_type);
                if (question_type.equals("2")) {
                    Intent intent = new Intent(TaskInformationActivity.this, QuestionType2Activity.class);
                    intent.putExtra("_id", getIntent().getStringExtra("_id"));
                    intent.putExtra("questionTypeID", getIntent().getStringExtra("questionTypeID"));
                    intent.putExtra("group_name", getIntent().getStringExtra("group_name"));
                    intent.putExtra("question_type", question_type);
                    startActivity(intent);
                } else if (question_type.equals("4")) {
                    Log.e("sfgsf", "sdf" + data);
                    Intent intent = new Intent(TaskInformationActivity.this, QuestionType4Activity.class);
                    intent.putExtra("_id", getIntent().getStringExtra("_id"));
                    intent.putExtra("questionTypeID", getIntent().getStringExtra("questionTypeID"));
                    intent.putExtra("group_name", getIntent().getStringExtra("group_name"));
                    intent.putExtra("question_type", question_type);

                    // intent.putExtra("questionList", data);
                    intent.putExtra("questionList", String.valueOf(data));
                    intent.putExtra("totalQuestion", data.length());
                    //Log.e("dfsddfttggfdgff", "dzxfc" + data.length());
                    intent.putExtra("option1", option1);
                    intent.putExtra("option2", option2);
                    intent.putExtra("option3", option3);
                    intent.putExtra("option4", option4);
                    intent.putExtra("question", question);
                    //Log.i("ybdcord", " " + questionList.get(1));
                    startActivity(intent);
                }
            }
        });

        llTaskTypeTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskInformationActivity.this, TaskTypeTutorialActivity.class);
                startActivity(intent);
            }
        });

        llControlInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskInformationActivity.this, ControlInstructionActivity.class);
                startActivity(intent);
            }
        });
    }

    public void processGetQuestionType4(String questionGroup) {
        //RequestQueue initialized
        Log.e("2332323", "QuestionActivityTest");

        questionType4RequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        questionType4StringRequest = new StringRequest(Request.Method.GET, Constant.url + "question/getQuestions?questionGroupId=" + questionGroupId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", "QuestionType4Activity: " + response);

                try {
                    JSONObject object = new JSONObject(response);

                    if (object.getString("status").equals("success")) {
                        data = object.getJSONArray("data");
                        Log.i("vfkjhl", "" + data);

                        questionList = new ArrayList<HashMap>(data.length());

                        for (int i = 0; i < data.length(); i++) {
                            JSONObject object1 = data.getJSONObject(i);

                            question = object1.getString("question");
                            Log.i("response", "QuestionType4Activity_q1: " + question);

                            //tvQuestion.setText(question);
                            JSONArray array = object1.getJSONArray("answer");
                            option1 = String.valueOf(array.get(0));
                            option2 = String.valueOf(array.get(1));
                            option3 = String.valueOf(array.get(2));
                            option4 = String.valueOf(array.get(3));

                            Log.i("response", "detgtrujyjkukuku: " + array.get(0));
                            Log.e("sfdsfsf", "asdff" + array);

                            HashMap<String, String> map = new HashMap<>();
                            map.put("id", object1.getString("_id"));
                            map.put("question", question);
                            map.put("answer", object1.getString("answer"));
                            questionList.add(map);
                        }

                        lodingstatus = true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "QuestionActivityError :" + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("questionGroupId", questionGroup);
                //params.put("userid", username);

                Log.e("params", "QuestionActivityParams" + params);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //  params.put("Content-Type", "application/json");
                params.put("Authorization", auth_token);

                Log.e("header", "QuestionActivityHeader" + params);
                return params;
            }
        };
        questionType4RequestQueue.add(questionType4StringRequest);
    }
}
