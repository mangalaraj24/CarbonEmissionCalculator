package CarbonEmissionCalculator;

public class Activities {

	private int activity_id;
	private int user_id;
	private String activityType;
	private float distance;
	private float emission;

	public Activities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Activities(int activity_id, int user_id, String activityType, float distance, float emission) {
		super();
		this.activity_id = activity_id;
		this.user_id = user_id;
		this.activityType = activityType;
		this.distance = distance;
		this.emission = emission;
	}

	public int getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}
	
	

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getEmission() {
		return emission;
	}

	public void setEmission(float emission) {
		this.emission = emission;
	}

	@Override
	public String toString() {
		return "Activities [activity_id=" + activity_id + ", user_id=" + user_id + ", activityType=" + activityType
				+ ", distance=" + distance + ", emission=" + emission + "]";
	}

	

}
