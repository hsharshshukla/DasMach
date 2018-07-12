package a_a;

import java.io.*;
import java.util.*;

public class Numberofone {
	
	private static final Scanner scanner = new Scanner(System.in);
	public static void main(String args[]){
		int n = scanner.nextInt();
		// ones
		int ones =0,a=0,oness=0;
		while(n>0){
			
			a = n%2;
			if (a==1){
				ones = ones + 1;
			}
			else{
				if(oness < ones){
				oness=ones;
				}
				ones=0;
				
			}
			
			n = n/2;
		}
		if(oness > ones){
			ones=oness;
		}
		
		System.out.println(oness);
	}
}
