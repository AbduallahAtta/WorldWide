package abdullah.elamien.worldwide.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

import java.util.List;

import abdullah.elamien.worldwide.R;
import abdullah.elamien.worldwide.adapters.CountriesAdapter;
import abdullah.elamien.worldwide.models.Countries;
import abdullah.elamien.worldwide.models.Result;
import abdullah.elamien.worldwide.rest.ApiUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountriesListActivity extends AppCompatActivity {

    private static final String TAG = CountriesListActivity.class.getSimpleName();
    @BindView(R.id.countriesRecyclerView)
    RecyclerView mCountriesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_countries_list);
        ButterKnife.bind(this);
        loadCountries();
    }

    private void loadCountries() {
        ApiUtils.getCountriesService().getAllCountries().enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                if (response.isSuccessful()) {
                    setCountries(response.body().getResult());
                } else {
                    Log.d(TAG, String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                Crashlytics.logException(t);
            }
        });
    }

    private void setCountries(List<Result> result) {
        CountriesAdapter adapter = new CountriesAdapter(result, this);
        mCountriesRecyclerView.setAdapter(adapter);
    }
}
