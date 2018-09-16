package com.example.personalcomputer.a2simpleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText textEmail;
    private EditText textSubject;
    private EditText textMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Инициализация EditText'ов MainActivity
        textEmail = findViewById(R.id.edit_text_email);
        textSubject = findViewById(R.id.edit_text_subject);
        textMsg = findViewById(R.id.edit_text_msg);

        //Объявление и инициализация Button MainActivity
        Button btnPreview = findViewById(R.id.button_preview);
        //Объявление обработчика событий нажатия с помощью метода .setOnClickListener()
        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(textEmail)) {
                    Toast.makeText(MainActivity.this, "Empty e-mail field", Toast.LENGTH_SHORT).show();
                } else if (isEmpty(textSubject)){
                    Toast.makeText(MainActivity.this, "Empty subject field", Toast.LENGTH_SHORT).show();
                } else if (isEmpty(textMsg)) {
                    Toast.makeText(MainActivity.this, "Empty message field", Toast.LENGTH_SHORT).show();
                } else {
                    openPreviewActivity();
                }
            }
        });
    }

    //Объявление метода openPreviewActivity, который реализует метод класса PreviewActivity
    public void openPreviewActivity() {
        PreviewActivity.start(this,
                "<b>Recipient's email:</b> \n" +
                        textEmail.getText().toString() + "\n" +
                        "<b>Subject:</b> \n" +
                        textSubject.getText().toString() + "\n" +
                        "<b>Text message:</b> \n" +
                        textMsg.getText().toString());
    }

    //Объявление метода isEmpty для проверки элементов EditText на отсутствие значений (возвращение boolean)
    public static boolean isEmpty(EditText editText) {
        boolean isEmptyResult = false;
        if (editText.getText().length() == 0) {
            isEmptyResult = true;
        }
        return isEmptyResult;
    }

    //Объявление get'еров
    public String getTextEmail() {
        return textEmail.getText().toString();
    }

    public String getTextSubject() {
        return textSubject.getText().toString();
    }

    public String getTextMsg() {
        return textMsg.getText().toString();
    }
}
