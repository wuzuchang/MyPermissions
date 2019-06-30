package com.wzc.permissionsdemo;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wzc.requestpermissionlibrary.PermissionCallBack;
import com.wzc.requestpermissionlibrary.PermissionManager;


public class AppFragment extends Fragment implements View.OnClickListener {

    private Activity mActivity;
    private View contentView;
    private Button mBtReadContacts;

    public AppFragment() {
    }

    public static AppFragment newInstance() {
        return new AppFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (contentView == null) {
            mActivity = getActivity();
            contentView = inflater.inflate(R.layout.fragment_app, container, false);
            mBtReadContacts = contentView.findViewById(R.id.bt_read_contacts);
            mBtReadContacts.setOnClickListener(this);
        }
        return contentView;

    }

    @Override
    public void onClick(View v) {
        if (v == mBtReadContacts) {
            PermissionManager.getInstance().requestPermissions(this, Manifest.permission.READ_CONTACTS, new PermissionCallBack() {
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
