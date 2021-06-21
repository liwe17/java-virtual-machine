package com.weiliai.stack;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Doug Li
 * @Date 2021/6/21
 * @Describe: json数据转换
 *
 * com.fasterxml.jackson.databind.JsonMappingException: Infinite recursion (StackOverflowError)
 */
public class Demo1_19 {

    public static void main(String[] args) throws JsonProcessingException {
        Dept d = new Dept();
        d.setName("Market");

        Emp e1 = new Emp();
        e1.setName("lea");
        e1.setDept(d);

        Emp e2 = new Emp();
        e2.setName("leb");
        e2.setDept(d);

        d.setEmpList(Arrays.asList(e1,e2));

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(d));

    }

}

class Emp {

    private String name;
    @JsonIgnore //打破循环引用
    private Dept dept;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}

class Dept {

    private String name;
    private List<Emp> empList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Emp> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Emp> empList) {
        this.empList = empList;
    }
}