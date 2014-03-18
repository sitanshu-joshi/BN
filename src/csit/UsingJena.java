package csit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.semanticweb.yars.nx.Node;
import org.semanticweb.yars.nx.parser.NxParser;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class UsingJena {

	public static void main(String []a)
	{
	Template clsTemp = new Template();
	Template atrTemp = new Template();
	Template relTemp= new  Template();
	 
	Template insTemp = new Template();//used to find Relation
	
	 ArrayList <String> classes =new ArrayList();
	 ArrayList <String> instances =new ArrayList();
	 ArrayList <String> relations =new ArrayList();
	 
	 
	    Model model = ModelFactory.createDefaultModel();
	    InputStream is = FileManager.get().open(CommonDeclare.fileNameOrUri);
	    if (is != null) {
	        model.read(is, null, CommonDeclare.rdfsyntax); //read rdf file
	       
	    		try {
					model.write(new FileOutputStream(CommonDeclare.nqpath), CommonDeclare.ntriplesyntax);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //Convert to NTRIPLE FORMAT
	    		
	    		clsTemp = TemplateGenerator.Generate(TemplateType.ClassType);
	    		insTemp = TemplateGenerator.Generate(TemplateType.InstanceType);
	    	//	insTemp.showTemplate();	
	    		List rellist= TemplateGenerator.generateRelationTemplate(insTemp.fullList);
	    		List attlist =TemplateGenerator.generateAttributeTemplate(insTemp.fullList);
	    		//	clsTemp.showTemplate();
	    	//	atrTemp.showTemplate();
	    
	    clsTemp.showTemplate();
	    RelationTemplate.showAllRelation(rellist);
	    AttributeTemplate.showAllAttribute(attlist);
	    
	    //Apply String Synopsis
	    
	    attlist = AttributeTemplate.applySynopsis(attlist);
	    
	    TemplateGenerator.marginalize();
	    
	    ByesNet Bnet= new ByesNet();
	    Bnet.setInput(clsTemp, attlist, rellist,CommonDeclare.sypsize);
	    Bnet.genereateModel();
	   //Here  Problem is without query how can we define which attributes of
	    // Instance to be shown in CPT?
	    
	    }
	}
}
