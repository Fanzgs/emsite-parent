/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.common.persistence.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.common.utils.Reflections;

/**
 * 类PaginationMapperProxy.java的实现描述：TODO 类实现描述
 * 
 * @author arron 2017年10月30日 下午4:03:15
 */
public class PaginationMapperProxy implements InvocationHandler {

    private static final Set<String> OBJECT_METHODS = new HashSet<String>() {
                                                        private static final long serialVersionUID = -1782950882770203583L;
                                                        {
                                                            add("toString");
                                                            add("getClass");
                                                            add("hashCode");
                                                            add("equals");
                                                            add("wait");
                                                            add("notify");
                                                            add("notifyAll");
                                                        }
                                                    };

    private boolean isObjectMethod(Method method) {
        return OBJECT_METHODS.contains(method.getName());
    }

    private final SqlSession sqlSession;

    private PaginationMapperProxy(final SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (isObjectMethod(method)) {
            return null;
        }
        final Class<?> declaringInterface = findDeclaringInterface(proxy, method);
        if (Page.class.isAssignableFrom(method.getReturnType())) {
            // 分页处理
            return new PaginationMapperMethod(declaringInterface, method, sqlSession).execute(args);
        }
        // 原处理方式
        final MapperMethod mapperMethod = new MapperMethod(declaringInterface, method, sqlSession.getConfiguration());
        final Object result = mapperMethod.execute(sqlSession, args);
        if (result == null && method.getReturnType().isPrimitive()) {
            throw new BindingException("Mapper method '" + method.getName() + "' (" + method.getDeclaringClass()
                    + ") attempted to return null from a method with a primitive return type ("
                    + method.getReturnType() + ").");
        }
        return result;
    }

    private Class<?> findDeclaringInterface(Object proxy, Method method) {
        Class<?> declaringInterface = null;
        for (Class<?> mapperFaces : proxy.getClass().getInterfaces()) {
            Method m = Reflections.getAccessibleMethod(mapperFaces, method.getName(), method.getParameterTypes());
            if (m != null) {
                declaringInterface = mapperFaces;
            }
        }
        if (declaringInterface == null) {
            throw new BindingException("Could not find interface with the given method " + method);
        }
        return declaringInterface;
    }

    @SuppressWarnings("unchecked")
    public static <T> T newMapperProxy(Class<T> mapperInterface, SqlSession sqlSession) {
        ClassLoader classLoader = mapperInterface.getClassLoader();
        Class<?>[] interfaces = new Class[] { mapperInterface };
        PaginationMapperProxy proxy = new PaginationMapperProxy(sqlSession);
        return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
    }
}
