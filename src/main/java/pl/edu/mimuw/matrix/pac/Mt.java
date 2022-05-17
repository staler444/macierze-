package pl.edu.mimuw.matrix.pac;

import pl.edu.mimuw.matrix.*;

public abstract class Mt implements IDoubleMatrix {
    protected final int n, m;
    protected double myNormOne, myNormInfinity, myFrobeniusNorm;
    protected Shape myShape;

    public Mt(int n, int m){
        this.n = n;
        this.m = m;
        myNormOne = -1;
        myNormInfinity = -1;
        myFrobeniusNorm = -1;
        myShape = null;
    }

    // times 
    public Mt times(double scalar){
        if(scalar == 1)
            return this;
        if(scalar == 0)
            return new ZeroMt(n, m);

        return timesIfless(scalar);
    }
    public abstract Mt timesIfless(double scalar);

    public IDoubleMatrix times(IDoubleMatrix other){
        assert m == other.getN() : "times: myM != other.getN";
        if(other instanceof Mt)
            return this.times((Mt)other);
        return timesDefault(this, other);        
    }
    public abstract Mt times(Mt other);

    private static Mt timesDefault(IDoubleMatrix A, IDoubleMatrix B){
        int n = A.getN(), m = B.getM();
        double res[][] = new double[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                for(int k = 0; k < A.getM(); k++)
                    res[i][j] += A.get(i, k)*B.get(k,j);
        return new FullMt(res);
    }

    public Mt times(SparseMt other){
        return timesDefault(other, this);
    }
    public Mt times(FullMt other){
        return timesDefault(other, this);
    }
    public Mt times(IdentityMt other){
        return timesDefault(other, this);
    }
    public Mt times(DiagonalMt other){
        return timesDefault(other, this);
    }
    public Mt times(AntiDiagonalMt other){
        return timesDefault(other, this);
    }
    public Mt times(VectorMt other){
        return timesDefault(other, this);
    }
    public Mt times(ZeroMt other){
        return timesDefault(other, this);       
    }

    // plus 
    public Mt plus(double scalar){
        if(scalar == 0)
            return this;
        return plusIfless(scalar);
    }
    public abstract Mt plusIfless(double scalar);

    public IDoubleMatrix plus(IDoubleMatrix other){
        assert (n == other.getN() && m == other.getM()) : "plus: size error";
        if(other instanceof Mt)
            return this.plus((Mt)other);
        return plusDefault(this, other);        
    }
    public abstract Mt plus(Mt other);

    private static Mt plusDefault(IDoubleMatrix A, IDoubleMatrix B){
        int n = A.getN(), m = A.getM();
        double res[][] = new double[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                    res[i][j] = A.get(i, j)+B.get(i, j);
        return new FullMt(res);
    }

    public Mt plus(SparseMt other){
        return plusDefault(other, this);
    }
    public Mt plus(FullMt other){
        return plusDefault(other, this);
    }
    public Mt plus(IdentityMt other){
        return plusDefault(other, this);
    }
    public Mt plus(DiagonalMt other){
        return plusDefault(other, this);
    }
    public Mt plus(AntiDiagonalMt other){
        return plusDefault(other, this);
    }
    public Mt plus(VectorMt other){
        return plusDefault(other, this);
    }
    public Mt plus(ZeroMt other){
        return plusDefault(other, this);      
    }

    // minus 
    public Mt minus(double scalar){
        if(scalar == 0)
            return this;
        return minusIfless(scalar);
    }
    public abstract Mt minusIfless(double scalar);

    public IDoubleMatrix minus(IDoubleMatrix other){
        assert (n == other.getN()) && (m == other.getM()) : "minus: size error";
        if(other instanceof Mt)
            return this.minus((Mt)other);
        return minusDefault(this, other);        
    }
    public abstract Mt minus(Mt other);
    private static Mt minusDefault(IDoubleMatrix A, IDoubleMatrix B){
        int n = A.getN(), m = A.getM();
        double res[][] = new double[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                    res[i][j] = A.get(i, j)-B.get(i, j);
        return new FullMt(res);
    }

    public Mt minus(SparseMt other){
        return minusDefault(other, this);
    }
    public Mt minus(FullMt other){
        return minusDefault(other, this);
    }
    public Mt minus(IdentityMt other){
        return minusDefault(other, this);
    }
    public Mt minus(DiagonalMt other){
        return minusDefault(other, this);
    }
    public Mt minus(AntiDiagonalMt other){
        return minusDefault(other, this);
    }
    public Mt minus(VectorMt other){
        return minusDefault(other, this);
    }
    public Mt minus(ZeroMt other){
        return minusDefault(other, this);
    }

    // normy 
    public double normOne(){
        if(myNormOne == -1)
            myNormOne = getNormOne();
        return myNormOne;
    }
    protected double getNormOne(){
        double res = 0;
        for(int j = 0; j < m; j++)
        {
            double pom = 0;
            for(int i = 0; i < n; i++)
                pom+=Math.abs(get(i, j));
            res = Double.max(pom, res);
        }
        return res; 
    }

    public double normInfinity(){
        if(myNormInfinity == -1)
            myNormInfinity = getNormInfinity();
        return myNormInfinity;
    }
    protected double getNormInfinity(){
        double res = 0;
        for(int i = 0; i < n; i++)
        {
            double pom = 0;
            for(int j = 0; j < m; j++)
                pom+=Math.abs(get(i, j));
            res = Double.max(pom, res);
        }
        return res;
    }

    public double frobeniusNorm(){
        if(myFrobeniusNorm == -1)
            myFrobeniusNorm = getForbeniusNorm();
        return myFrobeniusNorm;
    }
    protected double getForbeniusNorm(){
        double res = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                res+=get(i, j)*get(i, j);
        return Math.sqrt(res);
    }

    // reszta 

    public Shape shape(){
        if(myShape == null)
            myShape = Shape.matrix(n, m);
        return myShape;
    }

    public int getN(){
        return n;
    }

    public int getM(){
        return m;
    }

    public double get(int x, int y){
        assert (x >= 0 && x < n) : "get: x out of range" + Double.toString(n);
        assert (y >= 0 && y < m) : "get: y out of range";
        return getVal(x, y);
    }

    protected abstract double getVal(int x, int y);

    public abstract double[][] data();

    public String toString(){
        String res[] = new String[n];
        for(int i = 0; i < n; i++)
            res[i] = "";

        for(int i = 0; i < n; i++){
            double pom = get(i, 0);
            int ile = 1;
            for(int j = 1; j < m; j++){
                if(get(i, j) == pom){
                    ile++;
                    continue;
                }
                if(ile >= 3)
                    res[i] += " " + pom + "..." + pom;
                else
                    while(ile > 0){
                        res[i]+=" " + pom;
                        ile--;
                    }
                pom = get(i, j);
                ile = 1;
            }
            if(ile >= 3){
                res[i] += " " + pom + "..." + pom;
                ile = 0;
            }
            while(ile > 0){
                res[i]+=" " + pom;
                ile--;
            }
        }
        
        String pomer = "";
        for(int i = 0; i < n; i++)
            pomer += res[i]+"\n";
        return pomer;
    }
}