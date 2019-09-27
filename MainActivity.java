package com.example.lotta.linearregression;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int color = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.randomButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomize(view);
            }
        });

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButtonBlue){
                    color = 2;
                }
                else if (i == R.id.radioButtonGreen){
                    color = 1;
                }
                else if (i == R.id.radioButtonRed){
                    color = 0;
                }
            }
        });

        findViewById(R.id.drawButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText e = findViewById(R.id.cxBox);
                String cxString = e.getText().toString();
                double cx = Double.parseDouble(cxString);

                EditText ee = findViewById(R.id.cyBox);
                String cyString = ee.getText().toString();
                double cy = Double.parseDouble(cyString);

                EditText eee = findViewById(R.id.intesityBox);
                String intenString = eee.getText().toString();
                int inten = Integer.parseInt(intenString);
                loadDraw(cx, cy, color, inten);
            }
        });

    }

    private void randomize(View v)
    {
        Random r = new Random();
        loadDraw(r.nextDouble()*2-1, r.nextDouble()*2-1, r.nextInt(3), r.nextInt(5)+2);
    }

    private void loadDraw(double cx, double cy, int color, int intensity){
        Intent intent = new Intent(this, Draw.class);
        Bundle b = new Bundle();
        b.putDouble("cx", cx);
        b.putDouble("cy", cy);
        b.putInt("color", color);
        b.putInt("intensity", intensity);
        intent.putExtras(b);
        startActivity(intent);
    }
}
