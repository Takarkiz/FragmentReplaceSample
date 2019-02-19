package com.test.sawada.fragmentpracti;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements AddFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

//    public void add(View view) {
//        createNewFragment();
//    }

    public void delete(View view) {

        fragmentManager.popBackStack();
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
    }
}
