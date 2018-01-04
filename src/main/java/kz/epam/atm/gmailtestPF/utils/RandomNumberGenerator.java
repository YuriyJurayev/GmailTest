package kz.epam.atm.gmailtestPF.utils;
import java.util.Random;
import static kz.epam.atm.gmailtestPF.property.GlobalConstants.GENERATOR_BOUND;

public class RandomNumberGenerator {

    private static int generateRandomInt(int bound){
        return new Random().nextInt(bound);
    }
    public static int getRandomInt(){
        return generateRandomInt(GENERATOR_BOUND);
    }
}
