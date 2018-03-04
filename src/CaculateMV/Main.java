package CaculateMV;

/**
 * Created by huan on 2017/11/21.
 */
public class Main {

    public static void main(String[] args) {
        /**
         * n 工人的数量
         * data 工人质量参数
         */
        int n = 3;

        double data[] = new double[]{1.0, 0.6, 0.7, 0.8};

        double test[][] = new double[(n + 1) / 2 + 1][n + 1];

        //根据质量推导公式，test[i][j]表示工人数量为j时，超过i个人回答正确的概率
        for (int i = 0; i <= (n + 1) / 2; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    test[i][j] = 1;
                } else if (i > j){
                    test[i][j] = 0;
                }else if (j == 0){
                    test[i][j] = 0;
                }else {
                    test[i][j] = test[i - 1][j - 1] * data[j] + test[i][j - 1] * (1 - data[j]);
                }
            }
        }
        System.out.println(test[(n + 1) / 2][n]);





    }

}
