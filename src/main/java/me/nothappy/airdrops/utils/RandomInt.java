package me.nothappy.airdrops.utils;

public class RandomInt {

    public static int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
