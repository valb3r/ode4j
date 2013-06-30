package org.ode4j.math;

import org.ode4j.math.DMatrix3.DVector3ColView;

/**
 * This class provides functionality for dVector3 math.
 * 
 * Performance considerations:
 * - Each class implements its own strictly typed methods. Using
 *   generic methods in super-classes showed to slow down operations
 *   like add() or set() by a factor of ~10 (SUN JDK 6 on SuSE 11.1 64bit).
 * - Using complex methods (e.g. v0.sum(v1, v2, s2)) showed to be about
 *   2 times faster than concatenating methods, e.g. v0.set(v2).scale(s2).add(v1). 
 *
 * @author Tilmann Zaeschke
 *
 */
public class DVector3 implements DVector3I, DVector3C {
//public class DVector3 implements DVector3I, DVector3C {
	private final double[] v;
	private static final int LEN = 4;  //TODO 3 ?
	public static final DVector3 ZERO = new DVector3(0, 0, 0);
	public static final int CURRENT_LENGTH = 4;

	//private static int COUNT = 0;
	
	public DVector3() { 
		v = new double[LEN];
		//if (COUNT++%1000000==0) System.out.println("V3="+COUNT);
	}
	
	public DVector3(DVector3C v2) {
		this();
		set(v2);
	}
	
	public DVector3(double[] v2) {
		this();
		set(v2);
	}
	
	public DVector3(double i, double j, double k) {
		this();
		set(i, j, k);
	}
	
	public final DVector3 set(double[] v2) {
		set(v2[0], v2[1], v2[2]);
		return this;
	}
	
	public final DVector3 set(double x, double y, double z) {
		//set0( x ); set1( y ); set2( z );
		v[0] = x; v[1] = y; v[2] = z;
		return this;
	}
	
	public final DVector3 set(DVector3C v2) {
		set0( v2.get0() ); set1( v2.get1() ); set2( v2.get2() );
		return this;
	}
	
	public final DVector3 set(DVector3ColView v2) {
		set0( v2.get0() ); set1( v2.get1() ); set2( v2.get2() );
		return this;
	}
	
	@Override
	public DVector3 clone() {
		return new DVector3(this);
	}
	
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("DVector3[ ");
		b.append(get0()).append(", ");
		b.append(get1()).append(", ");
		b.append(get2()).append(" ]");
//		for (int i = 0; i < v.length-1; i++) {
//			b.append(v[i]).append(", ");
//		}
//		b.append(v[v.length-1]).append("]");
		return b.toString();
	}

//	@Override
//	public void assertLen(int n) {
//		if (n!=LEN) {
//			throw new IllegalStateException("LEN is " + LEN + ", not " + n);
//		}		
//	}
	
	public final void set0(double d) {
		v[0] = d;
	}
	
	public final void set1(double d) {
		v[1] = d;
	}
	
	public final void set2(double d) {
		v[2] = d;
	}
	
	public final double get0() {
		return v[0];
	}
	
	public final double get1() {
		return v[1];
	}
	
	public final double get2() {
		return v[2];
	}
	
	public final DVector3 add(double a, double b, double c) {
		v[0] += a; v[1] += b; v[2] += c;
		return this;
	}
	
	public final DVector3 add(DVector3C v2) {
		v[0] += v2.get0(); v[1] += v2.get1(); v[2] += v2.get2();
		return this;
	}
	
	public final DVector3 eqSum(DVector3C v2, DVector3C v3) {
		set0( v2.get0() + v3.get0() ); 
		set1( v2.get1() + v3.get1() ); 
		set2( v2.get2() + v3.get2() );
		return this;
	}
	
	public final DVector3 eqSum(DVector3 v2, double s1, DVector3 v3, double s2) {
		set0( v2.get0()*s1 + v3.get0()*s2 ); 
		set1( v2.get1()*s1 + v3.get1()*s2 ); 
		set2( v2.get2()*s1 + v3.get2()*s2 );
		return this;
	}
	
	public final DVector3 eqSum(DVector3ColView v2, double s1, DVector3 v3, double s2) {
		set0( v2.get0()*s1 + v3.get0()*s2 ); 
		set1( v2.get1()*s1 + v3.get1()*s2 ); 
		set2( v2.get2()*s1 + v3.get2()*s2 );
		return this;
	}
	
	public final DVector3 eqSum(DVector3ColView v2, double s1, DVector3ColView v3, double s2) {
		set0( v2.get0()*s1 + v3.get0()*s2 ); 
		set1( v2.get1()*s1 + v3.get1()*s2 ); 
		set2( v2.get2()*s1 + v3.get2()*s2 );
		return this;
	}
	
	public final DVector3 eqSum(DVector3C v2, DVector3C v3, double s2) {
		set0( v2.get0() + v3.get0()*s2 ); 
		set1( v2.get1() + v3.get1()*s2 ); 
		set2( v2.get2() + v3.get2()*s2 );
		return this;
	}

	public final DVector3 eqSum(DVector3 v2, DVector3ColView v3, double s2) {
		set0( v2.get0() + v3.get0()*s2 ); 
		set1( v2.get1() + v3.get1()*s2 ); 
		set2( v2.get2() + v3.get2()*s2 );
		return this;
	}
	
	public final DVector3 sub(double a, double b, double c) {
		v[0] -= a; v[1] -= b; v[2] -= c;
		return this;
	}

	public final DVector3 sub(DVector3C v2) {
		v[0] -= v2.get0(); v[1] -= v2.get1(); v[2] -= v2.get2();
		return this;
	}

	public final DVector3 scale(double a, double b, double c) {
		v[0] *= a; v[1] *= b; v[2] *= c;
		return this;
	}

	public final DVector3 scale(double s) {
		v[0] *= s; v[1] *= s; v[2] *= s;
		return this;
	}

	public final DVector3 scale(DVector3C v2) {
		v[0] *= v2.get0(); v[1] *= v2.get1(); v[2] *= v2.get2();
		return this;
	}

	/**
	 * Return the 'dot' product of two vectors.
	 * r = a0*b0 + a1*b1 + a2*b2;
	 * @param b 
	 * @return (this) * b
	 */
	public final double dot(DVector3C b) {
		return get0()*b.get0() + get1()*b.get1() + get2()*b.get2();
	}
	
	/**
	 * Return the 'dot' product of two vectors.
	 * r = a0*b0 + a1*b1 + a2*b2;
	 * @param b 
	 * @return (this) * b
	 */
	public final double dot(DVector3View b) {
		return get0()*b.get0() + get1()*b.get1() + get2()*b.get2();
	}
	
