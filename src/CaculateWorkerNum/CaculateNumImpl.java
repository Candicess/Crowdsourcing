package CaculateWorkerNum;

import java.text.DecimalFormat;
import java.util.ArrayList;

import net.sf.json.JSONArray;

public class CaculateNumImpl implements CaculateNum{
	public int getWorkNumber(String[] wm) {
        double[] quality=new double[wm.length];
		double u=0.65,average=0,c = 0;//��������ϵ�����Լ����˵�ƽ������
		
		/**
		 * ���㹤�˵�ƽ������
		 */
        for(int i=0;i<quality.length;i++){
        	quality[i]=ReadWm(wm[i]);
        	//System.out.println(quality[i]);
        	c=c+quality[i];
        }
        average=c/quality.length;//���˵�ƽ������
        
		/**
		 * ���㹤�˸���
		 */
		int n=0;
		n=getOptimizeWokerNum(average,u);
		System.out.println(n);
		return n;
	}
	//�Ż����˸���
		@SuppressWarnings("unused")
		private int getOptimizeWokerNum(double c,double u){
			//int s=1;
			
			int e=(int) (2*((0-Math.log(1-c))/(4*(u-0.5)*(u-0.5)))+1);
			/*while(s<e){
				int m=(int)((2*((s+e)/4+0.5))-1);
				double E=computeExpectedProb(m,u);
				if(E>=c){
					e=m;
					break;
				}
				else{
					s=m+2;
				}
				
			}*/
			return e;	
		}
		/*private double computeExpectedProb(int m,double u){
			double E=0,dit=Math.pow(u,m);
			double u1=0;
			for(int i=0;i<(m/2+1);i++){
				E=E+dit;
				u1=((1-u)*i)/(u*(m-i+1));
				dit=dit*u1;
				
			}
			return E;
			
		}*/
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
	   	DecimalFormat    df   = new DecimalFormat("######0.00"); 
	   	wa=Double.parseDouble(df.format(wa));
	   	return wa;	
	   }
	   
	   /**
		 * 
		 * ���ݹ���id�����ݿ��ж�ȡ����ģ�ͣ���ù���׼ȷ��
		 * 
		 */
		private double ReadWm(String wm) {
			// TODO Auto-generated method stub
			  ArrayList<Object> list = new ArrayList<Object>();
			  JSONArray jsonArray = new JSONArray();//����json����������
			  Object w ;
			  jsonArray.add(wm);//������ģ�ʹ���json������
			  
			  /**
			   * ��json�����еõ�����ģ�;��󣬸��ݸþ�����㹤�˵�׼ȷ��
			   */
			  w= jsonArray.get(0);
			  int n = 0;
			  for(int i=0;i<w.toString().length();i++){
				  if(w.toString().charAt(i)==(']'))
					  n++;//�����жϹ���ģ�͵Ĵ�С
				  }
			  double[][] m=new double[n-1][n-1];//���չ���ģ�;���
			  double qua=0;//���˵�׼ȷ��
			  JSONArray arr=JSONArray.fromObject(w);
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
			  qua=WokerAccuracy(m);//���ü��㹤�������������õ�����׼ȷ�� 
			  return  qua;
			  }
		}

