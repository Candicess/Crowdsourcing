package CaculateWorkerNum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class recomTask {
	@SuppressWarnings({ "rawtypes" })
	
	private int num;//�����Ƽ��Ĺ��˵ĸ���
	public String[] recomTask(List workerMessage,int n){
		num=n;
		ArrayList combineWorker =new ArrayList();//������¼����������	
		String[] wokerId=new String[n];//�洢����Ƽ��Ĺ���id
		
		/**
		 * �ó����й��˵�������
		 **/
		//�������Ĺ�����ϢΪ�գ��򷵻ؿ�ֵ
		if(workerMessage==null||workerMessage.size()==0){  
            return null ; 
        }  
        ArrayList list=new ArrayList();  
        combineWorker=combine(workerMessage,0,3,list); 
        System.out.println(combineWorker.toString());
        
        
        
        /**
         * ����������������Ҫ��Ĺ��˵����
         */
        //QualityCombine(combineWorker);
        
		return wokerId;
		
	}
	
	/**
	 * ��������������
	 * @param cs
	 * @param begin
	 * @param number
	 * @param list
	 * @return
	 * 
	 */
	//���ַ������е�begin���ַ���ʼ��ѡnumber���ַ�����list��  
	//private int k=0;//�ü�¼��ϵ�
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
     * ������������Ҫ����������
     */
    @SuppressWarnings({ "unused", "rawtypes" })
	private ArrayList QualityCombine(ArrayList combineWorker){
    	ArrayList satisWorkerMessage=new ArrayList();//�洢�����������������
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
