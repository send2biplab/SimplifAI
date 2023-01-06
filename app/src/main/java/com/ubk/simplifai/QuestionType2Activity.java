package com.ubk.simplifai;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yalantis.library.Koloda;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionType2Activity extends AppCompatActivity {

    private QuestionType2Adapter adapter;
    //private ArrayList<HashMap> questionList;
    //private List<String> listQuestion;
    //private List<Integer> list;

    private List<HashMap> questionList;
    Koloda koloda;

    String auth_token;
    String questionGroupId = "63ad4410654d22707da5f535";
    private RequestQueue questionType2RequestQueue;
    private StringRequest questionType2StringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_type2);

        koloda = findViewById(R.id.koloda);

        //question = new ArrayList<>();

        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        auth_token = sp.getString("auth_token", "");
        processGetQuestionType2(questionGroupId);

        // Log.e("sdfsfd",koloda.getKolodaListener());
        //  koloda.onClickLeft();
        // koloda.onClickRight();
        // Log.e("sdsds",""+koloda.getKolodaListener().onClickLeft(1));
    }


    public void processGetQuestionType2(String questionGroup) {
        //RequestQueue initialized
        Log.e("2332323", "QuestionType2ActivityTest");

        questionType2RequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        questionType2StringRequest = new StringRequest(Request.Method.GET, Constant.url + "question/getQuestions?questionGroupId=" + questionGroupId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);

                    if (object.getString("status").equals("success")) {
                        JSONArray data = object.getJSONArray("data");

                        Log.e("dfsf", "" + data);
                        Log.e("dfsf", "" + data.length());
                        //HashMap<String, String> map = new HashMap<>();

                        questionList = new ArrayList<HashMap>(data.length());
                        //questionList.size()
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject object1 = data.getJSONObject(i);

                            HashMap<String, String> map = new HashMap<>();
                            map.put("id", object1.getString("_id"));
                            map.put("question", object1.getString("question"));
                            questionList.add(map);
                        }
                        Log.e("sdfsfsfsf", "" + questionList);
                        adapter = new QuestionType2Adapter(QuestionType2Activity.this, questionList);
                        koloda.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "QuestionType2ActivityError :" + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("questionGroupId", questionGroup);
                //params.put("userid", username);

                Log.e("params", "QuestionType2ActivityParams" + params);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //  params.put("Content-Type", "application/json");
                params.put("Authorization", auth_token);

                Log.e("header", "QuestionType2ActivityHeader" + params);
                return params;
            }
        };
        questionType2RequestQueue.add(questionType2StringRequest);
    }
}
