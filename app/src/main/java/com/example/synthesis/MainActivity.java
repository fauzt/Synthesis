package com.example.synthesis;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goDiscover() {
        Intent intent =new Intent(MainActivity.this, CameraActivity.class);
        startActivity(intent);
    }
}
