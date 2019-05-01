package main;

import java.io.File;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.jxpath.JXPathContext;

import data.Student;
import data.Students;

public class App {

	public static void main(String[] args) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		File xmlFile = new File("./src/main/java/resources/students.xml");

		Students xmlStudents = (Students) jaxbUnmarshaller.unmarshal(xmlFile);
		// List<Student> students = studentList.getStudents();

		JXPathContext context = JXPathContext.newContext(xmlStudents);

		String ejemploNombre = "Francisco";
		context.getVariables().declareVariable("filtroNombre", ejemploNombre);

		bucle: for (Iterator<?> iter = context.iterate("/students[firstName = $filtroNombre]"); iter.hasNext();) {
			Student student = (Student) iter.next();
			System.out.println(student.getFirstName());
		}

	}

}