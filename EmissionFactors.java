package CarbonEmissionCalculator;

public class EmissionFactors {

	private String activityType;
	private float factorPerUnit;

	public EmissionFactors() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmissionFactors(String activityType, float factorPerUnit) {
		super();
		this.activityType = activityType;
		this.factorPerUnit = factorPerUnit;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public float getFactorPerUnit() {
		return factorPerUnit;
	}

	public void setFactorPerUnit(float factorPerUnit) {
		this.factorPerUnit = factorPerUnit;
	}

	@Override
	public String toString() {
		return "EmissionFactors [activityType=" + activityType + ", factorPerUnit=" + factorPerUnit + "]";
	}

}
