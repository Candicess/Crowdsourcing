package RecommendTask;


import WorkerInfo.RequesterTaskInfo;
import WorkerInfo.WorkerInfo;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;


public class GreedyQualityRecommend implements RecommendTask {
    @Override
    public String[] getRecommendTask(int worker_number, int times, List<WorkerInfo> workerInfos, RequesterTaskInfo requesterInfos) {
        if(worker_number <= 0 || workerInfos == null || requesterInfos == null)
            return null;
        /*
        **将工人信息按照质量放入Treemap中按照quality大小排序
         */

        TreeMap<Integer,WorkerInfo> sortQualityMap = new TreeMap<>();



        return new String[0];
    }

    @Override
    public Timestamp getTakenDeadline(int worker_number, int times, List<WorkerInfo> workerInfos, RequesterTaskInfo requesterInfos) {
        // TODO Auto-generated method stub
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime() + 1000 * 60 * 1);
        return time;
    }
}
