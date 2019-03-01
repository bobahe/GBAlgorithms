package ru.bobahe.algorithms.recursion;

public class Exponentiation {
    public static void main(String[] args) {
        System.out.println(exponentiate(0.5, -2));
    }

    public static double exponentiate(double number, int power) {
        // базовый случай
        if (power == 0) {
            return 1;
        }

        // рекурсивные случаи
        if (power < 0) {
            return 1 / (number * exponentiate(number, Math.abs(power) - 1));
        } else {
            return number * exponentiate(number, Math.abs(power) - 1);
        }
    }
}
