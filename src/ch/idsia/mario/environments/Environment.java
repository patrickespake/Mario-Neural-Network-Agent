package ch.idsia.mario.environments;

import ch.idsia.ai.agents.Agent;
import ch.idsia.tools.CmdLineOptions;
import ch.idsia.tools.EvaluationInfo;

/**
 * Created by IntelliJ IDEA.
 * User: Sergey Karakovskiy
 * Date: Mar 28, 2009
 * Time: 8:51:57 PM
 * Package: .Environments
 */

public interface Environment
{
    public static final int numberOfButtons = 5;
    public static final int numberOfObservationElements = 486 + 1;
    public static final int HalfObsWidth = 11;
    public static final int HalfObsHeight = 11;

    // always the same dimensionality: 22x22
    // always centered on the agent 

    // KILLS    

    // Chaning ZLevel during the game on-the-fly;
    // if your agent recieves too ambiguous observation, it might request for more precise one for the next step


    // ATAVIZMS for back compatibility! Strongly recommended to use new interface.

    @Deprecated
    public byte[][] getCompleteObservation();   // default: ZLevelScene = 1, ZLevelEnemies = 0
    @Deprecated
    public byte[][] getEnemiesObservation();    // default: ZLevelEnemies = 0
    @Deprecated
    public byte[][] getLevelSceneObservation(); // default: ZLevelScene = 1

    // NEW INTERFACE

    public void resetDefault();

    public void reset(int[] setUpOptions);

    public void tick();

    public float[] getMarioFloatPos();

    public int getMarioMode();

    public float[] getEnemiesFloatPos();

    public boolean isMarioOnGround();
    public boolean isMarioAbleToJump();
    public boolean isMarioCarrying();
    // Pilot (test) additions
    public boolean isMarioAbleToShoot();

    public byte[][] getMergedObservationZZ(int ZLevelScene, int ZLevelEnemies);
    public byte[][] getLevelSceneObservationZ(int ZLevelScene);
    public byte[][] getEnemiesObservationZ(int ZLevelEnemies);

    public int getKillsTotal();
    public int getKillsByFire();
    public int getKillsByStomp();
    public int getKillsByShell();

    int getMarioStatus();

    // FOR AmiCo

    public double[] getSerializedFullObservationZZ(int ZLevelScene, int ZLevelEnemies);
    /**
     * Serializes the LevelScene observation from 22x22 byte array to a 1x484 byte array
     * @param ZLevelScene
     * @return byte[] with sequenced elements of corresponding getLevelSceneObservationZ output
     */
    public int[] getSerializedLevelSceneObservationZ(int ZLevelScene);
    /**
     * Serializes the LevelScene observation from 22x22 byte array to a 1x484 byte array
     * @param ZLevelEnemies
     * @return byte[] with sequenced elements of corresponding <code>getLevelSceneObservationZ</code> output
     */
    public int[] getSerializedEnemiesObservationZ(int ZLevelEnemies);
    public int[] getSerializedMergedObservationZZ(int ZLevelScene, int ZLevelEnemies);

    public float[] getCreaturesFloatPos();

    /**
     * @return array filled with various data about Mario : {
     * getMarioStatus(),
     * getMarioMode(),
     * isMarioOnGround() ? 1 : 0,
     * isMarioAbleToJump() ? 1 : 0,
     * isMarioAbleToShoot() ? 1 : 0,
     * isMarioCarrying() ? 1 : 0,
     * getKillsTotal(),
     * getKillsByFire(),
     * getKillsByStomp(),
     * getKillsByShell(),
     * getTimeLimit(),
     * getTimeLeft
    }
     */
    public int[] getMarioState();

    void performAction(boolean[] action);

    boolean isLevelFinished();

    float [] getEvaluationInfoAsFloats();

    String getEvaluationInfoAsString();

    EvaluationInfo getEvaluationInfo();

    void reset(CmdLineOptions cmdLineOptions);

    void setAgent(Agent agent);
}
