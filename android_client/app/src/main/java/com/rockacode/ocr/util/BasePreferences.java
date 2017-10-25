package com.rockacode.ocr.util;

import android.content.SharedPreferences;

import java.util.Set;

public class BasePreferences {

    protected SharedPreferences manager;

    protected void setValue(String key, Object value) {
        SharedPreferences.Editor prefEditor = manager.edit();
        if (value instanceof Integer) {
            prefEditor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            prefEditor.putBoolean(key, (Boolean) value);
        } else if (value instanceof String) {
            prefEditor.putString(key, (String) value);
        } else if (value instanceof Float) {
            prefEditor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            prefEditor.putLong(key, (Long) value);
        } else if (value instanceof Double) {
            prefEditor.putLong(key, Double.doubleToLongBits((Double) value));
        } else if (value instanceof Set) {
            prefEditor.putStringSet(key, (Set<String>) value);
        }
        prefEditor.commit();
        manager.contains(key);
    }

    protected void deleteValue(String key) {
        SharedPreferences.Editor prefEditor = manager.edit();
        prefEditor.remove(key);
        prefEditor.commit();
    }

    @Deprecated
    protected Object getValue1(String key, Class<?> clazz, Object defaultValue) throws UnsupportedClassException {
        if (defaultValue != null && defaultValue.getClass().equals(clazz)) {
            throw new IllegalArgumentException("Invalid default value for class " + clazz);
        }

        if (clazz.equals(String.class)) {
            return manager.getString(key, (String) defaultValue);
        } else if (clazz.equals(Boolean.class)) {
            return manager.getBoolean(key, (Boolean) defaultValue);
        } else if (clazz.equals(Integer.class)) {
            return manager.getInt(key, (Integer) defaultValue);
        } else if (clazz.equals(Long.class)) {
            return manager.getLong(key, (Long) defaultValue);
        } else if (clazz.equals(Float.class)) {
            return manager.getFloat(key, (Float) defaultValue);
        } else if(clazz.equals(Double.class)){
            return Double.longBitsToDouble(manager.getLong(key, Double.doubleToLongBits((Long) defaultValue)));
        } else {
            throw new UnsupportedClassException("Need to add a primitive type to shared prefs");
        }
    }

    public SharedPreferences getSharedPreferences() {
        return manager;
    }

    public static class UnsupportedClassException extends Exception {

        private static final long serialVersionUID = -6550161745979178219L;

        public UnsupportedClassException(String message) {
            super(message);
        }

    }
}
