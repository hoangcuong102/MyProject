<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="form-inline text-right">
	<div class="col-md-12">
		<div class="col-md-1-button text-left-button padding-right-btn-delete">
			<button type="button" class="btn btn-primary btn-logout log-out-css">Logout</button>
		</div>
		<div class="col-md-1-button">
			<button type="button" class="btn-download btn btn-primary" onclick="doDowload()">Download</button>
		</div>
		<div class="col-md-1-button">
			<button type="button" class="btn-close btn btn-primary" onclick="doClose()">Close</button>
		</div>
	</div>
</div>
