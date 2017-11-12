package Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import CaculateQuality.CaculateQualityImpl;
import CaculateSalary.CaculateSalaryImp;
import CaculateWorkerNum.CaculateNumImpl;
import CaculateWorkerNum.recomTask;
import CaculateWorkerNum.taskAssignment;
import RecommendTask.RecommendTaskImpl;
import WorkerInfo.RequesterTaskInfo;
import WorkerInfo.WorkerInfo;

public class test {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	   List<ArrayList> workerMessage =new ArrayList();
       
	  /* for(int i=1;i<6;i++){
		   ArrayList message=new ArrayList<String>();
		   message.add("name"+i);
	       message.add("0.5");
	       message.add(i+1);
	       workerMessage.add(message);  
	   }*/
       //System.out.println(workerMessage.toString());
	   /*ArrayList messageid=new ArrayList<String>();
	   for(int i=1;i<4;i++){
		   messageid.add("name"+i);
	   }
	   messageid.add("4");
	   messageid.add("5");
	   
	   ArrayList messagequlity=new ArrayList<String>();
	   for(int i=1;i<6;i++){
		   messagequlity.add((double)((double)i/5.0));
		   //System.out.println(messagequlity);
	   }
	   ArrayList messagecost=new ArrayList<String>();
	   for(int i=1;i<6;i++){
		   messagecost.add(i);
	   }
	   
       int n=3;
       taskAssignment task=new taskAssignment();
       task.recomTask(messageid,messagequlity, messagecost,n);*/
       
       
       List<WorkerInfo> workerInfos=new ArrayList();
       List<RequesterTaskInfo> requesterTaskInfo=new ArrayList();
       int worker_id=1;
       String quality="[['1.750028','0.249972','0.0','0.0','0.0','0.0','0.0'],['0.0','2.0','0.0','0.0','0.0','0.0','0.0'],['0.0','1.0','0.0','0.0','0.0','0.0','0.0'],['0.0','0.0','0.0','1.0','0.0','0.0','0.0'],['0.0','0.0','0.0','0.0','0.0','0.0','0.0'],['0.10755','0.014776','0.014776','0.012194','0.009905','0.513037','0.327762'],['0.0','0.0','0.0','0.0','0.0','0.0','0.0']]";
       int level=1;
       Double aver_costtime=0.1;
       Double aver_di=0.2;
	   Double aver_reward=1.0;
       WorkerInfo worker=new WorkerInfo(worker_id,  quality,  level, aver_costtime, aver_di,
			 aver_reward);
       
       int worker_id1=2;
       //String quality1="[['0.7','0.1','0.1'],['0.1','0.5','0.1'],['0.2','0.1','0.8']]";
       String quality1="[['1.0','0.0','0.0','1.0','0.0','0.0','0.0'],['0.750028','2.249972','0.0','0.0','0.0','0.0','0.0'],['0.0','0.0','0.0','0.0','0.0','0.0','0.0'],['0.0','0.0','0.0','1.0','0.0','0.0','0.0'],['0.0','0.0','0.0','0.0','0.0','0.0','0.0'],['0.0','0.0','0.0','0.0','0.0','0.0','0.0'],['0.10755','0.014776','0.014776','0.012194','0.009905','0.513037','0.327762']]";
       int level1=2;
       Double aver_costtime1=0.2;
       Double aver_di1=0.3;
	   Double aver_reward1=2.0;
       WorkerInfo worker1=new WorkerInfo(worker_id1,  quality1,  level1, aver_costtime1, aver_di1,
			 aver_reward1);
       
       int worker_id2=3;
       //String quality2="[['0.4','0.5','0.1'],['0.1','0.4','0.1'],['0.2','0.1','0.4']]";
       String quality2="[['1.750028','1.249972','0.0','1.0','0.0'],['0.0','2.0','0.0','0.0','0.0'],['0.0','0.0','0.0','0.0','0.0'],['0.0','0.0','0.0','1.0','0.0'],['0.0','0.0','0.0','0.0','0.0']]";
       int level2=2;
       Double aver_costtime2=0.2;
       Double aver_di2=0.3;
	   Double aver_reward2=3.0;
       WorkerInfo worker2=new WorkerInfo(worker_id2,  quality2,  level2, aver_costtime2, aver_di2,
			 aver_reward2);
       
