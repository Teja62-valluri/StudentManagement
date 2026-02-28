package com.TejaCompany.Entity;

public class StudentEntity {
     private String name;
    private int age;
    private int id;
    private long number;
    
    public StudentEntity(){}

    // getters 
    public String getName() {
        return name;
    }
    public int getAge(){
        return age;
    }
    public int getId(){
        return id;
    }
    public long getNumber() {
        return number;
    }

    //setters

    public void setName(String name) {
        this.name=name;
    }
    public void setAge(int age) {
        this.age=age;
    }
    public void setId(int id) {
        this.id=id;
    }
    public void setNumber(long number) {
        this.number= number;
    }
}
