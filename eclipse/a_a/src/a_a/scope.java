
package a_a;


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


class Difference {
  	private int[] elements;
  	public int maximumDifference;
  	//Start of Code 
  	public Difference(int [] a){
  		elements = a;
  	}
  	public void computeDifference(){
  		for (int i=0;i<elements.length;i++){
  			for (int j=0;j<elements.length-i;j++){
  				int diff=0;
  				diff = elements[i]-elements[j];
  				if (diff <0){
  					diff = diff * (-1);
  				}
  				if (maximumDifference < diff){
  					maximumDifference = diff;
  				}
  				else {
  					
  				}
  			}
  		}
  	}
  	//End of Code
  	
} // End of Difference class

public class scope {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close();

        Difference difference = new Difference(a);

        difference.computeDifference();

        System.out.print(difference.maximumDifference);
    }
}  	