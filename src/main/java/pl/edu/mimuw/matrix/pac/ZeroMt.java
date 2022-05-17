package pl.edu.mimuw.matrix.pac;

public class ZeroMt extends Mt {

    public ZeroMt(int n, int m) {
        super(n, m);
    }

    // times 
    public Mt timesIfless(double scalar){
        return this;
    }

    public Mt times(Mt other){
        return new ZeroMt(n, other.getM());
    }
    // times override
    @Override
    public Mt times(SparseMt other){
        return new ZeroMt(other.getN(), m);
    }
    @Override
    public Mt times(FullMt other){
        return new ZeroMt(other.getN(), m);
    }
    @Override
    public Mt times(IdentityMt other){
        return new ZeroMt(other.getN(), m);
    }
    @Override
    public Mt times(DiagonalMt other){
        return new ZeroMt(other.getN(), m);
    }
    @Override
    public Mt times(AntiDiagonalMt other){
        return new ZeroMt(other.getN(), m);
    }
    @Override
    public Mt times(VectorMt other){
        return new ZeroMt(other.getN(), m);
    }
    @Override
    public Mt times(ZeroMt other){
        return new ZeroMt(other.getN(), m);
    }

    // plus 
    public Mt plusIfless(double scalar){
        double pom[][] = new double[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                pom[i][j] = scalar;
        return new FullMt(pom);
    }

    public Mt plus(Mt other){
        return other;
    }

    // plus override
    @Override
    public Mt plus(SparseMt other){
        return other;
    }
    @Override
    public Mt plus(FullMt other){
        return other;
    }
    @Override
    public Mt plus(IdentityMt other){
        return other;
    }
    @Override
    public Mt plus(DiagonalMt other){
        return other;
    }
    @Override
    public Mt plus(AntiDiagonalMt other){
        return other;
    }
    @Override
    public Mt plus(VectorMt other){
        return other;
    }
    @Override
    public Mt plus(ZeroMt other){
        return other;
    }

    // minus 
    public Mt minusIfless(double scalar){
        double pom[][] = new double[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                pom[i][j] = -scalar;
        return new FullMt(pom);
    }

    public Mt minus(Mt other){
        return other.minus((ZeroMt)this);
    }

    // minus override
    @Override
    public Mt minus(SparseMt other){
        return other;
    }
    @Override
    public Mt minus(FullMt other){
        return other;
    }
    @Override
    public Mt minus(IdentityMt other){
        return other;
    }
    @Override
    public Mt minus(DiagonalMt other){
        return other;
    }
    @Override
    public Mt minus(AntiDiagonalMt other){
        return other;
    }
    @Override
    public Mt minus(VectorMt other){
        return other;
    }
    @Override
    public Mt minus(ZeroMt other){
        return other;
    }

    // nomry overide
    @Override
    protected double getNormOne(){
        return 0;
    }
    @Override
    protected double getNormInfinity(){
        return 0;
    }
    @Override
    protected double getForbeniusNorm(){
        return 0;
    }

    // reszta

    public double[][] data(){
        double pom[][] = new double[n][m];
        return pom;
    }

    protected double getVal(int x, int y){
        return 0;
    }
}
