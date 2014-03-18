package csit;

import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.impl.ModelCom;

public class TemplateModel {

	public Model MyModel;
	
	public TemplateModel()
	{
		MyModel= ModelFactory.createDefaultModel();
	}
	
	
}
