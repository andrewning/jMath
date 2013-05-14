package jMath.linalg;

import java.util.Arrays;

import org.apache.commons.math.linear.*;

/**
 * a class of Matrix operations
 * @author S. Andrew Ning
 * @version 1.0, Feb. 2010
 * @version 1.1, June 2010
 * uses Apache Commons math library
 * no longer static - encapsulates a matrix object
 */
public class Matrix{
	
/* ------------------------
   Instance Variables
 * ------------------------ */
	// matrix
	RealMatrix A;
	
	// dimension m X n
	int m, n;
	
/* ------------------------
   Constructors
 * ------------------------ */
	
	public Matrix(){
		m = 0;
		n = 0;
	}
	
	public Matrix(int m, int n){
		A = new Array2DRowRealMatrix(m, n);
		this.m = m;
		this.n = n;
	}
	
	public Matrix(double[][] A){
		this(A,true);
	}

	/**
	 * construct matrix from double array A.  copy true if you want to copy contents of A 
	 * into the matrix, false if you just want to store by reference
	 * @param A
	 * @param copy
	 */
	public Matrix(double[][] A, boolean copy){
		this.A = new Array2DRowRealMatrix(A,copy);
		m = A.length;
		n = A[0].length;
	}
	
	/**
	 * construct a matrix either with data as columns or as rows
	 * @param columns
	 * @param data
	 */
	public Matrix(boolean columns, Vector...data){
		if (columns){
			m = data[0].length();
			n = data.length; // number of cols
			A = new Array2DRowRealMatrix(m, n);
			int i = 0;
			for (Vector col: data){
				A.setColumn(i++, col.getArrayRef());
			}
		} else{
			m = data.length; // number of rows
			n = data[0].length();
			A = new Array2DRowRealMatrix(m, n);
			int i = 0;
			for (Vector row: data){
				A.setRow(i++, row.getArrayRef());
			}
		}
	}
	
	public Matrix(RealMatrix A){
		this.A = A;
		m = A.getRowDimension();
		n = A.getColumnDimension();
	}
	
/* ------------------------
   Public Methods
 * ------------------------ */
	
	public double[][] getArray(){
		return A.getData();
	}
	
	
	/**
	 * get entry A(i,j) of the matrix
	 */
	public double get(int i, int j){
		return A.getEntry(i, j);
	}
	
	/**
	 * A(i,j) = alpha
	 * @param i
	 * @param j
	 * @param alpha
	 */
	public void set(int i, int j, double alpha){
		A.setEntry(i, j, alpha);
	}
	
	/**
	 * compute transpose of matrix
	 */
	public Matrix transpose(){
		return new Matrix(A.transpose());
	}
	
	/**
	 * add two matrices
	 * @param B
	 * @return
	 */
	public Matrix add(Matrix B){
		return new Matrix(A.add(B.A));
	}
	
	/**
	 * add an arbitrary number of matrices
	 * @param matrices
	 * @return
	 */
	public Matrix add(Matrix ... matrices){
		Matrix Sum = matrices[0];
		for (Matrix m : matrices){
			Sum = Sum.add(m);
		}
		return Sum;
	}
	
	/**
	 * subtract two matrices
	 * @param B
	 * @return
	 */
	public Matrix subtract(Matrix B){
		return new Matrix(A.subtract(B.A));
	}
	
	/**
	 * matrix-vector multiply
	 * @param x
	 * @return
	 */
	public Vector multiply(Vector x){
		return new Vector(A.operate(new ArrayRealVector(x.getArrayRef())));
	}
	
	/**
	 * replace a subset of this matrix starting at entry row,col with matrix B 
	 * @param B
	 * @param row
	 * @param col
	 * @return
	 */
	public void setSub(Matrix B, int row, int col){
		A.setSubMatrix(B.getArray(), row, col);
	}
	
	/**
	 * set column i equal to vector x
	 * @param i
	 * @param x
	 */
	public void setColumn(int i, Vector x){
		A.setColumn(i, x.getArrayRef());
	}
	
	/**
	 * set row i equal to vector x
	 * @param i
	 * @param x
	 */
	public void setRow(int i, Vector x){
		A.setRow(i, x.getArrayRef());
	}
	
	/**
	 * get column j
	 * @param j
	 * @return
	 */
	public Vector getColumn(int j){
		return new Vector(A.getColumn(j));
	}
	
	/**
	 * get row i
	 * @param i
	 * @return
	 */
	public Vector getRow(int i){
		return new Vector(A.getRow(i));
	}
	
	/**
	 * check to see if matrix is symmetric
	 * @return
	 */
	public boolean isSymmetric(){
		Matrix B = this.transpose();
		double[][] x = A.getData();
		double[][] y = B.A.getData();
		
		for(int i = 0; i < m; i++){
			if (!Arrays.equals(x[i],y[i])) return false;
		}
		return true;
	}
	
	/**
	 * check to see if matrix is square
	 * @return
	 */
	public boolean isSquare(){
		return m == n;
	}
	
	/**
	 * solve least squares solution to a linear system of equations Ax = b
	 * Assumes A is dense.  If there is some structure in A that you can 
	 * exploit you should be using a different method.
	 * @param A
	 * @param b
	 * @return x
	 */
	public Vector solve (Vector b){
		// TODO: check if positive definite.  if so should use Cholesky.
		return (this.isSquare()? this.LUSolve(b) :
								 this.QRSolve(b));
	}
	

	/**
	 * computes least squares solution to Ax=b using LU decomposition (appropriate for square matrix)
	 * @param b
	 * @return
	 */
	public Vector LUSolve(Vector b) {
		RealVector bv = new ArrayRealVector(b.getArrayRef());
		
		RealVector xv = new LUDecompositionImpl(A).getSolver().solve(bv);
		return new Vector(xv.getData());
	}

	/**
	 * computes least squares solution using QR decomposition
	 * @param b
	 * @return
	 */
	public Vector QRSolve(Vector b) {
		RealVector bv = new ArrayRealVector(b.getArrayRef());
		
		RealVector xv = new QRDecompositionImpl(A).getSolver().solve(bv);
		return new Vector(xv.getData());
	}


	
	
}
