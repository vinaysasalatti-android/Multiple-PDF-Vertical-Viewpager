package com.sasalatti.vinay.pdf_viewpager.pdf;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.sasalatti.vinay.pdf_viewpager.R;
import com.sasalatti.vinay.pdf_viewpager.customui.VerticalViewPager;


public class PdfpaperActivity extends AppCompatActivity {

    ProgressBar progressBar;
    VerticalViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_view);

        viewPager = (VerticalViewPager) findViewById(R.id.vertical_viewpager);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);

        initViewPager();
    }


    private void initViewPager() {

//        https://github.com/geftimov/android-viewpager-transformers
//        viewPager.setPageTransformer(true, new RotateUpTransformer());
        viewPager.setAdapter(new PdfpaperFragmentAdapter.Holder(getSupportFragmentManager())
                .add(PdfpaperItemFragment.newInstance("page1.pdf", this))
                .add(PdfpaperItemFragment.newInstance("page2.pdf", this))
                .add(PdfpaperItemFragment.newInstance("page3.pdf", this))
                .add(PdfpaperItemFragment.newInstance("page4.pdf", this))
                .add(PdfpaperItemFragment.newInstance("page5.pdf", this))
                .add(PdfpaperItemFragment.newInstance("page3-1.pdf", this))
                .add(PdfpaperItemFragment.newInstance("page4-1.pdf", this))
                .set());
        //If you setting other scroll mode, the scrolled fade is shown from either side of display.
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                // dummy progress to the user till the page rendering
                progressBar.setVisibility(View.VISIBLE);

                //disabling the screen touch until pdf load
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        progressBar.setVisibility(View.GONE);

                        //enabling the screen touch on pdf load
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }

                }, 3000);

            }

            @Override
            public void onPageSelected(int position) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
