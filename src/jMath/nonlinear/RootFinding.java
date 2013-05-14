package jMath.nonlinear;

import jMath.func.Function1Var;

public class RootFinding {

	/** fzero seeks the root of a function F(X) in an interval [A,B] using Brent's Method
	*
	*  Discussion:
	*
	*    The interval [A,B] must be a change of sign interval for F.
	*    That is, F(A) and F(B) must be of opposite signs.  Then
	*    assuming that F is continuous implies the existence of at least
	*    one value C between A and B for which F(C) = 0.
	*
	*    The location of the zero is determined to within an accuracy
	*    of 6 * MACHEPS * abs ( C ) + 2 * T.
	*
	*
	*  Modified:
	*
	*    17 Feb. 2010
	*
	*  Author:
	*
	*    Original FORTRAN77 version by Richard Brent
	*    JAVA version by S. Andrew Ning
	*
	*  Reference:
	*
	*    Richard Brent,
	*    Algorithms for Minimization Without Derivatives,
	*    Dover, 2002,
	*    ISBN: 0-486-41998-3,
	*    LC: QA402.5.B74.
	*
	*  Parameters:
	*
	*    Input, real A, B, the endpoints of the change of sign interval.
	*
	*    Input, real T, a positive error tolerance.
	*
	*    Input, real value = F ( x ), the name of a user-supplied
	*    function which evaluates the function whose zero is being sought.
	*
	*    Output, real VALUE, the estimated value of a zero of
	*    the function F.
	*/
	
	public static double fzero(Function1Var f, double a, double b, double t){
		
		double machep = Double.MIN_VALUE;
	
		double sa = a;
		double sb = b;
		double fa = f.f( sa );
		double fb = f.f( sb );

		double c = sa;
		double fc = fa;
		double e = sb - sa;
		double d = e;
		
		double p,q,r;

		while (true){

			if ( Math.abs ( fc ) < Math.abs ( fb ) ){
				sa = sb;
				sb = c;
				c = sa;
				fa = fb;
				fb = fc;
				fc = fa;
			}

			double tol = 2.0 * machep * Math.abs ( sb ) + t;
		    double m = 0.5 * ( c - sb );

		    if ( Math.abs ( m ) <= tol || fb == 0.0 ){
		    	break;
		    }

		    if ( Math.abs ( e ) < tol || Math.abs ( fa ) <= Math.abs ( fb ) ){
		    	e = m;
		    	d = e;
		    } else{
		    	
		    	double s = fb / fa;

		    	if ( sa == c ){
		    		p = 2.0 * m * s;
			    	q = 1.0 - s;
		    	} else{
		    		q = fa / fc;
		    		r = fb / fc;
		    		p = s * ( 2.0 * m * a * ( q - r ) - ( sb - sa ) * ( r - 1.0 ) );
		    		q = ( q - 1.0 ) * ( r - 1.0 ) * ( s - 1.0 );
		    	}

		    	if ( 0.0 < p ){
		    		q = - q;
		    	} else{
		    		p = - p;
		    	}

		    	s = e;
		    	e = d;

		    	if ( 2.0 * p < 3.0 * m * q - Math.abs ( tol * q ) && p < Math.abs ( 0.5 * s * q ) ){
		    		d = p / q;
		    	} else{
		    		e = m;
		    		d = e;
		    	}
		    }

		    sa = sb;
		    fa = fb;

		    if ( tol < Math.abs ( d ) ){
		    	sb = sb + d;
		    } else if ( 0.0 < m ){
		    	sb = sb + tol;
		    } else{
		    	sb = sb - tol;
		    }

		    fb = f.f( sb );

		    if ( ( 0.0 < fb && 0.0 < fc ) || ( fb <= 0.0 && fc <= 0.0 ) ){
		    	c = sa;
		    	fc = fa;
		    	e = sb - sa;
		    	d = e;
		    }
		}
		
		return sb;
	}
	
	/**
	 * computes zero of funciton f in interval [a,b] where a sign change must exist
	 * somewhere between a and b
	 * @param f
	 * @param a
	 * @param b
	 * @return
	 */
	public static double fzero(Function1Var f, double a, double b){
		return fzero(f,a,b,1e-6);
	}
}
