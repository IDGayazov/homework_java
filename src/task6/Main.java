package task6;

import java.util.Arrays;
import java.util.Comparator;

// Задание
// Разработать класс полином
// на основе массива
// получение степени полинома *
// индексатор коэффициент по индексу (get, set) *
// печать *
// сложение *
// вычитание *
// умножение *
// деление *
// вычисление полинома в точке *
// сравнение
// создать массив полиномов и отсортировать *
// его по значению в некоторой точке *
public class Main {
    public static void main(String[] args) {
        double[] pol1 = {5, 3, 2};
        double[] pol2 = {5, 3, 2};
        double[] pol3 = {4, 3, 1};
        double[] pol4 = {1, 2, 6};
        Polynomial[] pols = {
                new Polynomial(pol1),
                new Polynomial(pol2),
                new Polynomial(pol3),
                new Polynomial(pol4)
        };
//        Polynomial.sortingPolynomsInPoint(pols, 2);
        //Arrays.sort(pols, Comparator.comparingDouble(p -> p.calcInPoint(2)));
        System.out.println(Arrays.toString(pols));
        System.out.println(pols[0].equals(pols[1]));
    }
}
