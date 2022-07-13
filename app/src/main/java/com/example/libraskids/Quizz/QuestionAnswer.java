package com.example.libraskids.Quizz;

//classe onde ficam armazenadas as perguntas e repostas
public class QuestionAnswer {

    public static String questoes[] ={
            "Que animal é esse?",
            "Que animal é esse?",
            "Que número é esse?",
            "Que número é esse?"
    };

    public static String videos[] ={
            "https://firebasestorage.googleapis.com/v0/b/app-libras.appspot.com/o/Quiz%2Fboi_comp.mp4?alt=media&token=fce99a31-59e4-4385-8143-9a9d2ea80b11",
            "https://firebasestorage.googleapis.com/v0/b/app-libras.appspot.com/o/Quiz%2Fcobra_comp.mp4?alt=media&token=b3cb078b-e42a-4f14-829f-2c781156fc6d",
            "https://firebasestorage.googleapis.com/v0/b/app-libras.appspot.com/o/Quiz%2F3-tres_comp.mp4?alt=media&token=e21adbd7-c33c-454b-8d53-39a9658d6161",
            "https://firebasestorage.googleapis.com/v0/b/app-libras.appspot.com/o/Quiz%2F9-nove_comp.mp4?alt=media&token=a706481a-245d-47c4-87aa-ed4e9ce4b8f5"

    };
    public static String alternativas[][] = {
            {"GATO","BOI","CORUJA","CACHORRO"},
            {"ZEBRA","BURRO","CAVALO","COBRA"},
            {"3","4","5","6"},
            {"2","8","1","9"}
    };

    public static String respostasCorretas[] = {
            "BOI",
            "COBRA",
            "3",
            "9"
    };



}
