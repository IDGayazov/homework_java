package task7;

// Создать абстрактный класс функция
// прописать интерфейс для функции
// создать классы наследники
// линейная функция и квадратичная
// создать массив из функции:
// либо линейными, либо квадратичными функциями
// запрашиваем коэффициенты
// распечатать сумму всех квадратичных
// распечатать сумму всех линейных фукнций
// вычисление точки
// производная функции
// сложить
// вычесть
// умножение на число
// деление на число

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите количество элементов массива функций: ");
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        Function[] funcs = new Function[n];
        for(int i = 0; i < n; ++i){
            System.out.println("Введите тип функции: 1 - линейная, 2 - квадратичная: ");
            int q = sc.nextInt();
            if(q == 1){
                System.out.println("Введите два коэффициента для функции y = ax + b");
                System.out.println("a = ");
                double a = sc.nextDouble();
                System.out.println("b = ");
                double b = sc.nextDouble();
                funcs[i] = new LinearFunction(a, b);
            }else if(q == 2){
                System.out.println("Введите три коэффициента для функции y = ax^2 + bx + c");
                System.out.println("a = ");
                double a = sc.nextDouble();
                System.out.println("b = ");
                double b = sc.nextDouble();
                System.out.println("c = ");
                double c = sc.nextDouble();
                funcs[i] = new QuadraticFunction(a, b, c);
            }else{
                throw new RuntimeException("неправильный тип функции");
            }
        }

        LinearFunction lin = new LinearFunction(0, 0);
        QuadraticFunction quad = new QuadraticFunction(0, 0 , 0);

        for(int i = 0; i < n; ++i){
            if(funcs[i] instanceof LinearFunction){
                lin = (LinearFunction)lin.add(funcs[i]);
            }else{
                quad = (QuadraticFunction) quad.add(funcs[i]);
            }
        }

        System.out.println("Весь массив: ");
        System.out.println(Arrays.toString(funcs));

        System.out.println("Сумма всех линейных функции: ");
        System.out.println(lin);
        System.out.println("Сумма всех квадратичных функции: ");
        System.out.println(quad);

        System.out.println("Выберите функцию для тестирования операции: 1 - линейная, 2 - квадратичная");
        int q = sc.nextInt();
        if(q == 1){
            System.out.println("Введите два коэффициента для функции y = ax + b");
            System.out.println("a = ");
            double a = sc.nextDouble();
            System.out.println("b = ");
            double b = sc.nextDouble();
            LinearFunction linFunc = new LinearFunction(a, b);
            while(q != 0){
                System.out.println("Введите тип операции:");
                System.out.println("1 - вычисление в точке");
                System.out.println("2 - вычисление производной");
                System.out.println("3 - сложить с другой функцией");
                System.out.println("4 - вычесть из неё другую функцию");
                System.out.println("5 - умножить на число");
                System.out.println("6 - разделить на число");
                System.out.println("0 - выйти");
                q = sc.nextInt();
                switch(q){
                    case 1:
                        System.out.println("Введите точку: ");
                        System.out.println("num = ");
                        double num = sc.nextDouble();
                        System.out.println("result = " + linFunc.calcInPoint(num));
                        break;
                    case 2:
                        System.out.println("result = " + linFunc.diff());
                        break;
                    case 3:
                        System.out.println("Введите функцию для сложения: ");
                        System.out.println("Введите два коэффициента для функции y = ax + b");
                        System.out.println("a = ");
                        double a1 = sc.nextDouble();
                        System.out.println("b = ");
                        double b1 = sc.nextDouble();
                        LinearFunction lin2 = new LinearFunction(a1, b1);
                        System.out.println(linFunc.add(lin2));
                        break;
                    case 4:
                        System.out.println("Введите функцию для сложения: ");
                        System.out.println("Введите два коэффициента для функции y = ax + b");
                        System.out.println("a = ");
                        double a2 = sc.nextDouble();
                        System.out.println("b = ");
                        double b2 = sc.nextDouble();
                        LinearFunction lin3 = new LinearFunction(a2, b2);
                        System.out.println(linFunc.sub(lin3));
                        break;
                    case 5:
                        System.out.println("Введите число: ");
                        System.out.println("num = ");
                        double num1 = sc.nextDouble();
                        System.out.println("result = " + linFunc.multInNumber(num1));
                        break;
                    case 6:
                        System.out.println("Введите точку: ");
                        System.out.println("num = ");
                        double num2 = sc.nextDouble();
                        System.out.println("result = " + linFunc.divideOnNumber(num2));
                        break;
                    default:
                        q = 0;
                }
            }
        }else if(q == 2){
            System.out.println("Введите три коэффициента для функции y = ax^2 + bx + c");
            System.out.println("a = ");
            int a = sc.nextInt();
            System.out.println("b = ");
            int b = sc.nextInt();
            System.out.println("c = ");
            int c = sc.nextInt();
//            funcs[i] = new QuadraticFunction(a, b, c);
        }else{
            throw new RuntimeException("неправильный тип функции");
        }
    }
}
