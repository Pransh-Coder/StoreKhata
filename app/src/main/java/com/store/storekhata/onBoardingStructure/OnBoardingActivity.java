package com.store.storekhata.onBoardingStructure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.store.storekhata.Login.LoginCallBack;
import com.store.storekhata.Login.LoginFragment;
import com.store.storekhata.Login.SignupCallBack;
import com.store.storekhata.R;
import com.store.storekhata.SharePrefrence.SharePrefs;
import com.store.storekhata.SharePrefrence.UserSharedPrefs;
import com.store.storekhata.TrackDebit.DebitDetailFragment;
import com.store.storekhata.TrackDebit.TrackYourDebtFragment;

public class OnBoardingActivity extends AppCompatActivity implements LoginCallBack, SignupCallBack {

    SharePrefs sharePrefs;
    UserSharedPrefs userSharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        sharePrefs = new SharePrefs(this);
        userSharedPrefs = new UserSharedPrefs(this);
        Toolbar toolbar = findViewById(R.id.onBoardingToolbar);
        setSupportActionBar(toolbar);

      /*  NavController navController = Navigation.findNavController(this, R.id.on_boarding_fragment_container);
        NavigationUI.setupActionBarWithNavController(this, navController);*/

        if(sharePrefs.isLoggedIn()){            //if true move to TrackYourDebtFragment (ADMIN_Login)

            TrackYourDebtFragment fragment = new TrackYourDebtFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.on_boarding_fragment_container, fragment);
            transaction.commit();
        }
        //becoz of else if only when we do user login and then close the app the admin signup was automaticaaly done
        else if(userSharedPrefs.isLoggedInUser()){
            DebitDetailFragment fragment = new DebitDetailFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.on_boarding_fragment_container, fragment);
            transaction.commit();
        }
        else {
            LoginFragment fragment = new LoginFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.on_boarding_fragment_container, fragment);
            transaction.commit();
        }

        /*if(userSharedPrefs.isLoggedInUser()){
            DebitDetailFragment fragment = new DebitDetailFragment();
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
        }*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void AuthenticateAdmin() {   // This is for making transition

        TrackYourDebtFragment fragment = new TrackYourDebtFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.on_boarding_fragment_container, fragment);
        transaction.commit();

    }


    @Override
    public void Authenticateuser(String UID) {

        /*DebitDetailFragment fragment = new DebitDetailFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.on_boarding_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        Bundle bundle = new Bundle();
        bundle.putString("uid" ,UID*//*userSharedPrefs.getUID_forUserLogin()*//*);
        fragment.setArguments(bundle);*/
    }

    @Override
    public void AuthUser() {
        DebitDetailFragment fragment = new DebitDetailFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.on_boarding_fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {

            //Toast.makeText(this, "clicked!", Toast.LENGTH_SHORT).show();
            sharePrefs.removeAllSP();
            userSharedPrefs.removeAllSharedPref();
            LoginFragment loginFragment = new LoginFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.on_boarding_fragment_container,loginFragment);
            //fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void AuthenticateSignup() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.on_boarding_fragment_container,loginFragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}