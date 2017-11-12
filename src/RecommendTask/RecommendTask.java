package RecommendTask;

import java.util.List;

import WorkerInfo.RequesterTaskInfo;
import WorkerInfo.WorkerInfo;

import java.sql.Timestamp;

public interface RecommendTask {
    @SuppressWarnings("rawtypes")
    public String[] getRecommendTask(int worker_number, int times, List<WorkerInfo> workerInfos, RequesterTaskInfo requesterInfos);

    public Timestamp getTakenDeadline(int worker_number, int times, List<WorkerInfo> workerInfos, RequesterTaskInfo requesterInfos);
}
