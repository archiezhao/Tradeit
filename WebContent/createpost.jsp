<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login Page</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/Tradeit/css/bootstrap.min.css">
	<script src="/Tradeit/js/jquery.min.js"></script>
	<script src="/Tradeit/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="./css/dropzone.css">
	<link rel="stylesheet" href="/Tradeit/css/common.css">
	<script src="./js/dropzone.js"></script>
	
	</head>
	<body>
		<nav class="navbar navbar-default">
		<div class="container-fluid">
		  <div class="navbar-header">
		    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>                        
		    </button>
		    <a class="navbar-brand" href="#">Tradeit</a>
		  </div>
		  <div class="collapse navbar-collapse" id="myNavbar">
		    <ul class="nav navbar-nav">
		      <li><a href="./viewpost.html">Buy</a></li>
		      <li class="dropdown active">
		     		<a class="dropdown-toggle" data-toggle="dropdown" href="#">Sell<span class="caret"></span></a>
		      	<ul class="dropdown-menu">
		          	<li class="active"><a href="./createpost.jsp">Create a Post</a></li>
		          	<li><a href="./viewmypost.jsp">My Posts</a></li>
		        	</ul>
		      </li>
		      <li><a href="./about.html">About</a></li>
		    </ul>
		    <ul class="nav navbar-nav navbar-right">
		      <li><a href="./logout.do"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		    </ul>
		  </div>
		</div>
		</nav>
		<div>
			<div class="container">	
					<form action="createpost.do" method="POST" class="form-horizontal" enctype="multipart/form-data" autocomplete="off" id="postform" role="form">
						<div class="form-group">
							<label class="control-label col-sm-2" for="posttitle">Title:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="posttitle" id="posttitle">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="postprice">Price($):</label>
							<div class="col-sm-2">
								<input type="number" class="form-control" name="postprice" id="postprice">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="conditon">Condition:</label>
							<div class="col-sm-10">
								<label class="radio-inline"><input type="radio" name="condition" value="5" checked>New</label>
								<label class="radio-inline"><input type="radio" name="condition" value="4">Like New</label>
								<label class="radio-inline"><input type="radio" name="condition" value="3">Very Good</label>
								<label class="radio-inline"><input type="radio" name="condition" value="2">Good</label>
								<label class="radio-inline"><input type="radio" name="condition" value="1">Acceptable</label>
								<label class="radio-inline"><input type="radio" name="condition" value="0">Other(Please describe)</label>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="description">Description:</label>
							<div class="col-sm-10">
								<textarea class="form-control" rows="5" name="description" form="postform" placeholder="Describe the item here..."></textarea>
								<input type="hidden" name="csrf_token" value="<%= session.getAttribute("csrf_token") %>"/>
							</div>
						</div>
					</form>
					<div class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-2" style="text-align: right;">Image:</label>
							<div class="col-sm-10">
								<form action="/Tradeit/uploadimage.do" class="dropzone" id="dropzone-file-uploader" name="itemimage">
									<input type="hidden" name="csrf_token" value="<%= session.getAttribute("csrf_token") %>"/>
								</form>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2"></div>
							<div class="col-sm-10">
								<button type="submit" form="postform" class="btn btn-primary btn-block" style="width:100%" id="post-submit">Submit</button>
							</div>
						</div>		
					</div>
			</div>		
		</div>

		<div class="footer">
			<div class="navbar">
			  <p id="author-info" align="center">
			  	Created by: Jianqi Zhao &nbsp; Contact: <a href="mailto:zhao61@indiana.edu">Email</a> | <a href="https://www.linkedin.com/in/jianqizhao">Linkedin</a>
			  </p>
			</div>
		</div>	
	
	</body>
	
</html>

