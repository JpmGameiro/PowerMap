package pt.isel.poo.powermap;

public class Utils {
    public static int randomNum(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
