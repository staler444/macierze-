package pl.edu.mimuw.matrix;

import pl.edu.mimuw.matrix.pac.AntiDiagonalMt;
import pl.edu.mimuw.matrix.pac.DiagonalMt;
import pl.edu.mimuw.matrix.pac.FullMt;
import pl.edu.mimuw.matrix.pac.IdentityMt;
import pl.edu.mimuw.matrix.pac.SparseMt;
import pl.edu.mimuw.matrix.pac.VectorMt;
import pl.edu.mimuw.matrix.pac.ZeroMt;

public class DoubleMatrixFactory {

  private DoubleMatrixFactory() {
  }

  public static IDoubleMatrix sparse(Shape shape, MatrixCellValue... values){
    for(MatrixCellValue it : values)
    {
      assert (it.row >= 0) && (it.row < shape.rows) : "DoubleMatrixFactory: sparse: invalid input";
      assert (it.column >= 0) && (it.column < shape.columns) : "DoubleMatrixFactory: sparse: invalid input";
    }

    Shape shapebc = Shape.matrix(shape.rows, shape.columns);
    MatrixCellValue valsbc[] = new MatrixCellValue[values.length];

    for(int i = 0; i < values.length; i++)
      valsbc[i] = MatrixCellValue.cell(values[i].row, values[i].column, values[i].value);

    return new SparseMt(shapebc, valsbc);
  }

  public static IDoubleMatrix full(double[][] values) {
    assert values != null : "DoubleMatrixFactory: full: invalid input";
    int n = values.length;
    assert n > 0 : "DoubleMatrixFactory: full: invalid input";

    int m = values[0].length;
    assert m > 0 : "DoubleMatrixFactory: full: invalid input";

    for(int i = 0; i < n; i++)
      assert values[i].length == m : "DoubleMatrixFactory: full: invalid input"; 

    double pom[][] = new double[n][m];
    for(int i = 0; i < n; i++)
      for(int j = 0; j < m; j++)
        pom[i][j] = values[i][j];

    return new FullMt(pom);
  }

  public static IDoubleMatrix identity(int size) {
    assert size > 0 : "DoubleMatrixFactory: identity: size error";
      return new IdentityMt(size);
  }

  public static IDoubleMatrix diagonal(double... diagonalValues) {
    double pom[] = new double[diagonalValues.length];
    for(int i = 0; i < diagonalValues.length; i++)
      pom[i] = diagonalValues[i];

    return new DiagonalMt(pom);
  }

  public static IDoubleMatrix antiDiagonal(double... antiDiagonalValues) {
    double pom[] = new double[antiDiagonalValues.length];
    for(int i = 0; i < antiDiagonalValues.length; i++)
      pom[i] = antiDiagonalValues[i];

    return new AntiDiagonalMt(pom);
  }

  public static IDoubleMatrix vector(double... values){
    double pom[] = new double[values.length];
    for(int i = 0; i < values.length; i++)
      pom[i] = values[i];

    return new VectorMt(pom);
  }

  public static IDoubleMatrix zero(Shape shape) {
    return new ZeroMt(shape.rows, shape.columns);
  }
}