package com.test.sawada.fragmentpracti;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements AddFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction;
    BottomNavigationView bottomNavigationView;
    final String[] category = {"home", "dashboard", "notification"};
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitialFragment();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                AddFragment addFragment;

                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        index = 0;
                        //すでにホームタグのついたフラグメントが存在する場合
                        if (fragmentManager.findFragmentByTag("home") != null) {
                            addFragment = (AddFragment) fragmentManager.findFragmentByTag("home");

                        } else {
                            addFragment = AddFragment.newInstance("https://www.google.com/");
                        }
                        setFragment(addFragment, "home");
                        break;
                    case R.id.navigation_dashboard:
                        index = 1;
                        if (fragmentManager.findFragmentByTag("dashboard") != null) {
                            addFragment = (AddFragment) fragmentManager.findFragmentByTag("dashboard");
                        } else {
                            addFragment = AddFragment.newInstance("https://qiita.com/iKimishima/items/d44bb9cc2a1d04548fdd");
                        }
                        setFragment(addFragment, "dashboard");
                        break;
                    case R.id.navigation_notifications:
                        index = 2;
                        if (fragmentManager.findFragmentByTag("notification") != null) {
                            addFragment = (AddFragment) fragmentManager.findFragmentByTag("notification");
                        } else {
                            addFragment = AddFragment.newInstance("https://material.io/develop/android/components/bottom-navigation-view/");
                        }
                        setFragment(addFragment, "notification");
                        break;

                    default:
                        break;
                }
                return false;
            }
        });

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                AddFragment addFragment;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        addFragment = AddFragment.newInstance("https://www.google.com/");
                        setFragment(addFragment, category[0]);
                        break;
                    case R.id.navigation_dashboard:
                        addFragment = AddFragment.newInstance("https://qiita.com/iKimishima/items/d44bb9cc2a1d04548fdd");
                        setFragment(addFragment, category[1]);
                        break;
                    case R.id.navigation_notifications:
                        addFragment = AddFragment.newInstance("https://material.io/develop/android/components/bottom-navigation-view/");
                        setFragment(addFragment, category[2]);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void setFragment(AddFragment fragment, String tag) {
        if (fragment != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, fragment, tag);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private void setInitialFragment() {
        //トランザクションの開始
        transaction = fragmentManager.beginTransaction();
        //トランザクションにfragmentを追加
        transaction.add(R.id.container, AddFragment.newInstance("https://www.google.com/"), "home");
        // バックスタックに追加
        transaction.addToBackStack(null);
        //出力
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void addNewFragment(String url) {
        //トランザクションの開始
        transaction = fragmentManager.beginTransaction();
        //トランザクションにfragmentを追加
        transaction.replace(R.id.container, AddFragment.newInstance(url), category[index]);
        // バックスタックに追加
        transaction.addToBackStack(null);
        //出力
        transaction.commit();

        fragmentManager.executePendingTransactions();
    }

    @Override
    public void onBackPressed() {
        //バックスタックに保存されていれば戻る
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            return;
        }
        super.onBackPressed();
    }
}
