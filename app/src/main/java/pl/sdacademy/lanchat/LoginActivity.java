package pl.sdacademy.lanchat;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import layout.IrcFragment;
import layout.LanFragment;


public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void IRConClick(View view) {
//        Toast.makeText(getApplicationContext(), "IRC", Toast.LENGTH_SHORT).show();
        IrcFragment irc = new IrcFragment();
        loadFragment(irc);
    }

    public void LANonClick(View view) {
//        Toast.makeText(getApplicationContext(), "LAN", Toast.LENGTH_SHORT).show();
        LanFragment lan = new LanFragment();
        loadFragment(lan);
    }

    public void loadFragment(Fragment fragment){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.containerForFragments, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        }else{
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

