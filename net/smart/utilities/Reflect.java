// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.utilities;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Reflect
{
    public static Object NewInstance(final Class<?> base, final Name name) {
        try {
            return LoadClass(base, name, true).getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
        }
        catch (final Exception e) {
            throw new RuntimeException(name.deobfuscated, e);
        }
    }
    
    public static boolean CheckClasses(final Class<?> base, final Name... names) {
        for (int i = 0; i < names.length; ++i) {
            if (LoadClass(base, names[i], false) == null) {
                return false;
            }
        }
        return true;
    }
    
    public static Class<?> LoadClass(final Class<?> base, final Name name, final boolean throwException) {
        final ClassLoader loader = base.getClassLoader();
        if (name.obfuscated != null) {
            try {
                return loader.loadClass(name.obfuscated);
            }
            catch (final ClassNotFoundException ex) {}
        }
        try {
            return loader.loadClass(name.deobfuscated);
        }
        catch (final ClassNotFoundException cnfe) {
            if (throwException) {
                throw new RuntimeException(cnfe);
            }
            return null;
        }
    }
    
    public static void copyFields(final Class<?> type, final Object source, final Object target) {
        final Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            final Field field = fields[i];
            final int modifiers = field.getModifiers();
            if (!Modifier.isStatic(modifiers) && !Modifier.isFinal(modifiers)) {
                field.setAccessible(true);
                SetField(field, target, GetField(field, source));
            }
        }
    }
    
    public static void SetField(final Field field, final Object object, final Object value) {
        try {
            field.set(object, value);
        }
        catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Object GetField(final Field field, final Object object) {
        try {
            return field.get(object);
        }
        catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void SetField(final Class<?> theClass, final Object object, final Name name, final Object value) {
        try {
            GetField(theClass, name).set(object, value);
        }
        catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Object GetField(final Class<?> theClass, final Object object, final Name name) {
        try {
            return GetField(theClass, name).get(object);
        }
        catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Field GetField(final Class<?> theClass, final Name name) {
        return GetField(theClass, name, true);
    }
    
    public static Field GetField(final Class<?> theClass, final Name name, final boolean throwException) {
        if (theClass == null && !throwException) {
            return null;
        }
        Field field = null;
        try {
            field = GetRawField(theClass, name);
            field.setAccessible(true);
        }
        catch (final NoSuchFieldException oe) {
            if (throwException) {
                throw new RuntimeException(GetFieldMessage(theClass, name), oe);
            }
        }
        return field;
    }
    
    private static String GetFieldMessage(final Class<?> theClass, final Name name) {
        final Field[] fields = theClass.getDeclaredFields();
        final StringBuffer message = GetMessage(theClass, name, "field", "type");
        for (int i = 0; i < fields.length; ++i) {
            AppendElement(message, fields[i].getName(), fields[i].getType());
        }
        return message.toString();
    }
    
    private static Field GetRawField(final Class<?> theClass, final Name name) throws NoSuchFieldException {
        if (name.obfuscated != null) {
            try {
                return theClass.getDeclaredField(name.obfuscated);
            }
            catch (final NoSuchFieldException ex) {}
        }
        if (name.forgefuscated != null) {
            try {
                return theClass.getDeclaredField(name.forgefuscated);
            }
            catch (final NoSuchFieldException ex2) {}
        }
        return theClass.getDeclaredField(name.deobfuscated);
    }
    
    public static Method GetMethod(final Class<?> theClass, final Name name, final Class<?>... paramArrayOfClass) {
        return GetMethod(theClass, name, true, paramArrayOfClass);
    }
    
    public static Method GetMethod(final Class<?> theClass, final Name name, final boolean throwException, final Class<?>... paramArrayOfClass) {
        if (theClass == null && !throwException) {
            return null;
        }
        Method method = null;
        try {
            method = GetRawMethod(theClass, name, paramArrayOfClass);
            method.setAccessible(true);
        }
        catch (final NoSuchMethodException oe) {
            if (throwException) {
                throw new RuntimeException(GetMethodMessage(theClass, name), oe);
            }
        }
        return method;
    }
    
    private static String GetMethodMessage(final Class<?> theClass, final Name name) {
        final Method[] methods = theClass.getDeclaredMethods();
        final StringBuffer message = GetMessage(theClass, name, "method", "return type");
        for (int i = 0; i < methods.length; ++i) {
            AppendElement(message, methods[i].getName(), methods[i].getReturnType());
        }
        return message.toString();
    }
    
    private static Method GetRawMethod(final Class<?> theClass, final Name name, final Class<?>... paramArrayOfClass) throws NoSuchMethodException {
        if (name.obfuscated != null) {
            try {
                return theClass.getDeclaredMethod(name.obfuscated, paramArrayOfClass);
            }
            catch (final NoSuchMethodException ex) {}
        }
        if (name.forgefuscated != null) {
            try {
                return theClass.getDeclaredMethod(name.forgefuscated, paramArrayOfClass);
            }
            catch (final NoSuchMethodException ex2) {}
        }
        return theClass.getDeclaredMethod(name.deobfuscated, paramArrayOfClass);
    }
    
    public static Object Invoke(final Method method, final Object paramObject, final Object... paramArrayOfObject) {
        try {
            return method.invoke(paramObject, paramArrayOfObject);
        }
        catch (final Exception e) {
            throw new RuntimeException(method.getName(), e);
        }
    }
    
    private static StringBuffer GetMessage(final Class<?> theClass, final Name name, final String elementName, final String typeName) {
        final StringBuffer message = new StringBuffer().append("Can not find ").append(elementName).append(" \"").append(name.deobfuscated).append("\"");
        if (name.obfuscated != null) {
            message.append(" (ofuscated \"").append(name.obfuscated).append("\")");
        }
        message.append(" in class \"").append(theClass.getName()).append("\".\nExisting ").append(elementName).append("s (<name>, <").append(typeName).append(">) are:");
        return message;
    }
    
    private static StringBuffer AppendElement(final StringBuffer message, final String name, final Class<?> type) {
        return message.append("\n\t\t(").append(name).append(", ").append(type.getName()).append(")");
    }
}
