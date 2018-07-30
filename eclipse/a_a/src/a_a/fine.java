package a_a;


import java.util.*;
import java.io.*;
public class fine {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int a_dd = scan.nextInt(); 
        int a_mm = scan.nextInt();
        int a_yr = scan.nextInt();
        int e_dd = scan.nextInt();
        int e_mm = scan.nextInt();
        int e_yr = scan.nextInt();
        int fine=0;
        if (a_yr < e_yr ){fine =0;}
        else if (a_yr == e_yr){
            if(a_mm <= e_mm){
                if(a_dd <= e_dd){
                    fine = 0;
                }else{
                    fine = 15 * (a_dd-e_dd);
                }      
            }else{
            fine = 500 * (a_mm - e_mm);
            }
        }else{
            fine = 10000;
        }
        System.out.println(fine);
     scan.close();   
    }
}
