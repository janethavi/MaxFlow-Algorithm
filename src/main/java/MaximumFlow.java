import java.util.ArrayList;
import java.util.LinkedList;
/*Name- T.J.A.Fernando
IIT Number- 2017268
UoW Number-w1697713
 */
public class MaximumFlow {
    private static int numOfNodes ;    //Number of vertices in graph

    /* Returns true if there is a path from source 's' to sink
      't' in residual graph. Also fills parent[] to store the
      path */
    ArrayList<Integer> augPaths = new ArrayList();

    boolean bfs(int rGraph[][], int sourceNode, int sinkNode, int parent[])
    {
        // Create a visited array and mark all vertices as not
        // visited
        boolean visited[] = new boolean[numOfNodes];
        for(int i=0; i<numOfNodes; ++i)
            visited[i]=false;
        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(sourceNode);
        visited[sourceNode] = true;
        parent[sourceNode]=-1;
        // Standard BFS Loop
        while (queue.size()!=0)
        {
            int u = queue.poll();
            for (int v=0; v<numOfNodes; v++)
            {
                if (visited[v]==false && rGraph[u][v] > 0)
                {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[sinkNode] == true);
    }
    // Returns tne maximum flow from s to t in the given graph
    int fordFulkerson(int graph[][], int sourceNode, int sinkNode,int numOfNodes)
    {
        setNumOfNodes(numOfNodes);
        int u, v;
        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph
        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        int rGraph[][] = new int[numOfNodes][numOfNodes];
        for (u = 0; u < numOfNodes; u++)
            for (v = 0; v < numOfNodes; v++)
                rGraph[u][v] = graph[u][v];
        // This array is filled by BFS and to store path
        int parent[] = new int[numOfNodes];
        int max_flow = 0;  // There is no flow initially
        // Augment the flow while there is path from source
        // to sink
        while (bfs(rGraph, sourceNode, sinkNode, parent))
        {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (v=sinkNode; v!=sourceNode; v=parent[v])
            {
                augPaths.add(v);
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }
            Graph.printAugPaths(augPaths,path_flow);
            augPaths.clear();

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v=sinkNode; v != sourceNode; v=parent[v])
            {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }
            // Add path flow to overall flow
            max_flow += path_flow;
        }
        // Return the overall flow
        return max_flow;
    }

    public static void setNumOfNodes(int numOfNodes) {
        MaximumFlow.numOfNodes = numOfNodes;
    }
}
