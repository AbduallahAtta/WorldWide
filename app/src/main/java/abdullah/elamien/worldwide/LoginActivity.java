package abdullah.elamien.worldwide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.loginButton)
    public void onLoginButtonClick() {
        // TODO: 10/17/2018 perform login action
    }

    @OnClick(R.id.registerButton)
    public void onRegisterButtonClick() {
        // TODO: 10/17/2018 perform register action
    }
}
