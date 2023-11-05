package task7;

public class LinearFunction extends Function{

    // y = ax + b;

    private double a;
    private double b;

    public LinearFunction(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public double calcInPoint(final double num) {
        return a*num + b;
    }

    @Override
    public Function diff() {
        return new LinearFunction(0, a);
    }

    @Override
    public Function add(Function f) {
        if(!(f instanceof LinearFunction)) throw new UnsupportedOperationException();
        LinearFunction res = (LinearFunction) f;
        return new LinearFunction(a + res.getA(), b + res.getB());
    }

    @Override
    public Function sub(Function f) {
        if(!(f instanceof LinearFunction)) throw new UnsupportedOperationException();
        LinearFunction res = (LinearFunction) f;
        return new LinearFunction(a - res.getA(), b - res.getB());
    }

    @Override
    public Function multInNumber(final double num) {
        return new LinearFunction(num * a, num * b);
    }

    @Override
    public Function divideOnNumber(double num) {
        if(num == 0) throw new ArithmeticException("dividing on zero");
        return new LinearFunction(a / num, b / num);
    }

    @Override
    public String toString(){
        return a + "x + " + b;
    }
}
