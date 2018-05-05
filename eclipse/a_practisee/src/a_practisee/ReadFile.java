package a_practisee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
 private static final String Filename="/development/develop/test";
 public static void main(String args[]){
	 try(BufferedReader br = new BufferedReader( new FileReader (Filename))){
		 String currentline;
		 while ((currentline = br.readLine()) != null ){
			 System.out.println(currentline);
			 
		 }
	 } catch(IOException e){e.printStackTrace();}
 }
}
