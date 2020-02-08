package com.store.storekhata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.store.storekhata.Login.LoginFragment;
import com.store.storekhata.SharePrefrence.SharePrefs;
import com.store.storekhata.TrackDebit.TrackYourDebtFragment;
import com.store.storekhata.onBoardingStructure.OnBoardingActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final SharePrefs sharePrefs = new SharePrefs(this);
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                //1st from spalsh screen we must come to an Activity whic is OnBoardingActivity and then on reaching the deired activity you can replace the fragment according to sharedprefrences for session
                    Intent intent = new Intent(SplashScreen.this , OnBoardingActivity.class);
                    startActivity(intent);
                    finish();

            }
        }.start();
    }
}
