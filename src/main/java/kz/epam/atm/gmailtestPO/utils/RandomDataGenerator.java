package kz.epam.atm.gmailtestPO.utils;

public class RandomDataGenerator {

    public static int generateRandomInt(){
        java.util.Random random = new java.util.Random();
        return random.nextInt(1000);
    }
}
