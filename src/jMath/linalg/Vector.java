package jMath.linalg;

import java.util.*;

import org.apache.commons.math.linear.RealVector;

import jMath.func.Function1Var;


/**
 * The class Vector represents an n-dimensional vector.
 * Intentionally mimics many of the naming conventions of MATLAB.
 * @author S. Andrew Ning
 * @version April 1, 2010
 *
 */
public class Vector {

/* ------------------------
   Instance Variables
 * ------------------------ */

	// 1D array stores internal elements
	protected double[] x;
	
	// dimension of array
	protected int n;
	
/* ------------------------
   Constructors
 * ------------------------ */
	
	/**
	 * Default constructor
	 */
	public Vector(){
		n = 0;
		x = new double[n];
	}
	
	/**
	 * Construct a vector of zeros of size n.
	 */
	public Vector(int n){
		this.n = n;
		x = new double[n];
	}
	
	/**
	 * Construct a vector directly from a sequence of numbers 
	 * (Vector x = new Vector(4.3, 23.4, 32.3);)
	 * or pass in a 1D array.
	 */
	public Vector(double... nums){
		n = nums.length;
		x = new double[n];
		
		int i = 0;
		for(double d: nums){
			x[i++] = d;
		}
	}
//	
//	/**
//	 * Construct a vector directly from a sequence of numbers 
//	 * (Vector x = new Vector(4.3, 23.4, 32.3);)
//	 * or pass in a 1D array.
//	 */
//	public Vector(Double... nums){
//		n = nums.length;
//		x = new double[n];
//		
//		int i = 0;
//		for(double d: nums){
//			x[i++] = d;
//		}
//	}
	
	/**
	 * Construct a vector from Apache Commons' RealVector
	 * @param v
	 */
	public Vector(RealVector v){
		n = v.getDimension();
		x = v.getData();
	}
	
	/**
	 * Creates a constant vector with value alpha of length n 
	 * @param alpha
	 * @param n
	 * @return
	 */
	public static Vector constant(double alpha, int n){
		return new Vector(Array1D.constant(alpha, n));		
	}
	
	
	/**
	 * Creates an vector of ones of length n
	 * @param n
	 * @return
	 */
	public static Vector ones(int n){
		return constant(1.0,n);
	}
	
	/**
	 * Creates a vector of zeros of length n
	 * @param n
	 * @return
	 */
	public static Vector zeros(int n){
		return constant(0.0,n);
	}
	
	/**
	 * Generates a random vector of size n
	 * @param n  length of vector
	 * @return vector with random numbers
	 */
	public static Vector rand(int n){
		return new Vector(Array1D.rand(n));
	}
	
	/**
	 * Creates an vector mimicking Matlab's colon operation a:ds:b
	 * (does not necessarily end on b.  see {@link #linspace linspace} if you want it to end exactly at b)
	 * @param a
	 * @param ds
	 * @param b
	 */
	public static Vector colon(double a, double ds, double b){
		return new Vector(Array1D.colon(a, ds, b));
	}
	
	/**
	 * Creates a vector mimicking Matlab's colon operation a:b
	 * @param a
	 * @param b
	 */
	public static Vector colon(double a, double b){
		return colon(a,1.0,b);
	}
	
	/**
	 * Creates a linearly spaced vector
	 * @param x1  starting value
	 * @param x2  ending value
	 * @param n  number of points
	 * @return vector from x1 to x2 with length n
	 */
	public static Vector linspace(double x1, double x2, int n){
		return new Vector(Array1D.linspace(x1, x2, n));
	}
	
	/**
	 * a logarithmically spaced vector
	 * @param x1  starting value
	 * @param x2  ending value
	 * @param n  number of points
	 * @return vector from 10^x1 to 10^x2 with length n
	 */
	public static Vector logspace(double x1, double x2, int n){
		return new Vector(Array1D.logspace(x1, x2, n));
	}
	
	/**
	 * Create a copy of the vector
	 * @param x
	 * @return
	 */
	public static Vector copy(Vector x){
		Vector y = new Vector(x.n);
		
		System.arraycopy(x.x, 0, y.x, 0, x.n);
		return y;
	}
	
/* ------------------------
   Public Methods
 * ------------------------ */
	
	/** 
	 * Access the underlying 1D array.
	 * @return Pointer to the one-dimensional array of vector elements
	*/

