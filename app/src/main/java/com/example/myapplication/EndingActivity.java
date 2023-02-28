package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndingActivity extends AppCompatActivity {
    private TextView lbl1;
    private Button button;
    private com.example.myapplication.EndingActivity this2 = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);
        lbl1=(TextView) findViewById(R.id.lbl1);
        button=(Button) findViewById(R.id.button);
        Intent odbierzTure = getIntent();
        String wynik = odbierzTure.getStringExtra("WYNIK");
        lbl1.setText(wynik);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myActivity = new Intent(this2, MainActivity.class);
                startActivity(myActivity);
            }
        });
    }
}