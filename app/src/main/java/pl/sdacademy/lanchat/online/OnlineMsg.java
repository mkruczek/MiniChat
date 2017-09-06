package pl.sdacademy.lanchat.online;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;


/**
 * Created by mikr on 06/09/17.
 */

class OnlineMsg implements Serializable {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("postDate")
    @Expose
    private String postDate;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("style")
    @Expose
    private String style;
    @SerializedName("nick")
    @Expose
    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }


}
