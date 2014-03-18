package csit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {

	public Map <Object,List<Object>>fullList = null;
	public Map <Object,Object> uniqueList=null;
	
	
	public Template()
	{
	fullList= new HashMap<>();
	uniqueList= new HashMap<>();	
	}
	
	public void showTemplate()
	{
		System.out.println("Full List ");
		for(Map.Entry<Object, List<Object>> entry  :fullList.entrySet())
		{
			System.out.println("Class :"+entry.getKey());
			for(int i=0;i<entry.getValue().size();i++)
			{
				System.out.println("-->"+entry.getValue().get(i).toString());
			}
		}
	}
	
	public void marginalize()
	{
		
	}
	
}
