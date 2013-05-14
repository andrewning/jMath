package jMath.linalg;

import java.util.*;
import jMath.func.Function1Var;


/**
 * A class of static methods implementing operations on 1-dimensional arrays
 * Intentionally mimics many of the naming conventions of MATLAB.
 * @author S. Andrew Ning
 * @version April 1, 2010
 *
 */
public class Array1D {

/* ------------------------
   Public Methods
 * ------------------------ */
	
	/**
	 * Creates a constant vector with value alpha of length n 
	 * @param alpha
	 * @param n
	 * @return
	 */
	public static double[] constant(double alpha, int n){
		double[] x = new double[n];
		
		for(int i = 0; i < n; i++){
			x[i] = alpha;
		}
		return x;
	}
	
	/**
	 * Creates a linearly spaced vector
	 * @param x1  starting value
	 * @param x2  ending value
	 * @param n  number of points
	 * @return vector from x1 to x2 with length n
	 */
	public static double[] linspace(double x1, double x2, int n){
		double[] x = new double[n];
		
		for (int i = 0; i < n; i++){ 
			x[i] = x1 + (double)i/(n-1)*(x2-x1);
		}		
		return x;
	}
	
	/**
	 * a logarithmically spaced vector
	 * @param x1  starting value
	 * @param x2  ending value
	 * @param n  number of points
	 * @return vector from 10^x1 to 10^x2 with length n
	 */
	public static double[] logspace(double x1, double x2, int n){
		double[] x = new double[n];
		
		for (int i = 0; i < n; i++){ 
			x[i] = Math.pow(10.0, x1 + (double)i/(n-1)*(x2-x1));
		}		
		return x;
	}
	
	/**
	 * Creates an vector mimicking Matlab's colon operation a:ds:b
	 * (does not necessarily end on b.  see {@link #linspace linspace} if you want it to end exactly at b)
	 * @param a
	 * @param ds
	 * @param b
	 */
	public static double[] colon(double a, double ds, double b){
		int n = (int)((b-a)/ds) + 1;
		double[] x = new double[n];
		
		for (int i = 0; i < n; i++){
			x[i] = a + i*ds;
		}
		return x;
	}
	
	/**
	 * Creates a vector mimicking Matlab's colon operation a:b
	 * @param a
	 * @param b
	 */
	public static double[] colon(double a, double b){
		return colon(a,1.0,b);
	}
	
	/**
	 * Generates a random vector of size n
	 * @param n  length of vector
	 * @return vector with random numbers
	 */
	public static double[] rand(int n){
		double[] x = new double[n];
		
		for (int i = 0; i < n; i++){
			x[i] = Math.random();
		}
		return x;
	}
	
	/**
	 * Create a copy of the vector
	 * @param x
	 * @return
	 */
	public static double[] copy(double[] x){
		int n = x.length;
		double[] y = new double[n];
		
		System.arraycopy(x, 0, y, 0, n);
		return y;
	}
	
	/** 
	 * Get a sub-vector
	 * @param x original vector
	 * @param iStart  Starting index
	 * @param iEnd  Ending index
	 * @return  x(iStart:iEnd)
	 * @exception  ArrayIndexOutOfBoundsException
	 */
	public static double[] sub(double[] x, int iStart, int iEnd){
		int length = iEnd - iStart + 1;
		double[] y = new double[length];
		
		System.arraycopy(x, iStart, y, 0, length);         
		return y;
	}
	
	/** 
	 * Get a sub-vector
	 * @param e  array of elements of vector to extract
	 * @return x(e(:))
	 * @exception  ArrayIndexOutOfBoundsException
	 */
	public static double[] sub(double[] x, int[] e){
		int n = e.length;
		double[] y = new double[n];
		
		for(int i = 0; i < n; i++){
			y[i] = x[e[i]];
		}
		return y;
	}
	
	/** 
	 * Set a part of the vector y starting at iStart to x
	 * @param y 
	 * @param iStart  starting index
	 * @param x
	 */
	public static void setSub(double[] y, double[] x, int iStart){
		System.arraycopy(x, 0, y, iStart, x.length);
	}
	
