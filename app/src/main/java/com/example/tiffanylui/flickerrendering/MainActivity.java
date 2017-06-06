package com.example.tiffanylui.flickerrendering;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainView {

    private EditText mSearchBar;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<GalleryItem> mGalleryItems = new ArrayList<>();

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);

        initView();
    }

    private void initView() {
        mSearchBar = (EditText) findViewById(R.id.searchbar);
        mSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Changed", "TextChanged! " + s.toString());
                if (s.length() > 2) {
                    startSearch(s.toString());
                    Log.d("Searching", "SEARCHING! " + s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PhotoAdapter(mGalleryItems);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void startSearch(String text) {
        Toast.makeText(this, "Loading photos", Toast.LENGTH_SHORT).show();
        FlickerApi.getInstance().getResponse(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GetPhotosResponse>() {
                    @Override
                    public void call(GetPhotosResponse getPhotosResponse) {
                        Log.d("GetPhotoResponse", "Getting response");
                        mPresenter.showResponse(getPhotosResponse);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("GetPhotoResponse", "Error in getting response!" + throwable.getMessage());
                    }
                });

    }

    @Override
    public void showPhotos(List<GalleryItem> photos) {
        mGalleryItems.clear();
        mGalleryItems.addAll(photos);
        mAdapter.notifyDataSetChanged();
    }

}
