package ch.idsia.scenarios;

import ch.idsia.ai.agents.Agent;
import ch.idsia.ai.agents.human.HumanKeyboardAgent;
import ch.idsia.maibe.tasks.BasicTask;
import ch.idsia.mario.environments.Environment;
import ch.idsia.mario.environments.MarioEnvironment;
import ch.idsia.tools.CmdLineOptions; /**
 * Created by IntelliJ IDEA.
 * User: julian
 * Date: May 5, 2009
 * Time: 12:46:43 PM
 */

/**
 * The <code>Play</code> class shows how simple is to run a MarioAI Benchmark.
 * It shows how to set up some parameters, create a task,
 * use the CmdLineParameters class to set up options from command line if any.
 * Defaults are used otherwise.
 *
 * @author  Julian Togelius, Sergey Karakovskiy
 * @version 1.0, May 5, 2009

 */

public class Play
{
    /**
     * <p>An entry point of the class.</p>
     *
     * @param args input parameters for customization of the benchmark.
     *
     * @see ch.idsia.scenarios.oldscenarios.MainRun
     * @see ch.idsia.tools.CmdLineOptions
     * @see ch.idsia.tools.EvaluationOptions
     *
     * @since   MarioAI-0.1
     */

    public static void main(String[] args)
    {
        final CmdLineOptions cmdLineOptions = new CmdLineOptions(args);
        final Environment environment = new MarioEnvironment();
        final Agent agent = new HumanKeyboardAgent();
        final BasicTask basicTask = new BasicTask(environment, agent);
        cmdLineOptions.setVisualization(true);
        basicTask.reset(cmdLineOptions);
//        basicTask.runOneEpisode();
        // run 1 episode with same options, each time giving output of Evaluation info.
        basicTask.runEpisodes(1, false);
        System.out.println("\nEvaluationInfo: \n" + environment.getEvaluationInfoAsString());
        System.exit(0);
    }
}
