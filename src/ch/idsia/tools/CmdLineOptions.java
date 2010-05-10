package ch.idsia.tools;

import ch.idsia.mario.engine.GlobalOptions;

import java.util.Map;


/**
 * Created by IntelliJ IDEA.
 * User: Sergey Karakovskiy
 * Date: Apr 25, 2009
 * Time: 9:05:20 AM
 * Package: ch.idsia.tools
 */

/**
 * The <code>CmdLineOptions</code> class handles the commandline options received from actual
 * command line. It sets up parameters from command line if there are any.
 * Defaults are used otherwise.
 *
 * @author  Sergey Karakovskiy
 * @version 1.0, Apr 25, 2009
 *
 * @see ch.idsia.utils.ParameterContainer
 * @see ch.idsia.tools.EvaluationOptions
 *
 * @since   MarioAI0.1
 */

public class CmdLineOptions extends EvaluationOptions
{
    // TODO: SK Move default options to xml, properties, beans, whatever..
    public CmdLineOptions(String[] args)
    {
        super();
        this.setArgs(args);
    }

    public void setArgs(String[] args)
    {
        if (args.length > 0 && !args[0].startsWith("-") /*starts with a path to agent then*/)
        {
            this.setAgent(args[0]);

            String[] shiftedargs = new String[args.length - 1];
            System.arraycopy(args, 1, shiftedargs, 0, args.length - 1);
            this.setUpOptions(shiftedargs);
        }
        else
            this.setUpOptions(args);

        if (isEcho())
        {
            System.out.println("\nOptions have been set to:");
            for (Map.Entry<String,String> el : optionsHashMap.entrySet())
                System.out.println(el.getKey() + ": " + el.getValue());
        }
        GlobalOptions.GameVeiwerContinuousUpdatesOn = isGameViewerContinuousUpdates();
    }

    public Boolean isToolsConfigurator() {
        return b(getParameterValue("-tc"));      }

    public Boolean isGameViewer() {
        return b(getParameterValue("-gv"));      }

    public Boolean isGameViewerContinuousUpdates() {
        return b(getParameterValue("-gvc"));      }

    public Boolean isEcho() {
        return b(getParameterValue("-echo"));      }

    public String getPyAmiCoModuleName()
    {
        return getParameterValue("-pym");
    }

    public int[] toIntArray()
    {
//!!!        "-ag",    ?????? ??? ???????? ??????????? %,5^$#<>%
// !!!       "-amico",
//!!!        "-echo",
// !!!       "-ewf",
// !!!       "-gv",
//        "-gvc",
//        "-i",
//        "-ld",
//        "-ll",
//        "-ls",
//        "-lt",
//!!!<<<        "-m",
//        "-mm",
//        "-maxFPS",
//                    "-not",
//                    "-port",
//        "-pr",
//        "-pw",
//!!!        "-pym",
//                    "-server",
//                    "-ssiw",
//        "-t",
//        "-tc",
//        "-tl",
//        "-vaot",
//        "-vis",
//        "-vlx",
//        "-vly",
//        "-ze",
//        "-zs"
        return new int[]
                {
                        this.isGameViewer() ? 1 : 0,        /*0*/
                        this.isMarioInvulnerable() ? 1 : 0, /*1*/
                        this.getLevelDifficulty(),          /*2*/
                        this.getLevelLength(),              /*3*/
                        this.getLevelRandSeed(),            /*4*/
                        this.getLevelType(),                /*5*/
                        this.getMarioMode(),                /*6*/
                        this.getFPS(),                      /*7*/
                        this.isPowerRestoration() ? 1 : 0,  /*8*/
                        this.isPauseWorld() ? 1 : 0,        /*9*/
                        this.isTimer() ? 1 : 0,             /*10*/
                        // TODO:SK remove rudundancy (-1 -- no time limit)
                        this.isToolsConfigurator() ? 1 : 0, /*11*/
                        this.getTimeLimit(),                /*12*/
                        this.isViewAlwaysOnTop() ? 1 : 0,   /*13*/
                        this.isVisualization() ? 1 : 0,     /*14*/
                        this.getViewLocation().x,           /*15*/
                        this.getViewLocation().y,           /*16*/
                        this.getZLevelEnemies(),            /*17*/
                        this.getZLevelScene()               /*18*/
                }; 
    }
}
