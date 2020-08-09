package com.wzc.permissionsdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wzc.requestpermissionlibrary.PermissionCallBack;
import com.wzc.requestpermissionlibrary.PermissionManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Activity mActivity = this;
    private Button mBtCallPhone;
    private Button mBtOpenActivity;
    private Button mBtOpenAppFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtCallPhone = findViewById(R.id.bt_request_call_phone);
        mBtOpenActivity = findViewById(R.id.bt_open_activity);
        mBtOpenAppFragment = findViewById(R.id.bt_open_app_fragment);
        mBtCallPhone.setOnClickListener(this);
        mBtOpenActivity.setOnClickListener(this);
        mBtOpenAppFragment.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mBtCallPhone) {
            PermissionManager.getInstance().requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA}, new PermissionCallBack() {
                @Override
                public void agree(String permissions) {
                    Toast.makeText(mActivity.getApplicationContext(), "同意" + permissions + "权限", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void refusal(String permissions) {
                    Toast.makeText(mActivity.getApplicationContext(), "拒绝" + permissions + "权限", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void alwaysRefusal(String permissions) {
                    Toast.makeText(mActivity.getApplicationContext(), "不再提醒申请" + permissions + "权限", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (v == mBtOpenActivity) {
            Intent intent = new Intent(this, AppActivity.class);
            startActivity(intent);
        } else if (v == mBtOpenAppFragment) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, AndroidxFragment.newInstance())
                    .commitAllowingStateLoss();
        }
    }
}
