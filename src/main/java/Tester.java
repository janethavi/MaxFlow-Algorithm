import java.util.Scanner;
/*Name- T.J.A.Fernando
IIT Number- 2017268
UoW Number-w1697713
 */
public class Tester {
    public static void main(String[] args) {
        int choice;
        Graph s = new Graph();
        do {
            //asking if the user wants to manually enter values or randomly enter values
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter 1 to randomly generate a graph. Enter 2 to manually enter values..");
            choice = sc.nextInt();
            if (choice == 1) {
                s.generateRandomGraph();
                s.printGraph();
                break;
            }
            if (choice == 2) {
                s.getValuesFromUser();
                s.printGraph();
                break;
            }else {
                System.err.println("Please enter the correct number..");
            }
        }while (choice!=1||choice!=2);

        MaximumFlow m = new MaximumFlow();
        int sourceNode = 0;
        s.printTable();
        System.out.println("The maximum possible flow is " +
               m.fordFulkerson(s.getRow(), sourceNode, s.getNumNodes() - 1, s.getNumNodes()));
       JGraphT.myGraph();
    }
}
