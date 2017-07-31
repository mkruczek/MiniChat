package pl.sdacademy.lanchat;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Created by RENT on 2017-07-31.
 */

public class LanServerAsyncTask extends AsyncTask<Void, String, Void> {


    TextView textView;
    private String msg = "";

    public LanServerAsyncTask(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected Void doInBackground(Void... params) {

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(4446);
            socket.bind(new InetSocketAddress(4446));
        } catch (SocketException e) {
            e.printStackTrace();
        }
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);


        while (true) {
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String receive = new String(packet.getData(), 0, packet.getLength());
            publishProgress(receive);
        }


    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        msg = msg + values[0] + "\n";

        textView.setText(msg);

    }
}
