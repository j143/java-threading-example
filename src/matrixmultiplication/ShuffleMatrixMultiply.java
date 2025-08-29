
import java.util.Objects;

public class ShuffleMatrixMultiply {

    // Matrix Block Index
    static class MatrixIndex {
        final long row, col;

        MatrixIndex(long r, long c) { this.row = r; this.col = c; }

        @Override
        public boolean equals(Object o) {
            // add equal condition
        }

        @Override
        public int hashCode() { return Objects.hash(row, col)}

        @Override
        public String toString() { return "(" + row + ", " + col + ")"; }
    }

    // MatrixBlock structure
    static class MatrixBlock {
        final int rows, cols;
        final String name;

        MatrixBlock(int r, int c, String name) {
            this.rows = r;
            this.cols = c;
            this.name = name;
        }

        MatrixBlock multiply(MatrixBlock B) {    
            return new MatrixBlock(this.rows, B.cols, "product");
        }

        @Override
        public String toString() { return name; }
    }

    

}