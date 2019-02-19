package com.test.sawada.fragmentpracti;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements AddFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Toast.makeText(MainActivity.this, "ホーム", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_dashboard:
                        Toast.makeText(MainActivity.this, "ダッシュボード", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_notifications:
                        Toast.makeText(MainActivity.this, "通知", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }

                return false;
            }
        });

        //トランザクションの開始
        transaction = fragmentManager.beginTransaction();
        //トランザクションにfragmentを追加
        transaction.add(R.id.container, AddFragment.newInstance("https://www.google.com/"));
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

    public void createNewFragment(String url) {
        //トランザクションの開始
        transaction = fragmentManager.beginTransaction();
        //トランザクションにfragmentを追加
        transaction.replace(R.id.container, AddFragment.newInstance(url));
        // バックスタックに追加
        transaction.addToBackStack(null);
        //出力
        transaction.commit();

        fragmentManager.executePendingTransactions();

        Log.d("TAG:DebugLog", String.valueOf(fragmentManager.getBackStackEntryCount()));
        Log.d("TAG:DebugLog", url);
    }

    @Override
    public void onBackPressed() {
        //バックスタックに保存されていれば戻る
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            Log.d("TAG:DebugLog", String.valueOf(fragmentManager.getBackStackEntryCount()));
            return;
        }
        super.onBackPressed();
    }
}
