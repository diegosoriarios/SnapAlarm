package com.example.diegorios.snapalarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.clarifaimessage.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setAlarm(View view) throws ParseException {
        /*
        * Atribuindo inputs para variaveis
        */
        EditText date = findViewById(R.id.date);
        EditText hour = findViewById(R.id.hour);
        EditText minute = findViewById(R.id.minute);
        EditText object = findViewById(R.id.object);

        /*
        * Formata data
        */
        String dateString = date.getText().toString() + " " + hour.getText().toString() + ":" + minute.getText().toString();
        Date dateTime = (new SimpleDateFormat("dd-MM-yy HH:mm")).parse(dateString);

        /*
        *   Cria um Intent do alarme.
        * Intents são mensagens assíncronas que permitem que os componentes de um aplicativo]
        * solicitem a funcionalidade de outros componentes do Android.
        * Armazena o nome do objeto que o usuário precisa fotografar dentro desse Intent
        */
        Intent alarmIntent = new Intent(this, AlarmActivity.class);
        alarmIntent.putExtra(EXTRA_MESSAGE, object.getText().toString());

        /*
        * Cria o alarme
        */
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, dateTime.getTime(),
                PendingIntent.getActivity(this, 1, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT));

        finish();
    }
}
