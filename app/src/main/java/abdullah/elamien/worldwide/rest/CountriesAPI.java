package abdullah.elamien.worldwide.rest;

import abdullah.elamien.worldwide.models.Countries;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by AbdullahAtta on 10/17/2018.
 */
public interface CountriesAPI {
    @GET("countries")
    Call<Countries> getAllCountries();
}
