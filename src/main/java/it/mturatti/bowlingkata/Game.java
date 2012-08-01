/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mturatti.bowlingkata;

/**
 *
 * @author mturatti
 */
public class Game {

    private int[] rolls = new int[22];
    private int currentRoll = 0;

    public void roll(int pins) {
        rolls[currentRoll++] += pins;
    }

    public String rollsAsString() {
        StringBuilder bu = new StringBuilder();
        bu.append("[ ");
        for (int i = 0; i < rolls.length; i++) {
            bu.append(i).append(":").append(rolls[i]).append(" ");
        }
        bu.append("]");
        return bu.toString();
    }

    public int score() {
        System.out.println(rollsAsString());
        int score = 0;
        int rollIndex = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(rollIndex)) {
                score += 10 + strikeBonus(rollIndex);
                ++rollIndex;             
            } else if (isSpare(rollIndex)) {
                score += 10 + spareBonus(rollIndex);
                rollIndex += 2;               
            } else {
                score += sumOfRolls(rollIndex);
                rollIndex += 2;
            }
        }
        return score;
    }

    private int strikeBonus(int rollIndex) {
        return rolls[rollIndex + 1] + rolls[rollIndex + 2];
    }

    private int spareBonus(int rollIndex) {
        return rolls[rollIndex + 2];
    }

    private boolean isStrike(int rollIndex) {
        return rolls[rollIndex] == 10;
    }

    private boolean isSpare(int rollIndex) {
        return sumOfRolls(rollIndex) == 10;
    }

    private int sumOfRolls(int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex + 1];
    }
}
