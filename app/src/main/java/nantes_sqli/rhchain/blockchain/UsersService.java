package nantes_sqli.rhchain.blockchain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by mcame on 17/02/17.
 */

public interface UsersService {

    @GET("users/")
    Call<List<User>> getListeUsers();

    @GET("users/{mail}")
    Call<User> getUser(@Path("mail") String mail);

    @GET("users/{mail}/adress")
    Call<String> getAdress(@Path("mail") String mail);

    @POST("users/")
    Call<User> createUser(@Body User user);
}
