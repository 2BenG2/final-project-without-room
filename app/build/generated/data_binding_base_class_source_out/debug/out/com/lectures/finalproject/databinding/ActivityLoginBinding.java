// Generated by view binder compiler. Do not edit!
package com.lectures.finalproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.lectures.finalproject.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnLogin;

  @NonNull
  public final EditText etLoginEmail;

  @NonNull
  public final EditText etLoginPassword;

  @NonNull
  public final Guideline guideline21;

  @NonNull
  public final Guideline guideline25;

  @NonNull
  public final Guideline guideline26;

  @NonNull
  public final Guideline guideline27;

  @NonNull
  public final Guideline guideline28;

  @NonNull
  public final Guideline guideline29;

  @NonNull
  public final Guideline guideline30;

  @NonNull
  public final ProgressBar loginProgressBar;

  @NonNull
  public final TextView tvLoginRegister;

  @NonNull
  public final TextView tvLoginRegisterClick;

  @NonNull
  public final TextView tvLoginTitle;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnLogin,
      @NonNull EditText etLoginEmail, @NonNull EditText etLoginPassword,
      @NonNull Guideline guideline21, @NonNull Guideline guideline25,
      @NonNull Guideline guideline26, @NonNull Guideline guideline27,
      @NonNull Guideline guideline28, @NonNull Guideline guideline29,
      @NonNull Guideline guideline30, @NonNull ProgressBar loginProgressBar,
      @NonNull TextView tvLoginRegister, @NonNull TextView tvLoginRegisterClick,
      @NonNull TextView tvLoginTitle) {
    this.rootView = rootView;
    this.btnLogin = btnLogin;
    this.etLoginEmail = etLoginEmail;
    this.etLoginPassword = etLoginPassword;
    this.guideline21 = guideline21;
    this.guideline25 = guideline25;
    this.guideline26 = guideline26;
    this.guideline27 = guideline27;
    this.guideline28 = guideline28;
    this.guideline29 = guideline29;
    this.guideline30 = guideline30;
    this.loginProgressBar = loginProgressBar;
    this.tvLoginRegister = tvLoginRegister;
    this.tvLoginRegisterClick = tvLoginRegisterClick;
    this.tvLoginTitle = tvLoginTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_login;
      Button btnLogin = ViewBindings.findChildViewById(rootView, id);
      if (btnLogin == null) {
        break missingId;
      }

      id = R.id.et_login_email;
      EditText etLoginEmail = ViewBindings.findChildViewById(rootView, id);
      if (etLoginEmail == null) {
        break missingId;
      }

      id = R.id.et_login_password;
      EditText etLoginPassword = ViewBindings.findChildViewById(rootView, id);
      if (etLoginPassword == null) {
        break missingId;
      }

      id = R.id.guideline21;
      Guideline guideline21 = ViewBindings.findChildViewById(rootView, id);
      if (guideline21 == null) {
        break missingId;
      }

      id = R.id.guideline25;
      Guideline guideline25 = ViewBindings.findChildViewById(rootView, id);
      if (guideline25 == null) {
        break missingId;
      }

      id = R.id.guideline26;
      Guideline guideline26 = ViewBindings.findChildViewById(rootView, id);
      if (guideline26 == null) {
        break missingId;
      }

      id = R.id.guideline27;
      Guideline guideline27 = ViewBindings.findChildViewById(rootView, id);
      if (guideline27 == null) {
        break missingId;
      }

      id = R.id.guideline28;
      Guideline guideline28 = ViewBindings.findChildViewById(rootView, id);
      if (guideline28 == null) {
        break missingId;
      }

      id = R.id.guideline29;
      Guideline guideline29 = ViewBindings.findChildViewById(rootView, id);
      if (guideline29 == null) {
        break missingId;
      }

      id = R.id.guideline30;
      Guideline guideline30 = ViewBindings.findChildViewById(rootView, id);
      if (guideline30 == null) {
        break missingId;
      }

      id = R.id.login_progressBar;
      ProgressBar loginProgressBar = ViewBindings.findChildViewById(rootView, id);
      if (loginProgressBar == null) {
        break missingId;
      }

      id = R.id.tv_login_register;
      TextView tvLoginRegister = ViewBindings.findChildViewById(rootView, id);
      if (tvLoginRegister == null) {
        break missingId;
      }

      id = R.id.tv_login_register_click;
      TextView tvLoginRegisterClick = ViewBindings.findChildViewById(rootView, id);
      if (tvLoginRegisterClick == null) {
        break missingId;
      }

      id = R.id.tv_login_title;
      TextView tvLoginTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvLoginTitle == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, btnLogin, etLoginEmail,
          etLoginPassword, guideline21, guideline25, guideline26, guideline27, guideline28,
          guideline29, guideline30, loginProgressBar, tvLoginRegister, tvLoginRegisterClick,
          tvLoginTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}