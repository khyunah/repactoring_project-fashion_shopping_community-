<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  
<style>
    .modal-body {
        max-height: 500px;
        overflow: scroll;
        overflow-x: hidden;
        scrollbar-width: none;
    }

    .modal-body::-webkit-scrollbar {
        display: none;
    }
    
    .h-modal-box {
    	padding: 20px;
    }
    
    .follow-flex {
    	display: flex;
    	justify-content: space-between;
    	flex: 1;
    }

    .follow-list-item {
        display: flex;
    }

    .circle-img-box {
        border-radius: 50%;
        width: 45px;
        height: 45px;
        overflow: hidden;
        margin-right: 20px;
        margin-bottom: 18px;
        border: 0.5px solid #aaaaaa;
		cursor: pointer;
    }
    
    .follow-list-username-box {
    	cursor: pointer;
    }

    .profile-img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .follow-list-username {
        font-weight: 600;
        font-size: 16px;
        margin-bottom: 2px;
    }

    .follow-list-name {
        font-size: 13px;
    }
    
    .h-modal-text {
    	font-family: "Gowun Batang", serif;
    }
    
    .h-modal-title {
    	font-weight: bold;
    }
    
    .ms-follow-btn {
    	color: white;
		text-decoration: none;
		background-color: #453675;
		padding: 2px 3px;
		margin-left: 20px;
		margin-top: 10px;
		font-size: 12px;
    }
</style>

    <div class="modal" id="myModal">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content h-modal-box">

                <div class="modal-header">
                    <h5 class="h-modal-title h-modal-text"></h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                  
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
<script src="/js/like_modal.js"></script>
