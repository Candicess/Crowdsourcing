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
    private static Queue<String> mWorkersQueue = new LinkedList<>();

    public static void main(String[] args) {
        mWorkersInit.add(new Worker(1, 0.6, 0));
        mWorkersInit.add(new Worker(2, 0.7, 0));
        mWorkersInit.add(new Worker(3, 0.8, 0));
        mWorkersInit.add(new Worker(4, 0.6, 0));

        for (Worker worker : mWorkersInit) {
            mWorkersQueue.add(String.valueOf(worker.getId()));
        }

        while (!mWorkersQueue.isEmpty()) {
            // 取出队首元素 如13
            String head = mWorkersQueue.remove();
            // 取出队首元素的最后一个下标值 如3
            String lastIndex = head.substring(head.length() - 1);
            // 遍历InitWorkers
            for (Worker worker : mWorkersInit) {
                if (worker.getId() > Integer.valueOf(lastIndex)) {
                    mWorkersQueue.add(head + worker.getId());
                }
            }
            System.out.println(mWorkersQueue);
        }
    }
}