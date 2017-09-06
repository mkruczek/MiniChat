package pl.sdacademy.lanchat.online;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by mikr on 06/09/17.
 */

public interface OnlineAPI {

    @GET("all")
    Call<List<OnlineMsg>> allMsg();

    @POST("add")
    Call<Void> addMsg (@Body OnlineMsg onlineMsg);

    @DELETE("delete/{id}")
    Call<Void> deleteMsg(@Path("id") Long id);

    @DELETE("delete")
    Call<Void> deleteAll();
}
