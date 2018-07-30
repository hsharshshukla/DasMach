package a_a;
import java.util.*;
import java.io.*;


class festival {
    public static void main(String args[] ) throws Exception {
        //Scanner
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
    //loop for all spending and all test cases        
        for  (int i=0;i<2;i++){ 
        	String result="";
            int ns = s.nextInt();
            String[] spending_array= new String[ns];
            int[] spending_amt = new int[ns];
            for (int j=0;j<ns;j++){              
            	spending_array[j]= s.next();
            	spending_amt[j] = s.nextInt();
            }
            
            //Unique array for Spending
            	String[] distinct = Arrays.stream(spending_array).distinct().toArray(String[]::new);
           
            //Sort Distinct
            Arrays.sort(distinct);
            
            //Process and calculate max spending 
             
            int sum=0;
            for (int k=0;k<distinct.length;k++){
            	int[] s_max= new int[ns];//array for
            	int count=0;
             // Create s_max array for storing values of key
                for (int p=0;p<spending_array.length;p++){
                	if(distinct[k].equals(spending_array[p])){
                	s_max[count] = spending_amt[p];
                	count++;
                	}
                }
            //Sort s_max and find top 3 expenses
             count=0;
             for (int m=0; m<s_max.length;m++){
                 for (int n=0;n<s_max.length-1;n++){
                     if(s_max[n]<s_max[n+1]){//4,3;7,3;7,4;3,4
                         int sm = s_max[n];
                         s_max[n]=s_max[n+1];
                         s_max[n+1]= sm;
                     }
                 }
             }
             //get top 3 max values for spending 
             if(sum < (s_max[0]+s_max[1]+s_max[2])){
             sum = s_max[0]+s_max[1]+s_max[2];
             result = distinct[k]+ " "+sum;             
             }
             //distinct_val[k] = sum;
             }
            if(result != ""){
            System.out.println(result);
            }
        }
        
        //String name = s.nextLine();                 // Reading input from STDIN
        //System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        

        // Write your code here

    }
}
