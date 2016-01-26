import se.kth.id1020.Vertex;

public class Node{
    private boolean visited = false;
    private Vertex vertex;

    public Node(Vertex vertex){
        this.vertex = vertex;
    }

    public Vertex getVertex(){
        return vertex;
    }

    public boolean isVisited(){
        return visited;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }
}
