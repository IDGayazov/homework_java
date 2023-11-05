package task6;

// Задание
// Разработать класс полином *
// на основе массива *
// получение степени полинома *
// индексатор коэффициент по индексу (get, set) *
// печать *
// сложение *
// вычитание *
// умножение на число *
// умножение
// вычисление полинома в точке *
// делить на число *
// сравнение

// отсортировать полиномы в точке

public class Polynomial {
    private int n;
    private double[] pol;

    public Polynomial(final int n, final double[] pol) {
        this.n = n;
        this.pol = pol.clone();
    }
    public Polynomial(int n) {
        this.n = n;
        this.pol = new double[n + 1];
    }

    public Polynomial(double[] pol) {
        this.n = pol.length - 1;
        this.pol = pol.clone();
    }

    public double[] getPol() {
        return pol;
    }

    public void setPol(final double[] pol) {
        this.pol = pol.clone();
    }

    public int getDegree(){
        return n;
    }

    public double getCoeff(final int i){
        return pol[i];
    }

    public void setCoeff(final int i, final double coeff){
        pol[i] = coeff;
    }

    public void print(){
        for(int i = pol.length - 1; i >= 0; --i){
            if(pol[i] > 0){
                if(i != pol.length - 1)
                    System.out.print("+" + pol[i] + "x^" + i);
                else
                    System.out.print(pol[i] + "x^" + i);
            }else{
                System.out.print(pol[i] + "x^" + i);
            }
        }
        System.out.println();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = pol.length - 1; i >= 0; --i){
            if(pol[i] > 0){
                if(i != pol.length - 1)
                    sb.append("+" + pol[i] + "x^" + i);
                else
                    sb.append(pol[i] + "x^" + i);
            }else{
                sb.append(pol[i] + "x^" + i);
            }
        }
        return sb.toString();
    }

    public Polynomial addPolynom(final Polynomial p){

        int deg1 = Math.min(p.getDegree(), this.getDegree());
        int deg2 = Math.max(p.getDegree(), this.getDegree());

        Polynomial res = new Polynomial(deg2);
        for(int i = 0; i <= deg1; ++i){
            res.setCoeff(i, p.getCoeff(i) + this.getCoeff(i));
        }

        if(p.getDegree() < this.getDegree()){
            for(int i = deg1 + 1; i <= deg2; ++i){
                res.setCoeff(i, this.getCoeff(i));
            }
        }else{
            for(int i = deg1 + 1; i <= deg2; ++i){
                res.setCoeff(i, p.getCoeff(i));
            }
        }

        return res;
    }

    public Polynomial multToNumber(final double num){
        Polynomial p = new Polynomial(n, pol);
        for(int i = 0; i < n + 1; ++i){
            p.setCoeff(i, num * p.getCoeff(i));
        }
        return p;
    }

    public Polynomial divide(final double num){
        Polynomial res = new Polynomial(n, pol);
        if(num == 0){
            throw new ArithmeticException("divide zero");
        }else{
            for(int i = 0; i < n + 1; ++i){
                res.setCoeff(i, this.getCoeff(i) / num);
            }
        }
        return res;
    }

    public Polynomial substructPolynom(final Polynomial p){

        int deg1 = Math.min(p.getDegree(), this.getDegree());
        int deg2 = Math.max(p.getDegree(), this.getDegree());

        Polynomial res = new Polynomial(deg2);
        for(int i = 0; i <= deg1; ++i){
            res.setCoeff(i, this.getCoeff(i) - p.getCoeff(i));
        }

        if(p.getDegree() < this.getDegree()){
            for(int i = deg1 + 1; i <= deg2; ++i){
                res.setCoeff(i, this.getCoeff(i));
            }
        }else{
            for(int i = deg1 + 1; i <= deg2; ++i){
                res.setCoeff(i, -p.getCoeff(i));
            }
        }

        return res;
    }

    public double calcInPoint(final double x){
        double sum = 0;
        double mult = 1;
        for(int i = 0; i < n; ++i){
            sum += mult * pol[i];
            mult *= x;
        }
        return sum;
    }

    public Polynomial multPolynom(final Polynomial p){
        int deg2 = p.getDegree();
        int deg1 = this.getDegree();
        final int deg = deg1 + deg2;
        Polynomial result = new Polynomial(deg);
        for(int i = 0; i <= deg1; ++i)
        {
            for(int j = 0; j <= deg2; ++j){
                double x = this.getCoeff(i);
                double y = p.getCoeff(j);
                double r = result.getCoeff(i + j) + x*y;
                result.setCoeff(i + j, r);
            }
        }
        return result;
    }

    public static void sortingPolynomsInPoint(Polynomial[] pols, double x){
        for(int i = 1; i < pols.length; ++i){
            Polynomial tmp = pols[i];
            double y = tmp.calcInPoint(x);
            int j = i - 1;
            while(j >= 0 && pols[j].calcInPoint(x) > y){
                pols[j + 1] = pols[j];
                j--;
            }
            pols[j + 1] = tmp;
        }
    }

    public boolean equals(Polynomial p){
        if(this.getDegree() != p.getDegree()) return false;
        for(int i = 0; i <= n; ++i){
            if(p.getCoeff(i) != this.getCoeff(i)){
                return false;
            }
        }
        return true;
    }
}
