public class GoogleResult{
    private String url;
    private String titleNoFormatting;
    private String content;
    private String removeBold(String html)
    {
    	html=html.replaceAll("</b>","");
    	return html.replaceAll("<b>", "");
    }
    public String getUrl() { return url; }
    public String getTitle() { return titleNoFormatting; }
    public String getContent() { return removeBold(content); }
    public String toString() { return "Result[url:" + url +",title:" + titleNoFormatting + ",content:" + removeBold(content) +"]"; }
}