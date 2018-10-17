package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 * Task 07: Querying ontologies (RDFs)
 * @author elozano
 * @author isantana
 *
 */
public class Task07
{
	public static String ns = "http://somewhere#";
	
	public static void main(String args[])
	{
		String filename = "resources/example6.rdf";
		Individual per;
		OntClass person;
		OntClass subClass;
		OntClass cos;
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		person = model.getOntClass(ns+"Person");
		ExtendedIterator instances = person.listInstances();
		
		int i = 0;
		while(instances.hasNext()) {
			i++;
			per = (Individual) instances.next();
			System.out.println("Person " +i+ " "+ per.getURI());
		}
		i = 0;
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator subclasses = person.listSubClasses();
		while(subclasses.hasNext()) {
			i++;
			subClass = (OntClass) subclasses.next();
			System.out.println("SubClass " + i + " " + subClass.getURI());
		}
		
		i = 0;
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel mod = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		person = model.getOntClass(ns+"Person");
		ExtendedIterator instances1 = person.listSubClasses();
		while(instances1.hasNext()) {
			i++;
			cos = (OntClass) instances1.next();
			System.out.println("Subclass " + i +" "+ cos.getURI());
		}
		i=0;
		instances1 = person.listInstances();
		while (instances1.hasNext()) {
			Individual per1 = (Individual) instances1.next();
			System.out.println("Individual "+per1.getURI());
	}
}
}
