package pl.edu.mimuw.matrix.pac;

public class IdentityMt extends Mt {
    
    public IdentityMt(int size){
        super(size, size);
    }
    
    // times
    public Mt times(Mt other){
        return other;
    }
    public Mt timesIfless(double scalar){
        double[] pom = new double[n];
        for(int i = 0; i < n; i++)
            pom[i] = scalar;
        return new DiagonalMt(pom);
    }

    // times override
    @Override
    public Mt times(SparseMt other){
        return other;
    }
    @Override
    public Mt times(FullMt other){
        return other;
    }
    @Override
    public Mt times(IdentityMt other){
        return other;
    }
    @Override
    public Mt times(DiagonalMt other){
        return other;
    }
    @Override
    public Mt times(AntiDiagonalMt other){
        return other;
    }
    @Override
    public Mt times(VectorMt other){
        return other;
    }
    @Override
    public Mt times(ZeroMt other){
        return other;
    }

    // plus
    public Mt plus(Mt other){
        return other.plus((IdentityMt)this);
    }
    public Mt plusIfless(double scalar){
        double pom[][] = new double[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
            {
                pom[i][j] = scalar;
                if(i == j)
                    pom[i][j]+=1;
            }
        return new FullMt(pom);
    }

    // plus override
    @Override
    public Mt plus(IdentityMt other){
        double pom[] = new double[n];
        for(int i = 0; i < n; i++)
            pom[i] = 2;
        
        return new DiagonalMt(pom);
    }
    @Override
    public Mt plus(DiagonalMt other){
        double pom[] = new double[n];
        for(int i = 0; i < n; i++)
            pom[i] = other.get(i, i) + 1;

        return new DiagonalMt(pom);
    }

    // minus 
    public Mt minus(Mt other){
        return other.minus((IdentityMt)this);
    }
    public Mt minusIfless(double scalar){
        double pom[][] = new double[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
            {
                pom[i][j] = -scalar;
                if(i == j)
                    pom[i][j]+=1;
            }
        return new FullMt(pom);
    }

    // minus override
    @Override
    public Mt minus(IdentityMt other){
        return new ZeroMt(n, n);
    }
    @Override
    public Mt minus(DiagonalMt other){
        double pom[] = new double[n];
        for(int i = 0; i < n; i++)
            pom[i] = other.get(i, i) - 1;
        return new DiagonalMt(pom);
    }

    // reszta
    public double getVal(int x, int y){
        if(x == y)
            return 1;
        return 0;
    }

    public double[][] data(){
        double pom[][] = new double[n][n];
        for(int i = 0; i < n; i++)
            pom[i][i] = 1;
        return pom;
    }
}
