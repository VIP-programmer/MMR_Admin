package com.example.mmradmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class HomeActivity extends AppCompatActivity {
    LinearLayout medics;
    LinearLayout addMedicament;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        medics=findViewById(R.id.voir_users);
        addMedicament=findViewById(R.id.new_medicament);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        medics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,MedcinListActivity.class));
            }
        });
        addMedicament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> infos = new HashMap<>();
                Dialog dialog = new Dialog(HomeActivity.this);
                dialog.setContentView(R.layout.dialog_add_medicament);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button mActionOk = dialog.findViewById(R.id.add_medicament);
                Button mActionCancel = dialog.findViewById(R.id.cancel_medicament);
                EditText prix = dialog.findViewById(R.id.prix_medicament);
                EditText nom = dialog.findViewById(R.id.nom_medicament);
                dialog.show();
                mActionCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                mActionOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (prix.getText().toString().matches("") ||
                                nom.getText().toString().matches("")
                        )
                            Toast.makeText(getApplicationContext(),"Remplissez tous les champs !",Toast.LENGTH_LONG).show();
                        else {
                            infos.put("name",nom.getText().toString());
                            infos.put("prix",prix.getText().toString());
                            dialog.dismiss();
                            new Model(HomeActivity.this,queue).addMedicament(infos, new Model.SignUpCallBack() {
                                @Override
                                public void onSuccess(String message) {

                                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onErr(String message) {

                                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}