package a_practisee;
 import java.util.Scanner;    
 import a_practisee.Node;  
 
 /* Class Node */
 
 /* Class BST */
 class BST
 {
     private Node root;
 
     /* Constructor */
     public BST()
     {
         root = null;
     }
     /* Functions to insert data */
     public void insert(int data)
     {
         root = insert(root, data);
     }
     /* Function to insert data recursively */
     private Node insert(Node node, int data)
     {
         if (node == null)
             node = new Node(data);
         else
         {
             if (data <= node.data)
                 node.left = insert(node.left, data);
             else
                 node.right = insert(node.right, data);
         }
         return node;
     }
     /* Function for inorder traversal */
     public void inorder()
     {
         inorder(root);
     }
     private void inorder(Node r)
     {
         if (r != null)
         {
             inorder(r.left);
             System.out.print(r.data +" ");
             inorder(r.right);
         }
     }
     /* Function for preorder traversal */
     public void preorder()
     {
         preorder(root);
     }
     private void preorder(Node r)
     {
         if (r != null)
         {
             System.out.print(r.data +" ");
             preorder(r.left);             
             preorder(r.right);
         }
     }
     /* Function for postorder traversal */
     public void postorder()
     {
         postorder(root);
     }
     private void postorder(Node r)
     {
         if (r != null)
         {
             postorder(r.left);             
             postorder(r.right);
             System.out.print(r.data +" ");
         }
     }     
 }
 
 /* Class LinkedListBST */
 public class LinkedListBST
 {
     public static void main(String[] args)
     {                 
         Scanner scan = new Scanner(System.in);
         /* Creating object of BST */
         BST bst = new BST(); 
         System.out.println("Linked List Binary Search Tree Test\n");          
         char ch;
         /*  Accept input  */
         do    
         {
             System.out.println("Enter integer element to insert");
             bst.insert( scan.nextInt() );                     
 
             /*  Display tree  */ 
             System.out.print("\nPost order : ");
             bst.postorder();
             System.out.print("\nPre order : "); 
             bst.preorder();
             System.out.print("\nIn order : ");
             bst.inorder();
 
             System.out.println("\nDo you want to continue (Type y or n) \n");
             ch = scan.next().charAt(0);                        
         } while (ch == 'Y'|| ch == 'y');                    
     }
 }