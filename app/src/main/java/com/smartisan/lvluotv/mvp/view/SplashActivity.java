package com.smartisan.lvluotv.mvp.view;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.smartisan.lvluotv.R;

import yanzhikai.textpath.AsyncTextPathView;
import yanzhikai.textpath.PathAnimatorListener;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AsyncTextPathView asyncTextPathView = findViewById(R.id.view_async);
        asyncTextPathView.setPathPainter((x, y, paintPath) -> paintPath.addCircle(x, y, 6, Path.Direction.CCW));
        asyncTextPathView.startAnimation(0, 1);
        asyncTextPathView.setAnimatorListener(new PathAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                asyncTextPathView.showFillColorText();
                Handler handler = new Handler();
                handler.postDelayed(() ->
                        startActivity(new Intent(SplashActivity.this, MainActivity.class)), 500);
            }
        });
    }
}
