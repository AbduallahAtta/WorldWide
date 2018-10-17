package abdullah.elamien.worldwide.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.crashlytics.android.Crashlytics;

import java.util.List;

import abdullah.elamien.worldwide.R;
import abdullah.elamien.worldwide.adapters.CountriesAdapter;
import abdullah.elamien.worldwide.models.Countries;
import abdullah.elamien.worldwide.models.Result;
import abdullah.elamien.worldwide.viewmodel.CountriesViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class CountriesListActivity extends AppCompatActivity {
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
        CountriesViewModel model = ViewModelProviders.of(this).get(CountriesViewModel.class);
        model.getmCountriesData().observe(this, new Observer<Countries>() {
            @Override
            public void onChanged(@Nullable Countries countries) {
                setCountries(countries.getResult());
            }
        });
    }

    private void setCountries(List<Result> result) {
        CountriesAdapter adapter = new CountriesAdapter(result, this);
        mCountriesRecyclerView.setAdapter(adapter);
    }
}
