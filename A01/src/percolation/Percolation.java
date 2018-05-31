package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	public int size;
	private boolean[][] matrix;
	private WeightedQuickUnionUF quf;

	public Percolation(int n) {
		size = n;
		matrix = new boolean[size][size]; // Creates boolean array 0 equals closed 1 = open
		quf = new WeightedQuickUnionUF(size * size + 2); // Creates unionFind tracks boxes that are connected
		// Object zero and size*size+1 are the top and bottom of the matrix
	}

	public void open(int i, int j) {
		if (i < 0 || i >= size)
			throw new IndexOutOfBoundsException("row index " + i + " must be between 0 and " + (size - 1));
		if (j < 0 || j >= size)
			throw new IndexOutOfBoundsException("column index " + j + " must be between 0 and " + (size - 1));

		matrix[i][j] = true; // Open the square

		/* Edge case Merging */

		// Top Row
		if (i == 0) {
			quf.union(this.xyTo1d(i, j), 0);
			if (this.isOpen(i + 1, j)) {
				quf.union(this.xyTo1d(i, j), this.xyTo1d(i + 1, j));
			}
		}

		// Bottom Row
		if (i == (size - 1)) {

			quf.union(this.xyTo1d(i, j), (size * size + 1));

			if (this.isOpen(i - 1, j)) {
				quf.union(this.xyTo1d(i, j), this.xyTo1d(i - 1, j));
			}

			if (j > 0 && j < (size - 1)) {
				if (this.isOpen(i, j + 1)) {
					quf.union(this.xyTo1d(i, j), this.xyTo1d(i, j + 1));
				}
				if (this.isOpen(i, j - 1)) {
					quf.union(this.xyTo1d(i, j), this.xyTo1d(i, j - 1));
				}
			}

		}

		// Left Side
		if (j == 0 && i != 0) {
			if (this.isOpen(i, j + 1)) {
				quf.union(this.xyTo1d(i, j), this.xyTo1d(i, j + 1));
			}
		}

		// Right Side
		if (j == (size - 1) && i != 0) {
			if (this.isOpen(i, j - 1)) {
				quf.union(this.xyTo1d(i, j), this.xyTo1d(i, j - 1));
			}
		}

		/* Middle Cases */
		if (i > 0 && i < (size - 1)) {
			if (this.isOpen(i + 1, j)) {
				quf.union(this.xyTo1d(i, j), this.xyTo1d(i + 1, j));
			}
			if (this.isOpen(i - 1, j)) {
				quf.union(this.xyTo1d(i, j), this.xyTo1d(i - 1, j));
			}
			if (j > 0 && j < (size - 1)) {
				if (this.isOpen(i, j + 1)) {
					quf.union(this.xyTo1d(i, j), this.xyTo1d(i, j + 1));
				}
				if (this.isOpen(i, j - 1)) {
					quf.union(this.xyTo1d(i, j), this.xyTo1d(i, j - 1));
				}
			}

		}

	}

	public boolean isOpen(int i, int j) {
		if (i < 0 || i >= size)
			throw new IndexOutOfBoundsException("row index " + i + " must be between 0 and " + (size - 1));
		if (j < 0 || j >= size)
			throw new IndexOutOfBoundsException("column index " + j + " must be between 0 and " + (size - 1));
		return matrix[i][j];

	}

	// Converts from matrix indices (i, j) to linear array box number
	private int xyTo1d(int x, int y) {
		return x * size + y + 1;
	}

	public boolean isFull(int i, int j) {
		if (i < 0 || i >= size)
			throw new IndexOutOfBoundsException("row index " + i + " must be between 0 and " + (size - 1));
		if (j < 0 || j >= size)
			throw new IndexOutOfBoundsException("column index " + j + " must be between 0 and " + (size - 1));
		
		
		return quf.connected(this.xyTo1d(i, j), 0);
	}

	public boolean percolates() {
		return quf.connected(0, size * size + 1);
	}

	public String toString() {
		String outString = "";
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				outString = outString + matrix[x][y] + ", ";
			}
			outString = outString + "\n";
		}
		return outString;
	}
}
