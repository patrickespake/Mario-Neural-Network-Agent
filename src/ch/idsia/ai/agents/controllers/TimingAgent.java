package ch.idsia.ai.agents.controllers;

import ch.idsia.ai.agents.Agent;
import ch.idsia.mario.environments.Environment;

/**
 * Created by IntelliJ IDEA.
 * User: julian
 * Date: Aug 10, 2009
 * Time: 6:41:42 PM
 */
public class TimingAgent extends BasicAIAgent implements Agent {

    private Agent agent;
    private long timeTaken = 0;
    private int evaluations = 0;

    public TimingAgent (Agent agent)
    {
        super("TimingAgent");
        this.agent = agent;
    }

    public void integrateObservation(int[] serializedLevelSceneObservationZ, int[] serializedEnemiesObservationZ, float[] marioFloatPos, float[] enemiesFloatPos, int[] marioState)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean[] getAction()
    {
        return new boolean[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void reset() {
        agent.reset ();
    }

    public boolean[] getAction(Environment observation) {
        long start = System.currentTimeMillis();
        boolean[] action = agent.getAction (observation);
        timeTaken += (System.currentTimeMillis() - start);
        evaluations++;
        return action;
    }

    public AGENT_TYPE getType() {
        return agent.getType ();
    }

    public String getName() {
        return agent.getName ();
    }

    public void setName(String name) {
        agent.setName (name);
    }

    public double averageTimeTaken () {
        double average = ((double) timeTaken) / evaluations;
        timeTaken = 0;
        evaluations = 0;
        return average;
    }
}
