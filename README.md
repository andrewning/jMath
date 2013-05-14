jMath
=====

A collection of math routines for use in Java (primarily vector/matrix/linear algebra).  Intentionally mimics some of Matlab's naming conventions.

I don't use Java nearly as much as I used to so I can't guarantee much active development, however I thought these might be of use to someone else.  I started this because I wasn't happy with the availability of math libraries in Java, and wanted Matlab-like capabilities for simple but common operations like creating linear spaced arrays (linspace), trapezoidal integration (trapz), and linear interpolation (interp1), etc.  Furthermore, in the areas where decent Java libraries do exist I wanted to create an abstracted interface so I could change the underlying library at will without having to modify my code.

Warning: The unit tests provided are not complete.  For any methods you use, it would be a good idea to write some corresponding unit tests first.  Having said that, I have used jMath successfully in some commercial applications.

Array1D and Vector
------------------

Array1D and Vector are the two primary classes of this package and provide functionality such as finding unique entries in a vector, performing elementary operations, and taking a numerical derivative using central differencing.  For some cases where limited math is being done it is more convenient to work directly with primitive arrays arrays (i.e., abs(double[] x)) rather than a Vector object (i.e., abs(Vector x)).  The two options provide different syntatic/stylistic advantages, but both contain the same functionality  and internally they re-use the same code.

Matrix
------

The Matrix class provides additional functionality for matrices such as setting submatrices, adding and subtracing matrices, and finding least squares solutions through either QR or LU decomposition.  There is no primitive analog to the Matrix class.  At this level the advantage of working with a dedicated object is much greater.

Currently the Vector and Matrix objects utilize the [Apache Commons Mathematics Library](http://commons.apache.org/proper/commons-math/) for some of its functions, so this will need to be downloaded separately.  Of course this internal implementation is abstracted away from the use of the functions, so if you have some other favorite QR decomposition routine you can replace it in jMath without having to modify the external interface.

Root Finding
------------

Finally, a root finding method (Brent's method [1]) is provided.

[1] Brent, R. P., “An Algorithm with Guaranteed Convergence for Finding a Zero of a Function,” The Computer Journal, Vol. 14, No. 4, 1971, pp. 422–425.