import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import java.util.Iterator;

public class DFS {
    private Graph g;
    private boolean[] visited;
    private int countVerticesInGraph;

    /**
     * This method start death fists search by declaring some necessary variables
     * and calls another recursive method for performing the search
     * @param g is the graph i question of type se.kth.id1020.Graph
     * @param startVertexID is the int id of the starting vertex
     */
    public void performSearch(Graph g, int startVertexID){
        if(this.g != null && this.g.equals(g)){
            this.countVerticesInGraph = 0;
        }else {
            this.g = g;
            this.visited = new boolean[g.numberOfVertices()];
            this.countVerticesInGraph = 0;
        }

        performSearch(startVertexID);
    }


    /**
     * This method returns number of connected vertices in the graph
     * @return int
     */
    public int getCountVerticesInGraph(){
        return countVerticesInGraph;
    }

    /**
     * This method finds and return id of a vertex that has not been connected yet
     * If there is no such vertex(all vertices are connected) the number of vertices in starting graph is returned
     * @return int
     */
    public int findFirstUnvisited(){
        for(int i = 0; i < visited.length; i++){
            if(!visited[i]){
                return i;
            }
        }

        return g.numberOfVertices();
    }

    private void performSearch(int vertexID){
        countVerticesInGraph++;
        this.visited[vertexID] = true;
        Iterator<Edge> adjIterator = g.adj(vertexID).iterator();

        while(adjIterator.hasNext()){
            int currNeighbor = adjIterator.next().to;
            if(!visited[currNeighbor])
                performSearch(currNeighbor);
        }
    }
}