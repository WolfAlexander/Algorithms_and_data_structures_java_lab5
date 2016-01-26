import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;
import se.kth.id1020.Vertex;

import java.util.Iterator;

public class Paths {
    public static void main(String[] args) {
        Graph g = DataSource.load();
        // work on g
        connectedGraph(g);
        shortestPath(g);
    }

    private static void connectedGraph(Graph g){
        int foundConnectedVertices = 0;
        int startVertexID = 0;
        int countSubGraphs = 0;
        DFS dfs = new DFS();


        while(foundConnectedVertices <= g.numberOfVertices()){
            dfs.performSearch(g, startVertexID);
            countSubGraphs++;
            foundConnectedVertices += dfs.getCountVerticesInGraph();
            int nextFirstUnvisited = dfs.findFirstUnvisited();
            if(nextFirstUnvisited == g.numberOfVertices())
                break;
            else
                startVertexID = dfs.findFirstUnvisited();
        }

        if(countSubGraphs > 0)
            System.out.println("Graph g is not connected!\nNumber of subgraphs: " + countSubGraphs);
        else
            System.out.println("Graph g is connected!");
    }

    private static void shortestPath(Graph g){
        int startVertexID = 0;
        int endVertexID = 0;
        Iterator<Vertex> t = g.vertices().iterator();

        while (t.hasNext()){
            Vertex currVertex = t.next();
            if(startVertexID == 0 && currVertex.label.equals("Renyn"))
                startVertexID = currVertex.id;
            else if(endVertexID == 0 && currVertex.label.equals("Parses"))
                endVertexID = currVertex.id;

            if(startVertexID != 0 && endVertexID != 0)
                break;
        }

        BFS bfs = new BFS();
        bfs.performSearch(g, startVertexID, false);
        System.out.println("\nA) Ignoring weight: ");
        bfs.print(endVertexID);

        System.out.println("\nB) Taking weight into account: ");
        bfs.performSearch(g, startVertexID, true);
        bfs.print(endVertexID);
    }
}