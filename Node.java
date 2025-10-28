// imported first from grasp
import java.util.*;

public class Node {

    protected Node lChild;
    protected Node rChild;
    protected Binop op;
    protected Const c;
    protected Variable v;

    public Node(Const c) 
    {
        this.c = c;
        this.op = null;
        this.v = null;
        this.lChild = null;
        this.rChild = null;
    }

    public Node(Variable v) 
    {
        this.v = v;
        this.op = null;
        this.c = null;
        this.lChild = null;
        this.rChild = null;
    }

    public Node(Binop op) 
    {
        this.op = op;
        this.c = null;
        this.v = null;
        this.lChild = null;
        this.rChild = null;
    }

    public double eval(double[] data) 
    {
        if (c != null) {
            return c.eval(data);
        } else if (v != null) {
            return v.eval(data);
        } else if (op != null) {
            return op.eval(lChild, rChild, data);
        }
        return 0.0;
    }

    public String toString() 
    {
        if (c != null) {
            return c.toString();
        } else if (v != null) {
            return v.toString();
        } else if (op != null) {
            return "(" + lChild + " " + op + " " + rChild + ")";
        }
        return "";
    }

    public void traverse(Collector c) 
    {
        c.collect(this);
        if (lChild != null) {
            lChild.traverse(c);
        }
        if (rChild != null) {
            rChild.traverse(c);
        }
    }

    public void swapLeft(Node trunk) 
    {
        Node temp = this.lChild;
        this.lChild = trunk.lChild;
        trunk.lChild = temp;
    }

    public void swapRight(Node trunk) 
    {
        Node temp = this.rChild;
        this.rChild = trunk.rChild;
        trunk.rChild = temp;
    }

    public boolean isLeaf() 
    {
        return (op == null);
    }

    public void addRandomKids(NodeFactory n, int maxDepth, Random rand) 
    {
        if (isLeaf() || maxDepth <= 0) return;
        if (lChild == null) {
            Node child = n.getRandomNode(rand, maxDepth - 1);
            lChild = child;
            lChild.addRandomKids(n, maxDepth - 1, rand);
        }

        if (rChild == null) {
            Node child = n.getRandomNode(rand, maxDepth - 1);
            rChild = child;
            rChild.addRandomKids(n, maxDepth - 1, rand);
        }
    }
}
   
