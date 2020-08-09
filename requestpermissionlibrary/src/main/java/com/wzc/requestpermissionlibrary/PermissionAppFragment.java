package com.wzc.requestpermissionlibrary;


import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by wuzuchang On 2019/6/30
 **/
public class PermissionAppFragment extends Fragment {

    private PermissionCallBack permissionCallBack;

    public PermissionAppFragment() {
    }

    public static PermissionAppFragment newInstance() {
        return new PermissionAppFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void requestMyPermissions(@NonNull String[] permissions, PermissionCallBack callBack) {
        permissionCallBack = callBack;
        int requestCode = new Random().nextInt(0xFF);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("wzc", "requestCode = " + requestCode + "permissions = " + Arrays.toString(permissions) + "grantResults = " + Arrays.toString(grantResults));
        if (permissionCallBack == null) return;
        int length = grantResults.length;
        for (int i = 0; i < length; i++) {
            int grantResult = grantResults[i];
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                permissionCallBack.agree(permissions[i]);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!this.shouldShowRequestPermissionRationale(permissions[i])) {
                        permissionCallBack.alwaysRefusal(permissions[i]);
                    } else {
                        permissionCallBack.refusal(permissions[i]);
                    }
                }
            }
        }

    }
}
