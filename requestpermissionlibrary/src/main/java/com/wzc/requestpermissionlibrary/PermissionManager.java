package com.wzc.requestpermissionlibrary;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by wuzuchang On 2019/6/30
 **/
public class PermissionManager {
    private static final String TAG_PERMISSION = "TAG_PERMISSION";

    private PermissionManager() {
    }

    private static class SingletonHolder {
        private static final PermissionManager PERMISSION_MANAGER = new PermissionManager();
    }

    public static PermissionManager getInstance() {
        return SingletonHolder.PERMISSION_MANAGER;
    }

    /**
     * android.support.v4.app.FragmentActivity 中申请权限
     *
     * @param mActivity   mActivity
     * @param permissions 权限
     * @param callBack    callBack
     */

    public void requestPermissions(FragmentActivity mActivity, String permissions, PermissionCallBack callBack) {
        if (mActivity == null) return;
        PermissionV4AppFragment fragment = (PermissionV4AppFragment) mActivity.getSupportFragmentManager().findFragmentByTag(TAG_PERMISSION);
        if (fragment == null) {
            fragment = PermissionV4AppFragment.newInstance();
            FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(fragment, TAG_PERMISSION)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        fragment.requestMyPermissions(new String[]{permissions}, callBack);
    }

    /**
     * android.app.Activity 中申请权限
     *
     * @param mActivity   mActivity
     * @param permissions 权限
     * @param callBack    callBack
     */
    public void requestPermissions(Activity mActivity, String permissions, PermissionCallBack callBack) {
        if (mActivity == null) return;
        PermissionAppFragment fragment = (PermissionAppFragment) mActivity.getFragmentManager().findFragmentByTag(TAG_PERMISSION);
        if (fragment == null) {
            fragment = PermissionAppFragment.newInstance();
            android.app.FragmentManager fragmentManager = mActivity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(fragment, TAG_PERMISSION)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        fragment.requestMyPermissions(new String[]{permissions}, callBack);
    }

    /**
     * android.support.v4.app.Fragment 中申请权限
     *
     * @param mFragment   mFragment
     * @param permissions 权限
     * @param callBack    callBack
     */
    public void requestPermissions(Fragment mFragment, String permissions, PermissionCallBack callBack) {
        if (mFragment == null) return;
        PermissionV4AppFragment fragment = (PermissionV4AppFragment) mFragment.getChildFragmentManager().findFragmentByTag(TAG_PERMISSION);
        if (fragment == null) {
            fragment = PermissionV4AppFragment.newInstance();
            FragmentManager fragmentManager = mFragment.getChildFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(fragment, TAG_PERMISSION)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        fragment.requestMyPermissions(new String[]{permissions}, callBack);
    }

    /**
     * android.app.Fragment 中申请权限
     *
     * @param mFragment   mFragment
     * @param permissions 权限
     * @param callBack    callBack
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void requestPermissions(android.app.Fragment mFragment, String permissions, PermissionCallBack callBack) {
        if (mFragment == null) return;
        PermissionAppFragment fragment = (PermissionAppFragment) mFragment.getChildFragmentManager().findFragmentByTag(TAG_PERMISSION);
        if (fragment == null) {
            fragment = PermissionAppFragment.newInstance();
            android.app.FragmentManager fragmentManager = mFragment.getChildFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(fragment, TAG_PERMISSION)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        fragment.requestMyPermissions(new String[]{permissions}, callBack);
    }
}
