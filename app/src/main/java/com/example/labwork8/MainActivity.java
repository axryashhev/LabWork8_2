package com.example.labwork8;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.data);
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "http://172.20.10.8:8080/api/students";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> textView.setText(String.format(response.toString())),
                error -> {
                    Toast toast =
                            Toast.makeText(this,
                                    error.getMessage(),
                                    Toast.LENGTH_SHORT);
                    toast.show();
                });

        requestQueue.add(jsonObjectRequest);


    }

    public void onAdd(View view) {
        Intent intent = new Intent();
        intent.setClass(this, AddActivity.class);
        startActivity(intent);
    }
}