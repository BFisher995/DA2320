package Ben_Fisher_IC8;

import java.util.*;

public class Graph {
    private int V; // Number of vertices
    private LinkedList<Integer> adj[]; // Adjacency list

    // Constructor
    public Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public void DFS(int s) {
        boolean visited[] = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.pop(); 
            if (!visited[v]) {
                System.out.print(v + " ");
                visited[v] = true;
            }
            for (int n : adj[v]) {
                if (!visited[n]) {
                    stack.push(n);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Depth First Traversal starting from vertex 2:");
        g.DFS(2);
    }
}