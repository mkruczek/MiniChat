package pl.sdacademy.lanchat;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import layout.IrcFragment;
import layout.LanFragment;
import layout.OnlineFragment;


public class LoginActivity extends AppCompatActivity {

    private Context context;

    private RadioButton onlineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = LoginActivity.this;

        onlineButton = (RadioButton) findViewById(R.id.onlineButton);
        onlineButton.setChecked(true);

        OnlineFragment online = new OnlineFragment();
        loadFragment(online);

    }


    public void IRConClick(View view) {
        IrcFragment irc = new IrcFragment();
        loadFragment(irc);

        AlertDialog ad = new AlertDialog.Builder(context)
                .setTitle("Coming soon!")
                .setMessage("For now my IRC listening only one chanel. " +
                        "It`s chanel #ebook on  irc.irchighway.net server. " +
                        "All msg aren`t formatted. " +
                        "Add full functionality in the next version. " +
                        "Writing in below fields, have don't any result.")
                .show();

    }

    public void LANonClick(View view) {
        LanFragment lan = new LanFragment();
        loadFragment(lan);

        AlertDialog ad = new AlertDialog.Builder(context)
                .setTitle("Simple LAN chat")
                .setMessage("First version of my chat. Work properly on LAN, and don`t have any possible to format text.")
                .show();
    }

    public void OnlineonClick(View view) {
        OnlineFragment online = new OnlineFragment();
        loadFragment(online);
    }

    public void loadFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.containerForFragments, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


//    EditText editTextAddress;
//    EditText editTextNick;
//    Button buttonLogin;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
//        editTextNick = (EditText) findViewById(R.id.editTextNick);
//        buttonLogin = (Button) findViewById(R.id.buttonLogin);
//
//        editTextAddress.setText("10.0.0.255");
//        editTextNick.setText("TEL");
//
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(LoginActivity.this, LanChatActivity.class);
//                i.putExtra("ipAddress", editTextAddress.getText().toString());
//                i.putExtra("nick", editTextNick.getText().toString());
//                startActivity(i);
//            }
//        });


}

