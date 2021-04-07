package create.destroy;

/**
 * Effective Java: A single-element enum type is the best way to implement a singleton.
 *   It is more convenient and provides the serialization machinery for free.
 *   Finally, it provides ironclad guarantee against multiple instantiation, even in the face of sophisticated serialization or reflection attacks
 */
public enum Elvis {
    INSTANCE;

    public void leaveTheBuilding() {
        System.out.println(INSTANCE.toString() + " leaving the building.");
    }

    public static void main(String[] args) {
        Elvis firstElvis = Elvis.INSTANCE;
        Elvis secondElvis = Elvis.INSTANCE;

        System.out.println("Are firstElvis and secondElvis same?: " + firstElvis.equals(secondElvis));

        // Prints the same name
        firstElvis.leaveTheBuilding();
        secondElvis.leaveTheBuilding();
    }
}
