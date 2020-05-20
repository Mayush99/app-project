package com.example.shopeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intro);

    }

    public void move_to_login(View v){
        Intent register = new Intent(MainActivity.this,Login.class);
        startActivity(register);
        finish();
    }
}
