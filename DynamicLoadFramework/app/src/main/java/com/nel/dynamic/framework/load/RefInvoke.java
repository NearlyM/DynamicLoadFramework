package com.nel.dynamic.framework.load;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/8/8.
 */

public class RefInvoke {

    /**
     * 反射执行类的静态函数(public)
     * @param class_name	类名
     * @param method_name	函数名
     * @param pareTyple		函数的参数类型
     * @param pareVaules	调用函数时传入的参数
     * @return
     */
    public static Object invokeStaticMethod(String class_name, String method_name, Class[] pareTyple, Object[] pareVaules){

        try {
            Class obj_class = Class.forName(class_name);
            Method method = obj_class.getMethod(method_name, pareTyple);
            return method.invoke(null, pareVaules);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反射执行类的函数(public)
     * @param class_name    类名
     * @param method_name   函数名
     * @param obj           当前对象
     * @param pareTyple     函数的参数类型
     * @param pareValues    调用函数时传入的参数
     * @return
     * */
    public static Object invokeMethod(String class_name, String method_name, Object obj, Class[] pareTyple, Object[] pareValues){

        try {
            Class obj_class = Class.forName(class_name);
            Method method = obj_class.getMethod(method_name, pareTyple);
            return method.invoke(obj, pareValues);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 反射类得到的属性(包括private和protected)
     * @param class_name    类名
     * @param obj           当前对象
     * @param field_name    属性名
     * @return
     */
    public static Object getFieldObject(String class_name, Object obj, String field_name){

        try {
            Class obj_class = Class.forName(class_name);
            Field field = obj_class.getDeclaredField(field_name);
            field.setAccessible(true);
            return field.get(obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反射类得到的静态属性(包括私有和保护)
     * @param class_name    类名
     * @param field_name    属性名
     * @return
     */
    public static Object getStaticFieldObject(String class_name, String field_name){

        try {
            Class obj_class = Class.forName(class_name);
            Field field = obj_class.getDeclaredField(field_name);
            field.setAccessible(true);
            return field.get(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置类的属性(包括private和protected)
     * @param class_name    类名
     * @param field_name    属性名
     * @param object        当前对象
     * @param field_value   属性值
     */
    public static void setFieldObject(String class_name, String field_name, Object object, Object field_value){
        try {
            Class obj_class = Class.forName(class_name);
            Field field = obj_class.getDeclaredField(field_name);
            field.setAccessible(true);
            field.set(object, field_value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置静态类的属性(包括private和protected)
     * @param class_name    类名
     * @param field_name    属性名
     * @param field_value   属性值
     */
    public static void setStaticFieldObject(String class_name, String field_name, Object field_value){
        try {
            Class obj_class = Class.forName(class_name);
            Field field = obj_class.getDeclaredField(field_name);
            field.setAccessible(true);
            field.set(null, field_value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
