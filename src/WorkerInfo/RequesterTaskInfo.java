package WorkerInfo;

import java.sql.Timestamp;

public class RequesterTaskInfo {
	//杈??浠诲??扮?锛?浠诲?ID锛?????堕?锛?each_reward锛?宸叉?褰?伐浜烘?锛??瑕?伐浜烘?锛?浠诲??惧害绯绘?
	//杩?釜绫绘?涓轰?甯??璁＄??ㄨ?浠诲?瀹??
	
	private int taskID;
	private Timestamp deadline;
	private Double each_reward;
	private int hastaken_number;
	private int worker_number;
	private Double difficult_degree;


	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public Timestamp getDeadline() {
		return deadline;
	}
	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}
	public Double getEach_reward() {
		return each_reward;
	}
	public void setEach_reward(Double each_reward) {
		this.each_reward = each_reward;
	}
	public int getHastaken_number() {
		return hastaken_number;
	}
	public void setHastaken_number(int hastaken_number) {
		this.hastaken_number = hastaken_number;
	}
	public int getWorker_number() {
		return worker_number;
	}
	public void setWorker_number(int worker_number) {
		this.worker_number = worker_number;
	}
	public Double getDifficult_degree() {
		return difficult_degree;
	}
	public void setDifficult_degree(Double difficult_degree) {
		this.difficult_degree = difficult_degree;
	}


	public RequesterTaskInfo(int taskID, Timestamp deadline, Double each_reward, int hastaken_number,
			int worker_number, Double difficult_degree) {
		super();
		this.taskID = taskID;
		this.deadline = deadline;
		this.each_reward = each_reward;
		this.hastaken_number = hastaken_number;
		this.worker_number = worker_number;
		this.difficult_degree = difficult_degree;
	}
	
}
