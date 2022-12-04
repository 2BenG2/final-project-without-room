package com.lectures.finalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.FirebaseAuth;
import com.lectures.finalproject.controllers.login.LoginActivity;
import com.lectures.finalproject.databinding.ActivityMainBinding;
import com.lectures.finalproject.tools.RecycleViewService;
import com.lectures.finalproject.tools.StyleTools;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        StyleTools.setActionBar(Objects.requireNonNull(getSupportActionBar()),getBaseContext(),navView);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_browse_movies, R.id.navigation_browse_series, R.id.navigation_my_lists,R.id.navigation_search)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        findViewById(R.id.btn_sign_out).setOnClickListener(v->signOut());

        backToLogin();
        FirebaseAuth.getInstance().addAuthStateListener(firebaseAuth -> {
            backToLogin();
        });
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            binding.tvUserDetails.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }


    }

    private boolean isMember(){
        return FirebaseAuth.getInstance().getCurrentUser() == null;
    }
    private void backToLogin(){
        if(isMember()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
    public void signOut(){
        FirebaseAuth.getInstance().signOut();
        backToLogin();
    }


}