import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;
import javax.swing.*;
import java.awt.*;
/*Name- T.J.A.Fernando
IIT Number- 2017268
UoW Number-w1697713
 */
public class JGraphT extends JApplet {

    private static final long serialVersionUID = 2202072534703043194L;
    private static final Dimension DEFAULT_SIZE = new Dimension(750, 750);
    private JGraphXAdapter<String, MyEdge> jgxAdapter;

    public static void myGraph() {
        JGraphT applet = new JGraphT();
        applet.init();

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("Maximum Flow Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void init() {
        // create a JGraphT graph
        ListenableGraph<String, MyEdge> g =
                new DefaultListenableGraph<String, MyEdge>
                        (new DefaultDirectedGraph<String, MyEdge>(MyEdge.class));

        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<String, MyEdge>(g);

        setPreferredSize(DEFAULT_SIZE);
        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        getContentPane().add(component);
        resize(DEFAULT_SIZE);

        int[][] row = Graph.getRow();
        String v1;
        String v2;

        for (int j = 0; j < row.length; j++) {
            g.addVertex(Integer.toString(j));
        }

        for (int i = 0; i < row.length; i++) {
            for (int j = 0; j < row.length; j++) {
                if (row[i][j] != 0) {
                    v1 = Integer.toString(i);
                    v2 = Integer.toString(j);
                    g.addEdge(v1, v2);
                }
            }
        }
        // positioning via jgraphx layouts
        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

        // center the circle
        int radius = 300;
        layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
        layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
        layout.setRadius(radius);
        layout.setMoveCircle(true);

        layout.execute(jgxAdapter.getDefaultParent());
    }
}
