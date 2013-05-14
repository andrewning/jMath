package jMath.linalg;
import static jMath.linalg.Array1D.*;
import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.Test;

public class Array1DTest {
	
	public static final double tol = 1e-6;

	@Test
	public void testConstant() {
		double d = 5.4;
		int n = 10;
		double[] x = constant(d, n);
		for (int i = 0; i < n; i++){
			assertEquals(d, x[i],tol);
		}
	}
	
	@Test
	public void testLinspace(){
		double[] x = linspace(4.3, 6.8, 5);
		assertEquals(4.3, x[0], tol);
		assertEquals(4.925, x[1], tol);
		assertEquals(5.55, x[2], tol);
		assertEquals(6.175, x[3], tol);
		assertEquals(6.8, x[4], tol);
	}
	
	@Test
	public void testLogspace(){
		double[] x = logspace(0.5, 1.6, 5);
		assertEquals(3.16227766, x[0], tol);
		assertEquals(5.9566214, x[1], tol);
		assertEquals(11.2201845, x[2], tol);
		assertEquals(21.134890, x[3], tol);
		assertEquals(39.810717, x[4], tol);
	}
	
	@Test
	public void testColon(){
		double[] x = colon(2.4, 1.75, 7.9);
		assertEquals(2.4, x[0], tol);
		assertEquals(4.15, x[1], tol);
		assertEquals(5.9, x[2], tol);
		assertEquals(7.65, x[3], tol);
	}
	
	@Test
	public void testColon2(){
		double[] x = colon(2.4, 6.3);
		assertEquals(2.4, x[0], tol);
		assertEquals(3.4, x[1], tol);
		assertEquals(4.4, x[2], tol);
		assertEquals(5.4, x[3], tol);
	}
	
	@Test
	public void testCopy(){
		int n = 4;
		double[] x = rand(n);
		double[] y = copy(x);
		for(int i = 0; i < n; i++){
			assertEquals(x[i],y[i],tol);
			assertNotSame(x[i], y[i]);
		}
	}
	
	@Test
	public void testSub(){
		double[] x = rand(6);
		double[] y = sub(x, 2, 4);
		assertEquals(y.length, 3);
		assertEquals(y[0], x[2], tol);
		assertEquals(y[1], x[3], tol);
		assertEquals(y[2], x[4], tol);
	}
	
	@Test
	public void testSub2(){
		double[] x = rand(6);
		double[] y = sub(x, new int[] {1,2,5});
		assertEquals(y.length, 3);
		assertEquals(y[0], x[1], tol);
		assertEquals(y[1], x[2], tol);
		assertEquals(y[2], x[5], tol);
	}
	
	@Test
	public void testSetSub(){
		double[] x = rand(6);
		double[] y = rand(3);
		setSub(x,y,3);
		assertEquals(x[3], y[0], tol);
		assertEquals(x[4], y[1], tol);
		assertEquals(x[5], y[2], tol);
	}
	
	@Test
	public void testSetSub2(){
		double[] x = rand(6);
		double[] y = rand(3);
		setSub(x, y, new int[] {0,4,5});
		assertEquals(x[0], y[0], tol);
		assertEquals(x[4], y[1], tol);
		assertEquals(x[5], y[2], tol);
	}
	
	@Test
	public void testConcat(){
		double[] x = rand(3);
		double[] y = rand(4);
		double[] z = rand(5);
		double[] w = concat(x,y,z);
		assertEquals(w.length, 12);
		assertEquals(w[0],x[0],tol);
		assertEquals(w[3],y[0],tol);
		assertEquals(w[7],z[0],tol);
	}
	
	@Test
	public void testNorm(){
		double[] x = {4.8, 1.6, 12.1};
		assertEquals(12.325090, norm(x,3.1), tol);
	}
	
	@Test
	public void testSort(){
		double[] x = {4.8, 1.6, 12.1, 3.5};
		double[] y = sort(x);
		assertEquals(y[0],1.6,tol);
		assertEquals(y[1],3.5,tol);
		assertEquals(y[2],4.8,tol);
		assertEquals(y[3],12.1,tol);
	}
	
	@Test
	public void testMax(){
		double[] x = {4.8, 1.6, 12.1, 3.5};
		assertEquals(12.1,max(x),tol);
	}
	
	@Test
	public void testMin(){
		double[] x = {4.8, 1.6, 12.1, 3.5};
		assertEquals(1.6,min(x),tol);
	}
	
	@Test
	public void testNegative(){
		double[] x = {4.8, 1.6, 12.1, 3.5};
		double[] y = negative(x);
		assertEquals(-4.8,y[0],tol);
		assertEquals(-1.6,y[1],tol);
		assertEquals(-12.1,y[2],tol);
		assertEquals(-3.5,y[3],tol);
	}
	
	@Test
	public void testAdd(){
		double[] x = {4.8, 1.6, 12.1, 3.5};
		double[] y = add(x,1.2);
		double[] z = add(1.2,x);
		assertEquals(6.0,y[0],tol);
		assertEquals(2.8,y[1],tol);
		assertEquals(13.3,y[2],tol);
		assertEquals(4.7,y[3],tol);
		assertTrue(Arrays.equals(y, z));
	}

}
