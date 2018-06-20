<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.text-right{
	text-align: right
}
.no-border-form-control{
	-webkit-box-shadow:none !important; 
	border:none !important
}
.red-star{
	color: red;
}
.no-padding-right{
	padding-right:0;
}
.no-padding-left{
	padding-left: 0;
}
.form-group.required .control-label:after {
  content:"*";color:red;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
  <h2>Modal Login Example</h2>
  <!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-default btn-lg" id="myBtn">Login</button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:0px 50px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
        </div>
        <div class="modal-body" >
          <form role="form">
            <div class="row">
            	<div class="col-md-3 text-right form-group required no-padding-right">
              		<label class="form-control no-border-form-control control-label" for="usrname">Category</label>
              	</div>
              	<div class="col-md-5 no-padding-left">	
              		<input type="text" class="form-control" id="usrname" placeholder="Enter email">
              	</div>
            </div>
			<div class="row">
				<div class="col-md-3 text-right form-group required no-padding-right">
              <label class="form-control no-border-form-control control-label" for="psw">Class</label>
              </div>
              <div class="col-md-3 no-padding-left">
              <input type="text" class="form-control" id="psw" placeholder="Enter password">
              </div>
              <div class="col-md-3 text-right no-padding-right">
              <label class="form-control no-border-form-control" for="psw">Class Title</label>
              </div>
              <div class="col-md-2 no-padding-left">
              <input type="text" class="form-control" id="psw" placeholder="Enter password">
              </div>
			</div>
			<div class="row">
				<div class="col-md-3 text-right no-padding-right">
              <label class="form-control no-border-form-control" for="psw">CD</label>
              </div>
              <div class="col-md-5 no-padding-left">
              <input type="text" class="form-control" id="psw" placeholder="Enter password">
              </div>
			</div>
			
			<div class="row">
				<div class="col-md-3 text-right no-padding-right">
              <label class="form-control no-border-form-control" for="value1">Value 1</label>
              </div>
              <div class="col-md-9 no-padding-left">
              <input type="text" class="form-control" id="value1" placeholder="Enter password">
              </div>
			</div>
			
			<div class="row">
				<div class="col-md-3 text-right no-padding-right">
              <label class="form-control no-border-form-control" for="psw">Value 2</label>
              </div>
              <div class="col-md-9 no-padding-left">
              <input type="text" class="form-control" id="psw" placeholder="Enter password">
              </div>
			</div>
			
			<div class="row">
				<div class="col-md-3 text-right no-padding-right">
              <label class="form-control no-border-form-control" for="psw">Value 3</label>
              </div>
              <div class="col-md-9 no-padding-left">
              <input type="text" class="form-control" id="psw" placeholder="Enter password">
              </div>
			</div>
			
			<div class="row">
				<div class="col-md-3 text-right no-padding-right">
              <label class="form-control no-border-form-control" for="psw">Value 4</label>
              </div>
              <div class="col-md-9 no-padding-left">
              <input type="text" class="form-control" id="psw" placeholder="Enter password">
              </div>
			</div>
			
			<div class="row">
				<div class="col-md-3 text-right no-padding-right">
              <label class="form-control no-border-form-control" for="psw">Value 5</label>
              </div>
              <div class="col-md-9 no-padding-left">
              <input type="text" class="form-control" id="psw" placeholder="Enter password">
              </div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-2 no-padding-right">
					<button type="submit" class="btn btn-primary center-block" data-dismiss="modal">Save</button>
				</div>
				<div class="col-md-2 no-padding-left">
					<button type="submit" class="btn btn-primary center-block btn-grey" data-dismiss="modal">Cancel</button>
				</div>
			</div>
          </form>
        </div>
      </div>
      
    </div>
  </div> 
</div>
</body>
</html>