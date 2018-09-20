package com.example.personalcomputer.a2simpleapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PreviewActivity extends AppCompatActivity {
    private TextView textPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        textPreview = findViewById(R.id.text_view_preview);
        textPreview.setText(getIntent().getStringExtra("KEY_TEXT_PREVIEW"));

        Button btnSend = findViewById(R.id.button_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendViaEmail();
            }
        });
    }

    //Best practice: объявление Intent'а в Activity, который необходимо вызвать
    public static void start(Activity activity, String textPreview) {
        Intent intent = new Intent(activity, PreviewActivity.class);

        intent.putExtra("KEY_TEXT_PREVIEW", textPreview);
        activity.startActivity(intent);
    }

    public static void emailInfoView(Activity activity, String emailRecepient, String emailSubject, String emailMsg) {
        Intent intent = new Intent(activity, PreviewActivity.class);

        intent.putExtra("KEY_MSG_RECEPIENT", emailRecepient);
        intent.putExtra("KEY_MSG_SUBJECT", emailSubject);
        intent.putExtra("KEY_MSG", emailMsg);
    }

    //Объявление метода sendViaEmail()
    private void sendViaEmail() {
        String recepient = getIntent().getStringExtra("KEY_MSG_RECEPIENT");
        String subject = getIntent().getStringExtra("KEY_TEXT_PREVIEW");
        String msg = getIntent().getStringExtra("KEY_MSG");

        Intent sendIntent = new Intent(Intent.ACTION_SEND);

        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {recepient});
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);

        sendIntent.setType("message/rfc822");
        startActivity(sendIntent.createChooser(sendIntent, "Choose an e-mail client"));

        //textPreview.setText(subject);
    }
}
