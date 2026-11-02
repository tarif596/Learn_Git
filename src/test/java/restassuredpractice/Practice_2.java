package restassuredpractice;

import io.restassured.path.json.JsonPath;
import payloads.Payload;

public class Practice_2 {
	public static void main(String args[])
	{
		JsonPath js = new JsonPath(Payload.complexJson());
		
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		String firstTitle = js.getString("courses[0].title");
		System.out.println(firstTitle);
		
		
		for (int i = 0; i < count; i++)
		{
			String allTitle = js.get("courses["+i+"].title");
			System.out.println(allTitle);
			//int allPrices = js.getInt("courses["+i+"].price");
			System.out.println(js.get("courses["+i+"].price").toString());
		}
		
		int copies = 0;
		for (int i = 0; i < count; i++)
		{
			String allTitle2 = js.get("courses["+i+"].title");
			if(allTitle2.equals("RPA"))
			{
			copies = js.getInt("courses["+i+"].copies");
			break;
			}
		}
		    System.out.println(copies);
		    
		    int sum = 0;
			for (int i = 0; i < count; i++)
			{
				int allPrice = js.get("courses["+i+"].price");
				sum = sum + allPrice;
			}
			
		if(sum == purchaseAmount)
			System.out.println("Matches");
		else
			System.out.println("Does not matches");
	}

}
