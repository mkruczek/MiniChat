package layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import pl.sdacademy.lanchat.R;
import pl.sdacademy.lanchat.irc.IrcActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class IrcFragment extends Fragment {

    EditText nickEditTexIRC;
    EditText serverEditText;
    EditText chanelEditText;
    EditText socketEditText;
    ImageButton loginButtonIrc;


    public IrcFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_irc, container, false);
        nickEditTexIRC = (EditText) myView.findViewById(R.id.nickEditTexIRC);
        serverEditText = (EditText) myView.findViewById(R.id.serverEditText);
        chanelEditText = (EditText) myView.findViewById(R.id.chanelEditText);
        loginButtonIrc = (ImageButton) myView.findViewById(R.id.loginButtonIRC);

        loginButtonIrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getContext(), IrcActivity.class);
                intent.putExtra("nickIRC", nickEditTexIRC.getText().toString());
                intent.putExtra("serverIRC", serverEditText.getText().toString());
                intent.putExtra("chanelIRC",chanelEditText.getText().toString());
                startActivity(intent);
            }
        });

        return myView;
    }

}
