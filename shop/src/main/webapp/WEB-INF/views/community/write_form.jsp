<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include file="../layout/header.jsp"%>

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

<div class="container justify-content space-between">
 
   <form action="/board/upload" enctype="multipart/form-data" method="post">
   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <div class="form-group">
      <label for="title">title</label>
      <input type="text" class="form-control" placeholder="Enter title" name="title" id="title" />
    </div>

    <div class="form-group">
      <label for="image">image</label>
      <div class="input-group">
        <input type="file" name="file" class="form-control" id="image" required="required" accept=".jpg, .jpeg, .png, .gif">
        <label class="custom-file-label" for="customFile"></label>
      </div>
    </div>

    <div class="form-group mt-3">
      <label for="content">content</label>
      <textarea class="form-control" rows="5" id="content" name="content" placeholder="내용을 입력하세요"></textarea>
    </div>
    <div class="form-group mt-3">
  <button type="submit" id="btn-save" class="btn btn-dark" style="background-color: #453675;">글 쓰기 완료</button>
	</div>
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
