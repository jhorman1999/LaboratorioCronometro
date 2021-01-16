package com.example.cronometro;


import android.os.Bundle;
import android.view.View;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    public String time ="0:00:00";
    String textoVueltas="";
    int vueltas=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
    }
    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickRestart(View view) {

        running = false;
        seconds = 0;
        time ="0:00:00";
        textoVueltas="";
        vueltas=0;
        final TextView textNumeroDeVueltas = (TextView) findViewById(R.id.textNumeroDeVueltas);
        textNumeroDeVueltas.setText("--|--");
    }
    public void onClickVueltas(View view){
        final TextView textNumeroDeVueltas = (TextView) findViewById(R.id.textNumeroDeVueltas);
        if(vueltas<10) {
            vueltas++;
            textoVueltas=textoVueltas+"Nuelta N°"+vueltas+" | "+time+"\n";
            textNumeroDeVueltas.setText(textoVueltas);
        }
    }

    private void runTimer(){

        final TextView timeView = (TextView) findViewById(R.id.timeView);
        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                 time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

                timeView.setText(time);

                if (running) {
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

}

