import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import com.google.gson.Gson;

public class GoogleQuery {
	String query="";
	int size=8;
	int cursor=0;
	
	private final String HTTP_REFERER = "https://github.com/gdg-uos/errclipse-rendering-engine";
	
	private class Results{
	    private ResponseData responseData;
	    public ResponseData getResponseData() { return responseData; }
	    public String toString() { return "ResponseData[" + responseData + "]"; }

	    class ResponseData {
	        private List<GoogleResult> results;
	        public List<GoogleResult> getResults() { return results; }
	        public String toString() { return "Results[" + results + "]"; }
	    }
	
	}

	public List<GoogleResult> query_next() {
		return query(query,cursor,size);
	}
	
	public List<GoogleResult> query(String query) {
		this.query=query;
		this.cursor=0;
		this.size=8;
		List<GoogleResult> ret=query(query,8,0);
		this.cursor+=ret.size();
		return ret;
	}
	//query_size - max_size : 8
	public List<GoogleResult> query(String query,int size) {
		this.query=query;
		this.cursor=0;
		this.size=size;
		List<GoogleResult> ret=query(query,size,0);
		this.cursor+=ret.size();
		return ret;
	}
	public List<GoogleResult> query(String query,int size,int start) {
		this.query=query;
		this.cursor=start;
		this.size=size;
		try
		{
			query = URLEncoder.encode(query, "UTF-8");
			URL url = new URL("http://ajax.googleapis.com/ajax/services/search/web?start="+start+"&v=2.0&rsz="+size+"&q=" + query);
			URLConnection connection = url.openConnection();
			connection.addRequestProperty("Referer", HTTP_REFERER);

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			Results results = new Gson().fromJson(reader, Results.class);
			
			List<GoogleResult> ret=results.getResponseData().getResults();
			this.cursor+=ret.size();
			return ret;
		}
		catch (Exception e) {
			System.err.println("Something went wrong...");
			e.printStackTrace();
		}
		return null;
	}
	

}