package com.example.javaapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.javaapp.databinding.FragmentSecondBinding;
import com.example.javaapp.AppCenterSDK;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        try {
            super.onViewCreated(view, savedInstanceState);
            AppCenterSDK.trackEvent("View Loaded", "First Fragment");

            binding.buttonSecond.setOnClickListener((View v) -> {
                AppCenterSDK.trackEvent("Button Pressed", "Previous");
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            });

            binding.buttonUpdateCheck.setOnClickListener((View v) -> {
                AppCenterSDK.checkUpdate();
            });
        }
        catch(Exception exception) {
            AppCenterSDK.trackCrash(exception);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}