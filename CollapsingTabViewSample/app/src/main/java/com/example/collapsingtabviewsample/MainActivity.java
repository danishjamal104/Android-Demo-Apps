package com.example.collapsingtabviewsample;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        final CardView[] cardArray = new CardView[3];
        cardArray[0] = (CardView)findViewById(R.id.cv_left);
        cardArray[1] = (CardView)findViewById(R.id.cv_middle);
        cardArray[2] = (CardView)findViewById(R.id.cv_right);

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        toast(""+mAppBarLayout.getTotalScrollRange());


        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                toast(""+verticalOffset);

                for(CardView cv: cardArray){
                    cv.setAlpha(1-((float)Math.abs(verticalOffset)/875));

                }

                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    toast("Collapsed");
                } else if (isShow) {
                    isShow = false;
                    toast("Expand");
                }
            }
        });
    }

    private void toast(String s){
        Toast.makeText(this, s,Toast.LENGTH_SHORT).show();
    }


}
