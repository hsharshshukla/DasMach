package codechef;
import java.util.Scanner;
public class luckyfour {
	public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int t = in.nextInt(),c=0;
	int n=0;
	for (int i =0;i<t;i++){
		n=in.nextInt();
		c =0;
	while (n >0){
		if ((n%10)==4){
			c++;			
		}
		n = n/10;
	}
	System.out.println(c);
 }
	}
}	

