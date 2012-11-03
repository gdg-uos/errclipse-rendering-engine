import java.util.List;


public class Sample {

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		int i=0;
		
		GoogleQuery gq=new GoogleQuery();
		List<GoogleResult> res=gq.query("Logcat",8,0);	//max_size=8
		
		for(;i<res.size();i++)
		{
			System.out.println(i+".");
			System.out.println(res.get(i).getTitle());
			System.out.println(res.get(i).getUrl());
			System.out.println(res.get(i).getContent());
			System.out.println();
		}
		
		res=gq.query_next();
		for(;i<res.size();i++)
		{
			System.out.println(i+".");
			System.out.println(res.get(i).getTitle());
			System.out.println(res.get(i).getUrl());
			System.out.println(res.get(i).getContent());
			System.out.println();
		}
	}
}
