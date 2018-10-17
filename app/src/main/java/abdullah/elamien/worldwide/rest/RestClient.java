package abdullah.elamien.worldwide.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AbdullahAtta on 10/17/2018.
 */
public class RestClient {

    private static Retrofit sRetrofit = null;

    public static Retrofit getsRetrofit(String baseUrl) {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
