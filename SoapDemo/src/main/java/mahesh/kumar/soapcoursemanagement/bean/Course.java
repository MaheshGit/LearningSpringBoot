package mahesh.kumar.soapcoursemanagement.bean;

public class Course {
	private int id;
	private String description;
	private String name;

	public Course(int id, String description, String name) {
		super();
		this.id = id;
		this.description = description;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", description=" + description + ", name=" + name + "]";
	}

}
