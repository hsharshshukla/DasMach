package a_a;

import java.util.*;
import java.io.*;
class bfsNode{
    bfsNode left,right;
    int data;
    bfsNode(int data){
        this.data=data;
        left=right=null;
    }
}
class BFS{
	static void levelOrder(bfsNode root){
	//Begin of Code
	Queue<bfsNode> q = new LinkedList<bfsNode>();
	q.add(root);
	while(!q.isEmpty()){
		bfsNode node;
		node = q.poll();		
		System.out.print(node.data+ " ");
		
		if(node.left !=null ){
			q.add(node.left);
		}
		if (node.right !=null ){
			q.add(node.right);
		}
	}
	
	//End of Code
	}
	
	public static bfsNode insert(bfsNode root,int data){
        if(root==null){
            return new bfsNode(data);
        }
        else{
            bfsNode cur;
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
            bfsNode root=null;
            while(T-->0){
                int data=sc.nextInt();
                root=insert(root,data);
            }
            levelOrder(root);
        }	
}