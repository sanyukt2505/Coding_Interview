package problem;

public class TowerOfHanoi{

    public static void solveTOH(int size, char src, char inter, char dest) {
        if (size == 1) {
            System.out.println("Move plate 1 from " + src + " to " + dest);
            return;
        }
        solveTOH(size-1, src, dest, inter);
        System.out.println("Move plate "+ size + " from " + src + " to " + dest);
        solveTOH(size-1, inter, src, dest);
    }
    public static void main(String []args){
        solveTOH(4, 'S', 'I', 'D');
    }
}
