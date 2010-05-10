package ch.idsia.mario.engine;

import ch.idsia.tools.GameViewer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public abstract class GlobalOptions
{
    public static final int primaryVerionUID = 0;
    public static final int minorVerionUID = 1;
    public static final int minorSubVerionID = 3;

    public static boolean Labels = false;
    public static boolean MarioAlwaysInCenter = false;
    public static Integer FPS = 24;
    public static int MaxFPS = 100;
    public static boolean PauseWorld = false;

    public static boolean VisualizationOn = true;
    public static boolean GameVeiwerOn = true;

//    private static MarioComponent marioComponent = null;
    private static GameViewer GameViewer = null;
    public static boolean TimerOn = true;

    //    public static Defaults defaults = new Defaults();
    public static boolean GameVeiwerContinuousUpdatesOn = false;
    public static boolean PowerRestoration;

    public static String MAIBeVersionStr = "0.1";
    private static MarioVisualComponent marioVisualComponent;
    public static final int VISUAL_COMPONENT_WIDTH = 320;
    public static final int VISUAL_COMPONENT_HEIGHT = 240;

    public static int getPrimaryVersionUID()
    {
        return primaryVerionUID;
    }

    public static int getMinorVersionUID()
    {
        return minorVerionUID;
    }

    public static int getMinorSubVerionID()
    {
        return minorSubVerionID;
    }

    public static String getVersionUID()
    {
        return " v-" + getPrimaryVersionUID() + "." + getMinorVersionUID() + "." + getMinorSubVerionID();
    }

//    public static void registerMarioComponent(MarioComponent mc)
//    {
//        marioComponent = mc;
//    }

    public static void registerMarioVisualComponent(MarioVisualComponent mc)
    {
        marioVisualComponent = mc;
    }

//    public static MarioComponent getMarioComponent()
//    {   return marioComponent;                       }
    

    public static void registerGameViewer(GameViewer gv)
    {
        GameViewer = gv;
    }

    public static void AdjustMarioComponentFPS()
    {
        if (marioVisualComponent != null)
            marioVisualComponent.adjustFPS();
    }

    public static void gameViewerTick()
    {
        if (GameViewer != null)
            GameViewer.tick();
//        else
//            LOGGER.println("GameViewer is not available. Request for dump ignored.", LOGGER.VERBOSE_MODE.ERROR);
    }

    public static String getDateTime(Long d)
    {
        DateFormat dateFormat = (d == null) ? new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:ms") :
                new SimpleDateFormat("HH:mm:ss:ms") ;
        if (d != null)
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = (d == null) ? new Date() : new Date(d);
        return dateFormat.format(date);
    }
}
