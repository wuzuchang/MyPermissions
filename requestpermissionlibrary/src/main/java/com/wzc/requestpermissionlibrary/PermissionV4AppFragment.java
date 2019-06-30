package com.wzc.requestpermissionlibrary;



import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.Random;

/**
 * Created by wuzuchang On 2019/6/30
 **/
public class PermissionV4AppFragment extends Fragment {

    private PermissionCallBack permissionCallBack;

    public PermissionV4AppFragment() {
    }

    public static PermissionV4AppFragment newInstance() {
        return new PermissionV4AppFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void requestMyPermissions(@NonNull String[] permissions, PermissionCallBack callBack) {
        permissionCallBack = callBack;
        int requestCode = new Random().nextInt(0xFF);
        requestPermissions(permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("wzc", "requestCode = " + requestCode + "permissions = " + permissions.toString() + "grantResults = " + grantResults.toString());
        int length = grantResults.length;
        for (int i = 0; i < length; i++) {
            int grantResult = grantResults[i];
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                permissionCallBack.agree(permissions[i]);
            } else {
                if (!this.shouldShowRequestPermissionRationale(permissions[i])) {
                    permissionCallBack.alwaysRefusal(permissions[i]);
                } else {
                    permissionCallBack.refusal(permissions[i]);
                }
            }
        }

    }
}
