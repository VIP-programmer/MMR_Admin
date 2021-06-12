package com.example.mmradmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import java.util.Vector;

public class MedcinListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView empty;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medcin_list);
        recyclerView=findViewById(R.id.doc_list);
        empty=findViewById(R.id.empty_view);

        recyclerView.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        new Model(this,queue).getUsers(new Model.LoadHomeInfoCallBack() {
            @Override
            public void onSuccess(Vector<Medcin> vector) {
                UserListAdapter medListAdapter = new UserListAdapter(vector,getApplicationContext());
                recyclerView.setAdapter(medListAdapter);
                // Attach the adapter to the recyclerview to populate items
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                if (vector.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    empty.setVisibility(View.VISIBLE);
                }
                else {
                    recyclerView.setVisibility(View.VISIBLE);
                    empty.setVisibility(View.GONE);
                }
            }

            @Override
            public void onErr(String message) {
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            }
        });
    }
}