package CaculateMV;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by huan on 2018/01/13.
 */
public class Other {
    /**
     * 工人初始数据集
     */
    private static final List<Worker> mWorkersInit = new ArrayList<>();
    /**
     * 工人Id和工人的映射关系
     */
    private static final Map<Integer, Worker> mWorkersMap = new HashMap<>();
    /**
     * 工人Id队列
     */
    private static Queue<List<Integer>> mWorkersQueue = new LinkedList<>();

    public static void main(String[] args) {
        // MV算法的工人初始化
        mWorkersInit.add(new Worker(1, 0.8, 1));
        mWorkersInit.add(new Worker(2, 0.34, 1));
        mWorkersInit.add(new Worker(3, 0.8, 0.1));

        // 根据工人初始数据集初始化工人Id队列
        for (Worker worker : mWorkersInit) {
            List<Integer> list = new ArrayList<>();
            list.add(worker.getId());
            mWorkersQueue.add(list);

            mWorkersMap.put(worker.getId(), worker);
        }

        //bayes算法的工人初始化
        double pa = 0.6;
        double pb = 0.3;
        double pc = 0.1;

        List<Double> resList = method3Answer1Worker(pa, pb, pc, mWorkersInit.get(0).getQuality());
        for (int i = 1; i < mWorkersInit.size(); i++) {
            resList = method3Answer1Worker(resList.get(0), resList.get(1), resList.get(2), mWorkersInit.get(i).getQuality());
        }
        System.out.println(resList);
    }

    private static void method2Answer2Worker(double pa, double pb, double q1, double q2) {
        double first = (pa * q1 * q2) / (pa * q1 * q2 + pb * (1 - q1) * (1 - q2));  //两个工人都选A时,答案A的准确性
        double second = (pa * q1 * (1 - q2)) / (pa * q1 * (1 - q2) + pb * (1 - q1) * q2);  //两个工人分别选A,B时,答案A的准确性
        double third = (pa * (1 - q1) * q2 ) / (pa * (1 - q1) * q2 + pb * q1 * (1 - q2));  //两个工人分别选B,A时,答案A的准确性
        double fourth = (pa * (1 - q1) * (1 - q2)) / (pa * (1 - q1) * (1 - q2) + pb * q1 * q2);  //两个工人都选B时,答案A的准确性
        System.out.println("两个答案时：AA:"+first+",AB:"+second+",BA:"+third+",BB"+fourth);
    }


    //求解3个答案在1个工人的可能情况
    private static List<Double> method3Answer1Worker(double pa, double pb, double pc, double q1){
        double unq1 = (1 - q1) / 2;
        double a = (pa * q1) / (pa * q1 + pb * unq1 + pc * unq1);
        double b = (pa * unq1) / (pa * unq1 + pb * q1 + pc * unq1);
        double c = (pa * unq1) / (pa * unq1 + pb * unq1 + pc * q1);
        //System.out.println("A:"+ a +",B:"+ b +",C:"+ c);
        List<Double> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        System.out.println("A:"+ list.get(0) +",B:"+ list.get(1) +",C:"+ list.get(2));

        return list;
    }



    //bayes迭代计算答案情况
    private  static List<Double> iterationBayesDeduction(double pa, double pb, double pc, double q1, double q2){
        List<Double> list = method3Answer1Worker(pa, pb, pc, q1);
        List<Double> res = method3Answer1Worker(list.get(0), list.get(1), list.get(2), q2);
        return res;


    }


    //求解3个答案选项在2个工人中可能的答案情况

    private static void  bayesDeduction(double pa, double pb, double pc, double q1, double q2){
        double unq1 = (1 - q1) / 2;
        double unq2 = (1 - q2) / 2;
        double aa = (pa * q1 * q2) / (pa * q1 * q2 + pb * unq1  * unq2 + pc * unq1 * unq2);
        double ab = (pa * q1 * unq2) / (pa * q1 * unq2 + pb * unq1 * q2 + pc * unq1 * unq2);
        double ac = (pa * q1 * unq2) / (pa * q1 * unq2 + pb * unq1 * unq2 + pc * unq1 * q2);
        double ba = (pa * unq1 * q2) / (pa * unq1 * q2 + pa * q1 * unq2 + pc * unq1 * unq2);
        double bb = (pa *unq1 * unq2) / (pa *unq1 * unq2 + pb * q1 * q2 + pc * unq1 * unq2);
        double bc = (pa *unq1 * unq2) / (pa *unq1 * unq2 + pb * q1 * unq2 + pc * unq1 * q2);
        double ca = (pa * unq1 * q2) / (pa * unq1 * q2 + pb * unq1 * unq2 + pc * q1 * unq2);
        double cb = (pa * unq1 * unq2) / (pa * unq1 * unq2 + pb * unq1 * q2 + pc * q1 * unq2);
        double cc = (pa * unq1 * unq2) / (pa * unq1 * unq2 + pb * unq1 * unq2 + pc * q1 * q2);
        System.out.println("三个答案时：AA "+aa+",AB "+ab+",AC "+ac+",BA "+ba+",BB "+bb+",BC "+bc+",CA "+ca+",CB "+cb+",CC "+cc);
    }
    
    
    /**
     * MV算法计算工人质量
     */
    private static double calcQuality(List<Integer> workers) {
        List<Worker> allocation = new ArrayList<>();
        for (Integer workerId : workers) {
            allocation.add(mWorkersMap.get(workerId));
        }

        int n = allocation.size();
        // 质量矩阵
        double[][] data = new double[(int) Math.ceil(n / 2f) + 1][n + 1]; // data[2][3]

        for (int j = 0; j <= n; j++) {
            for (int i = 0; i <= Math.ceil(n / 2f); i++) {
                if (j == 0) {
                    data[0][j] = 0;
                    data[i][j] = 0;
                } else if (i == 0) {
                    data[i][j] = 1;
                } else {
                    data[i][j] = data[i - 1][j] * allocation.get(j - 1).getQuality()
                            + data[i][j - 1] * (1 - allocation.get(j - 1).getQuality());
                }
            }
        }

        return data[(int) Math.ceil(n / 2f)][n];
    }
}
