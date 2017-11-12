package CaculateSalary;

import java.text.DecimalFormat;
import java.util.ArrayList;

import net.sf.json.JSONArray;

@SuppressWarnings("unused")
public  class CaculateSalaryImp implements CaculateSalary{
	
	public double getSalary(String wquality,Double tfloat_minute,Double wbase,Double di){
		double wage=0.0,ui=0.0;
		double c=0.5;
		c=ReadWm(wquality);
		ui=0.5*(1+Math.pow(1-di, c));
		wage=wbase*ui;
		return wage;
		
	}
	   /**
    *
    * ���˵�׼ȷ�Լ���
    * 
    */
   
	private double WokerAccuracy(double[][] wm){
   	
   	double s=0;//��¼����ѡ��Ĵ�����ʵ����ͬ���ۻ�����ֵ
   	double sum=0;//��¼���˻ش�����������ۻ�����ֵ
   	double wa=0;//���˵�׼ȷ��
   	for(int i=0;i<wm.length;i++){
   		for(int j=0;j<wm.length;j++){
   			sum=sum+wm[i][j];
   			if(i==j){
   				s=s+wm[i][j];
   			}	
   		}
   	}
   	wa=s/sum;//����׼ȷ�ʵļ���
		return wa;	
   }

   
   
   /**
    * 
    * DealData�ӿڵ�ʵ��
    * 
    */
   
   /**
	 * 
	 * ���ݹ���id�����ݿ��ж�ȡ����ģ�ͣ���ù���׼ȷ��
	 * 
	 */
	private double ReadWm(String wm) {
		// TODO Auto-generated method stub
	      double Q=0;//���˵�׼ȷ��

		  /**
		   * ��json�����еõ�����ģ�;��󣬸��ݸþ�����㹤�˵�׼ȷ��
		   */
		      int n = 0;
		      for(int i=0;i<wm.length();i++){
		    	  if(wm.charAt(i)==(']'))
				  n++;//�����жϹ���ģ�͵Ĵ�С
		    	  }
		      double[][] m=new double[n-1][n-1];//���չ���ģ�;���
		      JSONArray arr=JSONArray.fromObject(wm);
		      //System.out.println(arr);
		      
			      /**
			       * ��json�����ж�ȡ�����ݿ��еĹ���ģ�;���
			       */
			     
			      int j=0;
			      for(Object o:arr){
			    	  JSONArray a1=(JSONArray) o;
			    	  for(int i=0;i<a1.size();i++){
			    		  m[j][i]=Double.parseDouble((String) a1.get(i));//��ȡ���ݿ��й���ģ��
			    		  }
			    	  j++;
			    	  }
			      Q=WokerAccuracy(m);//���ü��㹤�������������õ�����׼ȷ�� 
			 
			  return Q;
			  }
		
		}