       int worker_id3=4;
       //String quality3="[['0.5','0.1','0.1'],['0.1','0.5','0.1'],['0.2','0.5','0.8']]";
       String quality3="[['0.0','0.0','0.0','1.0','0.0'],['0.0','2.0','0.0','0.0','0.0'],['0.0','1.0','0.0','0.0','0.0'],['0.0','0.0','0.0','1.0','0.0'],['0.0','0.0','0.0','0.0','0.0']]";
       int level3=2;
       Double aver_costtime3=0.2;
       Double aver_di3=0.3;
	   Double aver_reward3=4.0;
       WorkerInfo worker3=new WorkerInfo(worker_id3,  quality3,  level3, aver_costtime3, aver_di3, aver_reward3);
       
       int worker_id4=5;
       String quality4="[['0.3','0.8','0.1'],['0.1','0.3','0.1'],['0.2','0.1','0.3']]";
       int level4=2;
       Double aver_costtime4=0.2;
       Double aver_di4=0.3;
	   Double aver_reward4=5.0;
       WorkerInfo worker4=new WorkerInfo(worker_id4,  quality4,  level4, aver_costtime4, aver_di4, aver_reward4);
       
       int worker_id5=6;
       String quality5="[['0.8','0.8','0.1'],['0.1','0.8','0.1'],['0.2','0.1','0.8']]";
       int level5=2;
       Double aver_costtime5=0.2;
       Double aver_di5=0.3;
	   Double aver_reward5=6.0;
       WorkerInfo worker5=new WorkerInfo(worker_id5,  quality5,  level5, aver_costtime5, aver_di5, aver_reward5);
       
       int worker_id6=7;
       String quality6="[['0.3','0.8','0.1'],['0.1','0.3','0.1'],['0.2','0.1','0.4']]";
       int level6=2;
       Double aver_costtime6=0.2;
       Double aver_di6=0.3;
	   Double aver_reward6=6.0;
       WorkerInfo worker6=new WorkerInfo(worker_id6,  quality6,  level6, aver_costtime6, aver_di6, aver_reward6);
       
       int worker_id7=8;
       String quality7="[['0.3','0.8','0.1'],['0.1','0.3','0.1'],['0.2','0.1','0.4']]";
       int level7=2;
       Double aver_costtime7=0.2;
       Double aver_di7=0.3;
	   Double aver_reward7=6.0;
       WorkerInfo worker7=new WorkerInfo(worker_id7,  quality7,  level7, aver_costtime7, aver_di7, aver_reward7);
       
      workerInfos.add(worker);
      workerInfos.add(worker1);
      workerInfos.add(worker2);
      workerInfos.add(worker3);
      workerInfos.add(worker4);
      workerInfos.add(worker5);
      workerInfos.add(worker6);
      workerInfos.add(worker7);



      int taskID=1;
      Timestamp deadline = null;
      Double each_reward=9.0;
      int hastaken_number=4;
      int worker_number=3;
      Double difficult_degree=0.5;
      RequesterTaskInfo requesterTask1=new RequesterTaskInfo( taskID, deadline, each_reward,  hastaken_number,worker_number,  difficult_degree);
     // requesterTaskInfo.add(requesterTask1);
      
      String[] work;
      RecommendTaskImpl task1=new RecommendTaskImpl();
      work=task1.getRecommendTask(5, 1,  workerInfos, requesterTask1);
      for(int m=0;m<work.length;m++){
	    	System.out.println(work[m]);
      }
      //System.out.println(workerInfos);
      
      /*String wquality="[['0.7','0.1','0.1'],['0.1','0.5','0.1'],['0.2','0.1','0.8']]";
  	  Double tfloat_minute=0.0;
  	  Double wbase=1.0;
  	  Double di=0.6;
  	  CaculateSalaryImp test=new CaculateSalaryImp();
  	  System.out.println(test.getSalary(wquality, tfloat_minute, wbase, di));*/
      //CaculateNum cal=new CaculateNum();
      //cal.getWorkNumber();

	  //task1.getTakenDeadline(worker_number, times, workerInfos,requesterTask1);
	  
	 
	  //String[] wm={"[['0.7','0.1','0.1'],['0.1','0.5','0.1'],['0.2','0.1','0.8']]","[['0.7','0.1','0.1'],['0.1','0.5','0.1'],['0.2','0.1','0.8']]","[['0.7','0.1','0.1'],['0.1','0.5','0.1'],['0.2','0.1','0.8']]"};
	  String[] wm={"[['1','0','0'],['0','0','0'],['0','0','0']]","[['1','0','0'],['0','0','0'],['0','0','0']]"};

	  CaculateNumImpl num=new CaculateNumImpl();
	  int n;
	 // n=num.getWorkNumber(wm);
	  
	  //String wm1="[['0.7','0.1','0.1'],['0.1','0.5','0.1'],['0.2','0.1','0.8']]";
	 // CaculateQualityImpl test1=new CaculateQualityImpl();
	  //System.out.println(test1.CaculateQua(wm1));
	}
	

}
