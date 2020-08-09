package com.wzc.requestpermissionlibrary;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wuzuchang On 2019/6/30
 **/
public class PermissionManager {
    private static final String TAG_PERMISSION = "TAG_PERMISSION";
    /**
     * androidx Fragment
     */
    private static Map<FragmentManager, PermissionFragment> pendingPermissionFragments = new ConcurrentHashMap<>();
    /**
     * android.app.Fragment
     */
    private static Map<android.app.FragmentManager, PermissionAppFragment> pendingPermissionAppFragments = new ConcurrentHashMap<>();

    private PermissionManager() {
    }

    private static class SingletonHolder {
        private static final PermissionManager PERMISSION_MANAGER = new PermissionManager();
    }

    public static PermissionManager getInstance() {
        return SingletonHolder.PERMISSION_MANAGER;
    }

    /**
     * androidx.fragment.app.FragmentActivity 中申请权限
     *
     * @param activity   mActivity
     * @param permissions 权限
     * @param callBack    callBack
     */

    public void requestPermissions(FragmentActivity activity, String[] permissions, PermissionCallBack callBack) {
        if (activity == null) return;
        PermissionFragment fragment = getAndroidxPermissionFragment(activity.getSupportFragmentManager());
        fragment.requestMyPermissions(permissions, callBack);
    }

    /**
     * android.app.Activity 中申请权限
     *
     * @param activity   mActivity
     * @param permissions 权限
     * @param callBack    callBack
     */
    public void requestPermissions(Activity activity, String[] permissions, PermissionCallBack callBack) {
        if (activity == null) return;
        PermissionAppFragment fragment = getPermissionFragment(activity.getFragmentManager());
        fragment.requestMyPermissions(permissions, callBack);
    }

    /**
     * androidx.fragment.app.Fragment 中申请权限
     *
     * @param mFragment   mFragment
     * @param permissions 权限
     * @param callBack    callBack
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void requestPermissions(Fragment mFragment, String[] permissions, PermissionCallBack callBack) {
        if (mFragment == null) return;
        PermissionFragment fragment = getAndroidxPermissionFragment(mFragment.getChildFragmentManager());
        fragment.requestMyPermissions(permissions, callBack);
    }

    /**
     * 获取Androidx下的Fragment
     * @param fm androidx.fragment.app.FragmentManager
     * @return androidx.fragment.app.Fragment
     */
    private PermissionFragment getAndroidxPermissionFragment(@NonNull final FragmentManager fm) {
        PermissionFragment current = (PermissionFragment) fm.findFragmentByTag(TAG_PERMISSION);
        if (current == null) {
            current = pendingPermissionFragments.get(fm);
            if (current == null) {
                current = PermissionFragment.newInstance();
                pendingPermissionFragments.put(fm, current);
                fm.beginTransaction().add(current, TAG_PERMISSION).commitAllowingStateLoss();
                //立即执行Transactions
                fm.executePendingTransactions();
            }
        }
        return current;
    }

    /**
     * 获取android.app包下的Fragment
     * @param fm android.app.FragmentManager
     * @return android.app.Fragment
     */
    private PermissionAppFragment getPermissionFragment(@NonNull final android.app.FragmentManager fm) {
        PermissionAppFragment current = (PermissionAppFragment) fm.findFragmentByTag(TAG_PERMISSION);
        if (current == null) {
            current = pendingPermissionAppFragments.get(fm);
            if (current == null) {
                current = PermissionAppFragment.newInstance();
                pendingPermissionAppFragments.put(fm, current);
                fm.beginTransaction().add(current, TAG_PERMISSION).commitAllowingStateLoss();
                //立即执行Transactions
                fm.executePendingTransactions();
            }
        }
        return current;
    }
}
