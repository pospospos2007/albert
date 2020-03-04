<%@ page contentType="text/html;charset=UTF-8" %>
<%--
# 分页片段
--%>
     <c:if test="${pageView.pageCount > 1}">
     <ul class="pagination pull-right">
      <c:if test="${currentPage <= 1}">
      <li class="disabled"><span>&lt;上一页</span></li>
      </c:if>
      <c:if test="${currentPage > 1}">
     <li><a href="#" data-page-number="${currentPage-1}">&lt;上一页</a></li>
      </c:if>
      <c:forEach var="p" begin="${pageView.pageIndex.startIndex}" end="${pageView.pageIndex.endIndex}">
        <c:if test="${p == currentPage}">
      <li class="active"><span>${p} <span class="sr-only">(current)</span></span></li>
        </c:if>
        <c:if test="${p != currentPage}">
      <li><a href="#" data-page-number="${p}">${p}</a></li>
        </c:if>
      </c:forEach>
      <c:if test="${currentPage >= pageView.pageCount}">
      <li class="disabled"><span>下一页&gt;</span></li>
      </c:if>
      <c:if test="${currentPage < pageView.pageCount}">
      <li><a href="#" data-page-number="${currentPage+1}">下一页&gt;</a></li>
      </c:if>
    </ul>
    </c:if>