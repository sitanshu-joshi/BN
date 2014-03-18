package csit;

import java.util.Iterator;
import java.util.List;

public class RelationTemplate {

	private String instfrom;
	private String instto;
	private String relation;
	
	
	
	public void setRelation(String i1,String i2,String r)
	{
		setInstfrom(i1);
		setInstto(i2);
		relation=r;
	}
	
	public static void showAllRelation(List lst)
	{
		Iterator i= lst.iterator();
	
		while(i.hasNext())
		{
			RelationTemplate rl= (RelationTemplate) i.next();
			System.out.println("Relation :"+rl.getInstfrom()+" "+rl.getRelation()+" "+rl.getInstto());
		}
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getInstto() {
		return instto;
	}

	public void setInstto(String instto) {
		this.instto = instto;
	}

	public String getInstfrom() {
		return instfrom;
	}

	public void setInstfrom(String instfrom) {
		this.instfrom = instfrom;
	}
}
