package ch.idsia.tools.tcp;

import ch.idsia.ai.agents.Agent;
import ch.idsia.ai.agents.controllers.BasicAIAgent;
import ch.idsia.mario.environments.Environment;
import ch.idsia.tools.EvaluationInfo;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Sergey Karakovskiy
 * Date: Apr 30, 2009
 * Time: 9:43:27 PM
 * Package: ch.idsia.tools.Network
 */

public class ServerAgent extends BasicAIAgent implements Agent
{
    Server server = null;
    private int port;
    private TCP_MODE tcpMode = TCP_MODE.SIMPLE_TCP;

    public ServerAgent(int port, boolean enable)
    {
        super("ServerAgent");
        this.port = port;
        if (enable)
        {
            createServer(port);
        }
    }

    public ServerAgent(Server server)
    {
        super("ServerAgent");
        this.server = server;
        this.tcpMode = TCP_MODE.SIMPLE_TCP;
    }

    public String getName()
    {
        return this.name + ((server == null) ? "" : server.getClientName());
    }

    // A tiny bit of singletone-like concept. Server is created ones for each egent. Basically we are not going
    // To create more than one ServerAgent at a run, but this flexibility allows to add this feature with certain ease.
    private void createServer(int port) {
        this.server = new Server(port, Environment.numberOfObservationElements, Environment.numberOfButtons);
//        this.name += server.getClientName();
    }

    public boolean isAvailable()
    {
        return (server != null) && server.isClientConnected();
    }

    public boolean[] getAction()
    {
        return new boolean[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void reset()
    {
        action = new boolean[Environment.numberOfButtons];
        if (server == null)
            this.createServer(port);
    }

    private void sendRawObservation(Environment observation)
    {
//        byte[][] levelScene = observation.getLevelSceneObservation();
        // MERGED
        byte[][] mergedObs = observation.getCompleteObservation(/*1, 0*/);

        String tmpData = "O " +
                observation.isMarioAbleToJump() + " " + observation.isMarioOnGround();
        for (int x = 0; x < mergedObs.length; ++x)
        {
            for (int y = 0; y < mergedObs.length; ++y)
            {
                tmpData += " " + (mergedObs[x][y]);
            }
        }
        tmpData += " " + observation.getMarioFloatPos()[0]
                 + " " + observation.getMarioFloatPos()[1];
        
        float[] enemiesFloatPoses = observation.getEnemiesFloatPos();
        for (int i = 0; i < enemiesFloatPoses.length; ++i)
            tmpData += " " + enemiesFloatPoses[i];

        server.sendSafe(tmpData);
        // TODO: StateEncoderDecoder.Encode.Decode.  zip, gzip
    }

    private void sendObservation(Environment observation)
    {
        if (this.tcpMode == TCP_MODE.SIMPLE_TCP)
        {
            this.sendRawObservation(observation);
        }
    }

    public void integrateEvaluationInfo(EvaluationInfo evaluationInfo)
    {
        String fitnessStr = "FIT " +
                evaluationInfo.marioStatus + " " +
                evaluationInfo.computeDistancePassed() + " " +
                evaluationInfo.timeLeft + " " +
                evaluationInfo.marioMode + " " +
                evaluationInfo.numberOfCoinsGained + " ";
        server.sendSafe(fitnessStr);
    }

    private boolean[] receiveAction() throws IOException, NullPointerException
    {
        String data = server.recvSafe();
        if (data == null || data.startsWith("reset"))
            return null;
        boolean[] ret = new boolean[Environment.numberOfButtons];
//        String s = "[";
        for (int i = 0; i < Environment.numberOfButtons; ++i)
        {
            ret[i] = (data.charAt(i) == '1');
//            s += data.charAt(i);
        }
//        s += "]";

//        System.out.println("ServerAgent: action received :" + s);
        return ret;
    }

    public boolean[] getAction(Environment observation)
    {
        try
        {
//            System.out.println("ServerAgent: sending observation...");
//            sendRawObservation(observation);
            sendObservation(observation);
            action = receiveAction();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("I/O Communication Error");
            reset();
        }
        return action;
    }

    public AGENT_TYPE getType()
    {
        return Agent.AGENT_TYPE.TCP_SERVER;
    }
}
