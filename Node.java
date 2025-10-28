import java.util.Random;

public class Node implements Cloneable {
    protected Node left;
    protected Node right;
    protected Op operation;
    protected int depth;

    public Node(Binop op, Node left, Node right) 
    {
        this.operation = op;
        this.left = left;
        this.right = right;
        this.depth = 0;
    }

    public Node(Unop op) 
    {
        this.operation = op;
        this.left = null;
        this.right = null;
        this.depth = 0;
    }

    public Node(Binop op) 
    {
        this.operation = op;
        this.left = null;
        this.right = null;
        this.depth = 0;
    }
    
    public void addRandomKids(NodeFactory nf, int maxDepth, Random rand) {
        if (operation instanceof Unop) {
            return;
        }

        if (depth >= maxDepth) {
            left = nf.getTerminal(rand);
            left.depth = depth + 1;
            right = nf.getTerminal(rand);
            right.depth = depth + 1;
            return;
        }

        int choiceLeft = rand.nextInt(nf.getNumOps() + nf.getNumIndepVars() + 1);
        if (choiceLeft < nf.getNumOps()) {
            left = nf.getOperator(rand);
            left.depth = depth + 1;
            left.addRandomKids(nf, maxDepth, rand);
        } else {
            left = nf.getTerminal(rand);
            left.depth = depth + 1;
        }

        int choiceRight = rand.nextInt(nf.getNumOps() + nf.getNumIndepVars() + 1);
        if (choiceRight < nf.getNumOps()) {
            right = nf.getOperator(rand);
            right.depth = depth + 1;
            right.addRandomKids(nf, maxDepth, rand);
        } else {
            right = nf.getTerminal(rand);
            right.depth = depth + 1;
        }
    }
    
    public double eval(double[] values) {
        if (operation instanceof Binop) {
            Binop b = (Binop) operation;
            return b.eval(left.eval(values), right.eval(values));
        } else if (operation instanceof Unop) {
            Unop u = (Unop) operation;
            return u.eval(values);
        }
        return 0;
    }
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Op can't clone.");
        }
        Node b = (Node) o;
        if (left != null) b.left = (Node) left.clone();
        if (right != null) b.right = (Node) right.clone();
        if (operation != null) b.operation = (Op) operation.clone();
        return b;
    }

    public String toString() {
        if (operation instanceof Binop) {
            return "(" + left.toString() + " " + operation.toString() + " " + right.toString() + ")";
        } else {
            return operation.toString();
        }
    }
}

   
