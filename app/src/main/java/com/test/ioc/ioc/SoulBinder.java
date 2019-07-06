package com.test.ioc.ioc;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SoulBinder {


    public static void inject(Activity activity) {
        Class<? extends Activity> clz = activity.getClass();
        injectLayout(activity, clz);
        injectViews(activity, clz);
        injectEvents(activity, clz);
    }

    private static void injectLayout(Activity activity, Class<? extends Activity> clz) {
        ContentView contentView = clz.getAnnotation(ContentView.class);
        if (contentView != null) {
            int layout = contentView.value();
            try {
                Method method = clz.getMethod("setContentView", int.class);
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

    private static void injectEvents(Activity activity, Class<? extends Activity> clz) {
        Method[] methods = activity.getClass().getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                EventBase base = annotation.annotationType().getAnnotation(EventBase.class);
                if (base != null) {
                    OnClick onClickAn = (OnClick) annotation;
                    int viewId = onClickAn.value();
                    View view = activity.findViewById(viewId);
                    Class onClickListenerClass = base.listenerTypeClz();
                    OnClickHandler proxy = new OnClickHandler(activity,method);
                    Object onClickProxy = Proxy.newProxyInstance(onClickListenerClass.getClassLoader(), new Class[]{onClickListenerClass}, proxy);
                    view.setOnClickListener((View.OnClickListener) onClickProxy);
                    try {
                        Method viewOnClickMethod = view.getClass().getMethod(base.setListenerName(),onClickListenerClass);
                        viewOnClickMethod.invoke(view,onClickProxy);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
