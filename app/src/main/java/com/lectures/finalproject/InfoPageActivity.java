package com.lectures.finalproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.lectures.finalproject.models.content.Content;
import com.lectures.finalproject.models.content.ContentForView;
import com.lectures.finalproject.models.search.SearchContent;
import com.lectures.finalproject.models.series.*;
import com.lectures.finalproject.models.movies.*;
import com.lectures.finalproject.tools.StyleTools;
import com.lectures.finalproject.ui.info_page.InfoPageFragment;

import java.util.Objects;

public class InfoPageActivity extends AppCompatActivity {
    static String CONTENT = "CONTENT";
    private String title = "title";
    private ContentForView contentForView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Content content = (Content)getIntent().getSerializableExtra(CONTENT);
        setContentForView(content);
        setContentView(R.layout.activity_info_page);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, InfoPageFragment.newInstance(contentForView))
                    .commitNow();
        }

        ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(true);
        StyleTools.setActionBar(actionBar,getBaseContext());

    }
    @Override
    public void onBackPressed()
    {
       finish();
    }

    public void setContentForView(Content content){
        if(content instanceof Movie) {
            title = ((Movie) content).getTitle();
            contentForView = new ContentForView((Movie) content);
        }else if(content instanceof Series) {
            title = ((Series) content).getName();
            contentForView = new ContentForView((Series) content);
        }else if(content instanceof SearchContent) {
            SearchContent searchContent = (SearchContent)content;
            if(searchContent.getMediaType().equals("movie")){
                title = searchContent.getTitle();
            }else if(searchContent.getMediaType().equals("tv")){
                title = searchContent.getName();
            }
            contentForView = new ContentForView(searchContent);
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}