	/** 
	 * Set a part of the vector
	 * y(e(:)) = x
	 * @param y  
	 * @param e  indices of y to set subVector x into
	 * @param x  
	 * 
	 */
	public static void setSub(double[] y, double[] x, int[] e){
		for(int i = 0; i < e.length; i++){
			y[e[i]] = x[i];
		}
	}
	
	/**
	 * concatenate vectors into one larger one
	 * [v1 v2 .... vn]
	 * @param vlist
	 * @return
	 */
	public static double[] concat(double[]...vlist){
		int size = 0;
		for (double[] v: vlist){
			size += v.length;
		}
		double[] vNew = new double[size];
		
		int pos = 0;
		for (double[] v: vlist){
			System.arraycopy(v, 0, vNew, pos, v.length);
			pos += v.length;
		}
		return vNew;
	}
	
	/**
	 * Vector norm
	 * @return norm(V,P) = sum(abs(x).^P)^(1/P).
	 */
	public static double norm(double[] x, double p){
		return Math.pow(sum(pow(abs(x),p)),1.0/p);
	}
	
	/**
	 * sort vector in ascending order
	 * @param x
	 * @return the new sorted vector
	 */
	public static double[] sort(double[] x){
		double[] y = copy(x);
		Arrays.sort(y);
		return y;
	}
	
	
	/**
	 * get maximum value in vector
	 * @param x
	 * @return
	 */
	public static double max(double... x){
		double[] y = sort(x);
		return y[y.length-1];
	}
	
	
	/**
	 * find minimum value in vector
	 * accepts comma separated doubles, or an array
	 * @param x
	 * @return
	 */
	public static double min(double... x){
		double[] y = sort(x);
		return y[0];
	}

	
	/**
	 * Change sign of vector
	 * @param x
	 * @return -x
	 */
	public static double[] negative(double[] x){
		return multiply(-1.0,x);
	}
	
	/**
	 * add a constant to every entry in the vector
	 * @param x
	 * @param alpha  constant
	 * @return x + alpha{<b>1</b>}
	 */
	public static double[] add(double[] x, double alpha){
		int n = x.length;
		double[] y = new double[n];
		
		for (int i = 0; i < n; i++){
			y[i] = x[i] + alpha;
		}
		return y;
	}
	
	/**
	 * add a constant to every entry in the vector
	 * @param alpha  constant
	 * @param x
	 * @return x + alpha{<b>1</b>}
	 */
	public static double[] add(double alpha, double[] x){
		return add(x,alpha);
	}
	
	/**
	 * add two vectors
	 * @param x
	 * @param y 
	 * @return x + y
	 * @throws IllegalArgumentException
	 */
	public static double[] add(double[] x, double[] y) throws IllegalArgumentException{
		checkDimension(x, y);
		
		int n = x.length;
		double[] z = new double[n];
		
		for(int i = 0; i < n; i++){
			z[i] = x[i] + y[i];
		}
		return z;
	}
	
	/**
	 * adds an arbitrary number of vectors together
	 * @param v
	 * @return x1 + x2 + ...
	 */
	public static double[] add(double[] ... vectors){
		double[] x = vectors[0];
		for(int i = 1; i < vectors.length; i++){
			x = add(x,vectors[i]);
		}
		return x;
	}
	
	/**
	 * subtract a constant from every entry in the vector
	 * @param x
	 * @param alpha  constant
	 * @return x - alpha{<b>1</b>}
	 */
	public static double[] subtract(double[] x, double alpha){
		return add(x,-alpha);
	}
	
	/**
	 * subtract a vector from a constant
	 * @param alpha  constant
	 * @param x
	 * @return alpha{<b>1</b>} - x
	 */
	public static double[] subtract(double alpha, double[] x){
		return add(alpha,negative(x));
	}
	
