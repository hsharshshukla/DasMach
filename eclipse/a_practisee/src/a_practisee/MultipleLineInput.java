package a_practisee;
import java.io.BufferedInputStream;
import java.util.Scanner;
public class MultipleLineInput {
	public static void main(String args[]){
		System.out.println("Please Provide Input : ");
		Scanner input = new Scanner(new BufferedInputStream(System.in));
		while(input.hasNext()){
			System.out.println(input.next());
		}
		
		
	}
	

}
