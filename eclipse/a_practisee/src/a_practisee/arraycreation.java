package a_practisee;
import java.util.Scanner;
public class arraycreation {
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int x = in.nextInt();
		int r = n-2;
		//Calculate Combination - kcr = k!/ (r!*(k-1)!)
	//Calculating factorial 
		long kf=1;
		long rf =0;
		long krf=0;
		int M =1000000009;
		for (int i=1;i<=k;i++){			
			kf = (kf*i)%M;
			
		if(i==r){
			rf = kf;
		}
		if (i==(k-r)){
			krf=kf;
		}
			
		}
		//Calculatin of combination 
		
		System.out.println(kf/(rf*krf));
		
		in.close();
	}
   	
}
