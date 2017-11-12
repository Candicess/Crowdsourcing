package RecommendTask;

import java.util.List;

import WorkerInfo.RequesterTaskInfo;
import WorkerInfo.WorkerInfo;

import java.sql.Timestamp;

public interface RecommendTask {
    @SuppressWarnings("rawtypes")
    public String[] getRecommendTask(Integer worker_number, Integer times, List<WorkerInfo> workerInfos, RequesterTaskInfo requesterInfos);

    public Timestamp getTakenDeadline(Integer worker_number, Integer times, List<WorkerInfo> workerInfos, RequesterTaskInfo requesterInfos);
}
