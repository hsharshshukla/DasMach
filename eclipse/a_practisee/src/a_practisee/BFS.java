package a_practisee;
import java.util.*;
import java.io.*;

public class BFS {
	private int V; //Number of Vertices
	private LinkedList<Integer>  adj[];
	
	BFS (int v){
		V = v;
		adj = new LinkedList[v];
		for (int i=0; i<v; i++){
			adj[i] = new LinkedList();
			}
		}
	void addEdge (int v, int w)
	   {
		adj[v].add(w);
		}

	void BFS (int s){
		//Mark all the Vertices not visited
		boolean visited[] = new boolean[V];
		
		//Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<>();
		
		//Mark Current Node as visited
		visited[s]=true;
		queue.add(s);
		
		
		
	}
	
	
	public static void main(String args[]){
		BFS g = new BFS(4);
		g.addEdge(0,1);
		g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        
        System.out.println("This is Breadth First Traversal of Graph" + "(" +"starting from vertext 2" + ")");
        
	}
	
}
