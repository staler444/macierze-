package pl.edu.mimuw.matrix.pac;

public class VectorMt extends Mt {
    private final double[] values;

    public VectorMt(double[] values){
        super(values.length, 1);
        this.values = values;
    }
    
    // times 
    public Mt times(Mt other){
        return other.times((VectorMt)this);
    }

    public Mt timesIfless(double scalar){
        double[] pom = new double[n];
        for(int i = 0; i < n; i++)
            pom[i] = values[i]*scalar;
        return new VectorMt(pom);
    }

    // plus
    public Mt plus(Mt other){
        return other.plus((VectorMt)this);
    }

    public Mt plusIfless(double scalar){
        double pom[] = new double[n];
        for(int i = 0; i < n; i++)
            pom[i] = values[i]+scalar;
        return new VectorMt(pom);
    }

    // minus
    public Mt minus(Mt other){
        return other.minus((VectorMt)this);
    }

    public Mt minusIfless(double scalar){
        double[] pom = new double[n];
        for(int i = 0; i < n; i++)
            pom[i] = values[i]-scalar;
        return new VectorMt(pom);
    }

    // reszta
    public double getVal(int x, int y){
        return values[x];
    }

    public double[][] data(){
        double[][] pom = new double[n][1];
        for(int i = 0; i < n; i++)
            pom[i][0] = values[i];
        return pom;
    }
}
