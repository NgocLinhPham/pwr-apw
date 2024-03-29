package apw.kohonenSom;

import apw.core.Samples;
import apw.kohonenSom.distances.*;
import apw.kohonenSom.learningFactors.*;
import apw.kohonenSom.logic.*;
import apw.kohonenSom.topology.*;
import apw.kohonenSom.trainingMethods.*;
import apw.kohonenSom.winnerSelection.*;
import apw.kohonenSom.neighborhoods.*;
import apw.kohonenSom.patterns.*;
import apw.kohonenSom.timeFactors.*;
import apw.kohonenSom.visualization.SOMSimpleVisualizationHex;
import apw.kohonenSom.visualization.SOMSimpleVisualizationRect;
import apw.kohonenSom.visualization.SOMVisualization;
import apw.kohonenSom.weightsInitialization.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Christopher Wadowski
 */
public class KohonenNetwork {
	private SOMKohonenMap map;

    //------------------------------------------------------
    private int TMax;
    private int XMax;
    private int YMax;

	private SOMDistanceFunction distanceType;
    private SOMWinnerSelection selector;
	private SOMWeightsInitializer wInit;
    private SOMTrainingMethod trainer;
    private SOMTopology structure;

    //------------------------------------------------------
    private SOMSamplesLoader patterns;
    private SOMVisualization visualizator;
	
	//------------------------------------------------------
    public KohonenNetwork(int TMax, int XMax, int YMax,
             SOMDistanceFunction distanceType, SOMWinnerSelection selector,
             SOMWeightsInitializer wInit, SOMTrainingMethod trainer,
             SOMTopology structure, SOMSamplesLoader patterns,
             SOMVisualization visualizator){
        
        this.TMax = TMax;
        this.XMax = XMax;
        this.YMax = YMax;
        this.distanceType = distanceType;
        this.selector = selector;
        this.wInit = wInit;
        this.trainer = trainer;
        this.patterns = patterns;
        this.visualizator = visualizator;
        this.structure = structure;
    }

    //------------------------------------------------------
    public void generateMap(){
       this.map = new SOMKohonenMap(TMax, XMax, YMax, distanceType,
               selector, wInit, trainer, structure);
    }

    public void trainMap(){
        this.map.trainIterative(patterns);
    }

    public BufferedImage getFeatMap(int feat){
        return visualizator.generateFeatMap(map, feat, patterns);
    }

    public BufferedImage getVoronoiMap(){
        return visualizator.generateVoronoiMap(map, patterns);
    }

    public BufferedImage getPatternsDensityMap(){
        return visualizator.generatePatternDensityMap(map, patterns);
    }

    public BufferedImage getClusterMap(){
        return visualizator.generateClusterMap(map, patterns);
    }

    public BufferedImage getUMap(){
        return visualizator.generateUMap(map, patterns);
    }

    //------------------------------------------------------
     public static SOMDistanceFunction initDistanceEuclidean(){
         return new SOMEuclideanDistance();
     }

     public static SOMDistanceFunction initDistanceL(){
         return new SOMLDistance();
     }

     public static SOMDistanceFunction initDistanceManhattan(){
         return new SOMManhattanDistance();
     }

     public static SOMDistanceFunction initDistanceScalar(){
         return new SOMScalarDistance();
     }

     public static SOMWinnerSelection initWinnerSelectorCount(
             int x, int y, int maxTime){
         return new SOMCountSelection(x, y, maxTime);
     }

     public static SOMWinnerSelection initWinnerSelectorConscience(int x, int y,
             double pMin, int maxTime){
         return new SOMConscienceSelection(x,y, pMin, maxTime);
     }

     public static SOMWinnerSelection initWinnerSelector(){
         return new SOMWinnerSelection();
     }

     public static SOMWeightsInitializer initWeightsInitializerRandom(){
        return new SOMRandomWeightsInitializer();
     }

     public static SOMTrainingMethod initTrainingMethodWTM(
             SOMLearningFactor eta, SOMNeighbourhoodFunction neighType,
			 SOMTopology nDist){
        return new SOMWTMMethod(eta, neighType, nDist);
     }

     public static SOMTrainingMethod initTrainingMethodWTA(SOMLearningFactor eta){
        return new SOMWTAMethod(eta);
     }

