package a_practisee;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.IOException;
public class Solution {
    public static int numberNeeded(String first, String second) {
    	char flag;
    	int count=0;
    	if (first.length() == 0 || second.length() ==0){
    		count = first.length();
    		count = second.length();
    		return count;
    	}
      	//Comparing first to Second
    	for (int i=0;i<first.length();i++){
    		flag ='x';
   innerloop:   for (int j=0;j<second.length();j++){
            	if (first.charAt(i) == second.charAt(j)){
                    flag = ' ';
                    break innerloop;
                    }         
            }

            if (flag == 'x'){
                    count = count + 1;
                }
    	}
    	//Compare second to first
    	for (int i=0;i<second.length();i++){
    		flag ='x';
        innerloop:    for (int j=0;j<first.length();j++){

            	if (second.charAt(i) == first.charAt(j)){
                    flag = ' ';
                    break innerloop;
                    }         
            }

            if (flag == 'x'){
                    count = count + 1;
                }
    	}

    	//Returning result
    	return count;
}            
				
        public static void main(String[] args) {
        int count =0;
            String b;
            String a ;
            Scanner in = new Scanner(System.in);
        	//
        	
        	if(!(a = in.nextLine()).isEmpty()) {    	          
        	
	        	if(!(b = in.nextLine()).isEmpty()) {
	        		
	    	        System.out.println(numberNeeded(a, b));  
	    	      
	        	}
        	count = a.length();
	        System.out.println(count);
	        
        	}  else {
        	
        	 System.out.println("Please enter 2 Strings for Anagram");
        	}
        	
    }
}
