package abdullah.elamien.worldwide.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import abdullah.elamien.worldwide.GlideApp;
import abdullah.elamien.worldwide.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.registerHeaderImage)
    ImageView mHeaderImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        loadHeaderImage();
    }

    private void setFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void loadHeaderImage() {
        GlideApp.with(this)
                .load(getString(R.string.register_header_image_url))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mHeaderImage);
    }

    @OnClick(R.id.registerUserButton)
    public void onRegisterButtonClick() {
        // TODO: 10/17/2018 perform register action
    }

    @OnClick(R.id.loginButton)
    public void onLoginButtonClick() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
