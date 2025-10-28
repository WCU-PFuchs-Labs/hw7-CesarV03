// imported first from grasp
public class Node {
    private Binop op;
    private Node left;
    private Node right;
    private Const constant;
    private Variable variable;

    public Node(Binop op, Node left, Node right) 
    {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public Node(Const constant) 
    {
        this.constant = constant;
    }

    public Node(Variable variable) 
    {
        this.variable = variable;
    }

    public double eval(double[] values) 
    {
        if (constant != null) {
            return constant.eval(values);
        } else if (variable != null) {
            return variable.eval(values);
        } else {
            double leftVal = left.eval(values);
            double rightVal = right.eval(values);
            return op.eval(leftVal, rightVal);
        }
    }

    public String toString() 
    {
        if (constant != null) return constant.toString();
        if (variable != null) return variable.toString();
        return "(" + left.toString() + " " + op.toString() + " " + right.toString() + ")";
    }

   
    public void traverse(Collector c) 
    {
        c.collect(this);  // collect this node first (preorder)
        if (left != null) left.traverse(c);
        if (right != null) right.traverse(c);
    }
   
    public void swapLeft(Node trunk) 
    {
        Node temp = this.left;
        this.left = trunk.left;
        trunk.left = temp;
    }

    public void swapRight(Node trunk) 
    {
        Node temp = this.right;
        this.right = trunk.right;
        trunk.right = temp;
    }

    public boolean isLeaf() 
    {
        return (left == null && right == null);
    }
}

   
