package CaculateWorkerNum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class recomTask {
	@SuppressWarnings({ "rawtypes" })
	
	private int num;//控制推荐的工人的个数
	public String[] recomTask(List workerMessage,int n){
		num=n;
		ArrayList combineWorker =new ArrayList();//用来记录工人组合情况	
		String[] wokerId=new String[n];//存储最后推荐的工人id
		
		/**
		 * 得出所有工人的组合情况
		 **/
		//如果输入的工人信息为空，则返回空值
		if(workerMessage==null||workerMessage.size()==0){  
            return null ; 
        }  
        ArrayList list=new ArrayList();  
        combineWorker=combine(workerMessage,0,3,list); 
        System.out.println(combineWorker.toString());
        
        
        
        /**
         * 计算所有满足质量要求的工人的组合
         */
        //QualityCombine(combineWorker);
        
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
    @SuppressWarnings({ "rawtypes" })
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
     * 计算满足质量要求的所有组合
     */
    @SuppressWarnings({ "unused", "rawtypes" })
	private ArrayList QualityCombine(ArrayList combineWorker){
    	ArrayList satisWorkerMessage=new ArrayList();//存储符合质量条件的组合
    	double[] workerquality =new double[num];
        for(int i=0;i<combineWorker.size();i++){
        	String[] wokerMessage =new String[num];
    		String message=combineWorker.get(i).toString().substring(1,combineWorker.get(i).toString().length()-1);
        	int m = 0,n = 0;
    		for(int j=0;j<message.length();j++){
    			if(message.charAt(j)==(']')){
    				m=j;
    				break;
    			}
		}
			System.out.println(m);
			System.out.println(n);

        }

		return null;
    	
    }
}
