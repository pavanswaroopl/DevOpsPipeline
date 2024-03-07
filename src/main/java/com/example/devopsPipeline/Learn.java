package com.example.devopsPipeline;

import lombok.Data;

public class Learn {
    
int rollNum;
String name;

public int getRollNum() {
    return rollNum;
}

public void setRollNum(int rollNum) {
    this.rollNum = rollNum;
}


public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}




    public static void main(String[] args) {
        int k=0;
        Application application = new Application();
       System.out.println("Lal");
       Learn student = application.createAGuy("dd");

   System.out.println(student.getName());
 }

 public  void lll(String args) {
    Application application = new Application();
   System.out.println("Lal");
   application.createAGuy();


}



}
