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

    //Объявление метода sendViaEmail()
    private void sendViaEmail() {
        MainActivity preview = new MainActivity();

        String recepient = preview.getTextEmail();
        String subject = preview.getTextSubject();
        String msg = preview.getTextMsg();

        /*Intent intent = new Intent(Intent.ACTION_SEND);

        intent.putExtra(Intent.EXTRA_EMAIL, recepient);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, msg);

        intent.setType("message/rfc822");
        startActivity(intent.createChooser(intent, "Choose an e-mail client"));*/

        textPreview.setText(recepient);
    }
}
