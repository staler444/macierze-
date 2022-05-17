package pl.edu.mimuw.matrix.pac;

public class DiagonalMt extends Mt {
    private final double diagTab[];

    public DiagonalMt(double[] values){
        super(values.length, values.length);
        diagTab = values;
    }
    
    // times
    public Mt times(Mt other){
        return other.times((DiagonalMt)this);
    }
    public Mt timesIfless(double scalar){
        double pom[] = new double[n];
        for(int i = 0; i < n; i++)
            pom[i] = diagTab[i]*scalar;

        return new DiagonalMt(pom);
    }

    private Mt timesHelper(Mt other){
        double pom[][] = new double[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                pom[i][j] = other.get(i, j)*diagTab[i];
        return new FullMt(pom);
    }

    // times override 
    @Override
    public Mt times(SparseMt other){
        return timesHelper(other);
    }
    @Override
    public Mt times(FullMt other){
        return timesHelper(other);
    }
    @Override
    public Mt times(IdentityMt other){
        return this;
    }
    @Override
    public Mt times(DiagonalMt other){
        double pom[] = new double[n];
        for(int i = 0; i < n; i++)
            pom[i] = other.get(i, i)*diagTab[i];
        return new DiagonalMt(pom);
    }
    @Override
    public Mt times(AntiDiagonalMt other){
        double pom[] = new double[n];
        for(int i = 0; i < n; i++)
            pom[i] = other.get(i, i)*diagTab[i];
        return new AntiDiagonalMt(pom);
    }
    @Override
    public Mt times(VectorMt other){
        return timesHelper(other);
    }
    @Override
    public Mt times(ZeroMt other){
        return new ZeroMt(other.getN(), m);
    }

    // plus 
    public Mt plus(Mt other){
        return other.plus((DiagonalMt)this);
    }
    public Mt plusIfless(double scalar){
        double pom[][] = new double[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
            {
                pom[i][j] = scalar;
                if(i == j)
                    pom[i][j] += diagTab[i];
            }
        return new FullMt(pom);
    }

    // plus override
    @Override
    public Mt plus(IdentityMt other){
        double pom[] = new double[n];
        for(int i = 0; i < n; i++)
            pom[i] = diagTab[i]+1;

        return new DiagonalMt(pom);
    }
    @Override
    public Mt plus(DiagonalMt other){
        double pom[] = new double[n];
        for(int i = 0; i < n; i++)
            pom[i] = diagTab[i]+other.get(i, i);

        return new DiagonalMt(pom);
    }

    // minus
    public Mt minus(Mt other){
        return other.minus((DiagonalMt)this);
    }
    public Mt minusIfless(double scalar){
        double pom[][] = new double[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
            {
                pom[i][j] = -scalar;
                if(i == j)
                    pom[i][j] += diagTab[i];
            }
        return new FullMt(pom);
    }

    // minus override
    @Override
    public Mt minus(IdentityMt other){
        double pom[] = new double[n];
        for(int i = 0; i < n; i++)
            pom[i] = 1-diagTab[i];

        return new DiagonalMt(pom);
    }
    @Override
    public Mt minus(DiagonalMt other){
        double pom[] = new double[n];
        for(int i = 0; i < n; i++)
            pom[i] = other.get(i, i) - diagTab[i];

        return new DiagonalMt(pom);
    }

    public double getVal(int x, int y){
        if(x == y)
            return diagTab[x];
        return 0;
    }

    public double[][] data(){
        double pom[][] = new double[n][n];
        for(int i = 0; i < n; i++)
            pom[i][i] = diagTab[i];
        return pom;
    }
}
