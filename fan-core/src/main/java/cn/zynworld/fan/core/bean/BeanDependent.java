package cn.zynworld.fan.core.bean;

import cn.zynworld.fan.core.enums.BeanDependentInjectTypeEnum;

/**
 * Created by zhaoyuening on 2019/2/17.
 * beanDefinition中的的依赖
 */
public class BeanDependent {
    /**
     * 例如setName
     */
    private String methodName;
    /**
     * 注入类型 {@link BeanDependentInjectTypeEnum#getCode()}
     * 有三种注入方式 类型注入、名称注入、值注入
     */
    private Integer injectType;
    /**
     * 注入信息：
     * 类型注入：存放类名称
     * 名称注入：beanName
     * 值注入：基本类型字符串类型的字符值
     */
    private String injectInfo;

    public String getMethodName() {
        return methodName;
    }

    public BeanDependent setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public Integer getInjectType() {
        return injectType;
    }

    public BeanDependent setInjectType(Integer injectType) {
        this.injectType = injectType;
        return this;
    }

    public String getInjectInfo() {
        return injectInfo;
    }

    public BeanDependent setInjectInfo(String injectInfo) {
        this.injectInfo = injectInfo;
        return this;
    }
}
