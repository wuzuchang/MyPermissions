package com.wzc.requestpermissionlibrary;


import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


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
//        setRetainInstance(true);
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
        Log.d("wzc", "requestCode = " + requestCode + "permissions = " + permissions.toString() + "grantResults = " + grantResults.toString());
        int length = grantResults.length;
        for (int i = 0; i < length; i++) {
            int grantResult = grantResults[i];
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                if (permissionCallBack != null)
                    permissionCallBack.agree(permissions[i]);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!this.shouldShowRequestPermissionRationale(permissions[i])) {
                        if (permissionCallBack != null)
                            permissionCallBack.alwaysRefusal(permissions[i]);
                    } else {
                        if (permissionCallBack != null)
                            permissionCallBack.refusal(permissions[i]);
                    }
                }
            }
        }

    }
}
