package pl.sdacademy.lanchat.irc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import pl.sdacademy.lanchat.R;

public class IrcActivity extends AppCompatActivity {

    String nick;
    String server;
    String socket;
    String chanel;

    TextView textViewIRC;

    IrcAsyncTask ircAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irc);

        Intent intent = getIntent();
        nick = intent.getStringExtra("nickIRC");
        server = intent.getStringExtra("serverIRC");
        socket = intent.getStringExtra("socketIRC");
        chanel = intent.getStringExtra("chanelIRC");

        textViewIRC = (TextView) findViewById(R.id.textViewIRC);

        ircAsyncTask = new IrcAsyncTask(textViewIRC);
        ircAsyncTask.execute(nick);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ircAsyncTask.onCancelled();
    }
}
