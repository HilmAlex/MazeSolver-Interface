package model;

public interface IArrays {

    static void fillArray(int[][] array, int rowNumbers, int columnNumbers){
        for (int i = 0; i < rowNumbers; i++) {
            for (int j = 0; j < columnNumbers; j++) {
                array[i][j] = 0;
            }
        }
    }

    static void printArray(int[][] array){
        String print;

        print = "";
        for (int i = 0; i < array.length; i++) {
            print += "| ";
            for (int j = 0; j < array[0].length; j++) {
                print += array[i][j] + "  ";
            }
            print += "|\n";
        }

        System.out.println(print);
    }
}
