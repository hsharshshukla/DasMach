package a_a;


import java.io.*;
import java.util.*;

public class LoopReview {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int n = scanner.nextInt();
        int odd_count=0,even_count=0;
        
        for (int i=0; i<n;i++){
            String line = scanner.next();            
            String even="", odd="";
            int len = line.length();
            for (int j=0;j<len;j++){
            	if (j%2==0){
            		even = even + line.charAt(j);
            	}
            	else {
            		odd = odd + line.charAt(j);
            	}
            }
            System.out.println(even + "  "+ odd);
            /* char[] array = line.toCharArray();
            int len = array.length;
            char[] odd = new char[len];
            char [] even = new char[len];
            
            if (i%2==0){
                
                even[even_count] = array[i];
                
                even_count = even_count + 1;
            }
            else {
                
                    odd[odd_count] =  array[i];
                
                odd_count = odd_count + 1;
            }*/
            
            //Print 
            
            
        }
        
        
    }
}