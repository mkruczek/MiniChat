package pl.sdacademy.lanchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText editTextAddress;
    EditText editTextNick;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextNick = (EditText) findViewById(R.id.editTextNick);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        editTextAddress.setText("10.0.0.255");
        editTextNick.setText("TEL");

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ChatActivity.class);
                i.putExtra("ipAddress", editTextAddress.getText().toString());
                i.putExtra("nick", editTextNick.getText().toString());
                startActivity(i);
            }
        });




    }
}
