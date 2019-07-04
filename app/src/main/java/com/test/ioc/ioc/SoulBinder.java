package com.test.ioc.ioc;

import android.app.Activity;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SoulBinder {


    public static void inject(Activity activity) {
        Class<? extends Activity> clz = activity.getClass();
        injectLayout(activity, clz);
        injectViews(activity, clz);
    }

    private static void injectLayout(Activity activity, Class<? extends Activity> clz) {
        ContentView contentView = clz.getAnnotation(ContentView.class);
        if (contentView != null) {
            int layout = contentView.value();
            try {
                Method method = clz.getDeclaredMethod("setContentView", int.class);
                method.setAccessible(true);
                method.invoke(activity, layout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void injectViews(Activity activity, Class<? extends Activity> clz) {
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            InjectView injectView = field.getAnnotation(InjectView.class);
            if (injectView != null) {
                int viewId = injectView.value();
                try {
                    Method method = clz.getMethod("findViewById", int.class);
                    method.setAccessible(true);
                    Object viewResult = method.invoke(activity, viewId);
                    field.setAccessible(true);
                    field.set(activity, viewResult);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
