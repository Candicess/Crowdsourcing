package CaculateMV;

import java.util.ArrayList;
import java.util.List;

public class AllCombination {

    /**
     * 保存结果的列表
     */
    private static List<Character> result = new ArrayList<>();
    /**
     * 要进行全组合的数据
     */
    private static char[] str = new char[] {'a', 'b', 'c'};

    public static void main(String[] args) {
        combination();
    }

    /**
     * 求一个字符串的组合
     */
    private static void combination() {
        for (int i = 1; i <= str.length; i++) {
            result = new ArrayList<>();
            doSelectElement(0, i);
        }
    }

    /**
     * 从start下标处开始选m个元素
     */
    private static void doSelectElement(int start, int m) {
        // 出口，输出组合
        if (m == 0) {
            for (int i = 0; i < result.size(); i++)
                System.out.print(result.get(i));
            System.out.println();
            return;
        }

        if (start == str.length) {
            return;
        }

        // 选中当前元素，并在start+1后选取m-1个元素
        result.add(str[start]);
        doSelectElement(start + 1, m - 1);
        // 不选中当前元素，并在start+1后选取m个元素
        result.remove(result.size() - 1);
        doSelectElement(start + 1, m);
    }
}