package pl.sdacademy.lanchat.online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pl.sdacademy.lanchat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OnlineActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://80.211.196.76:8080/msg/";

    private static String nick;

    private static String style;
    private static String color;

    static List<OnlineMsg> onlineMsgList;

    TextView textViewChat;
    Button bold;
    Button italic;
    Button bold_italic;
    Button red;
    Button green;
    Button blue;
    ImageButton sendButton;
    EditText editTextMsg;

    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private OnlineAdapter onlineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_activity);

        onlineMsgList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.onlineRV);
        llm = new LinearLayoutManager(OnlineActivity.this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), llm.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(llm);
        onlineAdapter = new OnlineAdapter(OnlineActivity.this, onlineMsgList);
        recyclerView.setAdapter(onlineAdapter);

        nick = getIntent().getStringExtra("nick_Online");
        style = "NORMAL";
        color = "BLACK";

        textViewChat = (TextView) findViewById(R.id.textViewChat);
        bold = (Button) findViewById(R.id.bold);
        italic = (Button) findViewById(R.id.italic);
        bold_italic = (Button) findViewById(R.id.bold_italic);
        red = (Button) findViewById(R.id.red);
        green = (Button) findViewById(R.id.green);
        blue = (Button) findViewById(R.id.blue);
        editTextMsg = (EditText) findViewById(R.id.editTextMsg);
        sendButton = (ImageButton) findViewById(R.id.sendButton);

        bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                style = "BOLD";
            }
        });

        italic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                style = "ITALIC";
            }
        });

        bold_italic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                style = "BOLD_ITALIC";
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "RED";
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "GREEN";
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = "BLUE";
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = editTextMsg.getText().toString();

                Long dateLong = new Date().getTime();
                String date = String.valueOf(dateLong);

                OnlineMsg onlineMsg = new OnlineMsg();

                onlineMsg.setNick(nick);
                onlineMsg.setMsg(msg);
                onlineMsg.setPostDate(date);
                onlineMsg.setColor(color);
                onlineMsg.setStyle(style);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                OnlineAPI onlineAPI = retrofit.create(OnlineAPI.class);
                Call<Void> call = onlineAPI.addMsg(onlineMsg);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

                editTextMsg.setText("");
                style = "NORMAL";
                color = "BLACK";

            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {

                Retrofit retrofitChat = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                final OnlineAPI onlineAPI = retrofitChat.create(OnlineAPI.class);
                Call<List<OnlineMsg>> call = onlineAPI.allMsg();

                while (true) {
                    call.clone().enqueue(new Callback<List<OnlineMsg>>() {
                        @Override
                        public void onResponse(Call<List<OnlineMsg>> call, Response<List<OnlineMsg>> response) {

                            if (onlineMsgList.size() == 0) {
                                onlineMsgList = response.body();
                                onlineMsgList = fromBack(onlineMsgList);
                                refreshRV(onlineMsgList);
                            } else if (onlineMsgList.size() < response.body().size()) {


                                onlineMsgList.add(0, response.body().get(response.body().size() - 1));

                                refreshRV(onlineMsgList);

                            }

                        }

                        @Override
                        public void onFailure(Call<List<OnlineMsg>> call, Throwable t) {

                        }
                    });

                }
            }
        }).start();


    }

    public void refreshRV(final List<OnlineMsg> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                onlineAdapter = new OnlineAdapter(OnlineActivity.this, list);
                recyclerView.setAdapter(onlineAdapter);
            }
        });
    }

    public static List<OnlineMsg> fromBack(List<OnlineMsg> onlineMsgList) {
        List<OnlineMsg> result = new ArrayList<>();

        for (int i = onlineMsgList.size()-1; i >= 0; i--) {
            result.add(onlineMsgList.get(i));
        }

        return result;
    }
}
