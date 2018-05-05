package a_practisee;
import java.util.Scanner;
public class Combination {
public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int arr[]= {1,2,3,4,5};
	int n = arr.length;
	
	for (int i=2;i<=5;i++){
		int r =i;
		printCombination(arr,n,r);
	}
}//main method ends here
//Print combination method 
 static void printCombination(int arr[],int n, int r){
	 int [] data = new int[r];
	 combination(arr,n,r,0,data,0); //
 }

 //Combination 
 static void combination(int arr[],int n,int r,int index,int data[],int i){
	//Current Combination
	 if(index==r){
		 for (int j=0;j<r;j++){
			 System.out.println(data[j]+" ");
		 }
		 System.out.println("");	 
		 return; 
	 }
	 //
	 if(i>=n){
		 return;
	 }
	 //Current is included, put next at next location
	 data[index] = arr[i];
	 combination(arr,n,r,index+1,data,i+1);
	 
	 //Current is excluded, replace it with next
	 combination(arr,n,r,index,data,i+1);
	 
 }
}
