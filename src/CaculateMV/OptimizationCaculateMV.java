package CaculateMV;

import java.util.*;

public class OptimizationCaculateMV {

    /**
     * 总预算阈值
     */
    private static double COST_THRESHOLD = Integer.MAX_VALUE;
    /**
     * 质量阈值
     */
    private static double QUALITY_THRESHOLD = 0.72;
    /**
     * 花费最小值
     */
    private static double mMinCost = 9999;
    /**
     * 花费最小时对应的工人信息
     */
    private static List<Integer> mAllocation = new ArrayList<>();
    /**
     * 花费最小时对应的质量
     */
    private static double mAllocationQuality;

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
        mWorkersInit.add(new Worker(1, 0.7, 1));
        mWorkersInit.add(new Worker(2, 0.6, 0.1));
        mWorkersInit.add(new Worker(3, 0.6, 0.1));
        mWorkersInit.add(new Worker(4, 0.7, 0.2));

        // 根据工人初始数据集初始化工人Id队列
        for (Worker worker : mWorkersInit) {
            List<Integer> list = new ArrayList<>();
            list.add(worker.getId());
            mWorkersQueue.add(list);

            mWorkersMap.put(worker.getId(), worker);
        }

        while (!mWorkersQueue.isEmpty()) {
            System.out.println(mWorkersQueue);
            // 取出队首元素 如13
            List<Integer> head = mWorkersQueue.remove();

            // 计算队首元素是否符合要求
            // 1、计算head的开销，只要开销超了直接扔掉
            double curCost = calcCost(head);
            if (curCost > COST_THRESHOLD) {
                continue;
            }

            // 2、工人数量为偶数个时，不计算质量直接追加
            double quality;
            if (head.size() % 2 == 0) {
                quality = 0;
            } else {
                // 3、工人数量为奇数个，计算head的质量
                quality = calcQuality(head);
                // 4.1 开销没超但质量符合放到allocation中进行分配
                if (quality >= QUALITY_THRESHOLD && curCost < mMinCost) {
                    // 保存最小的quality和对应的工人信息
                    mMinCost = curCost;
                    mAllocation = head;
                    mAllocationQuality = quality;
                    continue;
                }
            }

            // 4.2 开销没超但是质量不满足才需要继续迭代
            if (quality < QUALITY_THRESHOLD) {
                // 取出队首元素的最后一个下标值 如3
                int lastIndex = head.get(head.size() - 1);
                // 遍历InitWorkers
                for (Worker worker : mWorkersInit) {
                    if (worker.getId() > lastIndex) {
                        List<Integer> list = new ArrayList<>(head);
                        list.add(worker.getId());
                        mWorkersQueue.add(list);
                    }
                }
            }
        }

        System.out.println("minCost " + mMinCost);
        System.out.println("allocation " + mAllocation);
        System.out.println("quality " + mAllocationQuality);
    }

    /**
     * 计算工人质量
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

    /**
     * 计算给定工人的花费
     */
    private static double calcCost(List<Integer> workers) {
        if (workers == null || workers.isEmpty()) {
            return 0;
        }

        double sum = 0;
        for (Integer workerId : workers) {
            sum += mWorkersMap.get(workerId).getCost();
        }
        return sum;
    }
}