	/**
	 * subtract two vectors
	 * @param x
	 * @param y 
	 * @return x - y
	 * @throws IllegalArgumentException
	 */
	public static double[] subtract(double[] x, double[] y) throws IllegalArgumentException{
		checkDimension(x, y);
		int n = x.length;
		double[] z = new double[n];
		
		for(int i = 0; i < n; i++){
			z[i] = x[i] - y[i];
		}
		return z;
	}
	
	/**
	 * multiply a vector by a constant
	 * @param alpha  constant
	 * @param x 
	 * @return alpha*x
	 */
	public static double[] multiply(double alpha, double[] x){
		int n = x.length;
		double[] y = new double[n];
		
		for(int i = 0; i < n; i++){
			y[i] = alpha*x[i];
		}
		return y;
	}
	
	/**
	 * multiply a vector by a constant
	 * @param x
	 * @param alpha  constant
	 * @return alpha*x
	 */
	public static double[] multiply(double[] x, double alpha){
		return multiply(alpha,x);
	}
	
	/**
	 * element by element multiplication of two vectors
	 * @param x 
	 * @param y 
	 * @return a vector z where z_i = x_i*y_i
	 * @throws IllegalArgumentException
	 */
	public static double[] dotMultiply(double[] x, double[] y) throws IllegalArgumentException{
		checkDimension(x, y);
		int n = x.length;
		double[] z = new double[n];
		
		for (int i = 0; i < n; i++){
			z[i] = x[i]*y[i];
		}
		return z;
	}
	
	/**
	 * divide a vector by a constant
	 * @param alpha  constant
	 * @param x 
	 * @return x/alpha
	 */
	public static double[] divide(double[] x, double alpha){
		return multiply(x,1.0/alpha);
	}
	
	/**
	 * divide a constant by a vector
	 * @param alpha  constant
	 * @param x 
	 * @return alpha/x
	 */
	public static double[] divide(double alpha, double[] x){
		int n = x.length;
		double[] y = new double[n];
		
		for(int i = 0; i < n; i++){
			y[i] = alpha/x[i];
		}
		return y;
	}
	
	/**
	 * element by element division of two vectors
	 * @param x 
	 * @param y 
	 * @return a vector z where z_i = x_i/y_i
	 * @throws IllegalArgumentException
	 */
	public static double[] dotDivide(double[] x, double[] y) throws IllegalArgumentException{
		checkDimension(x, y);
		
		int n = x.length;
		double[] z = new double[n];
		
		for (int i = 0; i < n; i++){
			z[i] = x[i]/y[i];
		}
		return z;
	}
	
	/**
	 * computes powers of vectors elementwise
	 * @param x
	 * @param exp
	 * @return x_i^exp
	 */
	public static double[] pow(double[] x, double exp){
		int n = x.length;
		double[] y = new double[n];
		
		for (int i = 0; i < n; i++){
			y[i] = Math.pow(x[i], exp);
		}
		return y;
	}
	
	/**
	 * elementwise square root of a vector
	 * @param x
	 * @return x.^(1/2)
	 */
	public static double[] sqrt(double[] x){
		return pow(x,0.5);
	}
	
	/**
	 * Elementwise application of an arbitrary 1D function
	 * @param func
	 * @param x
	 * @return
	 */
	public static double[] eval(Function1Var func, double[] x){
		int n = x.length;
		double[] y = new double[n];
		
		for (int i = 0; i < n; i++){
			y[i] = func.f(x[i]);
		}
		return y;
	}
	
	/**
	 * returns dot product of two vectors
	 * @param x
	 * @param y 
	 * @return the dot product of x and y
	 */
	public static double dot(double[] x, double[] y){
		return sum(dotMultiply(x,y));
	}
	
	/**
	 * computes cross product of two 3 dimensional vectors
	 * @param x 1st vector
	 * @param y 2nd vector
	 * @return the cross product x X y
	 * @throws IllegalArgumentException
	 */
	public static double[] cross (double[] x, double[] y) throws IllegalArgumentException{
		if (x.length != 3 || y.length != 3){
			throw new IllegalArgumentException("Vectors must both be of dimension 3");
		}
		
		return new double[] {x[1]*y[2] - x[2]*y[1],
							 x[2]*y[0] - x[0]*y[2],
							 x[0]*y[1] - x[1]*y[0]};
	}
	
