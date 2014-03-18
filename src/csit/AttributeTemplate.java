package csit;

import java.util.Iterator;
import java.util.List;

public class AttributeTemplate {

	private String instfrom;
	private String value;
	private String attribute;
	
	public void setRelation(String i1,String i2,String r)
	{
		setInstfrom(i1);
		setValue(i2);
		setAttribute(r);
	}
	
	public static void showAllAttribute(List lst)
	{
		Iterator i= lst.iterator();
	
		while(i.hasNext())
		{
			AttributeTemplate rl= (AttributeTemplate) i.next();
			System.out.println("Attribute :"+rl.getInstfrom()+" "+rl.getAttribute()+" "+rl.getValue());
		}
	}
	
	public static List applySynopsis(List lst)
	{
		
		Iterator i= lst.iterator();
		
		while(i.hasNext())
		{
			AttributeTemplate rl= (AttributeTemplate) i.next();
		//	System.out.println("Attribute :"+rl.instfrom+" "+rl.attribute+" "+rl.value);
			
			if(isLargeText(rl.getValue()))
			{
			//Apply String Synopsis , but we are unable to get
			//What to do by Synopsis ,find Similar Text or Remove extra words from text ?
			}
		}
		return lst;
	}

	private static boolean isLargeText(String value2) {
		
		
		return false;
	}

	public String getInstfrom() {
		return instfrom;
	}

	public void setInstfrom(String instfrom) {
		this.instfrom = instfrom;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
}
