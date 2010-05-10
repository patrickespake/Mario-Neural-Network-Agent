package ch.idsia.tools;

import ch.idsia.mario.engine.sprites.Mario;
import ch.idsia.scenarios.champ.MarioSystemOfValues;

import java.text.DecimalFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Sergey Karakovskiy
 * Date: Apr 12, 2009
 * Time: 12:44:51 AM
 * Package: .Tools
 */
public class EvaluationInfo
{
    private static final int MagicNumberUnDef = -42;

    public static final int numberOfElements = 12;

    public float marioStatus = MagicNumberUnDef;
    public float lengthOfLevelPassedPhys = MagicNumberUnDef;
    public int lengthOfLevelPassedCells = MagicNumberUnDef;
    public float timeSpentOnLevel = MagicNumberUnDef;
    public int timeLeft = MagicNumberUnDef;
    public int marioMode = MagicNumberUnDef;
    public int killsTotal = MagicNumberUnDef;
    public float numberOfCoinsGained = MagicNumberUnDef;
    public int numberOfHiddenCoinsGained = MagicNumberUnDef;
    public int killsByFire = MagicNumberUnDef;
    public int killsByShell = MagicNumberUnDef;
    public int killsByStomp = MagicNumberUnDef;

    private static final float[] retFloatArray = new float[EvaluationInfo.numberOfElements];
    private static final float[] zeros = new float[EvaluationInfo.numberOfElements];
    public String Memo = "";
    
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public EvaluationInfo()
    {
        System.arraycopy(EvaluationInfo.zeros, 0, retFloatArray, 0, EvaluationInfo.numberOfElements);
    }

    public float computeBasicFitness()
    {
        return lengthOfLevelPassedPhys - timeSpentOnLevel + numberOfCoinsGained + marioStatus*MarioSystemOfValues.win;
    }

    public float computeMultiObjectiveFitness()
    {
        return
                lengthOfLevelPassedPhys * MarioSystemOfValues.distance +
                marioStatus * MarioSystemOfValues.win +
                marioMode * MarioSystemOfValues.mode  +
                numberOfCoinsGained * MarioSystemOfValues.coins+
                killsTotal * MarioSystemOfValues.kills +
                killsByStomp * MarioSystemOfValues.killedByStomp +
                killsByFire * MarioSystemOfValues.killedByFire +
                killsByShell * MarioSystemOfValues.killedByShell +
                numberOfHiddenCoinsGained * MarioSystemOfValues.hiddenCoins +
                timeLeft * MarioSystemOfValues.timeLeft;
    }

    public double computeDistancePassed()
    {
        return lengthOfLevelPassedPhys;
    }

    public int computeKillsTotal()
    {
        return this.killsTotal;
    }

    //TODO: possible fitnesses adjustments: penalize for collisions with creatures and especially for  suicide. It's a sin.

    public float[] toFloatArray()
    {
        return retFloatArray;
    }

    public String toString()
    {
        String ret = "\nEvaluation Information. Statistics and Score:";
        ret += "\n                       Mario Status : " + ((marioStatus == Mario.STATUS_WIN) ? "WIN!" : "Loss...");
        ret += "\n                         Mario Mode : " + Mario.MODES[marioMode];
        ret += "\n               Passed (Cells, Phys) : " + df.format((double)lengthOfLevelPassedCells ) + ", " +
                                                            df.format(lengthOfLevelPassedPhys ) ;
        ret += "\n           Time Spent(marioseconds) : " + timeSpentOnLevel;
        ret += "\n            Time Left(marioseconds) : " + timeLeft;
        ret += "\n                       Coins Gained : " + numberOfCoinsGained;
        ret += "\n                 Hidden Items Found : " + numberOfHiddenCoinsGained;
        ret += "\n                        kills Total : " + killsTotal;
        ret += "\n                      kills By Fire : " + killsByFire;
        ret += "\n                     kills By Shell : " + killsByShell;
        ret += "\n                     kills By Stomp : " + killsByStomp;
        ret += "\n               multiObjectiveFitness : " + df.format(computeMultiObjectiveFitness());
        ret += ((Memo.equals("")) ? "" : "\nMemo: " + Memo);
        return ret;
    }

