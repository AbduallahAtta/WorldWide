package abdullah.elamien.worldwide.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import abdullah.elamien.worldwide.GlideApp;
import abdullah.elamien.worldwide.R;
import abdullah.elamien.worldwide.utils.Validation;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.registerHeaderImage)
    ImageView mHeaderImage;
    @BindView(R.id.registerEmailEditText)
    EditText mRegisterEmailEditText;
    @BindView(R.id.registerPasswordEditText)
    EditText mRegisterPasswordEditText;
    @BindView(R.id.passwordConfirmEditText)
    EditText mPasswordConfirmEditText;

    @BindString(R.string.invalid_email_input_msg)
    String mEmailErrorMsg;
    @BindString(R.string.invalid_password_matching_msg)
    String mPasswordMatchingErrorMsg;
    @BindString(R.string.invalid_password_signature_msg)
    String mInvalidPasswordErrorMsg;

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
        if (isValidEmailInput() && isPasswordMatched() && isPasswordEligible()) {
            // TODO: 10/17/2018 register user
        }
    }

    private boolean isValidEmailInput() {
        if (Validation.isEmailType(mRegisterEmailEditText.getText().toString().trim())) {
            return true;
        } else {
            showInputError(mRegisterEmailEditText, mEmailErrorMsg);
            return false;
        }
    }

    private boolean isPasswordMatched() {
        if (mPasswordConfirmEditText.getText().toString().equals(mRegisterPasswordEditText.getText().toString())) {
            return true;
        } else {
            showInputError(mPasswordConfirmEditText, mPasswordMatchingErrorMsg);
            return false;
        }
    }

    private boolean isPasswordEligible() {
        if (Validation.isValidPassword(mRegisterPasswordEditText.getText().toString())) {
            return true;
        } else {
            showInputError(mRegisterPasswordEditText, mInvalidPasswordErrorMsg);
            return false;
        }
    }


    private void showInputError(EditText editText, String msg) {
        editText.setError(msg);
    }

    @OnClick(R.id.loginButton)
    public void onLoginButtonClick() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
