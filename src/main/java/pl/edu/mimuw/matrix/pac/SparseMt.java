package pl.edu.mimuw.matrix.pac;

import java.util.Vector;
import java.util.*;
import pl.edu.mimuw.matrix.MatrixCellValue;
import pl.edu.mimuw.matrix.Shape;

public class SparseMt extends Mt{
    private final int size;
    private final Vector<MatrixCellValue> tab[];
    private final HashMap<Integer, Integer> index;

    @SuppressWarnings("unchecked")
    public SparseMt(Shape shape, MatrixCellValue cells[]){
        super(shape.rows, shape.columns);

        myShape = shape;
        size = cells.length;

        Map<Integer, Double> pom = (Map<Integer, Double>) new TreeMap<Integer, Double>();
        for(MatrixCellValue it : cells)
            pom.putIfAbsent(it.row, 0.0);

        index = new HashMap<Integer, Integer>(pom.size());

        int k = 0;
        for(Map.Entry<Integer, Double> it : pom.entrySet())
        {
            index.put(it.getKey(), k);
            k++;
        }

        tab = (Vector<MatrixCellValue>[]) new Vector[pom.size()];

        for(int i = 0; i < pom.size(); i++)
            tab[i] = new Vector<MatrixCellValue>();

        for(MatrixCellValue it : cells)
            tab[index.get(it.row)].add(it);
    }

    // times 
    public Mt times(Mt other){
        return other.times((SparseMt)this);
    }

    public Mt timesIfless(double scalar){
        MatrixCellValue pom[] = new MatrixCellValue[size];

        int i = 0;
        for(Vector<MatrixCellValue> ve : tab)
            for(MatrixCellValue it : ve)
            {
                pom[i] = MatrixCellValue.cell(it.row, it.column, it.value*scalar);
                i++;
            }
        return new SparseMt(myShape, pom);
    }

    // times override 
    @Override
    public Mt times(SparseMt other){
        Map<Long, Double> ma = (Map<Long, Double>) new HashMap<Long, Double>(size);
        
        long mno = Math.max(other.getN(), m);

        for(Vector<MatrixCellValue> ve : tab)
            for(MatrixCellValue it : ve)
                for(Vector<MatrixCellValue> we : other.tab)
                    for(MatrixCellValue ite : we)
                        if(ite.column == it.row)
                        {
                            double pom = 0;
                            long x = ite.row;
                            long y = it.column;
                            
                            if(ma.get(x+y*mno) != null)
                                pom = ma.get(x+y*mno);
                            ma.put(x+y*mno, pom+it.value*ite.value);
                        }
        int i = 0;
        MatrixCellValue pom[] = new MatrixCellValue[ma.size()];
        for(Map.Entry<Long, Double> it : ma.entrySet())
            pom[i++] = MatrixCellValue.cell((int) (it.getKey()%mno), (int) (it.getKey()/mno), it.getValue());

        return new SparseMt(Shape.matrix(other.getN(), m), pom);
    }

    // plus 
    public Mt plus(Mt other){
        return other.plus((SparseMt)this);
    }

    public Mt plusIfless(double scalar){
        double pom[][] = new double[n][m];

        for(Vector<MatrixCellValue> ve : tab)
            for(MatrixCellValue it : ve)
                pom[it.row][it.column] = it.value;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                pom[i][j] += scalar;

        return new FullMt(pom);
    }

    // plus override 
    @Override
    public Mt plus(SparseMt other){
        Map<Long, Double> ma = (Map<Long, Double>) new HashMap<Long, Double>(size);
        
        long mno = Math.max(other.getN(), m);

        for(Vector<MatrixCellValue> ve : tab)
            for(MatrixCellValue it : ve)
            {
                long x = it.row;
                long y = it.column;
                double pom = 0;

                if(ma.get(x+y*mno) != null)
                    pom = ma.get(x+y*mno);
                ma.put(x+y*mno, pom+it.value);
            }

        for(Vector<MatrixCellValue> ve : other.tab)
            for(MatrixCellValue it : ve)
            {
                long x = it.row;
                long y = it.column;
                double pom = 0;

                if(ma.get(x+y*mno) != null)
                    pom = ma.get(x+y*mno);
                ma.put(x+y*mno, pom+it.value);
            }

        int i = 0;
        MatrixCellValue pom[] = new MatrixCellValue[ma.size()];
        for(Map.Entry<Long, Double> it : ma.entrySet())
            pom[i++] = MatrixCellValue.cell((int) (it.getKey()%mno), (int) (it.getKey()/mno), it.getValue());

        return new SparseMt(Shape.matrix(n, m), pom);
    }

