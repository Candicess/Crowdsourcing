package CaculateMV;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        // ������ֵ
        double QUALITY_THRESHOLD = 0.75;
        // Ԥ����ֵ
        double COST_THRESHOLD = Integer.MAX_VALUE;

        // ��ǰ��С���ĵ�Ԥ��
        double minCost = Integer.MAX_VALUE;

        // ��ʼ������
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker(1, 0.6, 0));
        workers.add(new Worker(2, 0.7, 0));
        workers.add(new Worker(3, 0.8, 0));
        workers.add(new Worker(4, 0.6, 0));
        workers.add(new Worker(4, 0.6, 0));

        // ��������Ҫ��Ĺ��˼���
        List<Worker> allocation = new ArrayList<>();

        Iterator<Worker> iterator = workers.iterator();
        while (iterator.hasNext()) {
            Worker worker = iterator.next();
            if (worker.getQuality() > QUALITY_THRESHOLD) {
                // ��ǰ��������������������Ҫ��
                if (worker.getCost() < COST_THRESHOLD) {
                    // ��ǰ��������Ԥ��Ҳ����Ԥ��Ҫ�󣬲�������С��Ԥ��,����ǰ������ӵ�allocation������
                    if (worker.getCost() < minCost) {
                        allocation.clear();
                        allocation.add(worker);
                        iterator.remove();
                        minCost = worker.getCost();
                    }
                } else {
                    // ��ǰ����Ԥ�㲻����Ҫ��
                    workers.remove(worker);
                }
            }
        }
        printWorkerInfo(allocation);
        System.out.println();
        printWorkerInfo(workers);
    }

    /**
     * ����Allocation������������
     */
    private static double calcQuality(List<Worker> allocation) {
        int n = allocation.size();
        // ��������
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
     * ��ӡ��������
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
     * ��ӡ�����еĹ�����Ϣ
     */
    private static void printWorkerInfo(List<Worker> workers) {
        for (Worker worker : workers) {
            System.out.println(worker);
        }
    }
}