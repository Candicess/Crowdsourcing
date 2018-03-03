package CaculateMV;

import java.util.*;

public class OptimizationCaculateMV {

    /**
     * ��Ԥ����ֵ
     */
    private static double COST_THRESHOLD = Integer.MAX_VALUE;
    /**
     * ������ֵ
     */
    private static double QUALITY_THRESHOLD = 0.72;
    /**
     * ������Сֵ
     */
    private static double mMinCost = 9999;
    /**
     * ������Сʱ��Ӧ�Ĺ�����Ϣ
     */
    private static List<Integer> mAllocation = new ArrayList<>();
    /**
     * ������Сʱ��Ӧ������
     */
    private static double mAllocationQuality;

    /**
     * ���˳�ʼ���ݼ�
     */
    private static final List<Worker> mWorkersInit = new ArrayList<>();
    /**
     * ����Id�͹��˵�ӳ���ϵ
     */
    private static final Map<Integer, Worker> mWorkersMap = new HashMap<>();
    /**
     * ����Id����
     */
    private static Queue<List<Integer>> mWorkersQueue = new LinkedList<>();

    public static void main(String[] args) {
        mWorkersInit.add(new Worker(1, 0.7, 1));
        mWorkersInit.add(new Worker(2, 0.6, 0.1));
        mWorkersInit.add(new Worker(3, 0.6, 0.1));
        mWorkersInit.add(new Worker(4, 0.7, 0.2));

        // ���ݹ��˳�ʼ���ݼ���ʼ������Id����
        for (Worker worker : mWorkersInit) {
            List<Integer> list = new ArrayList<>();
            list.add(worker.getId());
            mWorkersQueue.add(list);

            mWorkersMap.put(worker.getId(), worker);
        }

        while (!mWorkersQueue.isEmpty()) {
            System.out.println(mWorkersQueue);
            // ȡ������Ԫ�� ��13
            List<Integer> head = mWorkersQueue.remove();

            // �������Ԫ���Ƿ����Ҫ��
            // 1������head�Ŀ�����ֻҪ��������ֱ���ӵ�
            double curCost = calcCost(head);
            if (curCost > COST_THRESHOLD) {
                continue;
            }

            // 2����������Ϊż����ʱ������������ֱ��׷��
            double quality;
            if (head.size() % 2 == 0) {
                quality = 0;
            } else {
                // 3����������Ϊ������������head������
                quality = calcQuality(head);
                // 4.1 ����û�����������Ϸŵ�allocation�н��з���
                if (quality >= QUALITY_THRESHOLD && curCost < mMinCost) {
                    // ������С��quality�Ͷ�Ӧ�Ĺ�����Ϣ
                    mMinCost = curCost;
                    mAllocation = head;
                    mAllocationQuality = quality;
                    continue;
                }
            }

            // 4.2 ����û�������������������Ҫ��������
            if (quality < QUALITY_THRESHOLD) {
                // ȡ������Ԫ�ص����һ���±�ֵ ��3
                int lastIndex = head.get(head.size() - 1);
                // ����InitWorkers
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
     * ���㹤������
     */
    private static double calcQuality(List<Integer> workers) {
        List<Worker> allocation = new ArrayList<>();
        for (Integer workerId : workers) {
            allocation.add(mWorkersMap.get(workerId));
        }

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

        return data[(int) Math.ceil(n / 2f)][n];
    }

    /**
     * ����������˵Ļ���
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