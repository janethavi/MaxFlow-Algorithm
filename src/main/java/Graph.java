import java.util.ArrayList;
import java.util.Scanner;
/*Name- T.J.A.Fernando
IIT Number- 2017268
UoW Number-w1697713
 */
public class Graph {
    //number of nodes in the graph
    private int numNodes;
    //used to draw the graph
    //to decide if there is a edge or not
    private int[] choice = {0, 0};
    //number of columns in the matrix
    private int[] column;
    //number of rows in the matrix
    private static int[][] row;

    public Graph() {//by default the nodes are generated in the graph
        genNumNodes();
    }

    public void genNumNodes() {
        int maxNodes = 5;//max nodes with the source and the sink
        int minNodes = 5;//min nodes with the source and the sink
        int range = maxNodes - minNodes + 1;
        this.numNodes = (int) (Math.random() * range) + minNodes;
    }

    public void generateRandomGraph() {
        int genCapacity;
        column = new int[this.numNodes];
        row = new int[this.numNodes][];
        for (int i = 0; i < this.numNodes; i++) {
            for (int j = 0; j < this.numNodes; j++) {
                int maxCapacity = 20;
                int minCapacity = 5;
                int range = maxCapacity - minCapacity + 1;
                genCapacity = (int) (Math.random() * range) + minCapacity;
                choice[1] = genCapacity;
                if (i == j || j == 0 || i == this.numNodes - 1) {
                    column[j] = 0;
                } else {
                    column[j] = choice[(int) (Math.random() * 2)];
                }
            }
            row[i] = column.clone();
        }
        checkSourceAndSink();
    }

    //Check if the generated 2d array has only one source and only one sink
    //If not reenter values
    private void checkSourceAndSink() {
        boolean oneSource = false;
        boolean oneSink = false;
        for (int i = 1; i < this.numNodes; i++) {
            for (int j = 0; j < this.numNodes; j++) {
                if (row[j][i] != 0) {
                    oneSource = true;
                    break;
                }
                if (!oneSource && j == this.numNodes - 1) {
                    generateRandomGraph();
                    break;
                }
            }
            oneSource = false;
        }
        for (int i = 0; i < this.numNodes - 1; i++) {
            for (int j = 0; j < this.numNodes; j++) {
                if (row[i][j] != 0) {
                    oneSink = true;
                    break;
                }
                if (!oneSink && j == this.numNodes - 1) {
                    generateRandomGraph();
                    break;
                }
                oneSink = false;
            }
        }
    }

    //print the values in a matrix manner with the number of nodes
    public void printGraph() {
        System.out.println("The Graph is visualized in a matrix like this.....");
        System.out.println("The Number of nodes are " + numNodes+" including the source and the sink");
        for (int i = 0; i < this.numNodes; i++) {
            for (int j = 0; j < this.numNodes; j++) {
                System.out.printf("%5d ", row[i][j]);
            }
            System.out.println();
        }
    }


    //visualize the matrix in a table format
    public void printTable() {
        System.out.println("The graph can be visualized in a table like this...");
        for (int i = 0; i < this.numNodes; i++) {
            if (i == this.numNodes - 1) {
                break;
            }
            System.out.println("From Node " + i);
            for (int j = 0; j < this.numNodes; j++) {
                if (row[i][j] != 0) {
                    System.out.printf("%5d", row[i][j]);
                    System.out.print(" --> " + j);
                }
            }
            System.out.println();
        }
    }

    public static int[][] getRow() {
        return row;
    }

    public int getNumNodes() {
        return numNodes;
    }


    //print the augmented paths in the console
    public static void printAugPaths(ArrayList<Integer> pathArr, int residualCapacity) {
        System.out.print("0 --> ");
        for (int i = pathArr.size()-1; i >=0; i--) {
                System.out.print(pathArr.get(i) + " --> ");
        }
        System.out.println("Minimum residual capacity in flow = " + residualCapacity);
    }

    //get the vale from the user if the choice is 2
    public void getValuesFromUser() {
        int capacity;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Please enter the Number of nodes you wish to add. It should be between 6 and 12 including the source and the sink");
            numNodes = sc.nextInt();
            if (numNodes < 6 || numNodes > 12) {
                System.err.println("Please enter a number between 6 and 12");
            }
        } while (numNodes > 12 | numNodes < 6);
        column = new int[this.numNodes];
        row = new int[this.numNodes][];

        System.out.println("Enter the capacities now... Capacities should be between 5 and 20");
        System.out.println("If there is no connection between nodes, enter 0.");
        for (int i=0;i<numNodes;i++){
            for (int j=0;j<numNodes;j++){
                if(j==0){
                    column[j]=0;
                    continue;
                }if(j==i){
                    column[j]=0;
                    continue;
                }if(i==numNodes-1){
                    column[j]=0;
                    continue;
                }
                else{
                    do{System.out.println("Enter the Capacity from "+i+" node to "+ j);
                        capacity=sc.nextInt();
                        if(capacity>20||(capacity<5&&capacity>0)||capacity<0){
                            System.err.println("Please enter a valid capacity to nodes between "+i+" and "+j);
                        }}while (capacity>20||(capacity<5&&capacity>0)||capacity<0 );
                    column[j]=capacity;
                }
            }
            row[i]=column.clone();
        }
    }
}
