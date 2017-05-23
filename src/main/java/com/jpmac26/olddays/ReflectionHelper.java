package com.jpmac26.olddays;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReflectionHelper {
	 
	/**
     * Help in reflection methods and fields when used.
     */

    public static Object getPrivateValue(Class class1, Object obj, String s) throws IllegalArgumentException, SecurityException, NoSuchFieldException
    {
        try
        {
            Field field = class1.getDeclaredField(s);
            field.setAccessible(true);
            return field.get(obj);
        }
        catch (Exception e){}
        return null;
    }

    public static Object getPrivateMethod(Class class1, Object obj, String s) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException
    {
        try
        {
            Method[] methods = class1.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++){
                if (methods[i].getName().equals(s)){
                    getPrivateMethod(class1, obj, i);
                }
        }
        }
        catch (Exception e){}

        return null;
    }

	public static void setStringFieldWW(Class clazz, Object o, String s, Object val){
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++){
            if (fields[i].getName().equals(s)){
                setField(clazz, o, i, val);
                return;
            }
        }
        System.out.println("Fix Reflection usage: No such field: \""+s+"\"!");
    }

    
    public static Object getPrivateMethod(Class class1, Object o, int num) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
        try{
            Method m = class1.getDeclaredMethods()[num];
            m.setAccessible(true);
            return m.invoke(o);
        }
        catch (Exception e){}
        return null;
    }

    public static Class[] getClassesInPackage( String cPackage ) {
        List<Class> classes  = new ArrayList<Class>( );
        URL pResource = Thread.currentThread( ).getContextClassLoader( ).getResource(cPackage.replace(".", "/").trim());
        if ( pResource == null ) {
            return classes.toArray(new Class[classes.size()]);
        }
        File pDirectory = new File( pResource.getFile( ) );
        if(pDirectory != null){
        for ( String name : pDirectory.list( ) ) {
            if ( name.endsWith(".class") ) {
                String cName = cPackage + "." + name;
                try {
                    classes.add(Class.forName(cName.replace(".class", "")));
                } catch ( ClassNotFoundException cException ) {
                	System.out.println("Can't load module classes");
                    cException.printStackTrace();
                }
            }
        }
        }
        return classes.toArray(new Class[classes.size()]);
    }

    public static Field findFieldOfTypeInClass(final Class source, final Class type) {
        for (final Field e : source.getDeclaredFields()) {
            if (e.getType().equals(type)) {
                return e;
            }
    }
        return null;
    }


    public static Method findMethodOfTypeInClass(final Class source, final Class type){
        for(Method m : source.getDeclaredMethods()){
            if(m.getTypeParameters().equals(type)){
                return m;
            }
        }
        return null;
    }
    
    /**
     * Thanks Exalm.
     */
    public static void setField(Class c, Object o, String str, Object val){
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++){
            if (fields[i].getName().equals(str)){
            	System.out.println("Fix Reflection Usage: Use \""+i+"\" instead of \""+str+"\"!");
                setField(c, o, i,val);
                return;
            }
        }
        System.out.println("Fix Reflection Usage: No such field: \""+str+"\"!");
    }
   
    public static void setField(Class c, Object o, int num, Object val){
        try{
            Field f = c.getDeclaredFields()[num];
            f.setAccessible(true);
            Field modifiers = f.getClass().getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(f, f.getModifiers() & ~Modifier.FINAL);
            f.set(o, val);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}
