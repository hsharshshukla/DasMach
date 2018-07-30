package a_a;

import java.util.*;
import java.io.*;

class nNode{
    nNode left,right;
    int data;
    nNode(int data){
        this.data=data;
        left=right=null;
    }
}
class heigthbst{
	public static int getHeight(nNode root){
	      //Write your code here
	    //Begin of Code 
	    if (root == null){
	        return -1;
	    }
	    else {
	        int ldepth = getHeight(root.left);
	        int rdepth = getHeight(root.right);
	        if (ldepth> rdepth){
	            return (ldepth+1);
	        }else {
	            return (rdepth+1);
	        }
	    }
	    //End of Code            
	    }
	 public static nNode insert(nNode root,int data){
	        if(root==null){
	            return new nNode(data);
	        }
	        else{
	            nNode cur;
	            if(data<=root.data){
	                cur=insert(root.left,data);
	                root.left=cur;
	            }
	            else{
	                cur=insert(root.right,data);
	                root.right=cur;
	            }
	            return root;
	        }
	    }
		 public static void main(String args[]){
	        Scanner sc=new Scanner(System.in);
	        int T=sc.nextInt();
	        nNode root=null;
	        while(T-->0){
	            int data=sc.nextInt();
	            root=insert(root,data);
	        }
	        int height=getHeight(root);
	        System.out.println(height);
	    }
	}	
	