//	/**
//	 * Calculate the 'dot' or 'inner' product. <br>
//	 * x = b_t * c  <br>
//	 * x = b1*c1 + b2*c2 + b3*c3.
//	 * @param b
//	 * @param c
//	 * @return The dot product.
//	 */
//	public double eqDot(final dVector3C b, final dVector3C c) {
//		return b.get0()*c.get0() + b.get1()*c.get1() + b.get2()*c.get2();
//		
//	}
	
	/**
	 * Sets the current vector v0 = v2 - v3.
	 * @param v2
	 * @param v3
	 */
	public final DVector3 eqDiff(DVector3C v2, DVector3C v3) {
		v[0] = v2.get0() - v3.get0(); 
		v[1] = v2.get1() - v3.get1(); 
		v[2] = v2.get2() - v3.get2();
		return this;
	}
	
	/**
	 * Return a new vector v0 = v(this) - v3.
	 * @param v2
	 */
	public final DVector3 reSub(DVector3C v2) {
		return new DVector3(
				get0() - v2.get0(),
				get1() - v2.get1(),
				get2() - v2.get2());
	}
	
	/**
	 * this may be called for vectors `a' with extremely small magnitude, for
	 * example the result of a cross product on two nearly perpendicular vectors.
	 * we must be robust to these small vectors. to prevent numerical error,
	 * first find the component a[i] with the largest magnitude and then scale
	 * all the components by 1/a[i]. then we can compute the length of `a' and
	 * scale the components by 1/l. this has been verified to work with vectors
	 * containing the smallest representable numbers.
	 * 
	 * This method returns (1,0,0) if no normal can be determined.
	 */
	public final boolean safeNormalize ()
	{
		double s;

		double aa0 = Math.abs(get0());
		double aa1 = Math.abs(get1());
		double aa2 = Math.abs(get2());
		if (aa1 > aa0) {
			if (aa2 > aa1) { // aa[2] is largest
				s = aa2;
			}
			else {              // aa[1] is largest
				s = aa1;
			}
		}
		else {
			if (aa2 > aa0) {// aa[2] is largest
				s = aa2;
			}
			else {              // aa[0] might be the largest
				if (aa0 <= 0) { // aa[0] might is largest
//					a.v[0] = 1;	// if all a's are zero, this is where we'll end up.
//					a.v[1] = 0;	// return a default unit length vector.
//					a.v[2] = 0;
					set(1, 0, 0);
					return false;
				}
				else {
					s = aa0;
				}
			}
		}

		scale(1./s);
		scale(1./length());
		return true;
	}
	/**
	 * this may be called for vectors `a' with extremely small magnitude, for
	 * example the result of a cross product on two nearly perpendicular vectors.
	 * we must be robust to these small vectors. to prevent numerical error,
	 * first find the component a[i] with the largest magnitude and then scale
	 * all the components by 1/a[i]. then we can compute the length of `a' and
	 * scale the components by 1/l. this has been verified to work with vectors
	 * containing the smallest representable numbers.
	 * 
	 * This method throws an IllegalArgumentEception if no normal can be determined.
	 */
	public final void normalize()
	{
		if (!safeNormalize()) throw new IllegalStateException(
				"Normalization failed: " + this);
	}

	public final boolean isEq(DVector3 a) {
		return get0()==a.get0() && get1()==a.get1() && get2()==a.get2();
	}

	public final void eqAbs() {
		set0( Math.abs(get0()));
		set1( Math.abs(get1()));
		set2( Math.abs(get2()));
	}
	
