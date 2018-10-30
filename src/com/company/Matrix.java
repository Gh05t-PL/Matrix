package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Matrix {
    private Integer rows;
    private Integer columns;
    private ArrayList<ArrayList<Double>> matrix;

    /**
     * 👻
     *
     * @param rows    Number of rows to be set
     * @param columns Number of columns to be set
     */
    Matrix(Integer rows, Integer columns) {
        this.columns = columns;
        this.rows = rows;
        this.matrix = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Double> arr = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                arr.add(0.0);
            }
            this.matrix.add(arr);
        }

    }

    /**
     * @param x       x index (starts from 0)
     * @param y       y index (starts from 0)
     * @param element Double value to be set
     */
    public void set(int x, int y, Double element) {
        this.matrix.get(x).set(y, element);
    }

    /**
     *
     * @param x      x index (starts from 0)
     * @param values Array of values in y axis
     * @throws Exception
     */
    public void set(int x, Double[] values) throws Exception {
        if ( values.length != this.columns )
            throw new Exception("ValuesLengthOtherThenMatrixColumns");
        this.matrix.set(x, new ArrayList<>(Arrays.asList(values)));
    }

    /* GETTERS */

    /**
     * @param x x index (starts from 0)
     * @param y y index (starts from 0)
     * @return Double value
     */
    public Double get(int x, int y) {
        return this.matrix.get(x).get(y);
    }

    /**
     * @param x x index (starts from 0)
     * @return ArrayList with columns from x row
     */
    public ArrayList<Double> getRow(int x) {
        return this.matrix.get(x);
    }

    /**
     * @param y y index (starts from 0)
     * @return ArrayList with rows from y column
     */
    public ArrayList<Double> getColumn(int y) {
        ArrayList<Double> arr = new ArrayList<>();

        for (int i = 0; i < this.getRowsCount(); i++) {
            arr.add(this.get(i, y));
        }
        return arr;
    }

    /**
     * @return Number of Rows
     */
    public Integer getRowsCount() {
        return Integer.valueOf(this.rows);
    }

    /**
     * @return Number of Columns
     */
    public Integer getColumnsCount() {
        return Integer.valueOf(this.columns);
    }
    /* END OF GETTERS */

    /* CHECKERS */

    /**
     * @param B Matrix to be added
     * @return boolean value of logic
     */
    private boolean isSummable(Matrix B) {
        if ( this.getRowsCount() == B.getRowsCount() && this.getColumnsCount() == B.getColumnsCount() )
            return true;
        return false;

    }

    /**
     * @param B Matrix which going to be multiplicated with
     * @return boolean value of logic
     */
    private boolean isMultiplicable(Matrix B) {
        if ( this.getColumnsCount() == B.getRowsCount() )
            return true;
        return false;
    }
    /* END OF CHECKERS */

    /**
     * @param B Matrix to be added to this Matrix
     * @return Summed Matrix C
     * @throws Exception Matrix Not Summable Exception
     */
    public Matrix add(Matrix B) throws Exception {
        if ( !isSummable(B) )
            throw new Exception("MatrixNotSummableException");
        Matrix result = new Matrix(this.getRowsCount(), this.getColumnsCount());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.set(i, j, (this.get(i, j) + B.get(i, j)));
            }
        }
        return result;
    }

    /**
     * @param B Matrix to be multiplicated with this Matrix
     * @return A*B Matrix
     * @throws Exception Matrix Not Multiplicable Exception
     */
    public Matrix mult(Matrix B) throws Exception {
        if ( !isMultiplicable(B) )
            throw new Exception("MatrixNotMultiplicableException");
        Matrix result = new Matrix(this.getRowsCount(), B.getColumnsCount());
        for (int i = 0; i < this.getRowsCount(); i++) {
            for (int j = 0; j < B.getColumnsCount(); j++) {
                Double sum = Double.valueOf(0.0);
                for (int k = 0; k < this.getColumnsCount(); k++) {
                    sum += this.get(i, k) * B.get(k, j);
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }

    /**
     * @param b Double(scalar) value to be multiplicated with this Matrix
     * @return A*b Matrix
     */
    public Matrix mult(Double b) {
        Matrix result = new Matrix(this.getRowsCount(), this.getColumnsCount());
        for (int i = 0; i < this.getRowsCount(); i++) {
            for (int j = 0; j < this.getColumnsCount(); j++) {
                result.set(i, j, (this.get(i, j) * b));
            }
        }
        return result;
    }

    /**
     * @return Transposed Matrix
     */
    public Matrix transp() {
        Matrix result = new Matrix(this.getColumnsCount(), this.getRowsCount());
        for (int i = 0; i < this.getColumnsCount(); i++) {
            try {
                result.set(i, this.getColumn(i).toArray(new Double[0]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param x x index (starts from 0)
     * @param y y index (starts from 0)
     * @return Minor from previous Matrix reduced by x-row and y-column (Minor is Matrix)
     */
    public Matrix reduce(int x, int y) {
        Matrix result = new Matrix(this.getRowsCount() - 1, this.getColumnsCount() - 1);
        for (int i = 0, o = 0; i < this.getRowsCount(); i++, o++) {
            if ( i == x ) {
                o--;
                continue;
            }
            for (int j = 0, k = 0; j < this.getColumnsCount(); j++, k++) {
                if ( j == y ) {
                    k--;
                    continue;
                }
                result.set(o, k, this.get(i, j));
            }
        }
        return result;
    }

    /**
     * @return Rotated clockwise Matrix
     */
    public Matrix rotateCW() {
        Matrix result = new Matrix(this.getColumnsCount(), this.getRowsCount());
        for (int i = 0; i < this.getColumnsCount(); i++) {
            ArrayList<Double> arr = this.getColumn(i);
            Collections.reverse(arr);
            try {
                result.set(i, arr.toArray(new Double[0]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @return Rotated counter-clockwise Matrix
     */
    public Matrix rotateCCW() {
        Matrix result = new Matrix(this.getColumnsCount(), this.getRowsCount());
        for (int i = this.getColumnsCount() - 1, o = 0; i >= 0; i--, o++) {
            ArrayList<Double> arr = this.getColumn(i);
            //Collections.reverse(arr);
            try {
                result.set(o, arr.toArray(new Double[0]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @return JSON Friendly and human readable String
     */
    public String toString() {
        return Arrays.toString(matrix.toArray()).replace("],", "],\n").replace("[[", "[\n [").replace("]]", "]\n]");
    }

}




/*package com.company;

import java.util.ArrayList;

public class Matrix<T> {
    private Integer rows;
    private Integer columns;
    private ArrayList<ArrayList<T>> matrix;

    <T> Matrix(Integer rows, Integer columns) {
        this.columns = columns;
        this.rows = rows;
        this.matrix = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<T> arr = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                arr.add(new T());
            }
            this.matrix.add(arr);
        }
        System.out.println(this.matrix.size());
    }

    public void set(int x, int y, T element) {
        ArrayList<T> arr = this.matrix.get(x);
        arr.set(y, element);
        this.matrix.set(x, arr);
    }

    public T get(int x, int y){
        return this.matrix.get(x).get(y);
    }

}
*/