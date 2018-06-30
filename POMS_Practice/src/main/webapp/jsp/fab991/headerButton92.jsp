<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="form-inline">
	<div class="col-md-12 button-padding">
		<div class="align-button">
			<button class="btn btn-primary btn-logout">Logout</button>
		</div>
		<div class="align-button">
			<button class="btn_delete btn btn-danger" disabled="disabled" onclick="doDelete()">Delete</button>
		</div>
		<div style="float: right; margin-right: 30px;">
			<div class=" align-button">
				<button class="btn-save btn btn-primary" onclick="doSave()">Save</button>
			</div>
			<div class=" align-button">
				<button class="btn-close btn btn-primary" onclick="doClose()">Close</button>
			</div>
		</div>
	</div>
</div>

