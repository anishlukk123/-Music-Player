package model.locationstats;

public class locationstats {

	private String State;
	private String country;
	private int latestotal;
	private int difffromyesterday;

	public int getDifffromyesterday() {
		return difffromyesterday;
	}

	public void setDifffromyesterday(int difffromyesterday) {
		this.difffromyesterday = difffromyesterday;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getLatestotal() {
		return latestotal;
	}

	public void setLatestotal(int latestotal) {
		this.latestotal = latestotal;
	}

	@Override
	public String toString() {
		return "locationstats [State=" + State + ", country=" + country + ", latestotal=" + latestotal + "]";
	}

}
