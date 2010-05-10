package ch.idsia.maibe.tasks;

import ch.idsia.ai.agents.Agent;
import ch.idsia.tools.EvaluationOptions;

/**
 * Created by IntelliJ IDEA.
 * User: Sergey Karakovskiy
 * Date: Apr 8, 2009
 * Time: 11:20:41 AM
 * Package: ch.idsia.maibe.tasks
 */

public interface Task
{
    public double[] evaluate (Agent controller);

    public void setOptions (EvaluationOptions options);

    public EvaluationOptions getOptions ();

    void doEpisodes(int amount);

    boolean isFinished();

    void reset();
}
