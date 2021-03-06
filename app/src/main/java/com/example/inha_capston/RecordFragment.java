package com.example.inha_capston;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends Fragment implements View.OnClickListener {

    // permission variable
    static final String TAG = "MainFragment";
    private static final int WRITE_REQUEST = 112, READ_REQUEST = 113;

    // context
    private Context mContext;       // getContext()
    private Activity mActivity;     // getActivity()

    // fragment transition
    private NavController navController;

    public RecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        Button loadBtn = view.findViewById(R.id.load_btn);
        Button recordBtn = view.findViewById(R.id.record_btn);
        Button OptionsBtn = view.findViewById(R.id.setting_btn);

        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (!hasPermissions(mContext, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, WRITE_REQUEST );
            } else {
                //do here
            }
        } else {
            //do here
        }



        recordBtn.setOnClickListener(this);
        loadBtn.setOnClickListener(this);
        OptionsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.load_btn:
                if (Build.VERSION.SDK_INT >= 23) {
                    String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    if (!hasPermissions(mContext, PERMISSIONS)) {
                        ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, READ_REQUEST );
                    } else {
                        //do here
                    }
                } else {
                    //do here
                }


                navController.navigate(R.id.action_recordFragment_to_waitFragment);
                break;
            case R.id.record_btn:
                // transition fragment with anim, show list of audio record files
                navController.navigate(R.id.action_recordFragment_to_audioListFragment);
                break;
            case R.id.setting_btn:
                navController.navigate(R.id.action_recordFragment_to_optionFragment);
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (Activity) context;
    }

    @Override
    public void onResume() {
        super.onResume();
    }



    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
