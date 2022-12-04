package com.lectures.finalproject.tools;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lectures.finalproject.enums.ContentType;
import com.lectures.finalproject.models.content.Content;
import com.lectures.finalproject.models.lists.MyList;
import com.lectures.finalproject.models.movies.Movie;

import java.util.ArrayList;
import java.util.List;

public class AuthCheckTool {

    public static boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    public static boolean isValidPassword(String password){
        return (!TextUtils.isEmpty(password) && password.length() > 3);
    }




}
