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
							    <h3 class="panel-title">修改头像注意</h3>
							  </div>
							  <div class="panel-body">
							    <p>做个有头有脸的人!</p>
                               <p>上传你喜欢的照片并保存！</p>
                               <p>建议上传近距离的照片，比如大头照、特写。</p>
                               <p>您上传的图片已传到服务器，但是暂时不更换管理员头像。</p>
							  </div>
							</div>
                           <div id="preview-pane" class="preview-pane">
                               <div class="row">
                                   <div id="crop-avatar" class="col-md-6">
                                       <div class="avatar-view" title="点击更改头像">
                                       	<img src="http://huiwupay.com:9999/images/favicon.ico" alt="头像加载中...">
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
        
        <script>
        
        
        (function (factory) {
        	  if (typeof define === 'function' && define.amd) {
        	    define(['jquery'], factory);
        	  } else if (typeof exports === 'object') {
        	    // Node / CommonJS
        	    factory(require('jquery'));
        	  } else {
        	    factory(jQuery);
        	  }
        	})(function ($) {

        	  'use strict';

        	  var console = window.console || { log: function () {} };

        	  function CropAvatar($element) {
        	    this.$container = $element;

        	    this.$avatarView = this.$container.find('.avatar-view');
        	    this.$avatar = this.$avatarView.find('img');
        	    this.$avatarModal = $("body").find('#avatar-modal');
        	    this.$loading = $("#page-wrapper").find('.loading');

        	    this.$avatarForm = this.$avatarModal.find('.avatar-form');
        	    this.$avatarUpload = this.$avatarForm.find('.avatar-upload');
        	    this.$avatarSrc = this.$avatarForm.find('.avatar-src');
        	    this.$avatarData = this.$avatarForm.find('.avatar-data');
        	    this.$avatarInput = this.$avatarForm.find('.avatar-input');
        	    this.$avatarSave = this.$avatarForm.find('.avatar-save');
        	    this.$avatarBtns = this.$avatarForm.find('.avatar-btns');

        	    this.$avatarWrapper = this.$avatarModal.find('.avatar-wrapper');
        	    this.$avatarPreview = this.$avatarModal.find('.avatar-preview');

        	    this.init();
        	  }
        
        </script>
	 	<script src="<%=path%>/js/userInfo/sitelogo.js"></script>
 		<script src="<%=path%>/js/userInfo/cropper.min.js"></script>
		<script src="<%=path%>/js/userInfo/icheck.min.js"></script>
<%@ include file="/include/footer.jsp"%>
