import edu.princeton.cs.algs4.Queue;
import se.kth.id1020.Edge;
import se.kth.id1020.Graph;

import java.util.Iterator;

public class BFS {
    private boolean[] visited;
    private double[] distTo;
    private Edge[] edgeTo;

    /**
     * Performs breadth first search
     * @param g is the graph that search is going to be performed on
     * @param startVertexID is the starting index for the tree index
     * @param weight is if weight should be accounted for
     */
    public void performSearch(Graph g, int startVertexID, boolean weight){
        Queue<Integer> q = new Queue<Integer>();
        visited = new boolean[g.numberOfEdges()];
        distTo = new double[g.numberOfEdges()];
        edgeTo = new Edge[g.numberOfEdges()];
        if(weight)
            for (int i = 0; i < distTo.length; i++)
                distTo[i] = Double.POSITIVE_INFINITY;
        distTo[startVertexID] = 0.0;
        visited[startVertexID] = true;
        q.enqueue(startVertexID);

        while(!q.isEmpty()){
            int dequeued = q.dequeue();
            if(weight)
                visited[dequeued] = false;

            Iterator<Edge> childIterator = g.adj(dequeued).iterator();
            while (childIterator.hasNext()){
                Edge currEdge = childIterator.next();
                int currChild = currEdge.to;

                if(weight){
                    if(distTo[currChild] > distTo[dequeued] + currEdge.weight){
                        distTo[currChild] = distTo[dequeued] + currEdge.weight;
                        edgeTo[currChild] = currEdge;
                        if(!visited[currChild]){
                            q.enqueue(currChild);
                            visited[currChild] = true;
                        }
                    }
                }else {
                    if (!visited[currChild]) {
                        q.enqueue(currChild);
                        edgeTo[currChild] = currEdge;
                        distTo[currChild] = distTo[dequeued] + 1;
                        visited[currChild] = true;
                    }
                }
            }
        }
    }

    /**
     * Print path to given index
     * @param endVertexID is the given end index
     */
    public void print(int endVertexID){
        System.out.println("Path to " + endVertexID);
        for(Edge e = edgeTo[endVertexID]; e != null; e = edgeTo[e.from])
            System.out.println(e);
    }
}
