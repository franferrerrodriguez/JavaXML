package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.jxpath.JXPathContext;

import data.Student;
import data.Students;

public class App {

	public static void main(String[] args) throws JAXBException {

		Students students = new Students();
		Student student1 = new Student();
		List<Student> listStudents = new ArrayList<Student>();
		student1.setFirstName("Francisco");
		Student student2 = new Student();
		student2.setFirstName("Alejandro");
		listStudents.add(student1);
		listStudents.add(student2);
		students.setStudents(listStudents);

		// List Object + Iterator Filter
		JXPathContext context1 = JXPathContext.newContext(students);

		String ejemploNombre1 = "Francisco";
		context1.getVariables().declareVariable("filtroNombre", ejemploNombre1);

		bucle: for (Iterator<?> iter = context1.iterate("/students[firstName = $filtroNombre]"); iter.hasNext();) {
			Student student = (Student) iter.next();
			System.out.println(student.getFirstName());
		}

		// Xml To Java Pojo + Iterator Filter
		JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		File xmlFile = new File("./src/main/java/resources/students.xml");

		Students xmlStudents = (Students) jaxbUnmarshaller.unmarshal(xmlFile);
		// List<Student> students = studentList.getStudents();

		JXPathContext context2 = JXPathContext.newContext(xmlStudents);

		String ejemploNombre2 = "Amanda";
		context2.getVariables().declareVariable("filtroNombre", ejemploNombre2);

		bucle: for (Iterator<?> iter = context2.iterate("/students[firstName = $filtroNombre]"); iter.hasNext();) {
			Student student = (Student) iter.next();
			System.out.println(student.getFirstName());
		}

	}

}