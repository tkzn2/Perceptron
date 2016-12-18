package perceptron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyPerceptron {
	
	private static List<Double> Weight;
	private static List<List<Double>> TrainX; 
	private static List<Double> TrainY; 
	
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
	
	private static void train(List<Double> w,List<Double> a,Double y)
	{
		int out = out(dot(w,a));
		for(int i = 0;i < N;i++)
		{
			w.set(i, w.get(i) + (y - out) * a.get(i) * ETA);
		}
	}
	
	public static void init()
	{
		Double tx[][] = {
				{0.0,0.0,1.0},
				{0.0,1.0,1.0},
				{1.0,0.0,1.0},
				{1.0,1.0,1.0}
		};
		
		Double ty[] = {0.0,0.0,0.0,1.0};
		
		Weight = Stream.generate(() -> 0.0).limit(N).collect(Collectors.toList());
		TrainX = new ArrayList<List<Double>>(TN);
		TrainY = Arrays.stream(ty).collect(Collectors.toList());
		
		for(int i = 0;i < TN;i++)
		{
			TrainX.add(Arrays.stream(tx[i]).collect(Collectors.toList()));
		}
	}
	
	public static double dot(List<Double> vec1,List<Double> vec2)
	{
		double tmp=0;
		int n = vec1.size();
		for(int i = 0;i < n;i++)
		{
			tmp += vec1.get(i) * vec2.get(i);
		}
		return tmp;
	}
	
	private static int out(double val)
	{
		return val > 0 ? 1 : 0;
	}

}
