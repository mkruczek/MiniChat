package pl.sdacademy.lanchat;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    private String address;
    private String nick;

    TextView textViewChat;
    EditText editTextMsg;
    ImageButton sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent i = getIntent();
        address = i.getStringExtra("ipAddress");
        nick = i.getStringExtra("nick");

        textViewChat = (TextView) findViewById(R.id.textViewChat);
        editTextMsg = (EditText) findViewById(R.id.editTextMsg);
        sendButton = (ImageButton) findViewById(R.id.sendButton);


        textViewChat.setText(status());

        LanServerAsyncTask lsat = new LanServerAsyncTask(textViewChat);
        lsat.execute();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editTextMsg.getText().toString();
                LanClientAsyncTask lanClientAsyncTask = new LanClientAsyncTask(ChatActivity.this);
                lanClientAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, msg);
                Log.d("kru", "jestem w onClick");

            }
        });


    }


    public String status() {
        return "address : " + address + "\n" + "nick : " + nick;
    }
}
