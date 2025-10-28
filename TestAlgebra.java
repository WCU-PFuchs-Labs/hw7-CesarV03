import java.util.Random;

public class TestAlgebra {

    private static Random rand = new Random();

    private static Binop randomBinop() 
   {
        int choice = rand.nextInt(4);
        switch (choice) {
            case 0: return new Plus();
            case 1: return new Minus();
            case 2: return new Mult();
            default: return new Divide();
        }
    }

    private static Node randomTerminal() 
   {
        boolean heads = rand.nextBoolean();
        if (heads) {
            double value = 1 + rand.nextInt(20);
            return new Node(new Const(value));
        } else {
            int index = rand.nextInt(3);
            return new Node(new Variable(index));
        }
    }

    private static Node randomTree() 
  {
        Node left = new Node(randomBinop(), randomTerminal(), randomTerminal());
        Node right = new Node(randomBinop(), randomTerminal(), randomTerminal());
        return new Node(randomBinop(), left, right);
    }

    public static void main(String[] args) 
   {
        double[][] testValues = 
        {
            {1.0, 2.0, 3.0},
            {2.0, 1.0, 0.0}
        };

        for (int i = 0; i < 2; i++) {
            Node tree = randomTree();
            for (double[] vals : testValues) {
                System.out.println("When {X0,X1,X2} = {" 
                        + vals[0] + "," + vals[1] + "," + vals[2] + "}:");
                System.out.println(tree.toString() + " = " + tree.eval(vals));
                System.out.println();
            }
        }
    }
}
