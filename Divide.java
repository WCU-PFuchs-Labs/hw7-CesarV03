public class Divide extends Binop {
    public double eval(double left, double right) 
    {
        if (right == 0) return 0;  
        return left / right;
    }

    public String toString() 
    {
        return "/";
    }
}
