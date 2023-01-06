package com.ubk.simplifai;

// My account:- First Name: sau, Last Name: mita, Phone: 9876543210, Pwd: 1

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
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

public class SignupActivity extends AppCompatActivity {

    TextView tvLogin;
    Button btSignup;
    TextInputEditText tieFirstName, tieLastName, tieCountry, tiePhone, tiePassword, tieCPassword;
    CheckBox checkbox;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tvLogin = findViewById(R.id.tvLogin);
        btSignup = findViewById(R.id.btSignup);
        tieFirstName = findViewById(R.id.tieFirstName);
        tieLastName = findViewById(R.id.tieLastName);
        //tieCountry = findViewById(R.id.tieCountry);
        tiePhone = findViewById(R.id.tiePhone);
        tiePassword = findViewById(R.id.tiePassword);
        tieCPassword = findViewById(R.id.tieCPassword);
        checkbox = findViewById(R.id.checkbox);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (tieFirstName.getText().toString().isEmpty() || tieLastName.getText().toString().isEmpty() ||
                        tiePhone.getText().toString().isEmpty() || tiePassword.getText().toString().isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please enter all values", Toast.LENGTH_LONG).show();
                    return;
                }*/
                /*else if (tiePassword.getText().toString() != tieCPassword.getText().toString()) {
                    Toast.makeText(SignupActivity.this, "Please enter confirm password properly", Toast.LENGTH_LONG).show();
                    return;
                }*/
                if (tieFirstName.getText().toString().isEmpty()) {
                    tieFirstName.setError("Please Enter First Name");
                    tieFirstName.requestFocus();
                    return;
                }
                if (tieLastName.getText().toString().isEmpty()) {
                    tieLastName.setError("Please Enter Last Name");
                    tieLastName.requestFocus();
                    return;
                }
                if (tiePhone.length() < 10) {
                    tiePhone.setError("Please Enter Valid Phone Number");
                    tiePhone.requestFocus();
                    return;
                }
                if (tiePassword.length() < 6 || tiePassword.length() > 15) {
                    tiePassword.setError("Please Enter Valid Password (between 6-15 characters)");
                    tiePassword.requestFocus();
                    return;
                }
                if (!tieCPassword.getText().toString().equals(tiePassword.getText().toString())) {
                    tieCPassword.setError("Please Enter Password Again Properly");
                    tieCPassword.requestFocus();
                    return;
                }
                if (!checkbox.isChecked()) {
                    Toast.makeText(SignupActivity.this, "Please select the checkbox", Toast.LENGTH_LONG).show();
                    return;
                }

                // calling a method to post the data and passing our name and job.
                sendAndRequestResponse(tieFirstName.getText().toString(), tieLastName.getText().toString(),
                        tiePhone.getText().toString(), tiePassword.getText().toString());
            }
        });
    }

    private void sendAndRequestResponse(String firstName, String lastName, String phone, String password) {
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.POST, Constant.url + "users/create", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", "SignupActivityResponse: " + response);

                try {
                    JSONObject object = new JSONObject(response);

                    if (object.getBoolean("status")) {
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_LONG).show();//display the response on screen
                    }

                    //Log.e("response", "Response: " + object);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), "Response: " + response, Toast.LENGTH_LONG).show();//display the response on screen
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "SignupActivityError :" + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("firstName", firstName);
                params.put("lastName", lastName);
                params.put("password", password);
                params.put("phone", phone);
                return params;
            }
        };
        mRequestQueue.add(mStringRequest);
    }
}