    // minus
    public Mt minus(Mt other){
        return other.minus((SparseMt)this);
    }

    public Mt minusIfless(double scalar){
        double pom[][] = new double[n][m];

        for(Vector<MatrixCellValue> ve : tab)
            for(MatrixCellValue it : ve)
                pom[it.row][it.column] = it.value;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                pom[i][j] -= scalar;
                
        return new FullMt(pom);
    }

    // minus override 
    @Override
    public Mt minus(SparseMt other){
        Map<Long, Double> ma = (Map<Long, Double>) new HashMap<Long, Double>(size);
        
        long mno = Math.max(other.getN(), m);

        for(Vector<MatrixCellValue> ve : other.tab)
            for(MatrixCellValue it : ve)
            {
                long x = it.row;
                long y = it.column;
                double pom = 0;

                if(ma.get(x+y*mno) != null)
                    pom = ma.get(x+y*mno);
                ma.put(x+y*mno, pom+it.value);
            }

        for(Vector<MatrixCellValue> ve : tab)
            for(MatrixCellValue it : ve)
            {
                long x = it.row;
                long y = it.column;
                double pom = 0;

                if(ma.get(x+y*mno) != null)
                    pom = ma.get(x+y*mno);
                ma.put(x+y*mno, pom-it.value);
            }

        int i = 0;
        MatrixCellValue pom[] = new MatrixCellValue[ma.size()];
        for(Map.Entry<Long, Double> it : ma.entrySet())
            pom[i++] = MatrixCellValue.cell((int) (it.getKey()%mno), (int) (it.getKey()/mno), it.getValue());

        return new SparseMt(Shape.matrix(n, m), pom);
    }

    // normy
    @Override
    public double getNormOne(){
        Map<Integer, Double> ma = (Map<Integer, Double>) new HashMap<Integer, Double>(size);
        
        double res = 0;

        for(Vector<MatrixCellValue> ve : tab)
            for(MatrixCellValue it : ve)
            {
                double pom = (ma.get(it.column) == null ? 0 : ma.get(it.column));

                ma.put(it.column, pom+Math.abs(it.value));
                res = Double.max(res, ma.get(it.column));
            }
        return res;
    }
    @Override
    public double getNormInfinity(){
        double res = 0;

        for(Vector<MatrixCellValue> ve : tab)
        {
            double pom = 0;
            for(MatrixCellValue it : ve)
                pom += Math.abs(it.value);
            res = Double.max(pom, res);
        }
        return res;
    }

    @Override
    public double getForbeniusNorm(){
        double res = 0;
        for(Vector<MatrixCellValue> ve : tab)
            for(MatrixCellValue it : ve)
                res += it.value*it.value;
        return Math.sqrt(res);
    }

    // reszta 
    public double getVal(int x, int y){
        if(index.get(x) == null)
            return 0;
        for(MatrixCellValue it : tab[index.get(x)])
            if(it.column == y)
                return it.value;
        return 0;
    }

    public double[][] data(){
        double pom[][] = new double[n][m];
        for(Vector<MatrixCellValue> ve : tab)
            for(MatrixCellValue it : ve)
                pom[it.row][it.column] = it.value;
        return pom;
    }

    @Override 
    public String toString(){
        String res[] = new String[n];

        for(int i = 0; i < n; i++)
            res[i] = "";

        for(int i = 0; i < n; i++){
            if(index.get(i) == null){
                res[i] = " " + 0.0 + "..." + 0.0;
                continue;
            }
            double pom  = get(i, 0);
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
                        res[i]+= " " + pom;
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