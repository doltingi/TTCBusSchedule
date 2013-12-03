package seneca.slee201.ttcbusschedule.model;

public class RouteStop {

	private String tag;
	private String title;
	private Integer stopId;
	private double lon;
	private double lat;

	public RouteStop() {
	}
	
	public RouteStop(String tag, String title, Integer id, double lon, double lat) {
		this.tag = tag;
		this.title = title;
		this.stopId = id;
		this.lon = lon;
		this.lat = lat;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public Integer getStopId() {
		return stopId;
	}

	public void setStopId(Integer stopId) {
		this.stopId = stopId;
	}
	
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public double getlon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}	
}
