package kz.epam.atm.gmailtestPF.utils;
import java.util.Random;

public class RandomNumberGenerator {

    private static final int BOUND = 1000;

    public static int generateRandomInt(int bound){
        return new Random().nextInt(bound);
    }
    public static int getRandomInt(){
        return generateRandomInt(BOUND);
    }
}
