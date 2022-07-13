package com.example.libraskids.Quizz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;

import android.app.AlertDialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libraskids.Activity.HomeActivity;
import com.example.libraskids.R;

import java.util.ArrayList;
import java.util.Objects;

public class Quizz extends AppCompatActivity implements View.OnClickListener {

//declaração dos itens
    TextView totalQuestionsTextView;
    TextView textoQuestao;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;
    VideoView videoView;
    int score= 0;
    int totalQuestion = QuestionAnswer.questoes.length;
    int currentQuestionIndex = 0;
    String respostaSelecionada = "";

    private static boolean onInfo(MediaPlayer mediaController1, int what, int extra) {

        //to check if buffering, rendering etc
        switch (what) {
            case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
            case MediaPlayer.MEDIA_INFO_BUFFERING_END: {
                //rendering started
                return true;
            }//buffering started
//buffering end
        }
        return false;
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        //removendo sidebar
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        totalQuestionsTextView = findViewById(R.id.total_question);
        textoQuestao = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);


        //XXXXXXXXXXXXXXXXXXXXXXXXXXX
        // ativando reprodutor de vídeos - mediaplayer
        videoView =findViewById(R.id.videoQuiz);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();

        //XXXXXXXXXXXXXXXXXXXXXXXXXXX
        // tornando os botões clicaveis
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        //XXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        videoView.setOnInfoListener(Quizz::onInfo);
        //restart no video
        videoView.setOnCompletionListener(MediaPlayer::start);
        
        //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
        totalQuestionsTextView.setText("Total Questões : "+ totalQuestion);
        loadNewQuestion();

        //reproduzir outros vídeos
        videoView.start();
        videoView.setOnInfoListener(Quizz::onInfo);

        //restart no video
        videoView.setOnCompletionListener(MediaPlayer::start);

    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {
            if (respostaSelecionada.equals(QuestionAnswer.respostasCorretas[currentQuestionIndex])) {
                score++;
                //alerta resposta certa
                new AlertDialog.Builder(this)
                        .setTitle("")
                        .setMessage("Sua resposta está correta!")
                        .setPositiveButton("ok",(dialogInterface, i) -> loadNewQuestion() )
                        .setCancelable(false)
                        .show();



            } if (false == respostaSelecionada.equals(QuestionAnswer.respostasCorretas[currentQuestionIndex])){
                //alerta resposta errada
                new AlertDialog.Builder(this)
                        .setTitle("")
                        .setMessage("Sua resposta está errada!")
                        .setPositiveButton("ok",(dialogInterface, i) -> loadNewQuestion() )
                        .setCancelable(false)
                        .show();


            }
            currentQuestionIndex++;
            loadNewQuestion();

        } else {
            //quando o botão é selecionado muda de cor
            respostaSelecionada = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }
        textoQuestao.setText(QuestionAnswer.questoes[currentQuestionIndex]);
        videoView.setVideoURI(Uri.parse(QuestionAnswer.videos[currentQuestionIndex]));
        ansA.setText(QuestionAnswer.alternativas[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.alternativas[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.alternativas[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.alternativas[currentQuestionIndex][3]);

    }


    void finishQuiz(){
        String passStatus;
        if(score > totalQuestion*0.50){
            passStatus = "Parabéns!!!";
        }else{
            passStatus = "Precisa melhorar!";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Sua pontuação é "+ score+" de "+ totalQuestion)
                .setPositiveButton("Reiniciar",(dialogInterface, i) -> restartQuiz() )
                .setNegativeButton("Sair",(dialogInterface, i) -> sair())
                .setCancelable(false)
                .show();
    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }

    void sair(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    };
}
