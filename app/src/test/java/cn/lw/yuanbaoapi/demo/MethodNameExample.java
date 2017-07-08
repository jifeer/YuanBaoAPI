package cn.lw.yuanbaoapi.demo;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by lw on 2017/7/5.
 */
public class MethodNameExample implements TestRule {
    @Override
    public Statement apply(Statement base, Description description) {
        //在方法执行前执行,获取方法执行的类和方法名
        String clsName = description.getClassName();
        String methodName = description.getMethodName();
        try {
            //调用方法执行
            base.evaluate();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //方法执行完后执行
        System.out.println("Class :" + clsName + " methodName:" + methodName);
        return base;
    }
}
