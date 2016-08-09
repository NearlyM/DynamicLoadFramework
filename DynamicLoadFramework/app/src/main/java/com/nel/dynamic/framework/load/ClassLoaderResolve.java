package com.nel.dynamic.framework.load;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.ArrayMap;

import java.io.File;
import java.lang.ref.WeakReference;

import dalvik.system.DexClassLoader;

/**
 * Created by Administrator on 2016/8/8.
 */

public class ClassLoaderResolve {

    Context context;

    public ClassLoaderResolve(Context context){
        this.context = context;
    }

    /**
     * 替换classLoader
     */
    public void loadDexClassLoader(){
        replace(constructClassLoader());
    }

    /**
     * 构建一个新的DexClassLoader
     * @return
     */
    private DexClassLoader constructClassLoader(){
        String filedir = context.getCacheDir().getAbsolutePath();
        String dexPath = filedir + File.separator + "Plugin.apk";
        DexClassLoader dexClassLoader = new DexClassLoader(dexPath, filedir, filedir, context.getClassLoader());
        return dexClassLoader;
    }

    /**
     * 用我们自己的ClassLoader替换系统加载App的classLoader
     * @param classLoader
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void replace(DexClassLoader classLoader){
        Object currentActivityThread = RefInvoke.invokeStaticMethod("android.app.ActivityThread", "currentActivityThread", new Class[]{}, new Object[]{});
        String package_name = context.getPackageName();
        ArrayMap mPackages = (ArrayMap) RefInvoke.getFieldObject("android.app.ActivityThread", currentActivityThread, "mPackages");
        WeakReference weakReference = (WeakReference) mPackages.get(package_name);
        RefInvoke.setFieldObject("android.app.LoadedApk", "mClassLoader", weakReference.get(), classLoader);
    }
}
