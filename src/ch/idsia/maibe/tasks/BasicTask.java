package ch.idsia.maibe.tasks;

import ch.idsia.ai.agents.Agent;
import ch.idsia.mario.environments.Environment;
import ch.idsia.tools.CmdLineOptions;

import java.util.Random;

/**
 * Created by IntelliJ IDEA. User: Sergey Karakovskiy, sergey at idsia dot ch Date: Mar 14, 2010 Time: 4:47:33 PM
 * Package: ch.idsia.maibe.tasks
 */

public class BasicTask
{
    private Environment environment;
    private Agent agent;
    private CmdLineOptions options;
    private long COMPUTATIONAL_TIME_BOUND = 42; // stands for  FPS 24, prescribed FPS.

    public BasicTask(Environment environment, Agent agent)
    {
        this.setEnvironment(environment);
        this.setAgent(agent);
    }

    /**
     *
     * @return whether controller is disqualified or not
     */

    final Random r = new Random();

    public boolean runOneEpisode()
    {
        boolean tormoz = r.nextInt() < 10;
        while (!environment.isLevelFinished())
        {
            environment.tick();
//            start timer
            long tm = System.currentTimeMillis();
            agent.integrateObservation(environment);
//            try
//            {
//               if (tormoz)
//                    Thread.sleep(41);
//            } catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//            finish timer and check
            boolean[] action = agent.getAction();
//            System.out.println("System.currentTimeMillis() - tm > COMPUTATIONAL_TIME_BOUND = " + (System.currentTimeMillis() - tm ));
            if (System.currentTimeMillis() - tm > COMPUTATIONAL_TIME_BOUND)   
            {
//                # controller disqualified on this level
                System.out.println("Agent is disqualified on this level");
                // return false;
            }
            environment.performAction(action);
        }
        return true;
    }

    public void runEpisodes(int amount, boolean verbose)
    {
        for (int i = 0; i < amount; ++i)
        {
            this.reset(options);
            this.runOneEpisode();
            if (verbose)
            {
                System.out.println(environment.getEvaluationInfoAsString());
            }
        }
    }

    public void setEnvironment(Environment environment)
    {
        this.environment = environment;
    }

    public void setAgent(Agent agent)
    {
        this.agent = agent;
        environment.setAgent(agent);
    }

    public void reset(CmdLineOptions cmdLineOptions)
    {
        options = cmdLineOptions;
        environment.reset(cmdLineOptions);
        agent.reset();
    }
}
