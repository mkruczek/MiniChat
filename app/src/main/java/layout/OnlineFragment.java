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
import pl.sdacademy.lanchat.online.OnlineActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnlineFragment extends Fragment {


    EditText nickEditTexOnline;
    ImageButton loginButtonOnline;


    public OnlineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final SharedPreferences sp = getActivity().getSharedPreferences("OnLineValue", Context.MODE_PRIVATE);
        String name = sp.getString("OnLineNick", "");

        View myView = inflater.inflate(R.layout.fragment_online, container, false);
        nickEditTexOnline = (EditText) myView.findViewById(R.id.nickEditTexOnline);
        loginButtonOnline = (ImageButton) myView.findViewById(R.id.loginButtonOnline);

        nickEditTexOnline.setText(name);

        loginButtonOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor spEditor = sp.edit();
                spEditor.putString("OnLineNick", nickEditTexOnline.getText().toString());
                spEditor.commit();

                Intent intent = new Intent(getContext(), OnlineActivity.class);
                intent.putExtra("nick_Online", nickEditTexOnline.getText().toString());
                startActivity(intent);
            }
        });

        return myView;
    }

}