	public double[] getArrayRef() {
		return x;
	}
	
	/**
	 * Creates a copy of the underlying 1D array
	 * @return a copy of the underlying 1D array
	 */
	public double[] getArray(){
		double[] y = new double[n];
		
		System.arraycopy(x, 0, y, 0, n);
		return y;
	}
	
	/** 
	 * Get length of the vector
	 * @return length of the vector
	 */
	public int length(){
		return n;
	}
	
	/** Get a single element.
	 * @param i  index.
	 * @return x(i)
	 * @exception: ArrayIndexOutOfBoundsException
	 */
	public double get (int i) {
		return x[i];
	}
	
	/**
	 * get first element in vector
	 * @return
	 */
	public double getFirst(){
		return x[0];
	}
	
	/**
	 * get last element in vector
	 * @return
	 */
	public double getLast(){
		return x[x.length-1];
	}
	
	/** 
	 * Set a single element.
	 * @param i  index
	 * @param d  number to set to
	 */
	public void set(int i, double a){
		x[i] = a;
	}
	
	/** 
	 * Get a sub-vector
	 * @param iStart  Starting index
	 * @param iEnd  Ending index
	 * @return  x(iStart:iEnd)
	 * @exception  ArrayIndexOutOfBoundsException
	 */
	public Vector sub(int iStart, int iEnd){
		return new Vector(Array1D.sub(x, iStart, iEnd));
	}
	
	/** 
	 * Get a sub-vector
	 * @param e  array of elements of vector to extract
	 * @return x(e(:))
	 * @exception  ArrayIndexOutOfBoundsException
	 */
	public Vector sub(int[] e){
		return new Vector(Array1D.sub(x, e));
	}
	
	/** 
	 * Set a part of the vector
	 * this(istart:iend) = v
	 * @param iStart  starting index
	 * @param iEnd  ending index (inclusive)
	 * @param v
	 */
	public void setSub(Vector v, int iStart){
		Array1D.setSub(x, v.x, iStart);
	}
	
	
	/** 
	 * Set a part of the vector
	 * this(e(:)) = v
	 * @param e  elements to set the vector into
	 * @param v  vector to insert
	 */
	public void setSub(Vector v, int[] e){
		Array1D.setSub(x, v.x, e);
	}
	
	/**
	 * concatenate vectors into one larger one
	 * [v1 v2 .... vn]
	 * @param vlist
	 * @return
	 */
	public static Vector concat(Vector...vlist){
		int size = 0;
		for (Vector v: vlist){
			size += v.n;
		}
		Vector vNew = new Vector(size);
		
		int pos = 0;
		for (Vector v: vlist){
			System.arraycopy(v.x, 0, vNew.x, pos, v.n);
			pos += v.n;
		}
		return vNew;
	}
	
	
	/**
	 * Vector norm
	 * @return norm(V,P) = sum(abs(V).^P)^(1/P).
	 */
	public static double norm(Vector x, double p){
		return Array1D.norm(x.x, p);
	}
	
	/**
	 * sort vector in numerical order
	 * @param x
	 * @return the new sorted vector
	 */
	public static Vector sort(Vector x){
		return new Vector(Array1D.sort(x.x));
	}
	
	/**
	 * get maximum value in vector
	 * @param v
	 * @return
	 */
	public static double max(Vector x){
		return Array1D.max(x.x);
	}
	
	/**
	 * find minimum value in vector
	 * @param v
	 * @return
	 */
	public static double min(Vector x){
		return Array1D.min(x.x);
	}
	

	/**
	 * Change sign of vector
	 * @param x
	 * @return -x
	 */
	public static Vector negative(Vector x){
		return x.multiply(-1.0);
	}
	
	/**
	 * add a constant to every entry in the vector
	 * @param alpha  constant
	 * @return x + alpha{<b>1</b>}
	 */
	public Vector add(double alpha){
		return new Vector(Array1D.add(x, alpha));
	}
	
	/**
	 * add two vectors
	 * @param y  vector
	 * @return the sum of the vectors
	 */
	public Vector add(Vector y){
		return new Vector(Array1D.add(x,y.x));
	}
	
