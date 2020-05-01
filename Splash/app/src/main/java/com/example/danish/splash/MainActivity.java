package com.example.danish.splash;

import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private int mCurrentPage;

    private SliderAdapter mSliderAdapter;

    private Button mNext;
    private Button mPrev;

    private TextView[] mDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("User", MODE_PRIVATE);
        String s = preferences.getString("isFirstTimeLaunch", "true");
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        if(s.equals("true")){
            (preferences.edit()).putString("isFirstTimeLaunch", "false").apply();
            thisActivity();
        }else if(s.equals("false")){
            Toast.makeText(this, "You already used this app", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }


    }

    private void thisActivity(){
        mSlideViewPager = (ViewPager)findViewById(R.id.viewPager);
        mDotLayout = (LinearLayout)findViewById(R.id.dotsLayout);

        mNext = (Button)findViewById(R.id.nextBtn);
        mPrev = (Button)findViewById(R.id.prevBtn);

        mSliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(mSliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sleep(3000);
                mSlideViewPager.setCurrentItem(mCurrentPage+1);
            }
        });

        mPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage-1);
            }
        });
    }

    private void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addDotsIndicator(int position){
        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for(int i=0; i<mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;
            if(position == 0){
                mNext.setEnabled(true);
                mPrev.setEnabled(false);
                mPrev.setVisibility(View.INVISIBLE);
                mNext.setText("Next");
                mPrev.setText("");
            }else if(position == (mDots.length-1)){
                mNext.setEnabled(true);
                mPrev.setEnabled(true);
                mPrev.setVisibility(View.VISIBLE);
                mNext.setText("Finish");
                mPrev.setText("Back");
            }else{
                mNext.setEnabled(true);
                mPrev.setEnabled(true);
                mPrev.setVisibility(View.VISIBLE);
                mNext.setText("Next");
                mPrev.setText("Back");
            }
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}
