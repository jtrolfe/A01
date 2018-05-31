package percolation;

public class PercolationApp {

	public static void main(String[] args) {
	/*	Percolation mypercolator = new Percolation(5);
		mypercolator.open(0, 1);
		mypercolator.open(1, 1);
		mypercolator.open(2, 1);
		mypercolator.open(3, 1);
		mypercolator.open(4, 1);
		mypercolator.open(4, 2);
		mypercolator.open(4, 3);
		mypercolator.open(3, 3);
		//System.out.println(mypercolator.isOpen(4, 1));
		System.out.println(mypercolator.toString());
		System.out.println(mypercolator.percolates()); */
		
		PercolationStats myPercStats = new PercolationStats(200,100);
		System.out.println("mean: " + myPercStats.mean());
		System.out.println("stddev: " + myPercStats.stddev());
		System.out.println("Confidenc Low: " + myPercStats.confidenceLow());
		System.out.println("Confidenc High: " + myPercStats.confidenceHigh());

	}

}
