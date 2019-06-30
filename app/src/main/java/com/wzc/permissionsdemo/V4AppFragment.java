package com.wzc.permissionsdemo;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.wzc.requestpermissionlibrary.PermissionCallBack;
import com.wzc.requestpermissionlibrary.PermissionManager;


public class V4AppFragment extends Fragment implements View.OnClickListener {

    private Activity mActivity;
    private View contentView;
    private Button mBtWriteContacts;

    public V4AppFragment() {
    }


    public static V4AppFragment newInstance() {
        return new V4AppFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (contentView == null) {
            mActivity = getActivity();
            contentView = inflater.inflate(R.layout.fragment_v4, container, false);
            mBtWriteContacts = contentView.findViewById(R.id.bt_write_contacts);
            mBtWriteContacts.setOnClickListener(this);
        }
        return contentView;
    }


    @Override
    public void onClick(View v) {
        if (v == mBtWriteContacts) {
            PermissionManager.getInstance().requestPermissions(this, Manifest.permission.WRITE_CONTACTS, new PermissionCallBack() {
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
