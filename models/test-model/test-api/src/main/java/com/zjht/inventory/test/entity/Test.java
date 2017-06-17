package com.zjht.inventory.test.entity;

/**
 * Created by leaves chen<leaves615@gmail.com> on 16/3/30.
 *
 * @Author leaves chen<leaves615@gmail.com>
 */
public class Test {
    private Long id;
    private String name;

    public Test() {
    }

    public Test(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
