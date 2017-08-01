package layout;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import pl.sdacademy.lanchat.R;
import pl.sdacademy.lanchat.lan.LanChatActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LanFragment extends Fragment {


    public LanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final SharedPreferences sp = getActivity().getSharedPreferences(getString(R.string.my_file), Context.MODE_PRIVATE);
        String name = sp.getString(getString(R.string.nick_lan), "");

        View myView = inflater.inflate(R.layout.fragment_lan, container, false);

        final EditText nickEditTexLAN = (EditText) myView.findViewById(R.id.nickEditTexLAN);
        nickEditTexLAN.setText(name);

        final EditText IPaddressEditText = (EditText) myView.findViewById(R.id.IPaddressEditText);

        ImageButton loginButtonLAN = (ImageButton) myView.findViewById(R.id.loginButtonLAN);
        loginButtonLAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor spEditor = sp.edit();
                spEditor.putString(getString(R.string.nick_lan), nickEditTexLAN.getText().toString());
                spEditor.putString(getString(R.string.ip_address_lan), IPaddressEditText.getText().toString());
                spEditor.commit();

                Intent intent = new Intent(getContext(), LanChatActivity.class);
                intent.putExtra(getString(R.string.ip_address_lan), IPaddressEditText.getText().toString());
                intent.putExtra(getString(R.string.nick_lan), nickEditTexLAN.getText().toString());
                startActivity(intent);
            }
        });

        return myView;
    }

}
