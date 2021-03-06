package com.squapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.AccessToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;

import com.squapp.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    FirebaseAuth.AuthStateListener authListener;
    FirebaseAuth firebaseAuth;
    LoginButton facebookLoginButton;
    CallbackManager callbackManager;
    AccessTokenTracker tockenTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       /*
        FacebookSdk.sdkInitialize(getApplicationContext());
        firebaseAuth = FirebaseAuth.getInstance();
        facebookLoginButton = (LoginButton)findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        checkFacebookLoginStatus();
        facebookLoginButton.setReadPermissions("email", "public_profile");
        getFireBaseAuth();
        facebookLoginConnection();*/
        Toast toast = Toast.makeText(getBaseContext(),R.string.login_successful,Toast.LENGTH_LONG);
        toast.show();
        goToMapsActivity();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //checkFacebookLoginStatus();
        Toast toast = Toast.makeText(getBaseContext(),R.string.login_successful,Toast.LENGTH_LONG);
        toast.show();
        goToMapsActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
       // checkFacebookLoginStatus();
        Toast toast = Toast.makeText(getBaseContext(),R.string.login_successful,Toast.LENGTH_LONG);
        toast.show();
        goToMapsActivity();
    }

    void facebookLoginConnection(){
        Log.d("Login Act","Login");

        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>(){
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                Toast toast = Toast.makeText(getBaseContext(),R.string.login_successful,Toast.LENGTH_SHORT);
                toast.show();
                goToMapsActivity();
                Log.d("Login Act","Login");
            }

            @Override
            public void onCancel() {
                Toast toast = Toast.makeText(getBaseContext(),R.string.login_canceled,Toast.LENGTH_SHORT);
                toast.show();
                Log.d("Login Act","Cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Toast toast = Toast.makeText(getBaseContext(),R.string.login_failed,Toast.LENGTH_SHORT);
                toast.show();
                Log.d("Login Act","Error");
            }
        });
    }

    void goToMapsActivity(){
        Intent intent = new Intent(getBaseContext(), MapsActivity.class);
        startActivity(intent);
    }
    void checkFacebookLoginStatus(){
        tockenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                checkTokenStatus(currentAccessToken);
            }
        };
    }
    void checkTokenStatus (AccessToken currentToken){
        if(currentToken!=null){
            goToMapsActivity();
        }
    }
    void getFireBaseAuth(){
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("AutAct", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("AutAct", "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("This", "handleFacebookAccessToken:" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                             FirebaseUser user = firebaseAuth.getCurrentUser();
                            // Toast.makeText(getBaseContext(), "Authentication suce.",
                               //     Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                          //  Toast.makeText(getBaseContext(), "Authentication failed.",
                              //      Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
       // firebaseAuth.addAuthStateListener(authListener);
        Toast toast = Toast.makeText(getBaseContext(),R.string.login_successful,Toast.LENGTH_SHORT);
        toast.show();
        goToMapsActivity();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            firebaseAuth.removeAuthStateListener(authListener);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