//	public void dMultiply0 (final dMatrix3C B, final dVector3C C)
//	{
//		eqMul(B, C);
//	}
//	
//	public void eqMul (final dMatrix3C B, final dVector3C c)
//	{
////		double[] B2 = ((dMatrix3) B).v;
////		double[] C2 = ((dVector3) C).v;
////		double sum;
////		int aPos = 0;
////		int bPos, bbPos =0, cPos;
////		for (int i=3; i > 0; i--) {
////			cPos = 0;
////			bPos = bbPos;
////			sum = 0;
////			for (int k=3; k > 0; k--) sum += B2[bPos++] * C2[cPos++];
////			v[aPos++] = sum;
////			bbPos += 4;
////		}
//		v[0] = B.get00()*c.get0() + B.get01()*c.get1() + B.get02()*c.get2();
//		v[1] = B.get10()*c.get0() + B.get11()*c.get1() + B.get12()*c.get2();
//		v[2] = B.get20()*c.get0() + B.get21()*c.get1() + B.get22()*c.get2();
//	}

	public final void add0(double d) {
		v[0] += d;
	}

	public final void add1(double d) {
		v[1] += d;
	}

	public final void add2(double d) {
		v[2] += d;
	}

	/**
	 * 
	 * @return '3'.
	 */
	public final int dim() {
		//return LEN;
		//TODO
		return 3;
	}

	public final DVector3C reAdd(DVector3C c) {
		return new DVector3(this).add(c);
	}
	
	
	/**
	 * Writes the content of this vector into 
	 * <tt>array</tt> at position <tt>pos</tt>.  
	 * @param array
	 * @param pos
	 */
	public final void wrapSet(double[] array, int pos) {
		array[pos] = get0();
		array[pos + 1] = get1();
		array[pos + 2] = get2();
	}
	
	/**
	 * Adds the content of this vector to the elements of 
	 * <tt>array</tt> at position <tt>pos</tt>.  
	 * @param array
	 * @param pos
	 */
	public final void wrapAdd(double[] array, int pos) {
		array[pos] += get0();
		array[pos + 1] += get1();
		array[pos + 2] += get2();
	}
	
	/**
	 * Subtracts the content of this vector from the elements of 
	 * <tt>array</tt> at position <tt>pos</tt>.  
	 * @param array
	 * @param pos
	 */
	public final void wrapSub(double[] array, int pos) {
		array[pos] -= get0();
		array[pos + 1] -= get1();
		array[pos + 2] -= get2();
	}

	public final DVector3 reScale(double d) {
		return new DVector3(this).scale(d);
	}

	public final void eqZero() {
		set(0, 0, 0);
	}

	public final void setZero() {
		eqZero();
	}
	
	public final void eqIdentity() {
		set(1, 0, 0);
	}

	public final void setIdentity() {
		eqIdentity();
	}

	@Override
	public final float[] toFloatArray4() {
		return new float[]{ (float) get0(), (float) get1(), (float) get2(), 0.0f };
	}
	
	/**
	 * @return The geometric length of this vector.
	 */
	public final double length() {
		return Math.sqrt( get0()*get0() + get1()*get1() + get2()*get2() ) ;
	}

	public final double lengthSquared() {
		return get0()*get0() + get1()*get1() + get2()*get2();
	}
	
	public final double get(int i) {
		return v[i];
	}

	@Override
	public final float[] toFloatArray() {
		return new float[]{(float) get0(), (float) get1(), (float) get2()};
	}
	
	public final void set(int i, double d) {
		v[i] = d;
	}

	public final void scale(int i, double d) {
		v[i] *= d;
	}

	public final void add(int i, double d) {
		v[i] += d;
	}
	
	/**
	 * Calculates the dot product of this vector with the specified column 
	 * of the given Matrix.
	 * @param m
	 * @param col
	 */
	public final double dotCol(DMatrix3C m, int col) {
		if (col == 0) {
			return get0()*m.get00() + get1()*m.get10() + get2()*m.get20();
		} else if (col == 1) {
			return get0()*m.get01() + get1()*m.get11() + get2()*m.get21();
		} else if (col == 2) {
			return get0()*m.get02() + get1()*m.get12() + get2()*m.get22();
		} else {
			throw new IllegalArgumentException("col="+col);
		}
	}
	
	
	/**
	 * Any implementation of DVector3I will return true if get0(), get1()
	 * and get2() return the same values.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof DVector3I)) return false;
		DVector3I v = (DVector3I) obj;
		return get0()==v.get0() && get1()==v.get1() && get2()==v.get2();
	}
	
	@Override
	public int hashCode() {
		return (int) (Double.doubleToRawLongBits(get0())  * 
		Double.doubleToRawLongBits(get1()) * 
		Double.doubleToRawLongBits(get2()));
	}
}

