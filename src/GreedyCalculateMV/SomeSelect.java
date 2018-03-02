package GreedyCalculateMV;

import java.util.*;

public class SomeSelect {

    /**
     * ���˳�ʼ���ݼ�
     */
    private static final List<Worker> mWorkersInit = new ArrayList<>();
    /**
     * ����Id����
     */
    private static Queue<List<Integer>> mWorkersQueue = new LinkedList<>();

    public static void main(String[] args) {
        mWorkersInit.add(new Worker(1, 0.6, 0));
        mWorkersInit.add(new Worker(2, 0.7, 0));
        mWorkersInit.add(new Worker(3, 0.8, 0));
        mWorkersInit.add(new Worker(4, 0.6, 0));

        // ���ݹ��˳�ʼ���ݼ���ʼ������Id����
        for (Worker worker : mWorkersInit) {
            List<Integer> list = new ArrayList<>();
            list.add(worker.getId());
            mWorkersQueue.add(list);
        }

        while (!mWorkersQueue.isEmpty()) {
            // ȡ������Ԫ�� ��13
            List<Integer> head = mWorkersQueue.remove();
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
            System.out.println(mWorkersQueue);
        }
    }
}