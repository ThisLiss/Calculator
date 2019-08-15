package com.example.calculator;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends FragmentActivity implements calculator_fragment.OnFragmentInteractionListener {

    Fragment fragment = null;
    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = System.currentTimeMillis();

        Bundle bundle = new Bundle();
        bundle.putLong("time",time);

        fragment = new calculator_fragment();
        fragment.setArguments(bundle);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.calc,fragment);
        tx.commit();
    }

    @Override
    public void onFragmentInteraction() {
        MainActivity.this.finish();
    }
}
