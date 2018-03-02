package GreedyCalculateMV;

import java.util.*;

public class SomeSelect {

    /**
     * 工人初始数据集
     */
    private static final List<Worker> mWorkersInit = new ArrayList<>();
    /**
     * 工人Id队列
     */
    private static Queue<List<Integer>> mWorkersQueue = new LinkedList<>();

    public static void main(String[] args) {
        mWorkersInit.add(new Worker(1, 0.6, 0));
        mWorkersInit.add(new Worker(2, 0.7, 0));
        mWorkersInit.add(new Worker(3, 0.8, 0));
        mWorkersInit.add(new Worker(4, 0.6, 0));

        // 根据工人初始数据集初始化工人Id队列
        for (Worker worker : mWorkersInit) {
            List<Integer> list = new ArrayList<>();
            list.add(worker.getId());
            mWorkersQueue.add(list);
        }

        while (!mWorkersQueue.isEmpty()) {
            // 取出队首元素 如13
            List<Integer> head = mWorkersQueue.remove();
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
            System.out.println(mWorkersQueue);
        }
    }
}