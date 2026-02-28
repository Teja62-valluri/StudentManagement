package com.TejaCompany.DTO;



public class StudentDTO {
    private String name;
    private int age;
    private int id;
    private long number;
    
    public StudentDTO(){}

    public StudentDTO(int id, String name, int age, long number) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.number = number;
}


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
