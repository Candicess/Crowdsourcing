package RecommendTask;


import CaculateQuality.CaculateQualityImpl;
import WorkerInfo.RequesterTaskInfo;
import WorkerInfo.WorkerInfo;

import java.sql.Timestamp;
import java.util.*;


public class GreedyQualityRecommend implements RecommendTask {
    @Override
    public String[] getRecommendTask(int worker_number, int times, List<WorkerInfo> workerInfos, RequesterTaskInfo requesterInfos) {
        if (worker_number <= 0 || workerInfos == null || requesterInfos == null)
            return null;
        double taskAccuracy = 0.75;
        /*
        **将工人信息按照质量放入Treemap中按照quality大小排序,不考虑质量<0.5的工人
         */
        TreeMap<Integer, Double> qualityMap = new TreeMap<>();
        for (int i = 0; i < workerInfos.size(); i++) {
            if (workerInfos.get(i).getQuality() != null) {
                CaculateQualityImpl caculateQuality = new CaculateQualityImpl();
                Double workerQuality = caculateQuality.CaculateQua(workerInfos.get(i).getQuality());
                if (workerQuality >= 0.5)
                    qualityMap.put(workerInfos.get(i).getWorker_id(), workerQuality);
            }
        }
        ValueComparator bvc = new ValueComparator(qualityMap);
        TreeMap<Integer, Double> sortedQualityMap = new TreeMap<>(bvc.base);

        /*
        **traverse the sortedmap ; format workerID string
         */
        ArrayList<Integer> recommendedWorkerID = new ArrayList<>();
        ArrayList<Double> recommendedWorkerQuality = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry : sortedQualityMap.entrySet()) {
            if (entry.getValue() > taskAccuracy) {
                recommendedWorkerID.add(entry.getKey());
                System.out.println("只输出一个工人"+entry.getKey()+entry.getValue());
                recommendedWorkerQuality.add(entry.getValue());
                break;
            } else {
                recommendedWorkerID.add(entry.getKey());
                recommendedWorkerQuality.add(entry.getValue());
                if (currentAccuracy(recommendedWorkerQuality) >= taskAccuracy){
                    break;
                }
            }
        }
        Object[] recWorkerID = recommendedWorkerID.toArray();
        String[] res = new String[recWorkerID.length];
        for (int i = 0; i < recWorkerID.length; i++) {
            res[i] = String.valueOf(recWorkerID);
        }
        return res;
    }

    @Override
    public Timestamp getTakenDeadline(int worker_number, int times, int mins, List<WorkerInfo> workerInfos, RequesterTaskInfo requesterInfos) {
        // TODO Auto-generated method stub
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime() + 1000 * 60 * mins);
        return time;
    }

    /*
    **Determine whether the current worker can reach the taskAccuracy
    */

    public Double currentAccuracy(ArrayList<Double> curWorker) {
        if (curWorker == null)
            return 0.0;
        Double curAccuracy = 1.0;
        for (int i = 0; i < curWorker.size(); i++) {
            curAccuracy = curAccuracy * curWorker.get(i);
        }
        return curAccuracy;
    }
}


class ValueComparator implements Comparator<Double> {
    Map<Integer, Double> base;

    //这里需要将要比较的map集合传进来
    public ValueComparator(Map<Integer, Double> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.
    //比较的时候，传入的两个参数应该是map的两个key，根据上面传入的要比较的集合base，可以获取到key对应的value，然后按照value进行比较
    public int compare(Double a, Double b) {
        if (a >= b) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}