package com.example.yan.translate.ui.activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.view.MenuItem;

import com.example.yan.translate.R;
import com.example.yan.translate.db.HistoryManager;
import com.example.yan.translate.ui.fragments.HistoryFragment;
import com.example.yan.translate.ui.fragments.TranslationFragment;
import com.example.yan.translate.ui.fragments.SettingsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

// main activity

public class MainActivity extends BaseActivity {

    private static final String TAG_FRAGMENT_TRANSLATION = "tag_fragment_translation";
    private static final String TAG_FRAGMENT_HISTORY = "tag_fragment_history";
    private static final String TAG_FRAGMENT_SETTINGS = "tag_fragment_settings";

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.new_activity_main);
        ButterKnife.bind(this);

        bottomNav.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.action_translate:
                        replaceFragment(R.id.fragment_container, new TranslationFragment(), TAG_FRAGMENT_TRANSLATION);
                        return true;

                    case R.id.action_settings:
                        replaceFragment(R.id.fragment_container, new SettingsFragment(), TAG_FRAGMENT_SETTINGS);
                        return true;

                    case R.id.action_favorites:
                        replaceFragment(R.id.fragment_container, new HistoryFragment(), TAG_FRAGMENT_HISTORY);
                        return true;

                }
                return false;
            }

        });

        bottomNav.setSelectedItemId(R.id.action_translate);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onStop() {
        HistoryManager.clearInputTable();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        HistoryManager.clearInputTable();
        super.onDestroy();
    }
}
