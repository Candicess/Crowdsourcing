package CaculateWorkerNum;

import java.util.ArrayList;
import java.util.List;

public class taskAssignment {
	private int num;//�����Ƽ��Ĺ��˵ĸ���
	public String[] recomTask(ArrayList messageid,ArrayList messagequality,ArrayList messagecost,int n){
		num=n;
		ArrayList combineWorker =new ArrayList();//������¼����������	
		String[] wokerId=new String[n];//�洢����Ƽ��Ĺ���id
		
		/**
		 * �ó����й��˵�������
		 **/
		//�������Ĺ�����ϢΪ�գ��򷵻ؿ�ֵ
		if(messageid==null||messageid.size()==0){
            return null ; 
        }  
        ArrayList list=new ArrayList();  
        combineWorker=combine(messageid,0,n,list);
        System.out.println(combineWorker.toString());
        
        
        
        /**
         * ����������������Ҫ�󻨷����ٵĹ��˵����
         */
        wokerId=QualityCombine(combineWorker,messageid,messagequality,messagecost);
        /*for(int m=0;m<worker.length;m++){
        	System.out.println(worker[m]);
        }*/
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
     * ������������Ҫ��ͻ��ѵ����
     */
	private int statisQuaNum;//���ԵĹ��˸���
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private String[] QualityCombine(ArrayList combineWorker,ArrayList messageid,ArrayList messagequality,ArrayList messagecost){
    	ArrayList satisWorkerMessage=new ArrayList();//�洢�����������������
		double[] cost =new double[combineWorker.size()];//�洢������ϵĻ�����
		double finalcost=1111111;//�洢��������Ҫ�����С�Ļ�����
		int index = 0;//�洢��������Ҫ����С���ѵ��������
		String[] worker=new String[num];//�洢��������Ҫ�����С������ϵĹ���id
		
	/**
	 * ������������Ҫ�����С���ѵ����
	 */
    	for(int i=0;i<combineWorker.size();i++){
    		System.out.println(combineWorker.get(i));
    		String[] workerID=new String[num];//�洢����id
    		int j=0;
    		
    		/**
    		 * ��ȡÿ�鹤�˵�id
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
    		 * ��ȡÿ�鹤�˵�����
    		 */
    		double[] wokerQua=new double[num];
			for(int l=0;l<workerID.length;l++){
				for(int k=0;k<messageid.size();k++){
					//��ȡ�����������ϵĹ��˵��������洢��wokerQua��
    				if(workerID[l].equals(messageid.get(k).toString()))
    				{
    					wokerQua[l]=Double.parseDouble(messagequality.get(k).toString());
    				}
    			}
	    		//System.out.println(wokerQua[l]);
    		}
			
			
			/**
			 * ���������жϷ����ʸ�Ĺ��˵����
			 */
			double quality=0;
			//������������ķ���
			ArrayList list=new ArrayList();
			for(statisQuaNum=(num+1)/2;statisQuaNum<=num;statisQuaNum++){
				quality=QualityCom(wokerQua,0,statisQuaNum,list);
				}
			if(quality>0.5){
				satisWorkerMessage.add(combineWorker.get(i));	
			}
			comquality=0;//���㣬Ϊ��һ�������׼��
    		
			
			/**
	    	 * ���ݷ�������Ҫ��Ĺ��˵���ϣ�ѡȡ��С���ѵĹ���
	    	 */
	    	//���û��Ѽ���ķ���
			for(int l=0;l<workerID.length;l++){
				for(int k=0;k<messagecost.size();k++){
					//��ȡ�����������ϵĹ��˵Ļ��ѣ��洢��cost������
    				if(workerID[l].equals(messageid.get(k).toString()))
    				{
    					cost[i]=cost[i]+Integer.parseInt((messagecost.get(k).toString()));
    				}
    			}
    		}
			
			//�жϸ�����Ƿ��ڷ�������Ҫ����б���
			if(satisWorkerMessage.contains(combineWorker.get(i))){
				//����ڣ��жϸ���ϵĻ����Ƿ���С
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
     * �������㷽��
     */
    double comquality=0;
    //double c=1;
    @SuppressWarnings({ "unchecked" })
	private double  QualityCom(double[] wokerQua,int begin,int number,List list){
		double c=1;
		
		/**
		 * �ݹ麯���ĳ���
		 * ���ع�������ȷ�ĸ���
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
    		comquality=comquality+c;//�洢��������ȷ�ĸ���ֵ
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
    	//����û�����Ե����
        list.add(wokerQua[begin]); 
        QualityCom(wokerQua,begin+1,number-1,list);
        //�������Ե����
        list.remove(wokerQua[begin]);
        return QualityCom(wokerQua,begin+1,number,list);  
    }
    
}
