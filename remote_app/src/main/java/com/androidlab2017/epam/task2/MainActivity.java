package com.androidlab2017.epam.task2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button mSendButton;
    private EditText mSubjectEditText,
                    mDestinationEditText,
                    mMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSubjectEditText = (EditText) findViewById(R.id.subject_edittext);
        mDestinationEditText = (EditText) findViewById(R.id.destination_edittext);
        mMessageEditText = (EditText) findViewById(R.id.body_message);

        mSendButton = (Button) findViewById(R.id.send_button);
        mSendButton.setOnClickListener((ignored)->clickSendButton());
    }

    private void clickSendButton(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT, mSubjectEditText.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, mMessageEditText.getText().toString());
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {mDestinationEditText.getText().toString()});
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getResources().getText(R.string.title_chooser)));
    }
}
