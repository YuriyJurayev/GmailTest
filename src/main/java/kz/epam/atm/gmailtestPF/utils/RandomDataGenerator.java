package kz.epam.atm.gmailtestPF.utils;

public class RandomDataGenerator {

    private static final int BOUND = 1000;

    public static int generateRandomInt(){
        java.util.Random random = new java.util.Random();
        return random.nextInt(BOUND);
    }
}
