package com.example.devopsPipeline;

public class Application {
    

    public Learn  createAGuy(){
        Learn student = new Learn();
        student.setName("Lalith");
        student.setRollNum(1);
        int i =1/0;

        return student;
    }

    public Learn  createAGuy(int num){
        Learn student = new Learn();
        student.setName("Lalith");
        student.setRollNum(1);

        return student;
    }

    public Learn  createAGuy(String num){
        Learn student = new Learn();
        student.setName("Lalith");
        student.setRollNum(1);
       

        return student;
    }




}
