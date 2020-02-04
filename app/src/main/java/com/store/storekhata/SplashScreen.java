package com.store.storekhata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.store.storekhata.onBoardingStructure.OnBoardingActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SplashScreen.this , OnBoardingActivity.class);
                startActivity(intent);
                finish();
                /*LoginFragment fragment = new LoginFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.on_boarding_fragment_container, fragment);
                transaction.commit();*/
            }
        }.start();
    }
}
