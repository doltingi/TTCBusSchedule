package seneca.slee201.ttcbusschedule.model;

public class Route {
	private String name;
	private Integer id;

	public Route(String name, Integer id) {
		this.name = name;
		this.id = id;
	}

	public Route() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRouteId() {
		return id;
	}

	public void setRouteId(Integer id) {
		this.id = id;
	}

}
