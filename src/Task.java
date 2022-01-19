public class Task {
    public static void main(String[] args) {
        double n = 5000;
        double b = 1;
        double t = 1;
        for (int i = 1; i <= t; i++) {
            n = n * (1 + b);
        }
        System.out.println(n);
    }
}
