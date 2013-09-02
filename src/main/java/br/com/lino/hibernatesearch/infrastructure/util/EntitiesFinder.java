package br.com.lino.hibernatesearch.infrastructure.util;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

public class EntitiesFinder {

	 public static Set<Class<?>> findEntities(String packageName) {
	        Set<Class<?>> classes = new HashSet<Class<?>>();
	        try {
	            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

	            Enumeration<URL> resources = classLoader.getResources(packageName.replace('.', '/'));
	            while (resources.hasMoreElements()) {
	                URL res = resources.nextElement();

                    classes.addAll(scanClassesFromDirectory(new File(URLDecoder.decode(res.getPath(), "UTF-8")), packageName));
	            }
	        } catch (Exception e) {
	            throw new IllegalArgumentException("invalid package");
	        }

	        return classes;
	    }

	    private static Set<Class<?>> scanClassesFromDirectory(File directory, String packageName) {
	        Set<Class<?>> classes = new HashSet<Class<?>>();

	        if (directory.exists() && directory.isDirectory()) {
	            String[] files = directory.list();
	            for (String fileItem : files) {
	                if (fileItem.endsWith(".class")) {
	                    try {
	                        Class<?> entityClass = Class.forName(packageName.concat(".").concat(fileItem.replace(".class","")));

	                        if(entityClass.isAnnotationPresent(Entity.class)) {
	                        	classes.add(entityClass);
	                        }
	                    } catch (Exception e) {
	                        throw new IllegalArgumentException("Invalid package " + packageName);
	                    }
	                } else if (!fileItem.matches(".+\\..+")) {
	                    File dir = new File(directory,fileItem);
	                    if (dir.isDirectory()) {
	                    	classes.addAll(scanClassesFromDirectory(dir, packageName.concat(".").concat(fileItem)));
	                    }
	                }
	            }
	        }
	        return classes;
	    }
}