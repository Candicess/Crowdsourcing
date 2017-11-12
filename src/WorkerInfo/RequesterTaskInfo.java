package WorkerInfo;

import java.sql.Timestamp;

public class RequesterTaskInfo {
	//杈撳叆浠诲姟鏁扮粍锛�浠诲姟ID锛�鎴鏃堕棿锛�each_reward锛�宸叉敹褰曞伐浜烘暟锛岄渶瑕佸伐浜烘暟锛�浠诲姟闅惧害绯绘暟
	//杩欎釜绫绘槸涓轰簡甯姪璁＄畻鎺ㄨ崘浠诲姟瀹炵幇
	
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
