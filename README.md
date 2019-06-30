# PermissionsDemo
一个简单的权限申请库

1.接口
```java
 //在android.support.v4.app.FragmentActivity中申请权限
 public void requestPermissions(FragmentActivity mActivity, String permissions, PermissionCallBack callBack)
 //在android.app.Activity中申请权限
 public void requestPermissions(Activity mActivity, String permissions, PermissionCallBack callBack) 
 //在android.support.v4.app.Fragment中申请权限
 public void requestPermissions(Fragment mFragment, String permissions, PermissionCallBack callBack)
 //在android.app.Fragment中申请权限
 public void requestPermissions(android.app.Fragment mFragment, String permissions, PermissionCallBack callBack)
```
2.例子
```java
 PermissionManager.getInstance().requestPermissions(this, Manifest.permission.READ_PHONE_STATE, new PermissionCallBack() {
                @Override
                public void agree(String permissions) {
                    Toast.makeText(mActivity.getApplicationContext(),"同意" + permissions + "权限",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void refusal(String permissions) {
                    Toast.makeText(mActivity.getApplicationContext(),"拒绝" + permissions + "权限",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void alwaysRefusal(String permissions) {
                    Toast.makeText(mActivity.getApplicationContext(),"不再提醒申请" + permissions + "权限",Toast.LENGTH_SHORT).show();
                }
            });
```
