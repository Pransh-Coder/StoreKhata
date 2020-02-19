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
            LoginFragment loginFragment = new LoginFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.on_boarding_fragment_container,loginFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }
}