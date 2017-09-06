package pl.sdacademy.lanchat.online;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pl.sdacademy.lanchat.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mikr on 06/09/17.
 */

public class OnlineAdapter extends RecyclerView.Adapter<OnlineAdapter.OnlineViewHolder> {

    private Context context;
    private List<OnlineMsg> onlineMsgList;

    public OnlineAdapter(Context context, List<OnlineMsg> onlineMsgList) {
        this.context = context;
        this.onlineMsgList = onlineMsgList;
    }

    @Override
    public OnlineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.online_rv, parent, false);

        return new OnlineViewHolder(row);
    }

    @Override
    public void onBindViewHolder(OnlineViewHolder holder, int position) {

        final OnlineMsg onlineMsg = onlineMsgList.get(position);

        if (onlineMsg.getStyle().equals("BOLD")) {
            holder.onlineRV_msg.setTypeface(null, Typeface.BOLD);
        } else if (onlineMsg.getStyle().equals("ITALIC")) {
            holder.onlineRV_msg.setTypeface(null, Typeface.ITALIC);
        } else if (onlineMsg.getStyle().equals("BOLD_ITALIC")) {
            holder.onlineRV_msg.setTypeface(null, Typeface.BOLD_ITALIC);
        }

        if (onlineMsg.getColor().equals("RED")) {
            holder.onlineRV_msg.setTextColor(Color.RED);
        } else if (onlineMsg.getColor().equals("GREEN")) {
            holder.onlineRV_msg.setTextColor(Color.GREEN);
        } else if (onlineMsg.getColor().equals("BLUE")) {
            holder.onlineRV_msg.setTextColor(Color.BLUE);
        }

        holder.onlineRV_nick.setText(onlineMsg.getNick());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String posteDate  = dateFormat.format(new Date(Long.valueOf(onlineMsg.getPostDate())));
        holder.onlineRV_date.setText(posteDate);
        holder.onlineRV_msg.setText(onlineMsg.getMsg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog ad = new AlertDialog.Builder(context)
                        .setTitle("Delete.")
                        .setMessage("Really want delete this message?")
                        .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Retrofit retrofitChat = new Retrofit.Builder()
                                        .baseUrl(OnlineActivity.BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                final OnlineAPI onlineAPI = retrofitChat.create(OnlineAPI.class);
                                Call<Void> call = onlineAPI.deleteMsg(onlineMsg.getId());

                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                    }
                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                    }
                                });

                                onlineMsgList.remove(onlineMsg);
                                notifyDataSetChanged();
                            }

                        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return onlineMsgList.size();
    }

    public class OnlineViewHolder extends RecyclerView.ViewHolder {

        TextView onlineRV_nick;
        TextView onlineRV_date;
        TextView onlineRV_msg;

        public OnlineViewHolder(View itemView) {
            super(itemView);

            onlineRV_nick = (TextView) itemView.findViewById(R.id.onlineRV_nick);
            onlineRV_date = (TextView) itemView.findViewById(R.id.onlineRV_date);
            onlineRV_msg = (TextView) itemView.findViewById(R.id.onlineRV_msg);
        }
    }
}
