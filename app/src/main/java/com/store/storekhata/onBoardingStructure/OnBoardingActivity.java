package com.store.storekhata.onBoardingStructure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.store.storekhata.Login.LoginCallBack;
import com.store.storekhata.Login.LoginFragment;
import com.store.storekhata.R;
import com.store.storekhata.SharePrefrence.SharePrefs;
import com.store.storekhata.TrackDebit.TrackYourDebtFragment;

public class OnBoardingActivity extends AppCompatActivity implements LoginCallBack {

    SharePrefs sharePrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        sharePrefs = new SharePrefs(this);
        Toolbar toolbar = findViewById(R.id.onBoardingToolbar);
        setSupportActionBar(toolbar);

      /*  NavController navController = Navigation.findNavController(this, R.id.on_boarding_fragment_container);
        NavigationUI.setupActionBarWithNavController(this, navController);*/

        if(sharePrefs.isLoggedIn()){

            TrackYourDebtFragment fragment = new TrackYourDebtFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.on_boarding_fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else {
            LoginFragment fragment = new LoginFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.on_boarding_fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void AuthenticateUser() {

        TrackYourDebtFragment fragment = new TrackYourDebtFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.on_boarding_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

       /* LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.on_boarding_fragment_container,loginFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/
    }
}