package pl.edu.mimuw.matrix.pac;

public class FullMt extends Mt {
    private final double tab[][];

    public FullMt(int n, int m){
        super(n, m);
        tab = new double[n][m];
    }

    public FullMt(double tabl[][]){
        super(tabl.length, tabl[0].length);
        tab = tabl;
    }
    public Mt timesIfless(double scalar){
        double pom[][] = new double[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                pom[i][j] = tab[i][j]*scalar;
        return new FullMt(pom);
    }
    public Mt times(Mt other){
        return other.times((FullMt)this);
    }
    
    public Mt plusIfless(double scalar){
        double pom[][] = new double[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                pom[i][j] = tab[i][j]+scalar;
        return new FullMt(pom);
    }
    public Mt plus(Mt other){
        return other.plus((FullMt)this);
    }

    public Mt minusIfless(double scalar){        
        double pom[][] = new double[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                pom[i][j] = tab[i][j]-scalar;
        return new FullMt(pom);
    }
    public Mt minus(Mt other){
        return other.minus((FullMt)this);
    }

    protected double getVal(int x, int y){
        return tab[x][y];
    }

    public double[][] data(){
        double pom[][] = new double[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                pom[i][j] = tab[i][j];
        return pom;
    }
}