	/**
	 * x += y
	 * @param y
	 */
	public void addEquals(Vector y){
		Vector z = this.add(y);
		x = z.x;
	}
	
	
	/**
	 * adds an arbitrary number of vectors together
	 * @param v
	 * @return x1 + x2 + ...
	 */
	public static Vector add(Vector ... vectors){
		Vector x = copy(vectors[0]);
		for(Vector y: vectors){
			x.add(y);
		}
		return x;
	}
	
	/**
	 * subtract a constant from every entry in the vector
	 * @param alpha  constant
	 * @return x - alpha{<b>1</b>}
	 */
	public Vector subtract(double alpha){
		return this.add(-alpha);
	}
	
	/**
	 * subtract two vectors (x-y)
	 * @param y  vector
	 * @return the sum of the vectors
	 */
	public Vector subtract(Vector y){
		return new Vector(Array1D.subtract(x, y.x));
	}
	
	/**
	 * x -= y
	 * @param y
	 */
	public void subtractEquals(Vector y){
		Vector z = this.subtract(y);
		x = z.x;
	}
	
	/**
	 * multiply a vector by a constant
	 * @param alpha  constant
	 * @return alpha*x
	 */
	public Vector multiply(double alpha){
		return new Vector(Array1D.multiply(x, alpha));
	}
	
	/**
	 * x *= alpha;
	 * @param alpha
	 */
	public void multiplyEquals(double alpha){
		Vector z = this.multiply(alpha);
		x = z.x;
	}
	
	/**
	 * element by element multiplication of two vectors
	 * @param y  vector
	 * @return a vector z where z_i = x_i*y_i
	 */
	public Vector dotTimes(Vector y){
		return new Vector(Array1D.dotMultiply(x, y.x));
	}
	
	/**
	 * divide a vector by a constant
	 * @param alpha  constant
	 * @return x/alpha
	 */
	public Vector divide(double alpha){
		return this.multiply(1.0/alpha);
	}
	
	/**
	 * x /= alpha;
	 * @param alpha
	 */
	public void divideEquals(double alpha){
		Vector z = this.divide(alpha);
		x = z.x;
	}
	
	/**
	 * element by element division of two vectors
	 * @param y  vector
	 * @return a vector z where z_i = x_i/y_i
	 */
	public Vector dotDivide(Vector y){
		return new Vector(Array1D.dotDivide(x, y.x));
	}
	
	/**
	 * computes powers of vectors elementwise
	 * @param x
	 * @param exp
	 * @return x_i^exp
	 */
	public Vector pow(double exp){
		return new Vector(Array1D.pow(x, exp));
	}
	
	
	/**
	 * elementwise square root of a vector
	 * @param x
	 * @return x.^(1/2)
	 */
	public static Vector sqrt(Vector x){
		return x.pow(0.5);
	}
	
	/**
	 * Elementwise application of an arbitrary 1D function
	 * @param v
	 * @param f
	 * @return
	 */
	public static Vector eval(Function1Var func, Vector x){
		return new Vector(Array1D.eval(func, x.x));
	}
	

	/**
	 * returns dot product of two vectors
	 * @param y  vector
	 * @return the dot product of x and y
	 */
	public double dot(Vector y){
		return sum(dotTimes(y));
	}
	
	/**
	 * computes cross product of two 3 dimensional vectors
	 * @param y  2nd vector
	 * @return the cross product x X y
	 * @throws IllegalArgumentException
	 */
	public Vector cross (Vector y) throws IllegalArgumentException{
		return new Vector(Array1D.cross(x, y.x));
	}

	
	/**
	 * returns the sum of the entries in the vector
	 * @return the sum entries in the vector
	 */
	public static double sum(Vector x){
		return Array1D.sum(x.x);
	}
	
	/**
	 * cumulative sum of elements in x
	 * @param x
	 * @return the cumulative sum of element in x
	 */
	public static Vector cumsum(Vector x){
		return new Vector(Array1D.cumsum(x.x));
	}
	
	/**
	 * takes absolute value of a vector
	 * @param x
	 * @return |x|
	 */
	public static Vector abs (Vector x){
		return new Vector(Array1D.abs(x.x));
	}
	
	/**
	 * numerical integration of y w.r.t x using trapezoidal method.
	 * @param x
	 * @param y
	 * @return value of the integral
	 * @throws IllegalArgumentException
	 */
	public static double trapz(Vector x, Vector y) throws IllegalArgumentException{
		return Array1D.trapz(x.x, y.x);
	}
	
