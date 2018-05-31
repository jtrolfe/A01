package percolation;

import edu.princeton.cs.algs4.Accumulator;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	public int matrixSize;
	public int numOfTests;
	public double[] percStats;
	
	public PercolationStats(int n, int t){
		this.matrixSize = n;
		this.numOfTests = t;
		
		if (matrixSize <= 0 || numOfTests <= 0)
			throw new IndexOutOfBoundsException("matrix of size " + matrixSize + "X" + matrixSize + 
					" and number of tests " + t + " must be greater than zero");
		this.testPerc();
		
	}
	
	public double mean(){
		//double[] stats = new double[numOfTests];
		// add some code here
		return StdStats.mean(this.percStats);
	}
	
	public double stddev(){
		//double[] stats = new double[numOfTests];
		
		return StdStats.stddev(this.percStats);
	}
	
	public double confidenceLow(){
		return mean()-(1.96*stddev()/Math.sqrt(numOfTests));
	}
	
	public double confidenceHigh(){
		return mean()+(1.96*stddev()/Math.sqrt(numOfTests));

	}
	
	private void testPerc(){
		int randRow;
		int randCol;
		int numOfOpen = 0;
		this.percStats = new double[this.numOfTests];
		for(int i = 0; i < this.numOfTests - 1; i++){
			Percolation myPercolator = new Percolation(this.matrixSize);
			while(myPercolator.percolates() == false){
				randRow = StdRandom.uniform(this.matrixSize);
				randCol = StdRandom.uniform(this.matrixSize);
				if(myPercolator.isOpen(randRow,randCol) == false){
					myPercolator.open(randRow,randCol);
					numOfOpen++;
				}
			}
			
			percStats[i] = numOfOpen/Math.pow(this.matrixSize, 2);
			numOfOpen = 0;
			
		}
		
	}

}
