package perceptron;

import java.util.Vector;

public class MyPerceptron {
	
	private static Vector<Double> Weight;
	private static Vector<Vector<Double>> TrainX; 
	private static Vector<Double> TrainY; 
	
	private static final int N = 3;
	private static final int TN = 4;
	
	private static final double ETA = 0.1;
	
	private static final int EPOCH = 100;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		init();
		
		for(int i = 0;i < EPOCH;i++)
		{
			for(int j = 0;j < TN;j++)
			{
				train(Weight,TrainX.get(j),TrainY.get(j));
			}
		}
		
		for(int i = 0;i < TN;i++)
		{
			System.out.println(out(dot(TrainX.get(i),Weight)));
		}
		
	}
	
	private static void train(Vector<Double> w,Vector<Double> a,Double y)
	{
		double out = out(dot(w,a));
		for(int i = 0;i < N;i++)
		{
			w.set(i, w.get(i) + (y - out) * a.get(i) * ETA);
		}
	}
	
	public static void init()
	{
		Weight = new Vector<>(N);
		TrainX = new Vector<Vector<Double>>(TN);
		TrainY = new Vector<>(TN);
		
		double tx[][] = {
				{0,0,1},
				{0,1,1},
				{1,0,1},
				{1,1,0}
		};
		
		double ty[] = {0,0,0,1};
		
		for(int i = 0;i < TN;i++)
		{
			TrainY.add(ty[1]);
		}
		
		for(int i = 0;i < TN;i++)
		{
			TrainX.add(new Vector<Double>(N));
			for(int j = 0;j < N;j++)
			{
				TrainX.get(i).add(tx[i][j]);
			}
		}
		
		for(int i = 0;i < N;i++)
		{
			Weight.add(0.0);
		}
	}
	
	public static double dot(Vector<Double> vec1,Vector<Double> vec2)
	{
		double tmp=0;
		int n = vec1.size();
		for(int i = 0;i < n;i++)
		{
			tmp = vec1.get(i) * vec2.get(i);
		}
		return tmp;
	}
	
	private static double out(double val)
	{
		return val >= 0 ? 1 : 0;
	}

}
