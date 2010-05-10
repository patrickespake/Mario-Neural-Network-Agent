package ch.idsia.scenarios.test;

/**
 * Created by IntelliJ IDEA.
 * User: julian
 * Date: Aug 6, 2009
 * Time: 8:21:23 PM
 */
public class PaperEvolveBatch {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            PaperEvolve.main(new String[]{"ch.idsia.controllers.agents.controllers.SmallMLPAgent"});
            PaperEvolve.main(new String[]{"ch.idsia.controllers.agents.controllers.SmallSRNAgent"});
            PaperEvolve.main(new String[]{"ch.idsia.controllers.agents.controllers.MediumMLPAgent"});
            PaperEvolve.main(new String[]{"ch.idsia.controllers.agents.controllers.MediumSRNAgent"});
            PaperEvolve.main(new String[]{"ch.idsia.controllers.agents.controllers.LargeMLPAgent"});
            PaperEvolve.main(new String[]{"ch.idsia.controllers.agents.controllers.LargeSRNAgent"});
        }
    }
}
