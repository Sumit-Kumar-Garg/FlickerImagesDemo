package com.example.flickerimgdownload;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.flickerimgdownload.entity.PhotoModel;
import com.example.flickerimgdownload.imageSearch.PhotoListAdapter;
import com.example.flickerimgdownload.imageSearch.PhotoPresenter;
import com.example.flickerimgdownload.imageSearch.PresenterContract;
import com.example.flickerimgdownload.imageSearch.PhotoViewContract;
import com.example.flickerimgdownload.utils.RecyclerViewScrollListener;
import com.example.flickerimgdownload.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class SearchFlickerImagesActivity extends AppCompatActivity implements PhotoViewContract, View.OnClickListener {

    private PresenterContract presenter;

    private RecyclerView rvImages;
    private EditText searchTextView;
    private View progressBar;

    private PhotoListAdapter adapter;
    private GridLayoutManager gridLayoutManager;

    private List<PhotoModel> photoModels = new ArrayList<>();
    private RecyclerViewScrollListener scrollListener;

    private int columnCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_images);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.searchBtn).setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);

        presenter = new PhotoPresenter(this);

        searchTextView = findViewById(R.id.searchImages);

        adapter = new PhotoListAdapter(this, photoModels);

        rvImages = findViewById(R.id.rvImageList);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), columnCount);
        rvImages.setLayoutManager(gridLayoutManager);
        rvImages.setItemAnimator(new DefaultItemAnimator());
        rvImages.setAdapter(adapter);

        scrollListener = new RecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.loadMoreImages(searchTextView.getText().toString(), page + 1);
            }
        };
        // Adds the scroll listener to RecyclerView
        rvImages.addOnScrollListener(scrollListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grid_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.col_2:
                columnCount = 2;
                gridLayoutManager.setSpanCount(columnCount);
                return true;
            case R.id.col_3:
                columnCount = 3;
                gridLayoutManager.setSpanCount(columnCount);
                return true;
            case R.id.col_4:
                columnCount = 4;
                gridLayoutManager.setSpanCount(columnCount);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showImages(List<PhotoModel> photoModelList) {
        photoModels.clear();
        photoModels.addAll(photoModelList);
        rvImages.getAdapter().notifyDataSetChanged();
        scrollListener.resetState();
    }

    @Override
    public void showError() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unbind();
    }

    @Override
    public void showMore(List<PhotoModel> photoModelList) {
        photoModels.addAll(photoModelList);
        rvImages.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.searchBtn:
                presenter.searchImagesResult(searchTextView.getText().toString());
                View view = this.getCurrentFocus();
                if (view != null) {
                    Util.hideKeyboard(this,view.getWindowToken());
                }
                break;
        }
    }
}
