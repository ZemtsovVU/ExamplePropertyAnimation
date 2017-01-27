package com.example.viktor.examplepropertyanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    private ViewGroup content;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        content = (ViewGroup) findViewById(R.id.content_main);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                float displayWidth = displayMetrics.widthPixels;
                float dislpayHeight = displayMetrics.heightPixels;

                float viewGroupX = content.getX();
                float viewGroupY = content.getY();
                float viewGroupWidth = content.getWidth();
                float viewGroupHeight = content.getHeight();

                float fabX = fab.getX();
                float fabY = fab.getY();
                float fabWidth = fab.getWidth();
                float fabHeight = fab.getHeight();

                float[] fabInDisplayOrigin = PercentPositionUtil
                        .getPercentFromPositionInsideDisplay(
                                MainActivity.this, fab, PercentPositionUtil.Pivot.LEFT_TOP);
                float[] fabInDisplayCenter = PercentPositionUtil
                        .getPercentFromPositionInsideDisplay(
                                MainActivity.this, fab, PercentPositionUtil.Pivot.CENTER);

                float[] fabInViewGroupOrigin = PercentPositionUtil
                        .getPercentFromPositionInsideViewGroup(
                                content, fab, PercentPositionUtil.Pivot.LEFT_TOP);
                float[] fabInViewGroupCenter = PercentPositionUtil
                        .getPercentFromPositionInsideViewGroup(
                                content, fab, PercentPositionUtil.Pivot.CENTER);

                float[] newPosition = PercentPositionUtil.getPositionFromPercentInsideViewGroup(
                        content, fab, PercentPositionUtil.Pivot.LEFT_TOP, new float[]{0f, 0f});
                int duration = getResources().getInteger(android.R.integer.config_mediumAnimTime);

                ObjectAnimator xAnimator = ObjectAnimator
                        .ofFloat(fab, "x", newPosition[0]).setDuration(duration);
                ObjectAnimator yAnimator = ObjectAnimator
                        .ofFloat(fab, "y", newPosition[1]).setDuration(duration);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(xAnimator).with(yAnimator);
                animatorSet.start();
            }
        });
    }
}