	/**
	 * vector sum
	 * @param x
	 * @return the sum of the entries in the vector
	 */
	public static double sum(double[] x){
		double sum = 0;
		
		for(int i = 0; i < x.length; i++){
			sum += x[i];
		}
		return sum;
	}
	
	/**
	 * cumulative sum of elements in x
	 * @param x
	 * @return the cumulative sum of element in x
	 */
	public static double[] cumsum(double[] x){
		int n = x.length;
		double[] y = new double[n];
		
		y[0] = x[0];
		for(int i = 1; i < n; i++){
			y[i] = y[i-1] + x[i];
		}
		return y;
	}
	
	/**
	 * takes absolute value of a vector
	 * @param x
	 * @return |x|
	 */
	public static double[] abs(double[] x){
		int n = x.length;
		double[] y = new double[n];
		
		for (int i = 0; i < n; i++){
			y[i] = Math.abs(x[i]);
		}
		return y;
	}
	
	/**
	 * numerical integration of y w.r.t x using trapezoidal method.
	 * @param x
	 * @param y
	 * @return value of the integral
	 * @throws IllegalArgumentException
	 */
	public static double trapz(double[] x, double[] y) throws IllegalArgumentException{
		checkDimension(x, y);
		
		double sum = 0;
		
		for(int i = 0; i < x.length-1; i++){
			sum += (x[i+1]-x[i])*(y[i]+y[i+1])/2.0;
		}
		return sum;
	}
	
	/**
	 * numerical derivative of y w.r.t x using central difference
	 * except at end points.
	 * @param x
	 * @param y
	 * @return dy/dx
	 * @throws IllegalArgumentException
	 */
	public static double[] deriv(double[] x, double[] y) throws IllegalArgumentException{
		checkDimension(x, y);
		
		int n = x.length;
		double[] dydx = new double[n];
		
		dydx[0] = (y[1]-y[0])/(x[1]-x[0]);
		for (int i = 1; i < n-1; i++){
			dydx[i] = (y[i+1]-y[i-1])/(x[i+1]-x[i-1]);
		}
		dydx[n-1] = (y[n-1]-y[n-2])/(x[n-1]-x[n-2]);
		
		return dydx;
	}
	
