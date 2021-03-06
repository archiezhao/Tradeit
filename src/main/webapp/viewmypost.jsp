<!DOCTYPE html>
<html lang="en">
<head>
  <title>Login Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="/Tradeit/css/bootstrap.min.css">
  <link rel="stylesheet" href="/Tradeit/css/common.css">
  <script src="/Tradeit/js/jquery.min.js"></script>
  <script src="/Tradeit/js/bootstrap.min.js"></script>
  
  <script>
    var timer;
    var curPage = 1;
    $(document).ready(callShowResultDefault);
    
    function callShowResultDefault() {
    	$("#showmorebutton").hide();
    	showResult("");
    }
  	function callShowResult(str) {
  		clearTimeout(timer);
  		timer=setTimeout(showResult, 1000, str);
  	}
    var showResult = function(str) {
   	  if (window.XMLHttpRequest) {
   	    // code for IE7+, Firefox, Chrome, Opera, Safari
   	    xmlhttp=new XMLHttpRequest();
   	  } else {  // code for IE6, IE5
   	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
   	  }
   	  xmlhttp.onreadystatechange=function() {
   	    if (xmlhttp.readyState==4 && xmlhttp.status==200) {
   	      var obj = JSON.parse(xmlhttp.responseText);
   	      if(curPage == 1)
   	      	$("#itemlist ul").empty();
   	      if(obj.length >= 15)
   	    	$("#showmorebutton").show();  
   	      for(var i = 0; i < obj.length; i++) {
   	    	$("#itemlist ul").append('<li href="#my_modal" data-toggle="modal" data-post-title="' + obj[i].title + '" data-postid="' + obj[i].postid + '" data-price="' + obj[i].price + '" data-condition="' + obj[i].condition + '" data-descrip="' + obj[i].descrip + '" data-imageid1="' + obj[i].imageid1 + '" data-imageid2="' + obj[i].imageid2 + '" data-imageid3="' + obj[i].imageid3 + '" class="list-group-item hoverLi"><span>' + obj[i].title + '</span><span class="pull-right">$' + obj[i].price + '</span></li>')
   	      }   	      
   	    }
   	  }
	
	  xmlhttp.open("GET","getmypostlist.do?pageIndex=" + curPage,true);
	  curPage++;
	  xmlhttp.send();

    }
 
  </script>     
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
            	<li><a href="./createpost.jsp">Create a Post</a></li>
            	<li class="active"><a href="./viewmypost.jsp">My Posts</a></li>
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

<div class="container"> 
  <h3>My Posts</h3> 
  <div class="itemlist-group">
    <div id="itemlist">
      <ul class="list-group">
        <!--<li class="list-group-item"><span>test 1</span></li>-->
      </ul>
    </div>
  </div>
  <div class="show-more-button">
  	<button id="showmorebutton" type="button" class="btn" onclick="callShowResultDefault()">Show More</button>
  </div>
</div>

<div class="modal fade" id="my_modal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <div class="form-group">	
        	<h4 id="title" class="col-sm-5"></h4>
        </div>
      </div>
      <div class="modal-body">
        <div class="form-group">
			<label class="control-label col-sm-2" for="price">Price:</label>
			<div class="col-sm-5">
				<p id="price"></p>
			</div>
		</div>
		<br/>
        <div class="form-group">
			<label class="control-label col-sm-2" for="condition">Condition:</label>
			<div class="col-sm-5">
				<div id="condition"></div>
			</div>
		</div>
		<br/>
        <div class="form-group">
			<label class="control-label col-sm-2" for="description">Description:</label>
			<div class="col-sm-5">
				<p id="description"></p>
			</div>
		</div>
        <div id="image1"></div>
        <div id="image2"></div>
        <div id="image3"></div>
        <div>
        	<form action="deletepost.do" method="POST" autocomplete="off" id="deletepostform" role="form">
        		<input type="hidden" name="postid" id="deletepostid" value='1234'>
        		<input type="hidden" name="csrf_token" value="<%= session.getAttribute("csrf_token") %>"/>
        	</form>
        </div>
        <div class="form-group">
        	<button form="deletepostform" class="btn btn-danger btn-block" style="width:100%">Delete</button>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
	$('#my_modal').on('show.bs.modal', function(e) {
	    var postTitle = $(e.relatedTarget).data('post-title');
	    $(e.currentTarget).find('h4[id="title"]').text(postTitle);
	    var price = $(e.relatedTarget).data('price');
	    $(e.currentTarget).find('p[id="price"]').text('$' + price);
	    var descrip = $(e.relatedTarget).data('descrip');
	    $(e.currentTarget).find('p[id="description"]').text(descrip);
	    var condition = $(e.relatedTarget).data('condition');
	    $(e.currentTarget).find('div[id="condition"]').empty();
	    var postId = $(e.relatedTarget).data('postid');
	    $(e.currentTarget).find('input[id="deletepostid"]').val(postId);
	    var i = 0;
	    for(i = 0; i < condition; i++)
	    	$(e.currentTarget).find('div[id="condition"]').append('<span class="glyphicon glyphicon-star"></span>');
	    for(i = 0; i < (5-condition); i++)
	      $(e.currentTarget).find('div[id="condition"]').append('<span class="glyphicon glyphicon-star-empty"></span>');
	    var imageid1 = $(e.relatedTarget).data('imageid1');
	    $(e.currentTarget).find('div[id="image1"]').show();
	    $(e.currentTarget).find('div[id="image2"]').show();
	    $(e.currentTarget).find('div[id="image3"]').show();
	    if(imageid1 == 0)
	    	  $(e.currentTarget).find('div[id="image1"]').hide();
	    else
	    	  $(e.currentTarget).find('div[id="image1"]').html("<image src='/Tradeit/displaypostimage.do?id=" + imageid1 + "' style='width:565px;height:auto;' /><br/><br/>");
	    var imageid2 = $(e.relatedTarget).data('imageid2');
	    if(imageid2 == 0)
	  	  $(e.currentTarget).find('div[id="image2"]').hide();
	    else
	    	  $(e.currentTarget).find('div[id="image2"]').html("<image src='/Tradeit/displaypostimage.do?id=" + imageid2 + "' style='width:565px;height:auto;' /><br/><br/>");
	    var imageid3 = $(e.relatedTarget).data('imageid3');
	    if(imageid3 == 0)
	  	  $(e.currentTarget).find('div[id="image3"]').hide();
	    else
	    	  $(e.currentTarget).find('div[id="image3"]').html("<image src='/Tradeit/displaypostimage.do?id=" + imageid3 + "' style='width:565px;height:auto;' /><br/><br/>");
	});
</script>

<div class="footer">
	<div class="navbar">
	  <p id="author-info" align="center">
	  	Created by: Jianqi Zhao &nbsp; Contact: <a href="mailto:zhao61@indiana.edu">Email</a> | <a href="https://www.linkedin.com/in/jianqizhao">Linkedin</a>
	  </p>
	</div>
</div>

</body>
</html>