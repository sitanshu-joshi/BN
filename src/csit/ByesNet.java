package csit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.graph.TripleMatch;
import com.hp.hpl.jena.graph.impl.GraphBase;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class ByesNet extends GraphBase {
	
	private static String uri="http://www.csitpark.com/";
	
List <CPTRecord>lst=new ArrayList<>();
Template clsTemplate= null;
List <AttributeTemplate> atrTemp=null;
List <RelationTemplate> relTemp=null;
long  synopsissize =0;

private TemplateModel MyModel=null;


public  void setInput(Template t1,List<AttributeTemplate> t2,List <RelationTemplate> t3,long synsize)
{
	clsTemplate= t1;
	atrTemp= t2;
	relTemp= t3;
	synopsissize= synsize;
}


public void genereateModel()
{
    List lst=CPT.showCPT(relTemp,atrTemp);

    Iterator <CPTRecord>cit= lst.iterator();
    
    while(cit.hasNext())
    {
    	addCPT(cit.next());
    }
MyModel= new TemplateModel();

Iterator it=relTemp.iterator();

while(it.hasNext())
{
	RelationTemplate rel=(RelationTemplate)it.next();
	
	String insfromstring= CPT.removeURI(rel.getInstfrom());
	String relstring= CPT.removeURI(rel.getRelation());
	String instostring= CPT.removeURI(rel.getInstto());
	
	Resource res1= MyModel.MyModel.createResource(uri+insfromstring);
	//Resource res2= MyModel.createResource(uri+rel.getInstto());
	Property prt= MyModel.MyModel.createProperty(uri+relstring);
	Statement S= MyModel.MyModel.createStatement(res1, prt, uri+instostring);
	
	MyModel.MyModel.add(S);
	
}
System.out.println("\n\nThis is our Newly Generated Model ");
MyModel.MyModel.write(System.out, CommonDeclare.rdfsyntax);
}
@Override
public void performAdd(Triple T)
{
	
}


	public void addCPT(CPTRecord rec)
	{
		lst.add(rec);
	}
	
	void showNetwork()
	{
		for(int i=0;i<lst.size();i++)
		{
			CPTRecord r= lst.get(i);
			r.showCPT();
		}
	}



	public TemplateModel getMyModel() {
		return MyModel;
	}



	public void setMyModel(TemplateModel myModel) {
		MyModel = myModel;
	}


	@Override
	protected ExtendedIterator<Triple> graphBaseFind(TripleMatch arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
