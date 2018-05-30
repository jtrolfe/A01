package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
public int size;

	private boolean[][] matrix;
	private WeightedQuickUnionUF quf;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Percolation(int n) {
		size = n;
		matrix = new boolean[n][n];
		quf = new WeightedQuickUnionUF(size*size +2);
	}
	
	public void open(int i, int j) {
		matrix[i][j] = true;
		
		if(i==0) {
			quf.union(this.xyTo1d(i, j), 0);
			if(this.isOpen(i+1, j)) {
				quf.union(this.xyTo1d(i, j), this.xyTo1d(i+1, j));
			}
		}
		
		if(i==(size -1)) {
			quf.union(this.xyTo1d(i, j), (size*size+1) );
			if(this.isOpen(i-1, j)) {
				quf.union(this.xyTo1d(i, j), this.xyTo1d(i-1, j));
			}
		}
		
		if(j==0 && i!=0 && i!=(size -1)) {
			if(this.isOpen(i,j+1)) {
				quf.union(this.xyTo1d(i, j), this.xyTo1d(i, j+1));
			}
		}
		
		if(j==size && i!=0 && i!=(size -1)) {
			if(this.isOpen(i,j-1)) {
				quf.union(this.xyTo1d(i, j), this.xyTo1d(i, j-1));
			}
		}
		
		if(i > 0 && i<(size-1)) {
			if(this.isOpen(i+1, j)) {
				quf.union(this.xyTo1d(i, j), this.xyTo1d(i+1, j));
			}
			if(this.isOpen(i-1, j)) {
				quf.union(this.xyTo1d(i, j), this.xyTo1d(i-1, j));
			}
			if(j>0 && j<(size-1)) {
				if(this.isOpen(i, j+1)) {
					quf.union(this.xyTo1d(i, j), this.xyTo1d(i, j+1));
				}
				if(this.isOpen(i, j-1)) {
					quf.union(this.xyTo1d(i, j), this.xyTo1d(i, j-1));
				}
			}
			
		}
		
	}
	
	public boolean isOpen(int i, int j) {
		return matrix[i][j];
		
	}
	
	private int xyTo1d(int x, int y) {
		return x*size + y + 1;
	}
	
	public boolean isFull(int i, int j) {
		return quf.connected(this.xyTo1d(i, j), 0);
	}
	
	public boolean percolates() {
		return quf.connected(0, size*size + 1);
	}
	
	public String toString() {
		String outString = "";
		for(int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				outString = outString + matrix[x][y] + ", ";
			}
			outString = outString + "\n";
		}
		return outString;
	}
}
