<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="form-inline">
	<div class="col-md-3">
		<div class="col-md-1-button text-left-button padding-right-btn-delete">
			<button type="button"
				class="btn-logout btn btn-primary btn-logout log-out-css">Logout</button>
		</div>
		<div class=" text-left-button">
			<button type="button" class="btn-function btn btn-danger">Cancel</button>
		</div>
	</div>
	<div class="col-md-5 text-center">
		<div class="col-md-1-button">
			<button type="button" class="btn-function btn btn-primary">DO</button>
		</div>
		<div class="col-md-1-button">
			<button type="button" class="btn-function btn btn-primary">Log</button>
		</div>
	</div>
	<div class="col-md-4 text-right">
		<div class="col-md-1-button">
			<button type="button" class="btn-add btn btn-primary"
				onclick="doEdit();">Edit</button>
		</div>
		<div class="col-md-1-button">
			<button type="button" class="btn-save btn btn-primary">Download</button>
		</div>
		<div class="col-md-1-button">
			<button type="button" class="btn-close btn btn-primary"
				onclick="doClose();">Close</button>
		</div>
	</div>
</div>