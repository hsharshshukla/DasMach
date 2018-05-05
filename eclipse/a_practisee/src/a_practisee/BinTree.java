package a_practisee;
import a_practisee.Node;
//Binary Tree
public class BinTree{
	Node root;
	BinTree(int key)
	{
		root = new Node(key);
		
	}
	BinTree(){
		root = null;
	}
	
//Recursive Methods;
	void printPostorder(Node node){
		if (node == null)
			return;
		//increment counter 
		printPostorder (node.left);
		printPostorder(node.right);
		//cal prob for this node
		System.out.print(node.key + " ");
		//decrement counter
	}
	/*void printPreorder(Node node){
		if (node == null)
			return;
		System.out.print(node.key + " ");
		printPreorder (node.left);
		printPreorder(node.right);
		
	}
	
	void printInorder(Node node){
		if (node == null)
			return;
		printInorder (node.left);
		System.out.println(node.key + " ");
		printInorder(node.right);
		
	}*/

	
//Wrappers above Recursive Traversal Methods
	void printPostorder() {
		printPostorder(root);
	}
	/*void printPreorder() {
		printPreorder(root);
	}
	void printInorder() {
		printInorder(root);
	}
*/	
	
	public static void main(String args[]){
		BinTree tree = new BinTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
/* Tree Structure
        1
      /   \
     2      3
   /    \    /  \
 null null null null  */
	/*	System.out.println("Preorder traversal of binary tree is ");
        tree.printPreorder();
 
        System.out.println("\nInorder traversal of binary tree is ");
        tree.printInorder();
 */
        System.out.println("\nPostorder traversal of binary tree is ");
        tree.printPostorder();
	}
	
}