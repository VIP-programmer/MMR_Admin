package com.example.mmradmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button login;
    TextView signUp;
    TextView email;
    TextView password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //make it fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.submit);
        email =findViewById(R.id.user_name);
        password=findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //test if somme input is empty
                if (email.getText().toString().matches("") ||
                        password.getText().toString().matches("")
                ){//if one at least is empty
                    Toast.makeText(getApplicationContext(),"Remplissez tous les champs !",Toast.LENGTH_LONG).show();
                }else{
                    //non is empty
                    //testing password length
                    if (! password.getText().toString().equals("admin")
                        || ! email.getText().toString().equals("admin")
                    ){
                        Toast.makeText(getApplicationContext(),"Attention mot de passe incorrect",Toast.LENGTH_LONG).show();
                    }else {
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                    }
                }
            }
        });
    }
}