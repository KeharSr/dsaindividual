// 5b)

// A network consisting of n servers is connected in a tree structure, where the servers are numbered from 0 to n -
// 1 and there are n - 1 connections between them that only allow for one-way communication. A 2D array a is
// used to represent these connections, where a[i] = [ai, bi] represents a directed path from server ai to server bi.
// However, due to specific requirements, all traffic from each server must route to server 0. The task is to
// reorient some connections to ensure that each server has a path to server 0. The goal is to minimize the number
// of edges that need to be changed. It is guaranteed that every server must have a path to server 0 after the
// connections are reordered.
// Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
// Output: 3
// Explanation: Change the direction of edges show in red such that each node can reach the node 0.

import java.util.ArrayList;
import java.util.List;

public class ReorientConnections {

    // Depth-First Search (DFS) function to calculate the number of edges to be reversed
    private static int dfs(int node, boolean[] visited, List<List<Integer>> adjList) {
        visited[node] = true;
        int edgesToReverse = 0;

        // Traverse through neighbors of the current node
        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                // Recursive DFS call to calculate edges for the neighbor
                edgesToReverse += dfs(neighbor, visited, adjList) + 1;
            }
        }

        return edgesToReverse;
    }

    // Function to find the minimum number of edges to be reversed
    public static int minReorientConnections(int n, int[][] connections) {
        // Create an adjacency list to represent the graph
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Populate the adjacency list with connections
        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];
            adjList.get(from).add(to);
        }

        // Array to track visited nodes during DFS
        boolean[] visited = new boolean[n];
        // Array to store the number of edges to be reversed for each server
        int[] edgesToReverse = new int[n];

        // Calculate the number of edges to be reversed for each server using DFS
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                edgesToReverse[i] = dfs(i, visited, adjList);
            }
        }

        // Find the server with the maximum count of edges to be reversed
        int maxEdgesToReverse = 0;
        int maxEdgesServer = 0;
        for (int i = 0; i < n; i++) {
            if (edgesToReverse[i] > maxEdgesToReverse) {
                maxEdgesToReverse = edgesToReverse[i];
                maxEdgesServer = i;
            }
        }

        return maxEdgesToReverse;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};

        // Find and print the minimum number of edges to be reversed
        int result = minReorientConnections(n, connections);
        System.out.println("Minimum number of edges to reverse: " + result); // Output: 3
    }
}