	/**
	 * numerical derivative of y w.r.t x using central difference
	 * except at end points.
	 * @param x
	 * @param y
	 * @return dy/dx
	 */
	public static Vector deriv(Vector x, Vector y){
		return new Vector(Array1D.deriv(x.x, y.x));
	}
	
	/**
	 * flip a vector around
	 * @param x
	 * @return flipped vector
	 */
	public static Vector flip(Vector x){
		return new Vector(Array1D.flip(x.x));
	}
	
	/**
	 * 1d linear interpolation to find yi, the approximate value of y(x) at xi.
	 * x must already be sorted in ascending order.
	 * If xi is not in bounds of interpolation it returns the closest value in y
	 * TODO: maybe it would be better to linearly extrapolate?
	 * Assumes x and y are the same size.
	 * @param x
	 * @param y
	 * @param xi
	 * @return an interpolated approximation for y(xi)
	 */
	public static double interp1(Vector x, Vector y, double xi){
		return Array1D.interp1(x.x, y.x, xi);
	}
	
	/**
	 * 1d linear interpolation to approximate y(x) at all of the entries in xi.
	 * x must already be sorted in ascending order.
	 * If xi is not in the bounds of interpolation it returns value at the closest bound 
	 * @param x
	 * @param y
	 * @param xi
	 * @return an interpolated approximation for y(x) at every value in xi
	 */
	public static Vector interp1(Vector x, Vector y, Vector xi){
		return new Vector(Array1D.interp1(x.x, y.x, xi.x));
	}
	
	
	
	/**
	 * Checks to see if element xi is in the vector x.  Array must be sorted first.
	 * @param x
	 * @param xi
	 * @return the index of element xi if it is contained in x.  -1 otherwise.
	 */
	public int find (double xi) {
		return Array1D.find(x, xi);
	}
	
	
	/**
	 * Checks to see if the elements of xi are in vector x.  Array must be sorted first.
	 * @param x
	 * @param xi
	 * @return for each entry in xi returns the index it is found in x, or -1 otherwise
	 */
	public int[] find (Vector xi) {
		return Array1D.find(x, xi.x);
	}
		
	
	/**
	 * projection of x onto y.  Assumes y is already a unit vector.
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector project(Vector y){
		return new Vector(Array1D.project(x, y.x));
	}
	
	/**
	 * returns a unit vector from x
	 * @param x
	 * @return
	 */
	public static Vector unit(Vector x){
		return new Vector(Array1D.unit(x.x));
	}
	
		
	/**
	 * Compute include angle between two vectors in radians
	 * @param a
	 * @param b
	 * @return
	 */
	public static double includedAngle (Vector x, Vector y){
		return Array1D.includedAngle(x.x, y.x);
	}
	
	/**
	 * removes duplicate elements from x, preserves order
	 * @param x
	 * @return
	 */
	public static Vector unique(Vector x){
		return new Vector(Array1D.unique(x.x));
	}
	
	/**
	 * computes mean of the vector
	 * @param x
	 * @return
	 */
	public static double mean(Vector x){
		return sum(x)/x.n;
	}
	
	/**
	 * Computes the elementwise average of a set of vectors
	 * @param vectors
	 * @return
	 */
	public static Vector average(Vector ... vectors){
		int N = vectors.length;
		
		return add(vectors).divide(N);
	}
	
	/**
	 * computes whether element x[i] == alpha
	 * @param alpha
	 * @return
	 */
	public boolean[] isEqualTo(double alpha){
		return Array1D.isEqualTo(x,alpha);
	}
	
	/**
	 * @param x
	 * @return sin(x)
	 */
	public static Vector sin(Vector x){
		return new Vector(Array1D.sin(x.x));
	}
	
	/**
	 * @param x
	 * @return cos(x)
	 */
	public static Vector cos(Vector x){
		return new Vector(Array1D.cos(x.x));
	}
	
	/**
	 * @param x
	 * @return tan(x)
	 */
	public static Vector tan(Vector x){
		return new Vector(Array1D.tan(x.x));
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vector [n=" + n + ", x=" + Arrays.toString(x) + "]";
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + n;
		result = prime * result + Arrays.hashCode(x);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (n != other.n)
			return false;
		if (!Arrays.equals(x, other.x))
			return false;
		return true;
	}
}
