package apw.kohonenSom.neighborhoods;

public class SOMRectangularNeighbourhood implements SOMNeighbourhoodFunction {
	private double maxR;
	private double decrease;
	
	public SOMRectangularNeighbourhood(double maxR, double decrease)
	{	
		this.maxR = maxR;
		this.decrease = decrease;
	}
	
	@Override
	public double getNeighbourhood(double dist, double time) {
		int n = 0;
		
		if(dist <= (decrease*time*maxR))
			n=1;
		
		return n;
	}

	@Override
	public String getNeighbourhoodName() {
		return "Rectangular neighbourhood";
	}

	@Override
	public int getNeighbourhoodNum() {
		return 0;
	}

}
