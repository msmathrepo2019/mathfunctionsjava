import java.util.ArrayList;

public class MathFunctions {

    /*
     Returns an integer Array of all the prime factors of num by trial division
    */
    public static int[] primeFactors(int num) {

        ArrayList<Integer> factors = new ArrayList<>();
        int factor = 2;
        int sqrt = (int) Math.sqrt(num);
        int[] factorArr;
        int index = 0;

        while (factor <= sqrt) {
            if (num % factor == 0) {
                factors.add(factor);
                num /= factor;
                sqrt = (int) Math.sqrt(num);
            } else if (factor == 2) {
                factor++;
            } else {
                factor += 2;
            }
        }

        if (num > 1) {
            factors.add(num);
        }

        factorArr = new int[factors.size()];

        for (Integer intNum : factors) {
            factorArr[index++] = intNum;
        }

        return factorArr;
    }

    /*
        Returns the totient value of a number, num, by using Euler phi identity.
    */
    public static int eulerPhi(int num) {

        int[] arr = primeFactors(num);
        int mult = arr[0];
        int div = mult;
        int subOne = (mult - 1);
        int curr;
        final int size = arr.length;

        for (int i = 1; i < size; i++) {
            curr = arr[i];
            mult *= curr;
            if (arr[i - 1] != curr) {
                div *= curr;
                subOne *= (curr - 1);
            }
        }

        return mult * subOne / div;
    }

    /*
        Computes the number of factors a number, num, has.
        Based on Tau(num) = (x_1 + 1)*(x_2 + 1)*...(x_n + 1) where
        x_1, x_2, ... , x_n are powers of all the prime factors of num.
    */
    public static int numberOfFactors(int num) {

        int[] arr = primeFactors(num);
        int numFactors = 1;
        int prev = arr[0];
        int total = 2;
        final int size = arr.length;

        for (int i = 1; i < size; i++) {
            if (arr[i] != prev) {
                prev = arr[i];
                numFactors *= total;
                total = 1;
            }
            total++;
        }

        numFactors *= total;

        return numFactors;
    }

    /*
        Computes the greatest common divisor with variable amount of
         arguments by iteration.
    */
    private static int gcdHelper(int num1, int num2) {

        int temp;

        while (num1 % num2 != 0) {
            temp = num1 % num2;
            num1 = num2;
            num2 = temp;
        }

        return Math.max(-num2, num2);
    }

    /*
        Function allows variable amount of arguments for greatest common divisor.
        Throws IllegalArgumentException when too few arguments are available.
    */
    public static int gcd(Integer... values) {

        int currentGcd;

        if (values.length <= 1) {
            throw new IllegalArgumentException();
        }

        currentGcd = values[0];

        for (int i = 1; i < values.length; i++) {
            currentGcd = gcdHelper(currentGcd, values[i]);
        }

        return currentGcd;
    }

    /*
        Returns least common multiple based on the well known relationship.
        This method works with negative values as well. Helper to lcm().
    */
    private static int lcmHelper(int num1, int num2) {

        final int retValue = num1 * num2 / gcd(num1, num2);

        return Math.max(-retValue, retValue);
    }

    /*
        Computes the least common multiple with a variable amount of
        arguments.
    */
    public static int lcm(Integer... values) {

        int currentLcm;

        if (values.length <= 1) {
            throw new IllegalArgumentException();
        }

        currentLcm = values[0];

        for (int i = 1; i < values.length; i++) {
            currentLcm = lcmHelper(currentLcm, values[i]);
        }

        return Math.max(-currentLcm, currentLcm);
    }

}
