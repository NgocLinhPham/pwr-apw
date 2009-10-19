package apw.kohonenSom;

import apw.core.Samples;
import apw.core.loader.ARFFLoader;
import apw.kohonenSom.distances.SOMDistanceFunction;
import apw.kohonenSom.learningFactors.SOMLearningFactor;
import apw.kohonenSom.logic.topology.SOMTopology;
import apw.kohonenSom.logic.trainingMethods.SOMTrainingMethod;
import apw.kohonenSom.logic.winnerSelection.SOMWinnerSelection;
import apw.kohonenSom.neighborhoods.SOMNeighbourhoodFunction;
import apw.kohonenSom.patterns.SOMSamplesLoader;
import apw.kohonenSom.timeFactors.SOMTimeFactor;
import apw.kohonenSom.visualization.SOMVisualization;
import apw.kohonenSom.weightsInitialization.SOMWeightsInitializer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import javax.imageio.ImageIO;

/**
 *
 * @author Christopher Wadowski
 */
public class KohonenNetworkTest {
    public static void main(String args[]) throws ParseException, IOException{

        String segments = "data/segment-challenge.arff";
        String iris = "data/iris.arff";
        String wine = "data/wine.arff";

        int TMax = 100;
        int XMax = 10;
        int YMax = 10;

        int maxR = 4;

        double minW;
        double maxW;

        double etaMax = 0.8;

        double consciencePotential = 0.75;

        boolean normalize = false;

        KohonenNetwork net;
        Samples samples =  new ARFFLoader(
                new File(iris)).getSamples();

        int numMisVal = SOMSamplesLoader.NUM_AVR_MISSING_VAL_ARITHM;
        int nomMisVal = SOMSamplesLoader.NOM_MFREQ_MISSING_VAL;

        SOMSamplesLoader patterns =
                KohonenNetwork.initLoader(
                    samples ,numMisVal, nomMisVal, normalize);

        minW = patterns.getMinNumValue();
        maxW = patterns.getMaxNumValue();

        int pattNum = samples.size();
        int timeMax = TMax*pattNum;

        int maxTimeSelMet = timeMax/10;

        SOMLearningFactor etaExp =
                KohonenNetwork.initLearningFactorExponential(etaMax, 1);
        SOMLearningFactor etaLin =
                KohonenNetwork.initLearningFactorLinear(etaMax, timeMax);
        SOMLearningFactor etaHyperb =
                KohonenNetwork.initLearningFactorHyperbolic(etaMax, 1);

        SOMTimeFactor timeTypeLin =
                KohonenNetwork.initTimeFactorExpotential(timeMax);

        SOMTimeFactor timeTypeHyper =
                KohonenNetwork.initTimeFactorHyperbolic(0.5);

        SOMTimeFactor timeTypeExpo =
                KohonenNetwork.initTimeFactorExpotential(0.5);

        SOMNeighbourhoodFunction neighTypeGaus =
                KohonenNetwork.initNeighFuncGaussian(timeTypeLin, maxR);

        SOMNeighbourhoodFunction neighTypeRect =
                KohonenNetwork.initNeighFuncRectangular(maxR, 0.2);

        SOMNeighbourhoodFunction neighTypeLin =
                KohonenNetwork.initNeighFuncLinear(timeTypeLin, maxR);

        SOMTopology hexTopology =
                KohonenNetwork.initHexTopology();

        int inputs = patterns.getNumAttrNumber();    

        SOMDistanceFunction distanceEucl =
                KohonenNetwork.initDistanceEuclidean();

        SOMDistanceFunction distanceScal =
                KohonenNetwork.initDistanceScalar();

        SOMDistanceFunction distanceL =
                KohonenNetwork.initDistanceL();

        SOMDistanceFunction distanceManh =
                KohonenNetwork.initDistanceManhattan();

        SOMWinnerSelection selectorConsc =
                KohonenNetwork.initWinnerSelectorConscience(
                XMax,YMax,consciencePotential,maxTimeSelMet);

        SOMWinnerSelection selectorCount =
                KohonenNetwork.initWinnerSelectorCount(XMax, YMax, timeMax);

        SOMWeightsInitializer wInit =
                KohonenNetwork.initWeightsInitializerRandom(
                    XMax, YMax, inputs, minW, maxW);

        SOMTrainingMethod trainerWTM =
                KohonenNetwork.initTrainingMethodWTM(
                    etaLin, neighTypeGaus, hexTopology);

        SOMTrainingMethod trainerWTA =
                KohonenNetwork.initTrainingMethodWTA(etaHyperb);

        SOMVisualization hexVisualizator=
                KohonenNetwork.initHexVisualization();

        net = new KohonenNetwork(
                TMax, XMax, YMax, distanceEucl, null, wInit, trainerWTM,
                hexTopology, patterns, hexVisualizator);

        System.out.println("start;");

        for(int i=0; i<1; i++)
        {
            System.out.println("test: "+(i+1));
            net.generateMap();
            net.trainMap();
        }

        System.out.println("stop;");

        BufferedImage umap = net.getUMap();
        BufferedImage pdmap = net.getPatternsDensityMap();
        BufferedImage clustmap = net.getClusterMap();

        ImageIO.write(umap, "PNG", new File ("Visualizations/testUMap.png"));  
        ImageIO.write(pdmap, "PNG", new File ("Visualizations/testPDMap.png"));
        ImageIO.write(clustmap, "PNG", new File ("Visualizations/testClustMap.png"));
    }
}
