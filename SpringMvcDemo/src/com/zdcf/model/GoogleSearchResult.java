package com.zdcf.model;

public class GoogleSearchResult {

	private String kind;
   
    private String htmlTitle;
	   
    private String link;
	   
    private String htmlSnippet;
	   
    private String width;
	   
    private String height;
	   
    private String src;

	public String getKind() {
		return kind;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public String getHtmlTitle() {
		return htmlTitle;
	}
	
	public void setHtmlTitle(String htmlTitle) {
		this.htmlTitle = htmlTitle;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getHtmlSnippet() {
		return htmlSnippet;
	}
	
	public void setHtmlSnippet(String htmlSnippet) {
		this.htmlSnippet = htmlSnippet;
	}
	
	public String getWidth() {
		return width;
	}
	
	public void setWidth(String width) {
		this.width = width;
	}
	
	public String getHeight() {
		return height;
	}
	
	public void setHeight(String height) {
		this.height = height;
	}
	
	public String getSrc() {
		return src;
	}
	
	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public String toString() {
		return "GoogleSearchResult [kind=" + kind + ", htmlTitle=" + htmlTitle + ", link=" + link + ", htmlSnippet="
				+ htmlSnippet + ", width=" + width + ", height=" + height + ", src=" + src + "]";
	}
   
   
   
   
}