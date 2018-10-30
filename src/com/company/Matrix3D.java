package com.company;

import java.util.ArrayList;

//todo if this zxy == b zyx then is multiplicable
//todo docs
public class Matrix3D {
    private Integer rows;
    private Integer columns;
    private Integer depth;
    private ArrayList<Matrix> matrix3d;

    Matrix3D(Integer depth, Integer rows, Integer columns) {
        this.columns = columns;
        this.rows = rows;
        this.depth = depth;
        this.matrix3d = new ArrayList<>();

        for (int i = 0; i < this.depth; i++) {
            Matrix m = new Matrix(this.rows, this.columns);
            this.matrix3d.add(m);
        }
    }

    public Matrix get(int z) {
        return matrix3d.get(z);
    }

    public Double get(int z, int x, int y) {
        return matrix3d.get(z).get(x, y);
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

    /**
     * @return Depth of Matrix
     */
    public Integer getDepth() {
        return Integer.valueOf(this.depth);
    }

    public void set(int z, Matrix element) {
        this.matrix3d.set(z, element);
    }

    public void set(int z, int x, int y, Double element) {
        this.matrix3d.get(z).set(x, y, element);
    }

    public Matrix3D rotateYCCW() {
        Matrix3D m3d = new Matrix3D(this.getRowsCount(), this.getDepth(), this.getColumnsCount());
        for (int i = 0, o = m3d.rows - 1; i < this.getDepth(); i++, o--) {//DEPTH
            for (int j = 0, k = m3d.depth - 1; j < getRowsCount(); j++, k--) {//ROWS
                for (int n = 0, m = m3d.columns - 1; n < this.getColumnsCount(); n++, m--) {//COLUMNS
                    //m3d.set(k, o, n, this.get(i, j, n));
                    m3d.set(k, i, n, this.get(i, j, n));
                }
            }
        }
        return m3d;
    }

    public Matrix3D rotateYCW() {
        Matrix3D m3d = new Matrix3D(getRowsCount(), this.getDepth(), this.getColumnsCount());
        for (int i = 0, o = m3d.rows - 1; i < this.getDepth(); i++, o--) {//DEPTH
            for (int j = 0, k = m3d.depth - 1; j < getRowsCount(); j++, k--) {//ROWS
                for (int n = 0, m = m3d.columns - 1; n < this.getColumnsCount(); n++, m--) {//COLUMNS
                    //m3d.set(k, o, n, this.get(i, j, n));
                    m3d.set(k, o, n, this.get(i, k, n));
                }
            }
        }
        return m3d;
    }

    public Matrix3D rotateZCW() {
        Matrix3D m3d = new Matrix3D(this.getDepth(), this.getColumnsCount(), getRowsCount());
        for (int i = 0; i < this.getDepth(); i++) {//DEPTH
            m3d.set(i, this.get(i).rotateCW());
//            for (int j = 0, k = m3d.columns - 1; j < this.getRowsCount(); j++, k--) {//ROWS
//                for (int n = 0, m = m3d.rows - 1; n < this.getColumnsCount(); n++, m--) {//COLUMNS
//                    m3d.set(i,m,k, this.get());
//                }
//            } todo test this solution
        }
        return m3d;
    }

    public Matrix3D rotateZCCW() {
        Matrix3D m3d = new Matrix3D(this.getDepth(), this.getColumnsCount(), getRowsCount());
        for (int i = 0; i < this.getDepth(); i++) {//DEPTH
            m3d.set(i, this.get(i).rotateCCW());
        }
        return m3d;
    }

    public Matrix3D reduce(int z, int x, int y) {
        Matrix3D m3d = new Matrix3D(this.getDepth() - 1, getRowsCount() - 1, this.getColumnsCount() - 1);
        for (int i = 0, k = 0; i < this.getDepth(); i++, k++) {
            if ( i == z ) {
                k--;
                continue;
            }
            m3d.set(k, this.get(i).reduce(x, y));
        }
        return m3d;
    }

    @Override
    public String toString() {
        String str = "";
        for (Matrix m : this.matrix3d) {
            str += m + "\n";
        }
        return str;
    }
}
