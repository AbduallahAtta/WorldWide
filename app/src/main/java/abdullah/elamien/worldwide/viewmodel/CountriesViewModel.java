package abdullah.elamien.worldwide.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import abdullah.elamien.worldwide.models.Countries;
import abdullah.elamien.worldwide.rest.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by AbdullahAtta on 10/17/2018.
 */
public class CountriesViewModel extends ViewModel {
    private static final String TAG = CountriesViewModel.class.getSimpleName();
    private MutableLiveData<Countries> mCountriesData;

    public MutableLiveData<Countries> getmCountriesData() {
        if (mCountriesData == null) {
            mCountriesData = new MutableLiveData<>();
            loadCountries();
        }
        return mCountriesData;
    }

    private void loadCountries() {
        ApiUtils.getCountriesService().getAllCountries().enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                if (response.isSuccessful()) {
                    mCountriesData.setValue(response.body());
                } else {
                    Log.d(TAG, String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }
}
