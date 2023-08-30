import java.util.Random;

public class Multiton {
    private static Multiton[] array = {new Multiton(), new Multiton(), new Multiton()};
    private Multiton(){
    }

    public static Multiton getInstance(){
        return array[random()];
    }

    public static int random(){
        Random random = new Random();
        int value = Math.abs(random.nextInt());
        value = value%3;
        return value;
    }
}
