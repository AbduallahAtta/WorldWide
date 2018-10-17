package abdullah.elamien.worldwide.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;

import abdullah.elamien.worldwide.R;
import io.fabric.sdk.android.Fabric;

public class CountriesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_countries_list);
    }
}
