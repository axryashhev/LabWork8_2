package com.example.labwork8;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.*;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void onBack(View view) throws JSONException {
        EditText editTextId = findViewById(R.id.editTextId);
        EditText editTextIdGroup = findViewById(R.id.editTextIdGroup);
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextSurname = findViewById(R.id.editTextSurname);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "http://172.20.10.8:8080/api/students";

        JSONObject jsonBody = new JSONObject();

        jsonBody.put("id", Long.parseLong(String.valueOf(editTextId.getText())));
        jsonBody.put("group_id", Long.parseLong(String.valueOf(editTextIdGroup.getText())));
        jsonBody.put("name", editTextName.getText());
        jsonBody.put("surname", editTextSurname.getText());

        final String requestBody = jsonBody.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> Log.i("VOLLEY", response), error -> {
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                return requestBody.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };


        requestQueue.add(stringRequest);
    }
}