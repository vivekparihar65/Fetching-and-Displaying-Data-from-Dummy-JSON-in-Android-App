package com.example.task_bonum;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<Userdata> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList=new ArrayList<>();
        UserAdapter userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
        //Function Fetching the data from json data
        fetchJsonData();
    }

    private void fetchJsonData() {
        //URL to fetch the data
        String url="https://gist.github.com/vivekparihar65/9e9094d659ec98e61e860c4c94b3f419/raw";

        //initialize the request queue
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        //create a json request object
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley", "Response: " + response.toString());
                       try {
                           Gson gson=new Gson();
                           Userresponse userresponse=gson.fromJson(response.toString(),Userresponse.class);
                           userList=userresponse.getUsers();

                           //set the adapter for recyclerview
                           userAdapter=new UserAdapter(userList);
                           recyclerView.setAdapter(userAdapter);
                       }catch (Exception e){
                           Toast.makeText(MainActivity.this, "Error parsing json", Toast.LENGTH_SHORT).show();
                       }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //handle error
                        Toast.makeText(MainActivity.this, "Error fetching data"+error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("Volley", "Error fetching data: " + error.getMessage());
                    }
                }
                );

        //add the request to the request queue
        requestQueue.add(jsonObjectRequest);

    }
}