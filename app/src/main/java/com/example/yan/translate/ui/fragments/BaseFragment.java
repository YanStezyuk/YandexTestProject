package com.example.yan.translate.ui.fragments;

import android.support.v4.app.Fragment;

import com.example.yan.translate.TranslationApp;

// fragment to use api

public class BaseFragment extends Fragment {
    protected final TranslationApp getApp() {
        return (TranslationApp) getActivity().getApplication();
    }
}
