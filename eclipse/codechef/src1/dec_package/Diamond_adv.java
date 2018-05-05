package dec_package;

import java.util.Scanner;
public class Diamond_adv{ 
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		int t = stdin.nextInt();
		if(1<=t && t<=100000){
	//Declaration
			int sum=0;
			int room = 0 ;
			int digit=0;
			int even_sum=0;
			int odd_sum=0;
			int diamonds =0;
			int a =0;
			
		for (int i=0;i<t;i++){
			diamonds =0;
			a =stdin.nextInt();
			if(1<=a&&a<=1000000){
			for (int j=1;j<=a;j++){
				for (int k=j;k<=a;k++){
					sum=0;
					room = j+k ;
					digit=0;
					even_sum=0;
					odd_sum=0;
					
//Check how many even and odd digits present
					while(room>0){
					digit = room % 10;				
					
					if(digit%2==0){
						even_sum=even_sum+digit;
					}
					else{
						odd_sum=odd_sum+digit;
					}
					room = room/10;
				}	
					sum = even_sum-odd_sum;
					if (sum<0){
						sum = sum * (-1);
					}
					if(j == k){}else{sum = 2*sum;}
					diamonds = diamonds + sum;
				}			
			}
			System.out.println(diamonds);
			}
		//Printing Total Diamonds in House
			
		}
		}
		stdin.close();
	}

}
