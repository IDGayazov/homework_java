package task7;

public class QuadraticFunction extends Function{

    // y = ax2 + bx + c

    private double a;
    private double b;
    private double c;

    public QuadraticFunction(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public double calcInPoint(double num) {
        return a * num * num + b * num + c;
    }

    @Override
    public Function diff() {
        return new QuadraticFunction(0, 2*a, b);
    }

    @Override
    public Function add(Function f) {
        if(!(f instanceof QuadraticFunction)) throw new UnsupportedOperationException();
        QuadraticFunction res = (QuadraticFunction) f;
        return new QuadraticFunction(a + res.getA(), b + res.getB(), c + res.getC());
    }

    @Override
    public Function sub(Function f) {
        if(!(f instanceof QuadraticFunction)) throw new UnsupportedOperationException();
        QuadraticFunction res = (QuadraticFunction) f;
        return new QuadraticFunction(a - res.getA(), b - res.getB(), c - res.getC());
    }

    @Override
    public Function multInNumber(double num) {
        return new QuadraticFunction(a*num, b*num, c*num);
    }

    @Override
    public Function divideOnNumber(double num) {
        if(num == 0) throw new ArithmeticException("dividing on zero");
        return new QuadraticFunction(a / num, b / num, c / num);
    }

    @Override
    public String toString(){
        return a + "x^2 + " + b + "x + " + c;
    }
}
