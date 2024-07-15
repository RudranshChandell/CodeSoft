
//Project --Student marks calculator--

import java.util.*;
public class Student_marks {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the marks of the three subject out of 100.");
        int m1 = sc.nextInt();
        int m2 = sc.nextInt();
        int m3 = sc.nextInt();

        //To Calculate the marks
        int Totalmarks = totalmarks(m1, m2, m3);

        //To get the percentage
        double Percent = percent(Totalmarks);

        //Grading according to marks as A,B,C,D.
        char Grade=grading(Percent);

        //Display marks
        System.out.println("The Marks Table is as follows:  ");
        System.out.println("The total marks is :"+Totalmarks);
        System.out.println("The total Percentage is :"+Percent);
        System.out.println("The  Grade  is :"+Grade);
    }

    public static int totalmarks(int m1, int m2, int m3) {

        return m1 + m2 + m3;
    }

    public static double percent(int Totalmarks){

        double Percentage=Totalmarks/300.00;
        return Percentage*100;
    }

    public static char grading (double Percent) {

        return (Percent>=90)? 'A':
                (Percent>=80)?'B':
                (Percent>=70)?'C':
                (Percent>=60)?'D':
                (Percent>=50)?'E':'F';
    }


}