    public String toStringSingleLine()
    {
        String ret = "##";
        ret += " Status: " + ((marioStatus == Mario.STATUS_WIN) ? "WIN!" : "Loss");
        ret += "; Mode: " + Mario.MODES[marioMode];
        ret += "; Passed (Cells, Phys): " + df.format((double)lengthOfLevelPassedCells ) + ", " +
                                                            df.format(lengthOfLevelPassedPhys ) ;
        ret += "; Time Spent: " + timeSpentOnLevel;
        ret += "; Time Left: " + timeLeft;
        ret += "; Coins: " + numberOfCoinsGained;
        ret += "; kills: " + killsTotal;
        ret += "; By Fire: " + killsByFire;
        ret += "; By Shell: " + killsByShell;
        ret += "; By Stomp: " + killsByStomp;
        return ret;
    }

//    public int levelType = MagicNumberUnDef;
//    public float totalLengthOfLevelCells = MagicNumberUnDef;
//    public float totalLengthOfLevelPhys = MagicNumberUnDef;
//    public int totalTimeGiven = MagicNumberUnDef;
//    public int totalNumberOfCoins = MagicNumberUnDef;
    // Number Of collisions with creatures
    // if large
    // if fire


//    public String agentName = "undefinedAgentName";
//    public String agentType = "undefinedAgentType";
//    public int levelDifficulty = MagicNumberUnDef;
//    public int levelRandSeed = MagicNumberUnDef;


//    public EvaluationInfo(float[] evaluationInfoArray)
//    {
//        // Turn double[] into a plausible form!
//        assert (evaluationInfoArray.length == 11);
//
//        this.marioStatus = (int) evaluationInfoArray[0];
//        this.lengthOfLevelPassedCells = (int) evaluationInfoArray[1];
//        this.lengthOfLevelPassedPhys = evaluationInfoArray[2];
//        this.totalLengthOfLevelCells = (int) evaluationInfoArray[3];
//        this.totalLengthOfLevelPhys = evaluationInfoArray[4];
//        this.timeSpentOnLevel = (int) evaluationInfoArray[5];
//        this.timeLeft = (int) evaluationInfoArray[6];
//        this.totalTimeGiven = (int) evaluationInfoArray[7];
//        this.numberOfCoinsGained = (int) evaluationInfoArray[8];
//        this.numberOfHiddenCoinsGained = (int)evaluationInfoArray[9];
//        this.marioMode = (int) evaluationInfoArray[9];
//        this.killsTotal = (int) evaluationInfoArray[10];
//        this.killsByFire = (int) evaluationInfoArray[11];
//        this.killsByShell = (int) evaluationInfoArray[12];
//        this.killsByStomp = (int) evaluationInfoArray[13];
//
//    }

//    public String toString()
//    {
//        String ret = "\nStatistics. Score:";
//        ret += "\n                  Player/Agent type : " + agentType;
//        ret += "\n                  Player/Agent name : " + agentName;
//        ret += "\n                       Mario Status : " + ((marioStatus == Mario.STATUS_WIN) ? "Win!" : "Loss...");
////        ret += "\n                         Level Type : " + levelType;
////        ret += "\n                   Level Difficulty : " + levelDifficulty;
////        ret += "\n                    Level Rand Seed : " + levelRandSeed;
//        ret += "\nTotal Length of Level (Phys, Cells) : " + "(" + totalLengthOfLevelPhys + "," + totalLengthOfLevelCells + ")";
//        ret += "\n               Passed (Phys, Cells) : " +
//               df.format(lengthOfLevelPassedPhys / totalLengthOfLevelPhys *100) +
//               "% ( " + df.format(lengthOfLevelPassedPhys) + " of " + totalLengthOfLevelPhys + "), " +
//               df.format((double)lengthOfLevelPassedCells / totalLengthOfLevelCells *100) + "% ( " + lengthOfLevelPassedCells + " of " + totalLengthOfLevelCells + ")";
//        ret += "\n           Time Spent(marioseconds) : " + timeSpentOnLevel + " ( " + df.format((double)timeSpentOnLevel/totalTimeGiven*100) + "% )";
//        ret += "\n            Time Left(marioseconds) : " + timeLeft + " ( " + df.format((double)timeLeft/totalTimeGiven*100) + "% )";
//        ret += "\n                   Total time given : " + totalTimeGiven;
////        ret += "\nCoins Gained: " + numberOfCoinsGained/totalNumberOfCoins*100 + "%. (" + numberOfCoinsGained + " of " + totalNumberOfCoins + ")";
//        ret += "\n                       Coins Gained : " + numberOfCoinsGained;
//        ret += "\n               multiObjectiveFitness : " + df.format(computeMultiObjectiveFitness());
//        ret += ((Memo.equals("")) ? "" : "\nMemo: " + Memo);
//        return ret;
//    }
}