     public static SOMSamplesLoader initLoader(Samples samples, int numMode, 
             int nomMode, boolean normalizeVectors){
		return new SOMSamplesLoader(
                samples, numMode, nomMode, normalizeVectors);
     }

     public static SOMLearningFactor initLearningFactorExponential(
             double etaMax, double C, double etaMin){
        return new SOMExponentialDecrease(etaMax, C, etaMin);
     }

     public static SOMLearningFactor initLearningFactorHyperbolic(
             double etaMax, double C, double etaMin){
        return new SOMHyperbolicDecrease(etaMax, C, etaMin);
     }

     public static SOMLearningFactor initLearningFactorLinear(
             double etaMax, double TMax, double etaMin){
        return new SOMLinearDecrease(etaMax, TMax, etaMin);
     }

     public static SOMLearningFactor initLearningFactorConstant(
             double eta){
        return new SOMConstantEta(eta);
     }

     public static SOMNeighbourhoodFunction initNeighFuncGaussian(
             SOMTimeFactor timeType, double maxR){
        return new SOMGaussianNeighbourhood(timeType, maxR);
     }

     public static SOMNeighbourhoodFunction initNeighFuncLinear(
             SOMTimeFactor timeType, double maxR){
        return new SOMLinearNeighbourhood(timeType, maxR);
     }

     public static SOMNeighbourhoodFunction initNeighFuncRectangular(
             double maxR, double decrease){
        return new SOMRectangularNeighbourhood(maxR, decrease);
     }

     public static SOMTopology initHexTopology(){
        return new SOMHexagonalNetwork();
     }

     public static SOMTopology initRectTopology(){
        return new SOMRectangonalNetwork();
     }

     public static SOMTimeFactor initTimeFactorExpotential(
             double C, double minModif){
         return new SOMExpotentialTimeFactor(C, minModif);
     }

     public static SOMTimeFactor initTimeFactorHyperbolic(
             double C, double minModif){
         return new SOMHyperbolicTimeFactor(C, minModif);
     }

     public static SOMTimeFactor initTimeFactorLinear(
             int tMax, double minModif){
         return new SOMLinearTimeFactor(tMax, minModif);
     }

     public static SOMTimeFactor initTimeFactorConstant(double modif){
         return new SOMConstantModifier(modif);
     }

     public static SOMVisualization initHexVisualization(){
         return new SOMSimpleVisualizationHex();
     }

     public static SOMVisualization initRectVisualization(){
         return new SOMSimpleVisualizationRect();
     }

    //------------------------------------------------------
    public void setTMax(int TMax){
        this.TMax = TMax;
    }

    public void setXMax(int XMax){
        this.XMax = XMax;
    }

    public void setYMax(int YMax){
        this.YMax = YMax;
    }

    public void setTrainer(SOMTrainingMethod trainer){
        this.trainer = trainer;
    }

    public void setSelector(SOMWinnerSelection selector){
        this.selector = selector;
    }

    public void setWeightsInitializer(SOMWeightsInitializer wInit){
        this.wInit = wInit;
    }

    public void setDistanceType(SOMDistanceFunction distanceType){
        this.distanceType = distanceType;
    }

    public void setLoader(SOMSamplesLoader patterns){
        this.patterns = patterns;
    }

    public void setVisualizator(SOMVisualization visualizator){
        this.visualizator = visualizator;
    }

    public void setStructure(SOMTopology structure) {
        this.structure = structure;
    }

    //------------------------------------------------------
    public int getTMax(){
        return this.TMax;
    }

    public int getXMax(){
        return this.XMax;
    }

    public int getYMax(){
        return this.YMax;
    }

    public SOMTrainingMethod getTrainer(){
        return this.trainer;
    }

    public SOMWinnerSelection getSelector(){
        return this.selector;
    }

    public SOMWeightsInitializer getWeightsInitializer(){
        return this.wInit;
    }

    public SOMDistanceFunction getDistanceType(){
        return this.distanceType;
    }

    public SOMSamplesLoader getLoader(){
        return this.patterns;
    }

    public SOMKohonenMap getMap(){
        return this.map;
    }

    public SOMVisualization getVisualizator(){
        return this.visualizator;
    }

    private SOMTopology getStructure() {
        return this.structure;
    }
}
