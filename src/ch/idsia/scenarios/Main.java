package ch.idsia.scenarios;

import ch.idsia.ai.agents.Agent;
import ch.idsia.ai.agents.controllers.NeuralNetworkAgent;
import ch.idsia.maibe.tasks.BasicTask;
import ch.idsia.mario.environments.Environment;
import ch.idsia.mario.environments.MarioEnvironment;
import ch.idsia.tools.CmdLineOptions;

/**
 * Created by IntelliJ IDEA. User: Sergey Karakovskiy, sergey at idsia dot ch Date: Mar 17, 2010 Time: 8:28:00 AM
 * Package: ch.idsia.scenarios
 */
public class Main
{
    public static void main(String[] args)
    {
    	// final String argsString = "-vis on";
    	// args = argsString.split("\\s");
        final CmdLineOptions cmdLineOptions = new CmdLineOptions(args);
        final Environment environment = new MarioEnvironment();
        // final Agent agent = new ForwardAgent();
        // final Agent agent = cmdLineOptions.getAgent();
        final Agent agent = new NeuralNetworkAgent();
        // final Agent a = AgentsPool.load("ch.idsia.controllers.agents.controllers.ForwardJumpingAgent");
        final BasicTask basicTask = new BasicTask(environment, agent);
        basicTask.reset(cmdLineOptions);
        basicTask.runOneEpisode();

        System.out.println("cmdLineOptions.getLevelLength() = " + cmdLineOptions.getLevelLength());
        System.out.println(environment.getEvaluationInfoAsString());
        System.exit(0);
    }
}
