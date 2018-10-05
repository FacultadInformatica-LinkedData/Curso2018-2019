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
 * Task 06: Modifying ontologies (RDFs)
 * @author fitigh91
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
		
		System.out.println(" All individuals of the class Person: ");
		while(instances.hasNext()){
			Individual p = (Individual)instances.next();
			System.out.println(" * " + p.getNameSpace() + p.getLocalName());
		}
		
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator subclasses = person.listSubClasses();
		
		System.out.println("All subclasses of the class Person: ");
		while(subclasses.hasNext()) {
			OntClass sc = (OntClass)subclasses.next();
			System.out.println(" * " + sc.getLocalName());
		}
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		OntModel inference = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model); /// https://jena.apache.org/documentation/javadoc/jena/org/apache/jena/ontology/OntModelSpec.html
		OntClass iPerson = inference.getOntClass(ns+"Person");
		ExtendedIterator iInstances = iPerson.listInstances();
		ExtendedIterator iSubclasses = iPerson.listSubClasses();
		
		System.out.println("Indirect instances of class Person: " );
		while (iInstances.hasNext()){
			Individual p2 = (Individual) iInstances.next();
			System.out.println(" * "+ p2.getURI());
		}
	
		System.out.println("Indirect subclasses who belong to the class Person: " );
		while (iSubclasses.hasNext()){
			OntClass sc2 = (OntClass) iSubclasses.next();
			System.out.println(" * " + sc2.getLocalName());
		}
		System.out.println("\n");
	
	}
}
