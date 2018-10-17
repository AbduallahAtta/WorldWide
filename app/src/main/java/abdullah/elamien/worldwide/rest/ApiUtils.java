package abdullah.elamien.worldwide.rest;

/**
 * Created by AbdullahAtta on 10/17/2018.
 */
public class ApiUtils {
    private static final String BASE_URL = "https://api.printful.com/";

    public static CountriesAPI getCountriesService() {
        return RestClient.getsRetrofit(BASE_URL).create(CountriesAPI.class);
    }
}
