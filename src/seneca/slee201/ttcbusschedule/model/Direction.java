package seneca.slee201.ttcbusschedule.model;

import java.util.List;
import java.util.ArrayList;

public class Direction {
	
	private String tag;
	private String title;
	private String name;
	private String branch;
	private List<RouteStop> stops;
	
	public Direction() {
		stops = new ArrayList<RouteStop>();
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public List<RouteStop> getAllStops() {
		return stops;
	}
	
	public RouteStop getStop(String tag) {
		for (RouteStop rs : stops) {
			if (rs.getTag().equals(tag)) {
				return rs;
			}
		}
		return null;
	}

	public void setStops(ArrayList<RouteStop> stops) {
		this.stops = stops;
	}
}
