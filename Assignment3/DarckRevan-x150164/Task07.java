<<<<<<< HEAD
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
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Create an inference model
		OntModel mInf = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		
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
			Individual persona = (Individual)instances.next();
			System.out.println(persona.getURI());
		}
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator subclasses = person.listSubClasses();
		while(subclasses.hasNext()) {
			OntClass cl = (OntClass)subclasses.next();
			System.out.println(cl.getURI());
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("\n** TASK 7.3 ** \n");
		OntClass personInf = mInf.getOntClass(ns+"Person");
		ExtendedIterator instancesInf = personInf.listInstances();
		while(instancesInf.hasNext()) {
			Individual persona = (Individual)instancesInf.next();
			System.out.println(persona.getURI());
		}
		
		ExtendedIterator subclassesInf = personInf.listSubClasses();
		while(subclassesInf.hasNext()) {
			OntClass cl = (OntClass)subclassesInf.next();
			System.out.println(cl.getURI());
		}
	}
}
=======
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
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Create an inference model
		OntModel mInf = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model);
		
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
			Individual persona = (Individual)instances.next();
			System.out.println(persona.getURI());
		}
		// ** TASK 7.2: List all subclasses of "Person" **
		ExtendedIterator subclasses = person.listSubClasses();
		while(subclasses.hasNext()) {
			OntClass cl = (OntClass)subclasses.next();
			System.out.println(cl.getURI());
		}
		
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
		System.out.println("\n** TASK 7.3 ** \n");
		OntClass personInf = mInf.getOntClass(ns+"Person");
		ExtendedIterator instancesInf = personInf.listInstances();
		while(instancesInf.hasNext()) {
			Individual persona = (Individual)instancesInf.next();
			System.out.println(persona.getURI());
		}
		
		ExtendedIterator subclassesInf = personInf.listSubClasses();
		while(subclassesInf.hasNext()) {
			OntClass cl = (OntClass)subclassesInf.next();
			System.out.println(cl.getURI());
		}
	}
}
>>>>>>> 96c777681fa728988ce9b03cbf4d4789600172f3
