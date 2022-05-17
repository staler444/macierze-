package pl.edu.mimuw.matrix.pac;

import java.util.Vector;
import java.util.*;
import pl.edu.mimuw.matrix.MatrixCellValue;
import pl.edu.mimuw.matrix.Shape;

public class SparseMt extends Mt{
    private final int size;
    private final Vector<MatrixCellValue> tab[];

    @SuppressWarnings("unchecked")
    public SparseMt(Shape shape, MatrixCellValue cells[]){
        super(shape.rows, shape.columns);

        myShape = shape;
        tab = (Vector<MatrixCellValue>[]) new Vector[shape.rows];
        size = cells.length;

        for(int i = 0; i < n; i++)
            tab[i] = new Vector<MatrixCellValue>();

        for(MatrixCellValue it : cells)
            tab[it.row].add(it);
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
        for(MatrixCellValue it : tab[x])
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
}