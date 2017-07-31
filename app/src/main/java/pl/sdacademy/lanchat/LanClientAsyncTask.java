package pl.sdacademy.lanchat;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Created by RENT on 2017-07-31.
 */

public class LanClientAsyncTask extends AsyncTask<String, Void, Void> {

    private Context context;

    public LanClientAsyncTask(Context context) {
        this.context = context;
    }

    public LanClientAsyncTask() {
    }

    @Override
    protected Void doInBackground(String... params) {

        String msg = params[0];

        Log.d("kru", "jestem w doInBackground");

        try {
            DatagramSocket socket = new DatagramSocket(4445);
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("10.0.0.255"), 4446);

                packet.setData((msg).getBytes());
                socket.send(packet);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}