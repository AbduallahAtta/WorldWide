package abdullah.elamien.worldwide.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import abdullah.elamien.worldwide.R;
import abdullah.elamien.worldwide.adapters.CountriesAdapter;
import abdullah.elamien.worldwide.models.Countries;
import abdullah.elamien.worldwide.models.Result;
import abdullah.elamien.worldwide.viewmodel.CountriesViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CountriesListActivity extends AppCompatActivity {
    @BindView(R.id.countriesRecyclerView)
    RecyclerView mCountriesRecyclerView;
    @BindView(R.id.loadingIndicator)
    SpinKitView mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_list);
        ButterKnife.bind(this);
        loadCountries();
    }

    private void loadCountries() {
        showLoadingIndicator();
        CountriesViewModel model = ViewModelProviders.of(this).get(CountriesViewModel.class);
        model.getmCountriesData().observe(this, new Observer<Countries>() {
            @Override
            public void onChanged(@Nullable Countries countries) {
                setCountries(countries.getResult());
            }
        });
    }

    private void showLoadingIndicator() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    private void setCountries(List<Result> result) {
        hideLoadingIndicator();
        CountriesAdapter adapter = new CountriesAdapter(result, this);
        mCountriesRecyclerView.setAdapter(adapter);
    }

    private void hideLoadingIndicator() {
        mLoadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signOutMenuItem:
                signOutUser();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void signOutUser() {
        FirebaseAuth.getInstance().signOut();
        launchLoginActivity();
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.countries_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            launchLoginActivity();
        }
        super.onStart();
    }
}
