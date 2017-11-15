package RecommendTask;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import WorkerInfo.RequesterTaskInfo;
import WorkerInfo.WorkerInfo;
import net.sf.json.JSONArray;

public class FirstCountNumRecommend implements RecommendTask {

    private int num;
    private int j = 0;

    public String[] getRecommendTask(int worker_number, int times, List<WorkerInfo> workerInfos, RequesterTaskInfo requesterInfos) {
        // TODO Auto-generated method stub
        ArrayList messageid = new ArrayList();
        ArrayList wm = new ArrayList();
        for (int i = 0; i < workerInfos.size(); i++) {
            messageid.add(workerInfos.get(i).getWorker_id());
            wm.add(workerInfos.get(i).getQuality());
        }
        ArrayList messagequality = ReadWm(wm);
        /**
         *将工人id和质量放在一个String类型的数组中，用于后续排序
         */
        String[][] quaId = new String[messageid.size()][2];
        double sum = 0;
        double average;//平均质量

        /**
         * 将工人id与质量放入一个二维数组中
         */
        for (int i = 0; i < quaId.length; i++) {
            quaId[i][0] = messageid.get(i).toString();
            quaId[i][1] = messagequality.get(i).toString();
            sum = sum + Double.parseDouble(quaId[i][1]);
        }

        average = sum / messageid.size();//工人的平均质量

        /**
         * 排序之后的工人质量及id信息
         */
        String[][] sortMessage = bubbleSort(quaId);

        num = worker_number;//获取需要的工人数目

        try {
            /**
             * * 推荐的工人组合
             * */
            String[] worker = new String[num];

            while (j < num) {
                int index = -1;//记录大于平均质量的第一个下标
                for (int i = 0; i < sortMessage.length - j; i++) {
                    if (Double.parseDouble(sortMessage[i][1]) >= average) {
                        index = i;
                        break;
                    }
                }
                /**
                 * 平均值保留4位小数
                 */
                DecimalFormat df = new DecimalFormat("######0.00");
                average = Double.parseDouble(df.format(average));
                //System.out.println(average);
		    	     
		    	     /*
		    	      * 工人质量小于平均值
		    	      */
                if (index == -1) {
                    break;
                }
                //修改平均质量值
                if (j == 0) {
                    average = (average * (sortMessage.length) - Double.parseDouble(sortMessage[index][1])) / (sortMessage.length - 1);//修改平均质量
                } else {
                    average = (average * (sortMessage.length - j) - Double.parseDouble(sortMessage[index][1])) / (sortMessage.length - 1 - j);//修改平均质量
                }

                /**
                 * * 获取工人id
                 */

                worker[j] = sortMessage[index][0];
                //每次将取出的工人id以及质量替换成后续的id以及质量
                for (int m = index; m < sortMessage.length - 1; m++) {
                    sortMessage[m][0] = sortMessage[m + 1][0];
                    sortMessage[m][1] = sortMessage[m + 1][1];
                }
                sortMessage[sortMessage.length - 1 - j][0] = "0";
                sortMessage[sortMessage.length - 1 - j][1] = "0";
                j++;//控制取出的工人id数目
            }


		        /*
		         * 填补未选出的工人id
		         */
            int v = 0;
            for (; j < num; j++) {
                worker[j] = sortMessage[v][0];
                v++;
            }
            return worker;
        } catch (Exception e) {
            System.out.println("Recommend failed");
        }
        return null;
    }

    /**
     * 去除数组中空的字符串
     */

    private Object[] change(String[] strings) {
        ArrayList<String> tmp = new ArrayList<String>();
        for (String str : strings) {
            if (str != null && str.length() != 0) {
                tmp.add(str);
            }
        }
        strings = tmp.toArray(new String[0]);
        return strings;
    }

    /**
     * 用改进的冒泡排序法对工人的质量进行排序
     */
    /**
     * 改进后的冒泡排序算法的实现：
     *
     * @param list 欲排序的数组
     * @author csc
     */
    private static String[][] bubbleSort(String[][] list) {
        boolean isSorted = true;
        for (int i = 1; i < list.length && isSorted; i++) {
            isSorted = false;
            //System.out.print(i + " ");
            for (int j = 0; j < list.length - i; j++) {
                if (Double.parseDouble(list[j][1]) > Double.parseDouble(list[j + 1][1])) {
                    double temp = Double.parseDouble(list[j][1]);
                    int id = Integer.parseInt(list[j][0]);

                    list[j][1] = list[j + 1][1];
                    list[j + 1][1] = Double.toString(temp);

                    list[j][0] = list[j + 1][0];
                    list[j + 1][0] = Integer.toString(id);

                    isSorted = true;
                }
            }
        }
        return list;
    }


    /**
     * 工人的准确性计算
     */

    private double WokerAccuracy(double[][] wm) {

        double s = 0;//记录工人选择的答案与真实答案相同的累积贡献值
        double sum = 0;//记录工人回答所有问题的累积贡献值
        double wa = 0;//工人的准确率
        for (int i = 0; i < wm.length; i++) {
            for (int j = 0; j < wm.length; j++) {
                sum = sum + wm[i][j];
                if (i == j) {
                    s = s + wm[i][j];
                }
            }
        }
        wa = s / sum;//工人准确率的计算
        DecimalFormat df = new DecimalFormat("######0.00");
        wa = Double.parseDouble(df.format(wa));
        return wa;
    }

    /**
     * 根据工人id从数据库中读取工人模型，获得工人准确率
     */
    private ArrayList ReadWm(ArrayList<String> wm) {
        // TODO Auto-generated method stub
        ArrayList<Object> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();//定义json，接收数组
        Object w = new double[wm.toArray().length][wm.toArray().length];//
        Object[] a = wm.toArray();
        String[] workmodel = new String[a.length];//用来接收转化为字符串的工人模型矩阵
        for (int i = 0; i < a.length; i++) {
            workmodel[i] = a[i].toString();
            jsonArray.add(workmodel[i]);//将工人模型存入json数组中
        }

        /**
         * 从json数组中得到工人模型矩阵，根据该矩阵计算工人的准确性
         */
        for (int z = 0; z < workmodel.length; z++) {
            w = jsonArray.get(z);

            int n = 0;
            for (int i = 0; i < w.toString().length(); i++) {
                if (w.toString().charAt(i) == (']'))
                    n++;//用来判断工人模型的大小
            }
            double[][] m = new double[n - 1][n - 1];//接收工人模型矩阵
            double qua = 0;//工人的准确性
            JSONArray arr = JSONArray.fromObject(w);

            /**
             * 从json数组中读取出数据库中的工人模型矩阵
             */

            int j = 0;
            for (Object o : arr) {
                JSONArray a1 = (JSONArray) o;
                for (int i = 0; i < a1.size(); i++) {
                    m[j][i] = Double.parseDouble((String) a1.get(i));//读取数据库中工人模型
                }
                j++;
            }
            qua = WokerAccuracy(m);//调用计算工人质量方法，得到工人准确性
            list.add(qua);
        }
        return list;
    }

    @Override
    public Timestamp getTakenDeadline(int worker_number, int times, List<WorkerInfo> workerInfos,
                                      RequesterTaskInfo requesterInfos) {
        // TODO Auto-generated method stub
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime() + 1000 * 60 * 1);
        return time;
    }
}
