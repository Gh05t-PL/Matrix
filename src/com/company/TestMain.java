package com.company;



public class TestMain {

    public static void main(String[] args) throws Exception {
       /* Matrix matrix = new Matrix(2, 2);
        Matrix matrix2 = new Matrix(3, 3);
        System.out.println("Macierz A ma wymiary " + matrix.getRowsCount() + "x" + matrix.getColumnsCount());
        System.out.println("Macierz B ma wymiary " + matrix2.getRowsCount() + "x" + matrix2.getColumnsCount());
        Double[] arr11 = {1., 2.};
        Double[] arr12 = {0., 1.};

        Double[] arr21 = {1., 2., 3.};
        Double[] arr22 = {4., 5., 6.};
        Double[] arr23 = {7., 8., 9.};
        //Double[] arr24 = {10., 11., 12.};
        matrix.set(0, arr11);
        matrix.set(1, arr12);


        matrix2.set(0, arr21);
        matrix2.set(1, arr22);
        matrix2.set(2, arr23);
        //matrix2.set(3, arr24);
        System.out.println(matrix);
        System.out.println(matrix2);


        try {
            System.out.println(matrix2.rotateCCW());
            System.out.println(matrix2.rotateCW());
            //System.out.println(matrix2.transp());
            //System.out.println(matrix2.reduce(0,2).reduce(1,0));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        Matrix3D m3d = new Matrix3D(3,3,3);
        m3d.set(0,0,2,5.1);
        m3d.set(1,0,2,5.2);
        m3d.set(2,0,2,5.3);

        try {
//            m3d.set(2, 3, 2, 5.9);
        } catch (Exception e){
            e.printStackTrace();
        }
        //m3d.set(3,0,2,5.4);
        

        m3d.set(0,0,2,5.11);
        m3d.set(0,1,2,5.22);
        m3d.set(0,2,2,5.33);
//        m3d.set(3,0,2,5.4);

        m3d.set(0,0,0,6.1);
        m3d.set(1,0,0,6.2);
        m3d.set(2,0,0,6.3);
        //m3d.set(3,0,0,6.4);
        Double[] arra = {1.25,2.5,1.75};
        m3d.get(1).set(1,arra);
//        m3d.set(1,1,1,2.5);
//        m3d.set(1,1,0,1.25);
//        m3d.set(1,1,2,1.75);
        //for (int i = 0; i < 3; i++) {
            System.out.println(m3d);
        //}
        System.out.println("SEPARATOR");
        long start = System.nanoTime();
        //Matrix3D m3d2 = m3d.rotateYCW().rotateYCW().rotateYCW();
        Matrix3D m3d2 = m3d.rotateZCCW();
        long end = System.nanoTime();
        System.out.println(m3d2);
        System.out.println(end-start);
//        for (int i = 0; i < 4; i++) {
//            System.out.println(m3d2.get(i));
//        }
    }
}
