package CaculateMV;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huan on 2018/01/13.
 * 进制位递增
 */
public class SystemIncrease {

    /**
     * 答案数量
     */
    public static int answers = 3;
    /**
     * 工人数量
     */
    public static int workers = 10;
    /**
     * 每一组的答案
     */
    public static int data[]= new int[workers];
    /**
     * 结果集
     */
    public static List<String> resList = new ArrayList<>();

    public static void main(String[] args) {
        // 将初始的00...00加到结果集中
        resList.add(getCurrentData());

        // 执行 m的n次方 次
        increase();

        // 打印结果集
        System.out.println(resList);
    }

    /**
     * 每次都给末尾的数字+1
     */
    private static void increase() {
        if (resList.size() == Math.pow(answers, workers)) {
            return;
        }

        for (int i = data.length - 1; i >= 0 ; i--) {
            // 不满足进位条件时，当前数位+1，并加进结果集
            if (data[i] < answers - 1) {
                data[i]++;
                resList.add(getCurrentData());
                increase();
                return;
            }

            // 满足进位条件时，先将当前位的后面置为0，再循环前面进位+1
            if (data[i] == answers - 1) {
                clear(i);
                continue;
            }
        }
        increase();
    }

    /**
     * 进位后将index后的数字都置为0
     */
    private static void clear(int index) {
        for (int i = index; i < data.length; i++) {
            data[i] = 0;
        }
    }

    /**
     * 将当前的data转化为字符串
     */
    private static String getCurrentData() {
        StringBuilder builder = new StringBuilder();
        for (int item : data) {
            builder.append(item);
        }
        return builder.toString();
    }
}