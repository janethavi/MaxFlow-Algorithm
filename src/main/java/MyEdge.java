import org.jgrapht.graph.DefaultEdge;
/*Name- T.J.A.Fernando
IIT Number- 2017268
UoW Number-w1697713
 */
public class MyEdge extends DefaultEdge {
    private String capacity;
    private int[][] row = Graph.getRow();
    public void generateString() {
        int a=Integer.parseInt(String.valueOf(getSource()));
        int b=Integer.parseInt(String.valueOf(getTarget()));
        capacity=Integer.toString(row[a][b]);
    }

    public String toString() {
        generateString();
        return this.capacity;
    }

}
