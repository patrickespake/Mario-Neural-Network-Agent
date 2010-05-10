package ch.idsia.ai.agents.human;

import ch.idsia.ai.agents.Agent;
import ch.idsia.mario.engine.sprites.Mario;
import ch.idsia.mario.environments.Environment;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sergey Karakovskiy
 * Date: Mar 29, 2009
 * Time: 12:19:49 AM
 * Package: ch.idsia.controllers.agents.controllers;
 */
public class HumanKeyboardAgent extends KeyAdapter implements Agent
{
    List<boolean[]> history = new ArrayList<boolean[]>();
    private boolean[] Action = null;
    private String Name = "HumanKeyboardAgent";
    protected float[] marioFloatPos = null;
	private float lastX = 0;
	private int i = 0;

    public HumanKeyboardAgent()
    {
        this.reset();
//        RegisterableAgent.registerAgent(this);
    }

    public void integrateObservation(int[] serializedLevelSceneObservationZ, int[] serializedEnemiesObservationZ, float[] marioFloatPos, float[] enemiesFloatPos, int[] marioState)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    	this.marioFloatPos = marioFloatPos;
    }

    public boolean[] getAction()
    {
    	/*if (lastX != this.marioFloatPos[0]) {	
    		if (Action[Mario.KEY_JUMP]) {
    			System.out.println("treinamento[" + this.i + "] = new Ponto(" + this.marioFloatPos[0] + ", " + this.marioFloatPos[1] + ", 1);");
    		} else {
    			System.out.println("treinamento[" + this.i + "] = new Ponto(" + this.marioFloatPos[0] + ", " + this.marioFloatPos[1] + ", 0);");
    		}
    		
    		lastX = this.marioFloatPos[0];
    		this.i++;
    	}*/
    	
    	return Action;
    }

    public void integrateObservation(Environment environment)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    	this.marioFloatPos = environment.getMarioFloatPos();
    }

    public void reset()
    {
        // Just check you keyboard. Especially arrow buttons and 'A' and 'S'!
        Action = new boolean[Environment.numberOfButtons];
    }

    public boolean[] getAction(Environment observation)
    {
        float[] enemiesPos = observation.getEnemiesFloatPos();
        return Action;
    }

    public AGENT_TYPE getType() {        return AGENT_TYPE.HUMAN;    }

    public String getName() {   return Name; }

    public void setName(String name) {        Name = name;    }


    public void keyPressed (KeyEvent e)
    {
        toggleKey(e.getKeyCode(), true);
    }

    public void keyReleased (KeyEvent e)
    {
        toggleKey(e.getKeyCode(), false);
    }


    private void toggleKey(int keyCode, boolean isPressed)
    {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                Action[Mario.KEY_LEFT] = isPressed;
                break;
            case KeyEvent.VK_RIGHT:
                Action[Mario.KEY_RIGHT] = isPressed;
                break;
            case KeyEvent.VK_DOWN:
                Action[Mario.KEY_DOWN] = isPressed;
                break;
            case KeyEvent.VK_S:
                Action[Mario.KEY_JUMP] = isPressed;
                break;
            case KeyEvent.VK_A:
                Action[Mario.KEY_SPEED] = isPressed;
                break;
        }
    }

   public List<boolean[]> getHistory () {
       return history;
   }
}
