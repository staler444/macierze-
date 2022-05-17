package pl.edu.mimuw.matrix.pac;

public class AntiDiagonalMt extends Mt {
    private final double anitDiagTab[];

    public AntiDiagonalMt(double[] values){
        super(values.length, values.length);
        anitDiagTab = values;
    }

    // times
    public Mt times(Mt other){
        return other.times((AntiDiagonalMt)this);
    }

    public Mt timesIfless(double scalar){
        double pom[] = new double[n]; 
        for(int i = 0; i < n; i++)
            pom[i] = anitDiagTab[i]*scalar;
        return new AntiDiagonalMt(pom);
    }

    // times override
    

    // plus
    public Mt plus(Mt other){
        return other.plus((AntiDiagonalMt)this);
    }

    public Mt plusIfless(double scalar){
        double pom[][] = new double[n][n];

        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
            {
                pom[i][j] = scalar;
                if(i+j+1 == n)
                    pom[i][j] += anitDiagTab[j];
            }
        return new FullMt(pom);
    }

    // minus 
    public Mt minus(Mt other){
        return other.minus((AntiDiagonalMt)this);
    }

    public Mt minusIfless(double scalar){
        double pom[][] = new double[n][n];

        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
            {
                pom[i][j] = -scalar;
                if(i+j+1 == n)
                    pom[i][j] += anitDiagTab[j];
            }
        return new FullMt(pom);
    }

    // reszta

    public double getVal(int x, int y){
        if(x+y+1 == n)
            return anitDiagTab[y];
        return 0;
    }

    public double[][] data(){
        double pom[][] = new double[n][n];
        for(int i = 0; i < n; i++)
            pom[n-i-1][i] = anitDiagTab[i];
        return pom;
    }
}