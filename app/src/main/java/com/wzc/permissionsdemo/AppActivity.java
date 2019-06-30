package com.wzc.permissionsdemo;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wzc.requestpermissionlibrary.PermissionCallBack;
import com.wzc.requestpermissionlibrary.PermissionManager;

public class AppActivity extends Activity implements View.OnClickListener {

    private Activity mActivity = this;
    private Button mBtReadPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        mBtReadPhone = findViewById(R.id.bt_read_phone);
        mBtReadPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==mBtReadPhone){
            PermissionManager.getInstance().requestPermissions(this, Manifest.permission.READ_PHONE_STATE, new PermissionCallBack() {
                @Override
                public void agree(String permissions) {
                    Toast.makeText(mActivity.getApplicationContext(),"同意"+permissions+"权限",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void refusal(String permissions) {
                    Toast.makeText(mActivity.getApplicationContext(),"拒绝"+permissions+"权限",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void alwaysRefusal(String permissions) {
                    Toast.makeText(mActivity.getApplicationContext(),"不再提醒申请"+permissions+"权限",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
