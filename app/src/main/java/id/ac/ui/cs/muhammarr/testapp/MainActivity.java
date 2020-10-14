package id.ac.ui.cs.muhammarr.testapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

// Source: https://developer.android.com/training/basics/fragments/communicating.html
public class MainActivity extends AppCompatActivity
        implements ItemFragment.OnItemFragmentClickListener {

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof ItemFragment) {
            ItemFragment itemFragment = (ItemFragment) fragment;
            itemFragment.setOnItemFragmentClickListener(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Create instance of your Fragment
        Fragment fragment = new ItemFragment();
        //Add Fragment instance to your Activity
        fragmentTransaction.add(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemFragmentClicked(Bundle bundle) {
        DetailFragment detail = new DetailFragment();
        detail.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, detail);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}