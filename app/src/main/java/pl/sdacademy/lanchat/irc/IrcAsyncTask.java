package pl.sdacademy.lanchat.irc;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by RENT on 2017-08-01.
 */

public class IrcAsyncTask extends AsyncTask<String, String, Void> {

    TextView textView;
    private boolean running = true;

    private String nick;
    private String server;
    private String socketValue;
    private String chanel;

    private static String strToTextViev;

    Socket socket;

    public IrcAsyncTask(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onCancelled(){
        running = false;
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(String... params) {

        while (running) {
            try {
                // The server to connect to and our details.
                server = "irc.irchighway.net";
                nick = params[0];
                String login = "janusz_z_miko";

                // The channel which the bot will join.
                String channel = "#ebooks";

                // Connect directly to the IRC server.
                socket = new Socket(server, 6662);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Log on to the server.
                writer.write("NICK " + nick + "\r\n");
                writer.write("USER " + login + " 8 * : Java IRC Hacks Bot\r\n");
                writer.flush();

                // Read lines from the server until it tells us we have connected.
                String line = null;
                while ((line = reader.readLine()) != null) {
                    if (line.indexOf("004") >= 0) {
                        // We are now logged in.
                        break;
                    } else if (line.indexOf("433") >= 0) {
                        publishProgress("Nickname is already in use.");

                    }
                }

                // Join the channel.
                writer.write("JOIN " + channel + "\r\n");
                writer.flush();

                // Keep reading lines from the server.
                while ((line = reader.readLine()) != null) {
                    if (line.toUpperCase().startsWith("PING ")) {
                        // We must respond to PINGs to avoid being disconnected.
                        writer.write("PONG " + line.substring(5) + "\r\n");
                        writer.write("PRIVMSG " + channel + " :I got pinged!\r\n");
                        writer.flush();

                    } else {
                        // Print the raw line received by the bot.

                        strToTextViev += line;

                        publishProgress(privmsg(strToTextViev));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
            return null;
    }

    @Override
    protected void onProgressUpdate(String[] values) {
        super.onProgressUpdate(values);

        textView.setText(values[0]);
    }

    public String privmsg(String line){
        if(line.toLowerCase().contains("privmsg")){
            line += "KLOPKLOPKLOPKLOPKLOP";
        }

        return line;
    }

}
