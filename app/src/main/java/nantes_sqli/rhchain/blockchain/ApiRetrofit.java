package nantes_sqli.rhchain.blockchain;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static nantes_sqli.rhchain.blockchain.GethConfigConstants.IP_ADMIN;

/**
 * Created by mcame on 17/02/17.
 * //http://square.github.io/retrofit/
 */


public class ApiRetrofit {
    private final String baseUrl = "http://"+ IP_ADMIN +":8000/api/";
    private UsersService service;

    public ApiRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        this.service = retrofit.create(UsersService.class);
    }

    public UsersService getUsersService() {
        return service;
    }


}
