package Ben_Fisher_HW4;

import java.util.*;

//@author Ben Fisher

public class Graphs {
    
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        
        if(source == destination){
            return false; // Since no self-loops this case is always false
        }
        
        // Use a map implementation that describes each point and its edge to other points
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // Create every vertex and its arraylist of edges
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        // Build the graph from edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Repurposed BFS from IC8
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        visited[source] = true;
        queue.add(source);

        while (queue.size() != 0) {
            int cur = queue.poll();
            if (cur == destination){return true;} //returns true when destination reached

            //looks at all edges out of current node
            for (int neighbor : graph.get(cur)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        // If BFS finishes without returning true, return false
        return false;
    }

}
