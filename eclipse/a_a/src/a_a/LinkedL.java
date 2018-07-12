package a_a;




import java.io.*;
import java.util.*;

class Node {
	int data;
	Node next;
	Node(int d) {
        data = d;
        next = null;
    }
}

public class LinkedL {
	
	 public static  Node insert(Node head,int data) {
	        //Complete this method
		 Node ptr = new Node(data);
		 //if node null
		 	if(head ==null){
		 		head = new Node(data);
		 		ptr = null;
		 		
		 	}
		 	// Enter other Nodes
		 	else {		
		 		Node last = head;
		 		while (last.next != null){
		 			last = last.next;
		 		}
		 		last.next = ptr;
		 	}
	
		 //if node not null  
		 
		 //Return list
		 	return head;
	    }
	 
	 
	 public static void display(Node head) {
	        Node start = head;
	        while(start != null) {
	            System.out.print(start.data + " ");
	            start = start.next;
	        }
	    }

	    public static void main(String args[]) {
	        Scanner sc = new Scanner(System.in);
	        Node head = null;
	        int N = sc.nextInt();

	        while(N-- > 0) {
	            int ele = sc.nextInt();
	            head = insert(head,ele);
	        }
	        display(head);
	        sc.close();
	    }
	}	 