package codechef;
import java.util.Scanner;
public class SquareRoot{
	public static void main(String args[]){
    	Scanner in = new Scanner(System.in);
    	int l = in.nextInt(),n=0;  
    	for (int i=0;i<l;i++){//3
	        n = in.nextInt();
    	    int y=1;
	        int x=n;
            while(x > y) 
            {
                x = (x + y)/2;  
                y = n/x; 
            }
  
    	    System.out.println(x);
	    }
	}
}
