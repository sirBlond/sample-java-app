package com.example.javaapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.javaapp.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;
import com.example.javaapp.AppCenterSDK;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCenterSDK.trackEvent("View Loaded", "First Fragment");

        binding.nextButton.setOnClickListener((View v) -> {
            AppCenterSDK.trackEvent("Button Pressed", "Next");
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        });

        binding.toastButton.setOnClickListener((View v) -> {
            AppCenterSDK.trackEvent("Button Pressed", "Toast");
            Snackbar.make(v, "Toast content", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });

        binding.crashButton.setOnClickListener((View v) -> {
            AppCenterSDK.trackEvent("Button Pressed", "Crash");
            throw new RuntimeException("Crash triggered!");
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}