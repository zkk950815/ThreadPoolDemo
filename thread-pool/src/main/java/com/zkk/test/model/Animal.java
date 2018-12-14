package com.zkk.test.model;

/**
 * @ProjectName: ThreadPoolDemo
 * @Package: com.zkk.test.model
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: Mr.zhu
 * @CreateDate: 2018/12/14 12:08
 * @UpdateUser: Mr.zhu
 * @UpdateDate: 2018/12/14 12:08
 * @UpdateRemark: The modified content
 * @Version: 版本
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class Animal {
    private String ID = "007";
    private String name = "tom";
    private int age = 1;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
