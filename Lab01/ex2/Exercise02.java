import vn.edu.tdtu.*;

public class Exercise02 {
    public static void main(String[] args) {
        int a[] = {12, 2, 34, 5, 6};
        int b[] = {8, 3, 24, 1, 19};
        ArrayOutput.print(a);
        ArrayOutput.print(b);
        int c[] = ArrayHandler.merge(a, b);
        ArrayHandler.sort(c);
        ArrayOutput.write(c, "./output.txt");
    }
}
