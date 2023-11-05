package task7;

// вычисление точки
// производная функции
// сложить
// вычесть
// умножение на число
// деление на число

public abstract class Function {

    abstract public double calcInPoint(final double num);

    abstract public Function diff();

    abstract public Function add(Function f);

    abstract public Function sub(Function f);

    abstract public Function multInNumber(final double num);

    abstract public Function divideOnNumber(final double num);
}
