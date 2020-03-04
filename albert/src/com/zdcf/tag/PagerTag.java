package com.zdcf.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PagerTag extends TagSupport {

	private static final long serialVersionUID = 5729832874890369508L;

	// 每页显示的记录数
	private int pageSize = 10;
	// 第几页
	private int pageNo = 1;
	// 总记录数
	private int recordCount;
	//表单名称
	private String fromId;
	//当前页数表单名称
	private String  currentPageId;
	


	public String getCurrentPageId() {
		return currentPageId;
	}

	public void setCurrentPageId(String currentPageId) {
		this.currentPageId = currentPageId;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public int doStartTag() throws JspException {  
    	  //总页数
    	 int pageCount = (this.recordCount + this.pageSize - 1) / this.pageSize;  
    	 
    	 String currentPage="currentPage";
    	 if(currentPageId!=null&&!"".equals(currentPageId)){
    		 currentPage=currentPageId;
    	 }
    	 
    	 StringBuffer pageContent=new StringBuffer();
    	 //判断总页数是否为0
    	 if (this.recordCount > 0){    		 
    		//判断前台传递过的页数是否小于第一页
    		 if (this.pageNo < 1){    			
				 this.pageNo = 1;  
			 }      		 
    		 //判断前台传递过的页数是否大于总页数
    		 if (this.pageNo > pageCount){
    			 this.pageNo = pageCount;      			 
    		 } 	 
    		 if(pageNo<=1){    			 
    			 pageContent.append("<span  class=\"onC\"><i></i><a>上一页</a></span>");
    		 }
    		 if (pageNo > 1){
    			 pageContent.append("<span ><i></i><a  href='javascript:turnOverPage");
    			 pageContent.append(currentPage);
    			 pageContent.append("(\"");
    			 pageContent.append(pageNo-1);
    			 pageContent.append("\")'>上一页</a></span>");
    		 }
    		 pageContent.append("<ul>");  
    		 
    		 int start=pageNo;
    		
    		 int end=start+4;
    		 
//    		 System.out.print("startstartstartstart"+start);
//    		 System.out.print("endendendendendendend"+end);
    		
    		 if(((end-start)==4)&&((start-3)>0)){
    			 start=start-2;
    			 end=end-2;
    		 }
    		 
    		 if(end>pageCount){
    			 end=pageCount;
    		 }
    		 //
    		 if(end>5&&(end-start)<4){
    			 start=end-4;
    		 }else if (end<5){
    			 start=1;
    		 }
    		 
    		 if(start>1&&((end-start)==4)){
     		    pageContent.append("..."); 
     		 }
    		 for(int i=start;i<=end;i++){
    			 pageContent.append("<li");    		
    			 if(pageNo==i){
    				 pageContent.append(" class='onC' "); 
    			 }
    			 pageContent.append("><a  href='javascript:turnOverPage");	 
    			 pageContent.append(currentPage);
    			 pageContent.append("(\"");
    			 pageContent.append(i).append("\")'>").append(i).append("</a></li>");	
    		 } 
    		 //判断总页数大于5
    		 if(pageCount>5&&(start+5)<=pageCount){
    		    pageContent.append("..."); 
    		 }
    		 pageContent.append("</ul>");
    
    		 if(pageNo>=pageCount){ 
    			 pageContent.append("<span class=\"last onC\"><a>下一页</a><i  class='onC'></i></span>");
    		 }else{
    			 pageContent.append("<span class=\"last\"><a  href='javascript:turnOverPage");
    			 pageContent.append(currentPage);
    			 pageContent.append("(\"");
    			 pageContent.append(pageNo+1);
    			 pageContent.append("\")'>下一页</a> <i ></i></span>");
    		 }	
                	 pageContent.append("<input type=\"hidden\"  id='"); 
                	 pageContent.append(currentPage);
                	 pageContent.append("'  name=\"");
                	 pageContent.append(currentPage);
                	 pageContent.append("\" value=\"").append(pageNo).append(  
      		                  "\"/>\r\n"); 
                 
    			  
    			  pageContent.append(" <script type=\"text/javascript\"> \r\n");
    			  pageContent.append("function obQ");
    			  pageContent.append(currentPage);
    			  pageContent.append("(ob){ \r\n");
    			  pageContent.append("ob.click(function(){ \r\n");
    			  pageContent.append("ob.removeClass(\"onC\"); \r\n");
    			  pageContent.append("ob.eq($(this).index()).addClass(\"onC\");\r\n");
    			  pageContent.append("}) \r\n");
    			  pageContent.append("}; \r\n");
    			  pageContent.append("obQ");
    			  pageContent.append(currentPage);
    			  pageContent.append("($(\"#page ul li\"));");
    			  
    			  pageContent.append("  function turnOverPage"); 
    			  pageContent.append(currentPage);
    			  pageContent.append("(no){\r\n");
    			  pageContent.append("$('#");
    			  pageContent.append(currentPage); 
    			  pageContent.append("').attr('value',no);\r\n");  
    			  pageContent.append(" $('#").append(fromId).append("').submit();\r\n");  
    			  pageContent.append("  }\r\n");  

    			
    			  pageContent.append("</script>");
    		

    	 }
    	 try {  
    		 this.pageContext.getOut().println(pageContent.toString());  
    		} catch (IOException e) {  
    		   throw new JspException(e);  
    	  }  
    	return 0;  

    	
    }


	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

}
