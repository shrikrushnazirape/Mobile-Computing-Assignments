package com.example.frameanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button startBtn, stopBtn;
    ImageView imageView;
    AnimationDrawable anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startBtn = findViewById(R.id.startBtn);
        stopBtn = findViewById(R.id.endBtn);
        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.walkingman);

        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                anim = (AnimationDrawable) imageView.getDrawable();
                anim.start();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                anim.stop();
            }
        });



    }
}