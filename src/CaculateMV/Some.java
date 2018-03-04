package CaculateMV;

/**
 * Created by huan on 2018/01/13.
 */
public class Some {

    public static void main(String[] args) {
        double q1 = 0.9;
        double q2 = 0.9;
        double q3 = 1;

        System.out.println("2 parameter");
        System.out.println(method(q1, q2));
        System.out.println("3 parameter");
        System.out.println(method(q1, q2, q3));
    }

    private static double method(double q1, double q2) {
        double member = q1 * q2;
        double denominator = q1 * q2 + q1 * (1 - q2) + (1 - q1) * q2 + (1 - q1) * (1 - q2);
        return member / denominator;
    }

    private static double method(double q1, double q2, double q3) {
        double member = q1 * q2 * q3;
        double denominator = q1 * q2 * q3 + q1 * q2 * (1 - q3) + q1 * (1 - q2) * q3 + q1 * (1 - q2) * (1 - q3)
                + (1 - q1) * q2 * q3 + (1 - q1) * q2 * (1 - q3) + (1 - q1) * (1 - q2) * q3 + (1 - q1) * (1 - q2) * (1 - q3);
        return member / denominator;
    }
}