	/**
	 * flip a vector around
	 * @param x
	 * @return flipped vector
	 */
	public static double[] flip(double[] x){
		int n = x.length;
		double[] xFlip = new double[n];
		
		for(int i = 0; i < n; i++){
			xFlip[i] = x[n-1-i];
		}
		return xFlip;
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
	public static double interp1(double[] x, double[] y, double xi){
		int n = x.length;
		
		int idx = Arrays.binarySearch(x, xi);
		int ip;
		if (idx < 0) ip = -idx - 1; // artifact of binary search
		else ip = idx;
		
		if (ip == 0) return y[0];
		if (ip == n) return y[n-1];
		
		int im = ip - 1;
		return y[im] + (xi - x[im])/(x[ip] - x[im])*(y[ip] - y[im]);
	}
	
	/**
	 * 1d linear interpolation to approximate y(x) at all of the entries in xi.
	 * x must already be sorted in ascending order.
	 * If xi is not in the bounds of interpolation it returns value at the closest bound 
	 * @param x
	 * @param y
	 * @param xi
	 * @return an interpolated approximation for y(x) at every value in xi
	 * @throws IllegalArgumentException
	 */
	public static double[] interp1(double[] x, double[] y, double[] xi) throws IllegalArgumentException{
		checkDimension(x,y);
		
		int n = xi.length;
		double[] yi = new double[n];
		
		for(int i = 0; i < n; i++){
			yi[i] = interp1(x,y,xi[i]);
		}
		return yi;
	}
	
	
	/**
	 * Checks to see if element xi is in the vector x. Array must be sorted first.
	 * @param x
	 * @param xi
	 * @return the index of element xi if it is contained in x.  -1 otherwise.
	 */
	public static int find (double [] x, double xi) {
		//TODO: list must be sorted first for a binary search
		int idx = Arrays.binarySearch(x, xi);
		if (idx >= 0) return idx;
		else return -1;
		
	}
	
	
	/**
	 * Checks to see if the elements of xi are in vector x.  Array must be sorted first.
	 * @param x
	 * @param xi
	 * @return for each entry in xi returns the index it is found in x, or -1 otherwise
	 */
	public static int[] find (double [] x, double[] xi) {
		int n = xi.length;
		int[] idx = new int[n];
		
		for (int i = 0; i < n; i++) {
			idx[i] = find(x,xi[i]);
		}
		return idx;
	}
		
	
	/**
	 * projection of x onto y.  Assumes y is already a unit vector.
	 * @param x
	 * @param y
	 * @return
	 */
	public static double[] project(double[] x, double [] y){
		return multiply(dot(x, y),y);
	}
	
	/**
	 * returns a unit vector from x
	 * @param x
	 * @return
	 */
	public static double[] unit(double[] x){
		return divide(x,norm(x,2.0));
	}
		
	/**
	 * Compute include angle between two vectors in radians
	 * @param a
	 * @param b
	 * @return
	 */
	public static double includedAngle (double[] x, double[] y){
		return Math.acos(dot(x, y)/(norm(x,2)*norm(y,2)));
	}
	
	/**
	 * removes duplicate elements from x, preserves order
	 * @param x
	 * @return
	 */
	public static double[] unique(double[] x){
		
		Set<Double> set = new LinkedHashSet<Double>();
		
		for (int i = 0; i < x.length; i++){
			set.add(x[i]);
		}
		
		double[] y = new double[set.size()];
		
		int i = 0;
		for (double d: set){
			y[i++] = d;
		}
		return y;
	}
	
	/**
	 * computes mean of the vector
	 * @param x
	 * @return
	 */
	public static double mean(double[] x){
		return sum(x)/x.length;
	}
	
	/**
	 * Computes the elementwise average of a set of vectors
	 * @param vectors
	 * @return
	 */
	public static double[] average(double[] ... vectors){
		int N = vectors.length;
		
		return divide(add(vectors),N);
	}

	/**
	 * computes whether element x[i] == alpha
	 * @param alpha
	 * @return
	 */
	public static boolean[] isEqualTo(double[] x, double alpha){
		boolean[] y = new boolean[x.length];
		
		for(int i = 0; i < x.length; i++){
			if (x[i] == alpha) y[i] = true;
			else y[i] = false;
		}
		return y;
	}
	
	/**
	 * are any of the elements of x true?
	 * @param x
	 * @return
	 */
	public static boolean any(boolean[] x){
		for(boolean element: x){
			if (element) return true;
		}
		return false;
	}
	
	/**
	 * sin(x)
	 * @param x
	 * @return
	 */
	public static double[] sin(double[] x){
		return eval(new Function1Var() {

			@Override
			public double f(double y) {
				return Math.sin(y);
			}
		}, x);
	}
	
	/**
	 * cos(x)
	 * @param x
	 * @return
	 */
	public static double[] cos(double[] x){
		return eval(new Function1Var() {

			@Override
			public double f(double y) {
				return Math.cos(y);
			}
		}, x);
	}
	
	/**
	 * tan(x)
	 * @param x
	 * @return
	 */
	public static double[] tan(double[] x){
		return eval(new Function1Var() {

			@Override
			public double f(double y) {
				return Math.tan(y);
			}
		}, x);
	}
	
	
/* ------------------------
   Private Methods
* ------------------------ */
	private static void checkDimension (double[] x, double[] y) {
		if (x.length != y.length) {
			throw new IllegalArgumentException("Vectors must be the same length");
		}
	}
}

