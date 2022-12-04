package com.lectures.finalproject.tools;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lectures.finalproject.R;
import com.lectures.finalproject.controllers.lists.ListManager;
import com.lectures.finalproject.enums.ContentType;
import com.lectures.finalproject.models.lists.MyList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PopupNewList extends Dialog {
    public Activity activity;
    public Dialog d;
    public Button btnAddToListIn;
    public EditText listName ;
    public RadioGroup radioGroup ;
    public PopupNewList(Activity a) {
        super(a);
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_list_popup);
        btnAddToListIn = findViewById(R.id.btn_add_new_list_in);
        listName = findViewById(R.id.et_list_name);
        radioGroup = findViewById(R.id.rg_list_type);
        ImageView closeBtn = findViewById(R.id.imageView_close);
        closeBtn.setOnClickListener(v->{this.dismiss();});




        btnAddToListIn.setOnClickListener(v->{
            if(listName.getText().toString().isEmpty()){
                Toast.makeText(getContext(),"fill name",Toast.LENGTH_SHORT).show();
            }else if(ListManager.getInstance().isNameExist(listName.getText().toString())){
                Toast.makeText(getContext(),"name taken",Toast.LENGTH_SHORT).show();
            }else {
                if(radioGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getContext(),"fill list type",Toast.LENGTH_SHORT).show();
                }else{
                    RadioButton selectedRadioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                    ContentType contentType;
                    if(selectedRadioButton.getText().toString().equals(getContext().getString(R.string.movies))){
                        contentType = ContentType.MOVIE;
                    }else{
                        contentType = ContentType.SERIES;
                    }

                    Date date = Calendar.getInstance().getTime();
                    @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                    String strDate = dateFormat.format(date);


                    ListManager.getInstance().addNewList(new MyList(listName.getText().toString(), contentType, strDate));
                    this.dismiss();
                }
            }

        });


    }



}
