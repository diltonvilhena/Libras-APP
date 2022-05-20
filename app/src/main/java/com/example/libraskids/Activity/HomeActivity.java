package com.example.libraskids.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libraskids.Quiz.StartGame;
import com.example.libraskids.R;
import com.example.libraskids.VideoPlay.VpActivity;
import com.example.libraskids.quizz.Quizz;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        //removendo sidebar
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button btone = findViewById(R.id.bt_crianca);
        Button bttwo = findViewById(R.id.bt_jovem);
        Button btthree = findViewById(R.id.bt_video);
        Button btfour = findViewById(R.id.buttonquiz);

        btone.setOnClickListener(v -> {
            callActivity("crianca");
        });

        bttwo.setOnClickListener(v -> {
            callActivity("jovem");
        });

        btthree.setOnClickListener(v -> {
            videoPlay();
        });

        btfour.setOnClickListener(v -> {
            startGame();
        });

    }

    private void callActivity(String path){

        Intent intent = new Intent(this, MenuCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("path",path);
        intent.putExtras(bundle);

        //iniciando a activity indicada acima
        startActivity(intent);
    }

    private void videoPlay(){

        Intent intent = new Intent(this, VpActivity.class);
        startActivity(intent);
    }


    private void startGame() {

        Intent intent = new Intent(this, Quizz.class);
        startActivity(intent);
    }
}

