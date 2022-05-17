package pl.edu.mimuw;

import pl.edu.mimuw.matrix.DoubleMatrixFactory;
import pl.edu.mimuw.matrix.IDoubleMatrix;
import pl.edu.mimuw.matrix.MatrixCellValue;
import pl.edu.mimuw.matrix.Shape;

public class Main {

  public static void main(String[] args) {
    System.out.println("AntiDiagonal");
    IDoubleMatrix xd = DoubleMatrixFactory.antiDiagonal(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
    System.out.println(xd);

    System.out.println("Diagonal");
    xd = DoubleMatrixFactory.diagonal(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
    System.out.println(xd);

    double tab[][] = new double[3][3];
    for(int i = 0; i < 3; i++)
      for(int j = 0; j < 3; j++)
        tab[i][j] = i^j;

    System.out.println("Full");
    xd = DoubleMatrixFactory.full(tab);
    System.out.println(xd);

    System.out.println("Identity");
    xd = DoubleMatrixFactory.identity(10);
    System.out.println(xd);

    System.out.println("Sparse");
    xd = DoubleMatrixFactory.sparse(Shape.matrix(10, 10), MatrixCellValue.cell(0, 0, 42.0), MatrixCellValue.cell(4, 7, 42.0));
    System.out.println(xd);

    System.out.println("Vector");
    xd = DoubleMatrixFactory.vector(3.0, 4.0, 7.0, 0.0, 4.0);
    System.out.println(xd);

    System.out.println("Zero");
    xd = DoubleMatrixFactory.zero(Shape.matrix(10, 10));
    System.out.println(xd);
  }
}
