package com.wzc.permissionsdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wzc.requestpermissionlibrary.PermissionCallBack;
import com.wzc.requestpermissionlibrary.PermissionManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Activity mActivity = this;
    private Button mBtCallPhone;
    private Button mBtOpenActivity;
    private Button mBtOpenAppFragment;
    private Button mBtOpenV4AppFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtCallPhone = findViewById(R.id.bt_request_call_phone);
        mBtOpenActivity = findViewById(R.id.bt_open_activity);
        mBtOpenAppFragment = findViewById(R.id.bt_open_app_fragment);
        mBtOpenV4AppFragment = findViewById(R.id.bt_open_v4_fragment);
        mBtCallPhone.setOnClickListener(this);
        mBtOpenActivity.setOnClickListener(this);
        mBtOpenAppFragment.setOnClickListener(this);
        mBtOpenV4AppFragment.setOnClickListener(this);

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
            getFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, AppFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        } else if (v == mBtOpenV4AppFragment) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, V4AppFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
