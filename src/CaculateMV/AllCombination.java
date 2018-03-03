package CaculateMV;

import java.util.ArrayList;
import java.util.List;

public class AllCombination {

    /**
     * ���������б�
     */
    private static List<Character> result = new ArrayList<>();
    /**
     * Ҫ����ȫ��ϵ�����
     */
    private static char[] str = new char[] {'a', 'b', 'c'};

    public static void main(String[] args) {
        combination();
    }

    /**
     * ��һ���ַ��������
     */
    private static void combination() {
        for (int i = 1; i <= str.length; i++) {
            result = new ArrayList<>();
            doSelectElement(0, i);
        }
    }

    /**
     * ��start�±괦��ʼѡm��Ԫ��
     */
    private static void doSelectElement(int start, int m) {
        // ���ڣ�������
        if (m == 0) {
            for (int i = 0; i < result.size(); i++)
                System.out.print(result.get(i));
            System.out.println();
            return;
        }

        if (start == str.length) {
            return;
        }

        // ѡ�е�ǰԪ�أ�����start+1��ѡȡm-1��Ԫ��
        result.add(str[start]);
        doSelectElement(start + 1, m - 1);
        // ��ѡ�е�ǰԪ�أ�����start+1��ѡȡm��Ԫ��
        result.remove(result.size() - 1);
        doSelectElement(start + 1, m);
    }
}