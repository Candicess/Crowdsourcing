package RecommendTask;


import WorkerInfo.RequesterTaskInfo;
import WorkerInfo.WorkerInfo;
import java.sql.Timestamp;
import java.util.List;


public class GreedyQualityRecommend implements RecommendTask {
    @Override
    public String[] getRecommendTask(int worker_number, int times, List<WorkerInfo> workerInfos, RequesterTaskInfo requesterInfos) {

        return new String[0];
    }

    @Override
    public Timestamp getTakenDeadline(int worker_number, int times, List<WorkerInfo> workerInfos, RequesterTaskInfo requesterInfos) {
        return null;
    }
}
