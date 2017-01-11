<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
	 <link rel="stylesheet" href="<%=path%>/css/userInfo/sitelogo.css" />
	 <link rel="stylesheet" href="<%=path%>/css/userInfo/green.css" />
     <link rel="stylesheet" href="<%=path%>/css/userInfo/cropper.min.css" />
    <title>
    <c:if test="${empty user}">${USER_SESSION_KEY.username}</c:if>
    <c:if test="${!empty user}">${user.username}</c:if>
    </title>


<div class ="container">
		<!--修改头像，开始-->
   		<div class="u-account-box undis">
       		<div id="tabCont">
           		<section>
               		<section class="ukindeditor of">
                   		<section class="clearfix">
							<div class="panel panel-default">
							  <div class="panel-heading">
							    <h3 class="panel-title">用户信息</h3>
							  </div>
							  <div class="panel-body">
							    <p>用户名：<c:if test="${empty user}">${USER_SESSION_KEY.username}</c:if>
   										 <c:if test="${!empty user}">${user.username}</c:if>
 								</p>
                               <p>邮箱:<c:if test="${empty user}">${USER_SESSION_KEY.email}</c:if>
  									  <c:if test="${!empty user}">${user.email}</c:if>
                               </p>
                               <c:if test="${empty user}"><p>Ps:点击下方头像可以修改头像哦～</p></c:if>
							  </div>
							</div>
                           <div id="preview-pane" class="preview-pane">
                               <div class="row">
                               
                                   <div <c:if test="${empty user}">id="crop-avatar"</c:if> class="col-md-6">
                                       <div class="avatar-view" title="点击更改头像">
	                                       	<c:if test="${empty user}"><img src="<%=path%>/uploadimage/${USER_SESSION_KEY.avatar}" alt="头像加载中..."></c:if>
	    									<c:if test="${!empty user}"><img src="<%=path%>/uploadimage/${user.avatar}" alt="头像加载中..."></c:if>
                                       	
                                       </div>                                        
									<p class="c-999" style="width: 220px;">220x220</p>
                                   </div>
                               </div>
                           </div>
                       
                             	<section class="clear"></section>
                         </section>
					</section>
                 </section>
             </div>
             <input type="button" class="commBtn bgGreen w80 ml50" id="deleImage" style="display: none">
   		</div>
    	<!--修改头像，结束-->
	</div>
 
 
  <div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form class="avatar-form" action="<%=path%>/file/uploadAvatar" enctype="multipart/form-data" method="post">
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal" type="button">&times;</button>
                        <h4 class="modal-title" id="avatar-modal-label">选择并上传图片</h4>
                    </div>
                    <div class="modal-body">
                        <div class="avatar-body">
                            <div class="avatar-upload">
                                <input class="avatar-src" name="avatar_src" type="hidden">
                                <input class="avatar-data" name="avatar_data" type="hidden">
                                <label for="avatarInput">图片上传</label>
                                <a href="javascript:;" class="file">选择文件
                                    <input class="avatar-input" id="avatarInput" name="avatar_file" type="file">
                                </a>
                                </div>
                            <div class="row">
                                <div class="col-md-9">
                                    <div class="avatar-wrapper"></div>
                                </div>
                                <div class="col-md-3">
                                    <div class="avatar-preview preview-lg"></div>
                                    <div class="avatar-preview preview-md"></div>
                                    <div class="avatar-preview preview-sm"></div>
                                </div>
                            </div>
                            <div class="row avatar-btns">
                                <div class="col-md-9">
                                    <div class="btn-group">
                                        <button class="btn" data-method="rotate" data-option="-90" type="button" title="Rotate -90 degrees"><i class="fa fa-undo"></i> 向左旋转</button>
                                    </div>
                                    <div class="btn-group">
                                        <button class="btn" data-method="rotate" data-option="90" type="button" title="Rotate 90 degrees"><i class="fa fa-repeat"></i> 向右旋转</button>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <button class="btn btn-success btn-block avatar-save" type="submit"><i class="fa fa-save"></i> 保存修改</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
          </div>
        </div>
        <div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
	 	<script src="<%=path%>/js/userInfo/sitelogo.js"></script>
 		<script src="<%=path%>/js/userInfo/cropper.min.js"></script>
		<script src="<%=path%>/js/userInfo/icheck.min.js"></script>
<%@ include file="/include/footer.jsp"%>
