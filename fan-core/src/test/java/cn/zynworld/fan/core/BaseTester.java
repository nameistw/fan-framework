package cn.zynworld.fan.core;

import cn.zynworld.fan.core.bean.BeanDefinition;
import cn.zynworld.fan.core.bean.BeanDependent;
import cn.zynworld.fan.core.config.ClassPathXmlBeanDefinitionReader;
import cn.zynworld.fan.core.config.MockBeanDefinitionReader;
import cn.zynworld.fan.core.entity.Dept;
import cn.zynworld.fan.core.entity.Employee;
import cn.zynworld.fan.core.enums.BeanDependentInjectTypeEnum;
import cn.zynworld.fan.core.enums.BeanStatusEnum;
import cn.zynworld.fan.core.factory.BaseAbstractBeanFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhaoyuening on 2019/2/17.
 */
public class BaseTester {

    @Test
    public void test() {
        List<BeanDefinition> definitions = new ArrayList<>();

        // 部门bean定义
        BeanDefinition deptDefinition = new BeanDefinition();
        deptDefinition.setBeanClass(Dept.class);
        deptDefinition.setBeanClassList(Collections.singletonList(Dept.class));
        deptDefinition.setBeanName(Dept.class.getName());
        deptDefinition.setLazyLoad(false);
        deptDefinition.setSingleton(true);
        deptDefinition.setBeanStatus(BeanStatusEnum.BEAN_STATUS_INIT.getCode());
        // 设置依赖
        List<BeanDependent> beanDependents = new ArrayList<>();
        BeanDependent managerDependent = new BeanDependent();
        managerDependent.setInjectType(BeanDependentInjectTypeEnum.INJECT_TYPE_CLASS.getCode());
        managerDependent.setInjectInfo(Employee.class.getName());
        managerDependent.setMethodName("setManager");
        beanDependents.add(managerDependent);
        // 设置依赖
        BeanDependent deptNameDependent = new BeanDependent();
        deptNameDependent.setInjectType(BeanDependentInjectTypeEnum.INJECT_TYPE_NAME.getCode());
        deptNameDependent.setInjectInfo("IT 部门");
        deptNameDependent.setMethodName("setDeptName");
        beanDependents.add(deptNameDependent);
        deptDefinition.setBeanDependents(beanDependents);
        definitions.add(deptDefinition);

        // 经理
        BeanDefinition managerDefinition = new BeanDefinition();
        managerDefinition.setBeanClass(Employee.class);
        managerDefinition.setBeanClassList(Collections.singletonList(Employee.class));
        managerDefinition.setBeanName(Employee.class.getName());
        managerDefinition.setLazyLoad(false);
        managerDefinition.setSingleton(true);
        managerDefinition.setBeanStatus(BeanStatusEnum.BEAN_STATUS_INIT.getCode());
        // 设置依赖
        beanDependents = new ArrayList<>();
        BeanDependent managerNameDependent = new BeanDependent();
        managerNameDependent.setInjectType(BeanDependentInjectTypeEnum.INJECT_TYPE_VALUE.getCode());
        managerNameDependent.setInjectInfo("zhao");
        managerNameDependent.setMethodName("setEmployeeName");
        managerDefinition.setBeanDependents(beanDependents);

        beanDependents.add(managerNameDependent);
        definitions.add(managerDefinition);

        BaseAbstractBeanFactory beanFactory = new BaseAbstractBeanFactory(Collections.singletonList(new MockBeanDefinitionReader(definitions)));

        Dept itDept = beanFactory.getBeanByClass(Dept.class);
        System.out.println(itDept.getManager().getEmployeeName());
    }

    @Test
    public void test1() {
        ClassPathXmlBeanDefinitionReader reader = new ClassPathXmlBeanDefinitionReader(Collections.singletonList("fanConfig.xml"));
        BaseAbstractBeanFactory beanFactory = new BaseAbstractBeanFactory(Collections.singletonList(reader));

        Dept itDept = beanFactory.getBeanByClass(Dept.class);
        System.out.println(itDept.getManager().getEmployeeName());
    }
}
