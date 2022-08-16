<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="../layout/header.jsp"%>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

<style>
  .container {
    margin-top: 100px;
  }

  .btn {
    float: right;
  }
</style>

<input type="hidden" value="${pricipal.user.id}" id="pricipal--id">
	<input type="hidden" value="${boardList.id}" id="boardId">



<div class="container justify-content space-between">
 
	<form action="/board/${boardList.id}/update" enctype="multipart/form-data" onsubmit="" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <div class="form-group">
      <label for="title" id=title>title</label>
      <input id="communityBoardTitle" value="${boardList.title}" type="text" class="form-control" placeholder="Enter title" name="title" id="title" />
    </div>

    <div class="form-group">
      <label for="image">image</label>
      <div class="input-group">
        <input type="file" name="file" class="form-control" id="image" accept=".jpg, .jpeg, .png, .gif">
        <label class="custom-file-label" for="customFile"></label>
      </div>
    </div>

    <div class="form-group mt-3">
      <label for="content">content</label>
      <textarea id="communityBoardContent" class="form-control" rows="5" name="content" placeholder="내용을 입력하세요">${boardList.content}</textarea>
    </div>
    <div class="form-group mt-3">
	</div>
  <button type="submit" id="commu-detail-btn-update"  class="btn btn-dark">글 수정 완료</button>
 </form>
</div>

<br />
<br />
<script>
  $(".custom-file-input").bind("change", function () {
    console.log($(this).val()); 
    let fileName = $(this).val().split("\\").pop();
    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
  });
</script>
<script type="text/javascript" src="/js/commu.js"></script>

