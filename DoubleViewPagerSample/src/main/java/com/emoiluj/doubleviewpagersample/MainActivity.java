package com.emoiluj.doubleviewpagersample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.Window;

import com.emoiluj.doubleviewpager.DoubleViewPager;
import com.emoiluj.doubleviewpager.DoubleViewPagerAdapter;

import com.emoiluj.doubleviewpager.HorizontalViewPager;
import java.util.ArrayList;


public class MainActivity extends Activity{

    private DoubleViewPager viewpager;
    private int horizontalChilds;
    private int verticalChilds;
    private DoubleViewPagerAdapter doubleViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        loadDataFromSplash();
        loadUI();
    }

    private void loadDataFromSplash(){
        horizontalChilds = getIntent().getExtras().getInt("HORIZONTAL");
        verticalChilds = getIntent().getExtras().getInt("VERTICAL");
    }

    private void loadUI() {

        ArrayList<PagerAdapter> verticalAdapters = new ArrayList<PagerAdapter>();
        generateVerticalAdapters(verticalAdapters);

        viewpager = (DoubleViewPager) findViewById(R.id.pager);
        doubleViewPagerAdapter =
            new DoubleViewPagerAdapter(getApplicationContext(), verticalAdapters);
        viewpager.setAdapter(doubleViewPagerAdapter);

        viewpager.setOnSwipeMoveListener(onSwipeMoveListener);
    }

    private void generateVerticalAdapters(ArrayList<PagerAdapter> verticalAdapters) {
        for (int i=0; i<horizontalChilds; i++){
            verticalAdapters.add(new VerticalPagerAdapter(this, i, verticalChilds));
        }
    }

    private DoubleViewPager.OnSwipeMoveListener onSwipeMoveListener = new DoubleViewPager.OnSwipeMoveListener() {
        @Override public void onSwipe() {
            Log.e("SWIPE", "Swipe");

            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    int currentItem = viewpager.getCurrentItem();
                    int currentPageSelectedWhenScrolled =
                        doubleViewPagerAdapter.getVerticalViewPager(currentItem)
                            .getCurrentPageSelectedWhenScrolled();

                    Log.e("SWIPE", String.valueOf(currentPageSelectedWhenScrolled));
                }
            }, 1000);


        }
    };
}