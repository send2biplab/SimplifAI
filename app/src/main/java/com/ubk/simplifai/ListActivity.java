package com.ubk.simplifai;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ListActivity extends AppCompatActivity {

    ImageView ivBack, iv_list_image, ivHeaderBg;
    RecyclerView rvQuestionGroup;

    private RequestQueue textRequestQueue;
    private StringRequest textStringRequest;

    String username, auth_token, catId, group_name;
    ArrayList images = new ArrayList<>(Arrays.asList(R.drawable.photo1, R.drawable.photo2, R.drawable.photo3));
    ArrayList data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ivBack = findViewById(R.id.ivBack);
        iv_list_image = findViewById(R.id.iv_list_image);
        ivHeaderBg = findViewById(R.id.ivHeaderBg);
        rvQuestionGroup = findViewById(R.id.rvQuestionGroup);

        catId = getIntent().getStringExtra("CatId");
        //Toast.makeText(this, catId, Toast.LENGTH_LONG).show();

        if (catId.equals("63ac4c034d9adfa4be0f523c")) {
            ivHeaderBg.setImageResource(R.drawable.style_blue_circle);
            iv_list_image.setImageResource(R.drawable.list_images);
        } else if (catId.equals("63ac4c4d4d9adfa4be0f523e")) {
            ivHeaderBg.setImageResource(R.drawable.style_yellow_circle);
            iv_list_image.setImageResource(R.drawable.list_text);
        } else if (catId.equals("63ac4c7a4d9adfa4be0f523f")) {
            ivHeaderBg.setImageResource(R.drawable.style_yellow_circle);
            //iv_list_image.setImageResource(R.drawable.list_polygon);
        } else if (catId.equals("63ac4d404d9adfa4be0f5240")) {
            ivHeaderBg.setImageResource(R.drawable.style_blue_circle);
            iv_list_image.setImageResource(R.drawable.list_audio);
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        username = sp.getString("username", "");
        auth_token = sp.getString("auth_token", "");
       // auth_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkZXZlbG9wZWRieSI6IlVCS0lORk9URUNIUFZUTFREIiwiaWF0IjoxNjcyMjk3NTYyfQ.InCS3BpiUiNTFAmEqcEdJs_uVTZeVs7pft5nnu8ig3g";
        Log.d("text", "ListActivityToken" + auth_token);

        processVolley(catId);

        /*List<items> itemsList = new ArrayList<>();
        itemsList.add(new items(R.drawable.style_blue_curved, "Multi Select"));
        itemsList.add(new items(R.drawable.style_yellow_curved, "Collection"));
        itemsList.add(new items(R.drawable.style_blue_curved, "Bounding Box"));
        itemsList.add(new items(R.drawable.style_yellow_curved, "Choice"));*/

        //GridView gridView = findViewById(R.id.grid_view);
        //CustomAdapter customAdapter = new CustomAdapter(this, R.layout.list_queston_group, itemsList);
        //gridView.setAdapter(customAdapter);
    }

    public void processVolley(String type) {
        //RequestQueue initialized
        Log.e("2332323", "ListActivityTest: " + auth_token);

        textRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        textStringRequest = new StringRequest(Request.Method.POST, Constant.url + "question/getQuestionGroup", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", "ListActivityResponse: " + response);

                try {
                    JSONObject object = new JSONObject(response);

                    if (object.getString("status").equals("success")) {
                        JSONArray data = object.getJSONArray("data");

                        /*for (int i = 0; i<data.length(); i++)
                        {
                            JSONObject object1 = data.getJSONObject(i);

                            String _id = object1.getString("_id");
                            String questionTypeID = object1.getString("questionTypeID");
                            String groupName = object1.getString("groupName");
                            String question_type = object1.getString("question_type");
                        }*/

                        //Setting the layout as Staggered Grid for vertical orientation
                        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
                        rvQuestionGroup.setLayoutManager(staggeredGridLayoutManager);
                        ListAdapter adapter = new ListAdapter(ListActivity.this, data);  // Sending reference and data to Adapter
                        rvQuestionGroup.setAdapter(adapter);  // Setting Adapter to RecyclerView
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "ListActivityError :" + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("questionTypeID", type);
                params.put("userid", username);

                Log.e("ListActivityParams", "" + params);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //  params.put("Content-Type", "application/json");
                params.put("Authorization", auth_token);

                Log.e("ListActivityHeader", "" + params);
                return params;
            }
        };
        textRequestQueue.add(textStringRequest);
    }
}
