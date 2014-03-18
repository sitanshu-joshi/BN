package csit;

import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.semanticweb.yars.nx.Node;
import org.semanticweb.yars.nx.parser.NxParser;

public class TemplateGenerator {


	public static Template Generate(TemplateType TT)
	{
		Template T=new Template();
		List <Object>instlist= new ArrayList<>();		
		
		NxParser nxp;
		try {
			nxp = new NxParser(new FileInputStream(CommonDeclare.nqpath),false);
		} catch (FileNotFoundException e) {
			System.out.println(CommonDeclare.nqpath+" Can not be Created");
			
			e.printStackTrace();
			return null;
		}
		
		if(TT== TemplateType.ClassType)
		{
			
			
    		while (nxp.hasNext()) 
    		{
    			Node[] ns = nxp.next();
    			if (ns.length == 3)
    		  {
    		    for (Node n: ns) 
    		    {
    		    	String value=n.toN3();
    		if(value.indexOf("#type")!=-1)
    		{
    			String ins=ns[0].toN3();
    			//ins=ins.substring((ins.lastIndexOf("/")+1),ins.length()-1);
    			String cls=ns[2].toN3();
    			if(cls.lastIndexOf("#")!=-1)
    			cls=cls.substring((cls.lastIndexOf("#")+1),cls.length()-1);
    			else
    				cls=cls.substring((cls.lastIndexOf("/")+1),cls.length()-1);	
    			//System.out.print("Instance "+  ins);
    			//System.out.println(" of Class "+ cls);
    			instlist= T.fullList.get(cls);
    			if(instlist==null)
    				instlist= new <Object> ArrayList();
    			instlist.add(ins);
    			
    			T.fullList.put(cls, instlist);
    			
    		}
    		else if(value.indexOf("\"")!=-1)
    		{
    			String subject = ns[0].toN3();
    			String relation = ns[1].toN3();
    			subject= subject.substring(subject.lastIndexOf("/")+1,subject.length()-1);
    			relation= relation.substring(relation.lastIndexOf("/")+1,relation.length()-1);
//    			System.out.println(subject+" >"+relation+" -->"+value);
    		}
    		else
    		{
  //  			System.out.println("Not Defined :"+value);
    		}
    		    
    		    
    		    }
    		    
    		  }
    		}
    		
	
    }
		else if(TT==TemplateType.InstanceType )
		{
			while (nxp.hasNext()) 
    		{
    			Node[] ns = nxp.next();
    			if (ns.length == 3)
    		  {
    		    for (Node n: ns) 
    		    {
    		    	String value=n.toN3();
    		if(value.indexOf("#type")!=-1)
    		{
    			String ins=ns[0].toN3();
    	
    			//ins=ins.substring((ins.lastIndexOf("/")+1),ins.length()-1);
    			String cls=ns[2].toN3();
    			if(cls.lastIndexOf("#")!=-1)
    			{
    			cls=cls.substring((cls.lastIndexOf("#")+1),cls.length()-1);
    			//System.out.println("class :"+cls);
    			}
    			else
    			{
    				cls=cls.substring((cls.lastIndexOf("/")+1),cls.length()-1);
    				//System.out.println("class :"+cls);
    			}
    				
    	//		System.out.print("Instance "+  ins);
    		//	System.out.println(" of Class "+ cls);
    			
    			T.fullList.put(ins,instlist);
    		}
    		    }
    		    
    		  }
    		}
    		
			
		}
		else if(TT== TT.RelationType)
		{
			while (nxp.hasNext()) 
    		{
    			Node[] ns = nxp.next();
    			if (ns.length == 3)
    		  {
    		    for (Node n: ns) 
    		    {
    		    	String value=n.toN3();
    		if(value.indexOf("#type")!=-1)
    		{
    			String ins=ns[0].toN3();
    			ins=ins.substring((ins.lastIndexOf("/")+1),ins.length()-1);
    			String cls=ns[2].toN3();
    			if(cls.lastIndexOf("#")!=-1)
    			cls=cls.substring((cls.lastIndexOf("#")+1),cls.length()-1);
    			else
    				cls=cls.substring((cls.lastIndexOf("/")+1),cls.length()-1);	
    //			System.out.print("Instance "+  ins);
    //			System.out.println(" of Class "+ cls);
    			T.fullList.put(cls, instlist);
    		}
    		    }
    		    
    		  }
    		}
    		
			
		}
		
		
		return T;
	}
	
	public static List generateRelationTemplate(Map inst)
	{
		ArrayList L= new ArrayList();
		RelationTemplate rel= new RelationTemplate();
		
		NxParser nxp;
		try {
			nxp = new NxParser(new FileInputStream(CommonDeclare.nqpath),false);
		} catch (FileNotFoundException e) {
			System.out.println(CommonDeclare.nqpath+" Can not be Created");
			
			e.printStackTrace();
			return null;
		}
		
		while (nxp.hasNext()) 
		{
			Node[] ns = nxp.next();
			if (ns.length == 3)
		  {
				
		  String sub=ns[0].toN3();
		  String pre=ns[1].toN3();
		  String obj= ns[2].toN3();
		 // System.out.println("Total Instance "+ inst.size());
		 
		if(inst.containsKey(sub.trim()) && inst.containsKey(obj))
		{
			rel= new RelationTemplate();
			rel.setRelation(sub, obj, pre);
			L.add(rel);
		//	System.out.println("Relation Founded"+ sub+"-"+pre+"-"+obj);
		}
		  }
		}
		return L;
		
	}

	
	public static List generateAttributeTemplate(Map inst)
	{
		ArrayList L= new ArrayList();
		AttributeTemplate rel= new AttributeTemplate();
		
		NxParser nxp;
		try {
			nxp = new NxParser(new FileInputStream(CommonDeclare.nqpath),false);
		} catch (FileNotFoundException e) {
			System.out.println(CommonDeclare.nqpath+" Can not be Created");
			
			e.printStackTrace();
			return null;
		}
		
		while (nxp.hasNext()) 
		{
			Node[] ns = nxp.next();
			if (ns.length == 3)
		  {
				
		  String sub=ns[0].toN3();
		  String pre=ns[1].toN3();
		  String obj= ns[2].toN3();
		 // System.out.println("Total Instance "+ inst.size());
		 
		if(inst.containsKey(sub.trim()) &&  ! inst.containsKey(obj) && obj.indexOf("owl")==-1)
		{
			rel= new AttributeTemplate();
			rel.setRelation(sub, obj, pre);
			L.add(rel);
	//		System.out.println("Attribute Founded"+ sub+"-"+pre+"-"+obj);
		}
		  }
		}
		return L;
		
	}

	public static void marginalize() {
				
	}
}
