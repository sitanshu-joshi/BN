package csit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

class CPTRecord
{
private String Parent;
private String Child1;
private String Child2;

private double probability;


void showCPT()
{
	System.out.println("Parent "+ Parent+" Child 1:"+Child1+" Child 2 :"+Child2);
}
public String getParent() {
	return Parent;
}
public void setParent(String parent) {
	Parent = parent;
}
public String getChild1() {
	return Child1;
}
public void setChild1(String child1) {
	Child1 = child1;
}
public String getChild2() {
	return Child2;
}
public void setChild2(String child2) {
	Child2 = child2;
}
public double getProbability() {
	return probability;
}
public void setProbability(double probability) {
	this.probability = probability;
}
}
public class CPT {



	public static List showCPT(List lst,List attlist)
	{
		List <CPTRecord> clist= new ArrayList<>();
		
		Iterator <RelationTemplate> it= lst.iterator();
		while(it.hasNext())
		{
		RelationTemplate r=it.next();
		String rel=removeURI(r.getRelation());
		String ins1=removeURI(r.getInstfrom());
		String ins2=removeURI(r.getInstto());
		
		List allattrib1 =new ArrayList();
		List allattrib2 =new ArrayList();
		
		//Find All Attributes
		
		allattrib1 = findAllAttribute(r.getInstfrom(),attlist);
		allattrib2 = findAllAttribute(r.getInstto(),attlist);
		System.out.println(allattrib1.size()+ " Attributes Founded in "+ ins1);
		System.out.println(allattrib2.size()+ " Attributes Founded in "+ ins2);
		
		System.out.println("CPT for "+ rel);
		/*
		for(int i=0;i<allattrib1.size();i++)
		{
			for(int j=0;j<allattrib2.size();j++)
			{
			}
		}
		*/
		
		for(int i=0;i<allattrib1.size();i++)
		{
			for(int j=0;j<allattrib2.size();j++)
			{
			String at1=allattrib1.get(i).toString();
			String at2=allattrib2.get(j).toString();
			System.out.println("P(X"+rel+"|"+removeURI(at1)+","+removeURI(at2)+")#X("+rel+"(@1,@2)#X("+removeURI(at1)+")#X("+removeURI(at2)+")");
			{
				List val1=getAllvalues(at1,attlist);
				List val2=getAllvalues(at2,attlist);
				for(int i1=0;i1<val1.size();i1++)
				{
					for(int j1=0;j1<val2.size();j1++)
					{
						float n=0;
						int ttl= val1.size()*val2.size();
					boolean isinquery= false;
					isinquery=findInQuery(rel);
					if(isinquery)
					{
		CPTRecord rec= new CPTRecord();
		rec.setParent(rel);
		rec.setChild1(val1.get(i1).toString());
		rec.setChild2(val2.get(j1).toString());
		//Bnet.addCPT(rec);
		clist.add(rec);
		n= 1/ttl;
					}
						String v="F";
						if(j1==0)
							v="T";
						System.out.println(n+"  # "+" "+v+" "+ val1.get(i1).toString()+"#"+ val2.get(j1).toString());
					}
				}
			}
			}
		}
		}
		
		return clist;
	}
	
	private static boolean findInQuery(String rel) {
		//Check Relation in Query
		return new Random().nextBoolean();
	}

	private static List getAllvalues(String at1, List attlist) {
		
		ArrayList <Object>ans= new ArrayList<Object>();
		for(int i=0;i<attlist.size();i++)
		{
			AttributeTemplate at= (AttributeTemplate)attlist.get(i);
			
		if(at.getAttribute().equals(at1))
		{
			ans.add(at.getValue());
		}
		}
		return ans;
	}

	private static List findAllAttribute(String ins1,List atlist) {
		
		ArrayList allist=new ArrayList<Object>();
		for(int i=0;i<atlist.size();i++)
		{
			AttributeTemplate at= (AttributeTemplate)atlist.get(i);
			
		if(at.getInstfrom().equals(ins1))
		{
			allist.add(at.getAttribute());
		}
		}
		return allist;
	}

	public static String removeURI(String data)
	{
		if(data.lastIndexOf("/")!=-1)
		{
			data= data.substring(data.lastIndexOf("/")+1,data.length()-1);
		}
		return data;
	}
}
