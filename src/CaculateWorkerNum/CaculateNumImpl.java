package CaculateWorkerNum;

import java.text.DecimalFormat;
import java.util.ArrayList;

import net.sf.json.JSONArray;

public class CaculateNumImpl implements CaculateNum{
	public int getWorkNumber(String[] wm) {
        double[] quality=new double[wm.length];
		double u=0.65,average=0,c = 0;//定义质量系数，以及工人的平均质量
		
		/**
		 * 计算工人的平均质量
		 */
        for(int i=0;i<quality.length;i++){
        	quality[i]=ReadWm(wm[i]);
        	//System.out.println(quality[i]);
        	c=c+quality[i];
        }
        average=c/quality.length;//工人的平均质量
        
		/**
		 * 计算工人个数
		 */
		int n=0;
		n=getOptimizeWokerNum(average,u);
		System.out.println(n);
		return n;
	}
	//优化工人个数
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
	    * 工人的准确性计算
	    * 
	    */
	   
		private double WokerAccuracy(double[][] wm){
	   	
	   	double s=0;//记录工人选择的答案与真实答案相同的累积贡献值
	   	double sum=0;//记录工人回答所有问题的累积贡献值
	   	double wa=0;//工人的准确率
	   	for(int i=0;i<wm.length;i++){
	   		for(int j=0;j<wm.length;j++){
	   			sum=sum+wm[i][j];
	   			if(i==j){
	   				s=s+wm[i][j];
	   			}	
	   		}
	   	}
	   	wa=s/sum;//工人准确率的计算
	   	DecimalFormat    df   = new DecimalFormat("######0.00"); 
	   	wa=Double.parseDouble(df.format(wa));
	   	return wa;	
	   }
	   
	   /**
		 * 
		 * 根据工人id从数据库中读取工人模型，获得工人准确率
		 * 
		 */
		private double ReadWm(String wm) {
			// TODO Auto-generated method stub
			  ArrayList<Object> list = new ArrayList<Object>();
			  JSONArray jsonArray = new JSONArray();//定义json，接收数组
			  Object w ;
			  jsonArray.add(wm);//将工人模型存入json数组中
			  
			  /**
			   * 从json数组中得到工人模型矩阵，根据该矩阵计算工人的准确性
			   */
			  w= jsonArray.get(0);
			  int n = 0;
			  for(int i=0;i<w.toString().length();i++){
				  if(w.toString().charAt(i)==(']'))
					  n++;//用来判断工人模型的大小
				  }
			  double[][] m=new double[n-1][n-1];//接收工人模型矩阵
			  double qua=0;//工人的准确性
			  JSONArray arr=JSONArray.fromObject(w);
			  /**
			   * 从json数组中读取出数据库中的工人模型矩阵
			   */
			  int j=0;
			  for(Object o:arr){
				  JSONArray a1=(JSONArray) o;
				  for(int i=0;i<a1.size();i++){
					  m[j][i]=Double.parseDouble((String) a1.get(i));//读取数据库中工人模型
					  }
				  j++;
				  }
			  qua=WokerAccuracy(m);//调用计算工人质量方法，得到工人准确性 
			  return  qua;
			  }
		}

