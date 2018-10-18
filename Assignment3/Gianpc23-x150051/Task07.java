package upm.oeg.wsld.jena;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
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
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		OntClass person = model.getOntClass(ns+"Person");
		ExtendedIterator instances = person.listInstances();
		while(instances.hasNext()) {
			System.out.println("List of all individuals of Person");
			System.out.println(instances.next());
		}
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator subclasses = person.listSubClasses();
		while(subclasses.hasNext()) {
			System.out.println("List of all subclasses of Person");
			System.out.println(subclasses.next());
		}
		
		System.out.println("\nTask 7.3**************************");
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		ExtendedIterator ptr = person.listInstances();
		
		System.out.println("Instances of Person");
		while(ptr.hasNext()) {
			System.out.println("\t"+ptr.next());
		}
		
		System.out.println("\nSubclasses of Person and their instances");
		subclasses = person.listSubClasses();
		
		while(subclasses.hasNext()) {
			OntClass clase = (OntClass) subclasses.next();
			ptr = clase.listInstances();
			while(ptr.hasNext()) {
				OntResource next = (OntResource) ptr.next();
				System.out.println("\tInstance: " + next.getURI() + " Class: "+clase.getURI());
				System.out.println("----------------------------");
			}
		}
		
		
	
	}
}
