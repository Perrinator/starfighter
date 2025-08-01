//use this to test your ship

public class Shiptest {
    public static void main(String args[]) {
        MovingThing test = new Ship();
        System.out.println("Ship 1 " + test);

        Ship test2 = new Ship(50, 75, 1);
        System.out.println("Ship 2 " + test2);

        Ship test3 = new Ship(7, 7, 6, 5, 1, 1);
        test3.setX(3);
        test3.setY(5);
        System.out.println("Ship 3 " + test3);
    }
}
/*
Expected Output:
Ship 1 0 0 50 50 0
Ship 2 50 75 50 50 0
Ship 3 3 5 6 5 1
*/