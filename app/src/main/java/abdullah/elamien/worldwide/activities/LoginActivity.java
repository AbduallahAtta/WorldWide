package abdullah.elamien.worldwide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import abdullah.elamien.worldwide.GlideApp;
import abdullah.elamien.worldwide.R;
import abdullah.elamien.worldwide.utils.Validation;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.headerImageView)
    ImageView mHeaderImage;
    @BindView(R.id.loginEmailEditText)
    EditText mLoginEmailEditText;
    @BindView(R.id.loginPasswordEditText)
    EditText mLoginPasswordEditText;
    @BindView(R.id.loadingIndicator)
    SpinKitView mLoadingIndicator;
    @BindView(R.id.loginInputsLayoutGroup)
    Group mInputsLayoutGroup;

    @BindString(R.string.invalid_email_input_msg)
    String mEmailErrorMsg;
    @BindString(R.string.invalid_password_signature_msg)
    String mInvalidPasswordErrorMsg;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loadHeaderImage();
        initFirebaseAuth();
    }

    private void initFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance();
    }

    private void setFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void loadHeaderImage() {
        GlideApp.with(this)
                .load(getString(R.string.header_image_url))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mHeaderImage);
    }

    @OnClick(R.id.loginButton)
    public void onLoginButtonClick() {
        if (isEmailValid() && isPasswordEligible()) {
            loginUser();
        }
    }

    private void loginUser() {
        showLoadingIndicator();
        mAuth.signInWithEmailAndPassword(mLoginEmailEditText.getText().toString().trim(),
                mLoginPasswordEditText.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            hideLoadingIndicator();
                            // TODO: 10/17/2018 show the countries list
                        } else {
                            hideLoadingIndicator();
                            showErrorOccurred();
                        }
                    }
                });
    }

    private void showLoadingIndicator() {
        mInputsLayoutGroup.setVisibility(View.GONE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    private void hideLoadingIndicator() {
        mLoadingIndicator.setVisibility(View.GONE);
        mInputsLayoutGroup.setVisibility(View.VISIBLE);
    }

    private void showErrorOccurred() {
        Toast.makeText(this, R.string.registration_error_msg, Toast.LENGTH_SHORT).show();
    }

    private boolean isEmailValid() {
        if (Validation.isEmailType(mLoginEmailEditText.getText().toString().trim())) {
            return true;
        } else {
            showInputError(mLoginEmailEditText, mEmailErrorMsg);
            return false;
        }
    }

    private boolean isPasswordEligible() {
        if (Validation.isValidPassword(mLoginPasswordEditText.getText().toString())) {
            return true;
        } else {
            showInputError(mLoginPasswordEditText, mInvalidPasswordErrorMsg);
            return false;
        }
    }

    private void showInputError(EditText editText, String msg) {
        editText.setError(msg);
    }

    @OnClick(R.id.registerButton)
    public void onRegisterButtonClick() {
        launchRegisterActivity();
    }

    private void launchRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
