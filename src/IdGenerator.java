public class IdGenerator {
    private static int id = 0;

    public static int getId() {
        id++;
        return id;
    }

    public static void decrementId() {
        id--;
    }
}
