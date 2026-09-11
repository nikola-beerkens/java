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
public class Group {
    private Student[] all;
    private int numStud;
    
    public Group(int amount) {
        all = new Student[amount];
        numStud = 0;
    }
    
    public void addStudent (Student name, int place) {
        this.all[place] = name;
        this.numStud++;
    }

    public Student getStudent(int index){
        return this.all[index];
    }

}
