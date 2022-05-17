package com.admin.demo.proxy;

import javassist.*;

import java.lang.reflect.Method;

/**
 * @author Songwe
 * @date 2021/9/2 17:36
 */
public class JavassistMain {
    public static void main(String[] args) throws Exception {
        ClassPool cp = ClassPool.getDefault();

        // 要生成的类名称
        CtClass ctClass = cp.makeClass("com.admin.demo.proxy.JavassistDemo");

        // 创建字段，指定了字段类型、字段名称、字段所属的类
        CtField prop = new CtField(cp.get("java.lang.String"), "prop", ctClass);
        // 指定该字段使用private修饰
        prop.setModifiers(Modifier.PRIVATE);

        // 设置prop字段的getter/setter方法
        ctClass.addMethod(CtNewMethod.getter("getProp", prop));
        ctClass.addMethod(CtNewMethod.setter("setProp", prop));

        ctClass.addField(prop, CtField.Initializer.constant("MyName"));

        // 创建构造方法，指定了构造方法的参数类型和构造方法所属的类
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
        ctConstructor.setBody("System.out.println(\"prop:\" + this.prop);");

        ctClass.addConstructor(ctConstructor);

        // 创建execute()方法，指定了方法返回值、方法名称、方法参数列表以及方法所属的类
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "execute", new CtClass[]{}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("System.out.println(\"execute():\" + this.prop);");

        ctClass.addMethod(ctMethod);

        // 将上面的类保存到指定的目录
        ctClass.writeFile("D:\\workspace\\com-atguigu\\spring-boot-demo\\src\\main\\java");

        // 加载clazz类，并创建对象
        Class<?> toClass = ctClass.toClass();
        Object object = toClass.newInstance();

        // 调用execute()方法

        Method method = object.getClass().getMethod("execute", new Class[]{});
        method.invoke(object, new Object[]{});
    }
}
