package CaculateMV;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GreedyCaculateMV {
    public static void main(String[] args) {
        // 质量阈值
        double QUALITY_THRESHOLD = 0.95;

        // 初始化工人
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker(1, 0.6, 0));
        workers.add(new Worker(2, 0.7, 0));
        workers.add(new Worker(3, 0.8, 0));
        workers.add(new Worker(4, 0.6, 0));
        workers.add(new Worker(4, 0.6, 0));

        // 对工人根据质量从大到小进行排序
        Collections.sort(workers, new Comparator<Worker>() {
            @Override
            public int compare(Worker w1, Worker w2) {
                if (w1.getQuality() <= w2.getQuality()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        List<Worker> allocation = new ArrayList<>();

        while (allocation.size() < workers.size()) {
            allocation.add(workers.get(allocation.size()));
            if (calcQuality(allocation) >= QUALITY_THRESHOLD) {
                // 满足质量条件
                System.out.println("YES");
                printWorkerInfo(allocation);
                break;
            } else {
                // 不满足质量条件
                System.out.println("NO");
                continue;
            }
        }
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