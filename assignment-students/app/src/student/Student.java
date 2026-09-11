/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

/**
 *
 * @author Gebruiker
 */
public class Student {
    private String firstName;
    private String lastName;
    private int stNumber;
    
    public Student (String fName, String lName, int sNum) {
        this.firstName = fName;
        this.lastName = lName;
        this.stNumber = sNum;
    }

    public void setFirstName(String name){
        this.firstName = name;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public int getNumber(){
        return this.stNumber;
    }

    public String toString(){
        String result = this.firstName + " " + this.lastName + ", s" + this.stNumber;
        return result;
    }

    
    
}
