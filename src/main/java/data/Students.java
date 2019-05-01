package data;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "students")
public class Students {

	private List<Student> students;

	public Students() {
	}

	public Students(List<Student> students) {
		this.students = students;
	}

	@XmlElement(name = "student")
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

}
