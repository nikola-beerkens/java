package student;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        welcome();
    }
    
    public static void welcome() {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Welcome, how big do you want the group to be?");
            int size = scan.nextInt();
            Group group = new Group(size);
            for(int i=0;i<size;i++){
                System.out.println("Please enter a student:");
                int snumber = scan.nextInt();
                String fName = scan.next();
                String lName = scan.next();
                Student student = new Student(fName, lName, snumber);
                group.addStudent(student, i);
            }
            System.out.println("The group now contains:");
            for(int i=0;i<size;i++){
                Student student = group.getStudent(i);
                System.out.println(student.toString());
            }
            while (true){
                System.out.println("Student number and new given/family name?");
                int numb = scan.nextInt();
                if(numb == -1)
                    break;
                String fName = scan.next();
                String lName = scan.next();
                for(int i=0;i<size;i++){
                    Student student = group.getStudent(i);
                    if(student.getNumber()==numb){
                        student.setFirstName(fName);
                        student.setLastName(lName);
                    }
                }
                System.out.println("The group now contains:");
                for(int i=0;i<size;i++){
                Student student = group.getStudent(i);
                System.out.println(student.toString());
                }
            }
        }
        System.out.println("Bye!");


        
    }
}
