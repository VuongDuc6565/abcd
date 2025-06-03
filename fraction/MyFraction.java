package hus.oop.fraction;

public class MyFraction implements MyFractionComparable {
    private int numerator;
    private int denominator;

    public MyFraction() {
        this(1, 1);
    }

    public MyFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public MyFraction(MyFraction copyMyFraction) {
        this(copyMyFraction.numerator, copyMyFraction.denominator);
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero");
        }
        this.denominator = denominator;
    }

    public byte byteValue() {
        return (byte) doubleValue();
    }

    public int intValue() {
        return (int) doubleValue();
    }

    public long longValue() {
        return (long) doubleValue();
    }

    public short shortValue() {
        return (short) doubleValue();
    }

    public double doubleValue() {
        return (double) numerator / denominator;
    }

    public float floatValue() {
        return (float) numerator / denominator;
    }

    private int gcd() {
        int a = Math.abs(numerator);
        int b = Math.abs(denominator);
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    public void simplify() {
        int g = gcd();
        numerator /= g;
        denominator /= g;
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    @Override
    public int compareTo(MyFraction another) {
        long left = (long) this.numerator * another.denominator;
        long right = (long) another.numerator * this.denominator;
        return Long.compare(left, right);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
