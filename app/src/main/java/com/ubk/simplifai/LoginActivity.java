package com.ubk.simplifai;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText etUsername, etPassword;
    TextView tvLogin, tvSignUp;

    private RequestQueue loginRequestQueue;
    private StringRequest loginStringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        tvLogin = findViewById(R.id.tvLogin);
        tvSignUp = findViewById(R.id.tvSignUp);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etUsername.getText().toString().isEmpty()) {
                    etUsername.setError("Please Enter Username");
                    etUsername.requestFocus();
                    return;
                }
                if (etPassword.length() < 6 || etPassword.length() > 15) {
                    etPassword.setError("Please Enter Valid Password (between 6-15 characters)");
                    etPassword.requestFocus();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                processLogin(etUsername.getText().toString(), etPassword.getText().toString());
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void processLogin(String username, String password) {
        //RequestQueue initialized
        loginRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        loginStringRequest = new StringRequest(Request.Method.POST, Constant.url + "users/login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", "LoginActivityResponse: " + response);

                try {
                    JSONObject object = new JSONObject(response);

                    if (object.getBoolean("status")) {
                        JSONObject data = object.getJSONObject("data");

                        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("username", etUsername.getText().toString());
                        editor.putString("auth_token", data.getString("auth_token"));
                        editor.commit();
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);

                      //  Toast.makeText(getApplicationContext(), object.getString("userMessage"), Toast.LENGTH_LONG).show();//display the response on screen
                    } else {
                        Toast.makeText(getApplicationContext(), object.getString("userMessage"), Toast.LENGTH_LONG).show();//display the response on screen
                    }

                    //Log.e("response", "Response: " + object);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "LoginActivityError :" + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("phone", username);
                params.put("password", password);
                return params;
            }
        };
        loginRequestQueue.add(loginStringRequest);
    }

    /*void processLogin() {
        String phone = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        Call<LoginResponseSuccess> call = LoginApiController
                .getInstance()
                .getapi()
                .loginUser(phone, password, "user");
        call.enqueue(new Callback<LoginResponseSuccess>() {
            @Override
            public void onResponse(Call<LoginResponseSuccess> call, Response<LoginResponseSuccess> response) {
                String obj_success = response.body().auth_token;

                Log.d("" + obj_success,"obj");
                try {
                  //  String output = obj.getAuth_token();
                    if (obj_success == null) {
                        etPassword.setText("");
                        Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Please Insert Valid Username and Password", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseSuccess> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }*/
}
