package com.smartisan.mvp_dagger2.mvp.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smartisan.mvp_dagger2.App;
import com.smartisan.mvp_dagger2.R;
import com.smartisan.mvp_dagger2.component.DaggerMainActivityComponent;
import com.smartisan.mvp_dagger2.finals.Constant;
import com.smartisan.mvp_dagger2.module.NewsInfoModule;
import com.smartisan.mvp_dagger2.mvp.contract.NewsInfoContract;
import com.smartisan.mvp_dagger2.mvp.presenter.NewsInfoPresenter;
import com.smartisan.netlibrary.entity.NewsInfo;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity implements NewsInfoContract.View, NavigationView.OnNavigationItemSelectedListener{

    @Inject
    NewsInfoPresenter mPresenter;
    LifecycleProvider<Lifecycle.Event> lifecycleProvider = AndroidLifecycle.createLifecycleProvider(this);
    private Dialog mDialog;
    private TextView mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainActivityComponent.builder().newsInfoModule(new NewsInfoModule(this))
                .netTaskComponent(App.getNetTaskComponent()).build().inject(this);
        mDialog = new ProgressDialog(this);
        mDialog.setTitle(R.string.dialog_get_info);
        mContent = findViewById(R.id.tv_main_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ImageView ic_head_portrait = findViewById(R.id.ic_head_portrait);
        Glide.with(this).load(Constant.ADDRESS_HEADER_PORTRAIT).into(ic_head_portrait);
    }

    @Override
    public void setNewsInfo(NewsInfo newsInfo) {
        if (newsInfo != null && newsInfo.getResult() != null && newsInfo.getResult().getData() != null) {
            mContent.setText(newsInfo.getResult().getData().get(0).getTitle());
        }
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mDialog.isShowing())
            mDialog.dismiss();
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.toast_net_tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(NewsInfoPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            mPresenter.getNewsInfo(lifecycleProvider,Constant.DEFAULT_TYPE);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
