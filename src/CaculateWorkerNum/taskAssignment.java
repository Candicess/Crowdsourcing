package CaculateWorkerNum;

import java.util.ArrayList;
import java.util.List;

public class taskAssignment {
	private int num;//控制推荐的工人的个数
	public String[] recomTask(ArrayList messageid,ArrayList messagequality,ArrayList messagecost,int n){
		num=n;
		ArrayList combineWorker =new ArrayList();//用来记录工人组合情况	
		String[] wokerId=new String[n];//存储最后推荐的工人id
		
		/**
		 * 得出所有工人的组合情况
		 **/
		//如果输入的工人信息为空，则返回空值
		if(messageid==null||messageid.size()==0){
            return null ; 
        }  
        ArrayList list=new ArrayList();  
        combineWorker=combine(messageid,0,n,list);
        System.out.println(combineWorker.toString());
        
        
        
        /**
         * 计算所有满足质量要求花费最少的工人的组合
         */
        wokerId=QualityCombine(combineWorker,messageid,messagequality,messagecost);
        /*for(int m=0;m<worker.length;m++){
        	System.out.println(worker[m]);
        }*/
		return wokerId;
		
	}
	
	/**
	 * 计算所有组合情况
	 * @param cs
	 * @param begin
	 * @param number
	 * @param list
	 * @return
	 * 
	 */
	//从字符数组中第begin个字符开始挑选number个字符加入list中  
	//private int k=0;//用记录组合的
	@SuppressWarnings("rawtypes")
	static ArrayList list1=new ArrayList();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  ArrayList combine(List cs,int begin,int number,ArrayList list){  
        if(number==0){  
            //System.out.println(list.toString());
        	if(list.size()==num){
            list1.add(list.toString());
        	}
            return list1;  
        }  
        if(begin==cs.size()){ 
        	if(list.size()==num){
            list1.add(list.toString());
        	}
            return list1;  
        }  
        list.add(cs.get(begin));  
        combine(cs,begin+1,number-1,list);  
        list.remove(cs.get(begin));  
        return combine(cs,begin+1,number,list);  
    } 
    
	
	
    /**
     * 计算满足质量要求和花费的组合
     */
	private int statisQuaNum;//做对的工人个数
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private String[] QualityCombine(ArrayList combineWorker,ArrayList messageid,ArrayList messagequality,ArrayList messagecost){
    	ArrayList satisWorkerMessage=new ArrayList();//存储符合质量条件的组合
		double[] cost =new double[combineWorker.size()];//存储所有组合的花费数
		double finalcost=1111111;//存储满足质量要求的最小的花费数
		int index = 0;//存储满足质量要求最小花费的组合索引
		String[] worker=new String[num];//存储满足质量要求的最小花费组合的工人id
		
	/**
	 * 计算满足质量要求的最小花费的组合
	 */
    	for(int i=0;i<combineWorker.size();i++){
    		System.out.println(combineWorker.get(i));
    		String[] workerID=new String[num];//存储工人id
    		int j=0;
    		
    		/**
    		 * 获取每组工人的id
    		 */
    		String message=combineWorker.get(i).toString();
    		while(j<num){
    			int m=0;
    			if(j==0){
    			workerID[j]=message.substring(m+1, message.indexOf(','));
    			message=message.substring(message.indexOf(',')+2, message.lastIndexOf(']')+1);
    			j++;
    			}
    			else if(j==num-1){
        			workerID[j]=message.substring(m, message.lastIndexOf(']'));
        			j++;
    			}
    			else{
    				workerID[j]=message.substring(m, message.indexOf(','));
        			message=message.substring(message.indexOf(',')+2, message.lastIndexOf(']')+1);
        			j++;
    			}
    			//System.out.println(workerID[j-1]);
    		}
    		
    		
    		/**
    		 * 获取每组工人的质量
    		 */
    		double[] wokerQua=new double[num];
			for(int l=0;l<workerID.length;l++){
				for(int k=0;k<messageid.size();k++){
					//获取包含在这个组合的工人的质量，存储在wokerQua中
    				if(workerID[l].equals(messageid.get(k).toString()))
    				{
    					wokerQua[l]=Double.parseDouble(messagequality.get(k).toString());
    				}
    			}
	    		//System.out.println(wokerQua[l]);
    		}
			
			
			/**
			 * 根据质量判断符合资格的工人的组合
			 */
			double quality=0;
			//调用质量计算的方法
			ArrayList list=new ArrayList();
			for(statisQuaNum=(num+1)/2;statisQuaNum<=num;statisQuaNum++){
				quality=QualityCom(wokerQua,0,statisQuaNum,list);
				}
			if(quality>0.5){
				satisWorkerMessage.add(combineWorker.get(i));	
			}
			comquality=0;//清零，为下一个组合做准备
    		
			
			/**
	    	 * 根据符合质量要求的工人的组合，选取最小花费的工人
	    	 */
	    	//调用花费计算的方法
			for(int l=0;l<workerID.length;l++){
				for(int k=0;k<messagecost.size();k++){
					//获取包含在这个组合的工人的花费，存储在cost数组中
    				if(workerID[l].equals(messageid.get(k).toString()))
    				{
    					cost[i]=cost[i]+Integer.parseInt((messagecost.get(k).toString()));
    				}
    			}
    		}
			
			//判断该组合是否在符合质量要求的列表中
			if(satisWorkerMessage.contains(combineWorker.get(i))){
				//如果在，判断该组合的花费是否最小
				if(cost[i]<finalcost){
					finalcost=cost[i];
					index=i;
					for(int m=0;m<workerID.length;m++){
						worker[m]=workerID[m];
					}
				}
			}
    	}
		//System.out.println(combineWorker.get(index));
    	
		return worker;
    	
    }
    
    
    
    /**
     * 质量计算方法
     */
    double comquality=0;
    //double c=1;
    @SuppressWarnings({ "unchecked" })
	private double  QualityCom(double[] wokerQua,int begin,int number,List list){
		double c=1;
		
		/**
		 * 递归函数的出口
		 * 返回工人做正确的概率
		 */
    	if(number==0){  
    		if(list.size()==statisQuaNum){
    			for(int m=0;m<wokerQua.length;m++){
    				if(list.contains(wokerQua[m])){
    					c=c*wokerQua[m];
    					}
    				else{
    					c=c*(1-wokerQua[m]);
    					}
    				}
    			}
    		comquality=comquality+c;//存储工人做正确的概率值
    		System.out.println(comquality);
            return comquality;
            }
    	if(begin==wokerQua.length){ 
    		if(list.size()==statisQuaNum){
    			for(int m=0;m<wokerQua.length;m++){
    				if(list.contains(wokerQua[m])){
    					c=c*wokerQua[m];
    					}
    				else{
    					c=c*(1-wokerQua[m]);
    					}
    				}
    		comquality=comquality+c;
    		System.out.println(comquality);
        	}
            return comquality;  
        }  
    	//工人没有做对的情况
        list.add(wokerQua[begin]); 
        QualityCom(wokerQua,begin+1,number-1,list);
        //工人做对的情况
        list.remove(wokerQua[begin]);
        return QualityCom(wokerQua,begin+1,number,list);  
    }
    
}
