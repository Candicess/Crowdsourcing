package WorkerInfo;

public class WorkerInfo {

    private int worker_id;
    private String quality;
    private int level;
    private Double aver_costtime;
    private Double aver_di;
    private Double aver_reward;

    public int getWorker_id() {
        return worker_id;
    }
    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }
    public String getQuality() {
        return quality;
    }
    public void setQuality(String quality) {
        this.quality = quality;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public Double getAver_costtime() {
        return aver_costtime;
    }
    public void setAver_costtime(Double aver_costtime) {
        this.aver_costtime = aver_costtime;
    }
    public Double getAver_di() {
        return aver_di;
    }
    public void setAver_di(Double aver_di) {
        this.aver_di = aver_di;
    }
    public Double getAver_reward() {
        return aver_reward;
    }
    public void setAver_reward(Double aver_reward) {
        this.aver_reward = aver_reward;
    }
    public WorkerInfo(int worker_id, String quality, int level, Double aver_costtime, Double aver_di,
                      Double aver_reward) {
        super();
        this.worker_id = worker_id;
        this.quality = quality;
        this.level = level;
        this.aver_costtime = aver_costtime;
        this.aver_di = aver_di;
        this.aver_reward = aver_reward;
    }

}
