package CaculateQuality;

import java.text.DecimalFormat;
import java.util.ArrayList;

import net.sf.json.JSONArray;

public class CaculateQualityImpl implements CaculateQuality{

	@Override
	public double CaculateQua(String wm) {
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
   
  
}
