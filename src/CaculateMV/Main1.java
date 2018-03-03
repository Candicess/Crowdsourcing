package CaculateMV;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        // 质量阈值
        double QUALITY_THRESHOLD = 0.75;
        // 预算阈值
        double COST_THRESHOLD = Integer.MAX_VALUE;

        // 当前最小消耗的预算
        double minCost = Integer.MAX_VALUE;

        // 初始化工人
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker(1, 0.6, 0));
        workers.add(new Worker(2, 0.7, 0));
        workers.add(new Worker(3, 0.8, 0));
        workers.add(new Worker(4, 0.6, 0));
        workers.add(new Worker(4, 0.6, 0));

        // 满足质量要求的工人集合
        List<Worker> allocation = new ArrayList<>();

        Iterator<Worker> iterator = workers.iterator();
        while (iterator.hasNext()) {
            Worker worker = iterator.next();
            if (worker.getQuality() > QUALITY_THRESHOLD) {
                // 当前单个工人质量满足质量要求
                if (worker.getCost() < COST_THRESHOLD) {
                    // 当前单个工人预算也满足预算要求，并且是最小的预算,将当前工人添加到allocation集合中
                    if (worker.getCost() < minCost) {
                        allocation.clear();
                        allocation.add(worker);
                        iterator.remove();
                        minCost = worker.getCost();
                    }
                } else {
                    // 当前工人预算不满足要求
                    workers.remove(worker);
                }
            }
        }
        printWorkerInfo(allocation);
        System.out.println();
        printWorkerInfo(workers);
    }

    /**
     * 根据Allocation计算质量矩阵
     */
    private static double calcQuality(List<Worker> allocation) {
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

        print(data);

        return data[(int) Math.ceil(n / 2f)][n];
    }

    /**
     * 打印质量矩阵
     */
    private static void print(double[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 打印集合中的工人信息
     */
    private static void printWorkerInfo(List<Worker> workers) {
        for (Worker worker : workers) {
            System.out.println(worker);
        }
    }
}