package a_a;


import java.io.*;
import java.util.*;

public class stackqueue {
	//Begin of Code
	//char arr[],queue[];
	int tops,sizes,lens;
	int frontq,rearq,sizeq,lenq;
	
	// Stack
	 Stack<Character> stack = new Stack<Character>();
	// Queue
	 Queue<Character> q = new LinkedList<Character>();
	 
	 //void push
	 void pushCharacter(char ch){
		 stack.push(ch);
	 }
	//Void Enqueue
	 void enqueueCharacter(char ch){
		 q.add(ch);
	 }
	 
	 //Pop 
	 char popCharacter() {
		 stack.pop();
		 return stack.peek();
	 }
	 
	 //Dequeue
	 char dequeueCharacter(){
		 q.remove();
		 return q.peek();
	 }
	//End of Code
public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String input = scan.nextLine();
    scan.close();

    // Convert input String to an array of characters:
    char[] s = input.toCharArray();

    // Create a Solution object:
    stackqueue p = new stackqueue();

    // Enqueue/Push all chars to their respective data structures:
    for (char c : s) {
        p.pushCharacter(c);
        p.enqueueCharacter(c);
    }

    // Pop/Dequeue the chars at the head of both data structures and compare them:
    boolean isPalindrome = true;
    for (int i = 0; i < s.length/2; i++) {
        if (p.popCharacter() != p.dequeueCharacter()) {
            isPalindrome = false;                
            break;
        }
    }

    //Finally, print whether string s is palindrome or not.
    System.out.println( "The word, " + input + ", is " 
                       + ( (!isPalindrome) ? "not a palindrome." : "a palindrome." ) );
}
}