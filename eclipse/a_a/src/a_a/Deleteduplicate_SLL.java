package a_a;

// Deleteduplicate_SLL 
import java.io.*;
import java.util.*;
class ddNode{
	int data;
	ddNode next;
	ddNode(int d){
        data=d;
        next=null;
    }
	
}
class Deleteduplicate_SLL 
{
	public static ddNode removeDuplicates(ddNode head) {
		 //Write your code here
        if (head == null){
            return head;
        }
        else {
            ddNode node = head;
            while (node.next != null){
                if(node.data == node.next.data){
                    node.next = node.next.next;
                } else {
                node = node.next ;
                }
            }                     
            return head;
        }
	    }
	 public static  ddNode insert(ddNode head,int data)
     {
        ddNode p=new ddNode(data);			
        if(head==null)
            head=p;
        else if(head.next==null)
            head.next=p;
        else
        {
            ddNode start=head;
            while(start.next!=null)
                start=start.next;
            start.next=p;

        }
        return head;
    }
    public static void display(ddNode head)
        {
              ddNode start=head;
              while(start!=null)
              {
                  System.out.print(start.data+" ");
                  start=start.next;
              }
        }
        public static void main(String args[])
        {
              Scanner sc=new Scanner(System.in);
              ddNode head=null;
              int T=sc.nextInt();
              while(T-->0){
                  int ele=sc.nextInt();
                  head=insert(head,ele);
              }
              head=removeDuplicates(head);
              display(head);

       }
    }
	