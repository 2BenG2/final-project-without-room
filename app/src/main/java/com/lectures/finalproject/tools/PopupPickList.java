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
import com.lectures.finalproject.models.content.Content;
import com.lectures.finalproject.models.lists.MyList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PopupPickList extends Dialog {

    private Dialog d;
    private RadioGroup radioGroup ;
    private ContentType contentType;
    private Content content;
    private String listName;

    public PopupPickList(Activity a, ContentType contentType, Content content) {
        super(a);
        this.contentType = contentType;
        this.content = content;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ListManager.getInstance().getListAsArray().size() < 1){
            Toast.makeText(getContext(),"no lists",Toast.LENGTH_SHORT).show();
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pick_list_popup);
        radioGroup = findViewById(R.id.rg_lists);
        ImageView closeBtn = findViewById(R.id.imageView_pick_close);
        Button btnAdd = findViewById(R.id.btn_add);
        closeBtn.setOnClickListener(v->{this.dismiss();});
        btnAdd.setOnClickListener(v->{
            if(radioGroup.getCheckedRadioButtonId() == -1){
                Toast.makeText(getContext(),"select list",Toast.LENGTH_SHORT).show();
            }else {
                RadioButton radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                MyList myList = ListManager.getInstance().getListOfLists().get(radioButton.getText().toString());
                try {
                    assert myList != null;
                    ListManager.getInstance().updateList(myList.getName(),content);
                    ListManager.getInstance().listsUpdated();
                    Toast.makeText(getContext(),"Added to list",Toast.LENGTH_SHORT).show();
                    this.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });


        List<MyList> listArray = ListManager.getInstance().getListAsArray();
        for(MyList myList:listArray){
            if(contentType.equals(myList.getListType())){
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setText(myList.getName());
                radioGroup.addView(radioButton,0);
            }

        }




    }



}