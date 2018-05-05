package a_practisee;

import java.util.Scanner;
import java.io.BufferedInputStream;

public class DiwaliLights {
	
	static int count_nodes = 0; 
	static int p=0;
	static int prob[]=new int[count_nodes-1];
	static int break_edge[] = new int[2];
	//Set tree as per the input in line 
static class Node {
	int key;
	Node left,right;
	public Node(int item){
		key = item;
		left = right = null;
	}
}
static class BinTree{
	Node root;

		
	//Create Tree Method
		Node createTree(int parent[], int n){
			Node[]created = new Node[n+1];
			for (int i=0;i<n+1;i++)
				created[i] =null;
			for (int i=0;i<n+1;i++)
				createNode(parent,i,created);		
			
			return root;
		}
	//Create Nodes
		void createNode(int parent[],int i, Node created[]){
			if(created[i] != null)
				return;
			created[i] = new Node(i);
			
			if (parent[i]==-1){
				root = created[i];
				
				return;
			}
			if (created[parent[i]] ==null)
				createNode(parent,parent[i],created);
			
			Node p= created[parent[i]];
			if(p.left==null)
				p.left = created[i];
			else 
				p.right=created[i];
			
		}
	
 
 void Postorder(Node node){
	 if(node != null){
		 //counter increment
		 
		 Postorder(node.left);
		 //return;
		 Postorder(node.right);
		 //Calculate Probability
		 System.out.print(node.key + "");
		 //Decrement counter
	 }
 }

}
public static void main (String args[]){
	System.out.println("Please enter the Input :nodes and pair of vertices having bound by edge");
	Scanner input = new Scanner(new BufferedInputStream(System.in));
	// nodes below is total number of nodes in Tree
	int count_nodes_l = input.nextInt();
	 count_nodes = count_nodes_l;
	//int vert[]= new int[2*count_nodes];
	//int count=0;
	int vertice=0;
	//char flag=' ';
	int parent[] = new int[count_nodes+1];

	int par_index=1;
	//Prepare the parent array 
	parent[1] =-1;
	for (int k=0;k<count_nodes-1;k++){
		
		vertice =input.nextInt();
		
		par_index=input.nextInt();
		parent[par_index]=vertice;
		
	}


	BinTree tree = new BinTree();
	Node node = tree.createTree(parent,count_nodes);
	System.out.println("Inorder Traversal of Tree: ");
	tree.Postorder(node);
}	
	//Count rest of nodes after detaching edge between each input
//take average of all the counts and return that in your answer
	
	
}

