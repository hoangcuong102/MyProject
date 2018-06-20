<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="form-inline text-right">
	<div class="col-md-12">
		<div class="col-md-1-button text-left-button padding-right-btn-delete">
			<button type="button" class="btn-logout btn btn-primary log-out-css">Logout</button>
		</div>
		<s:if test='userAuthority == "3"'>
			<div class="col-md-1-button text-left-button">
				<button type="button" class="btn-delete btn btn-danger" id="cancel" disabled="disabled">Cancel</button>
			</div>
		</s:if>
		<div class="col-md-1-button text-left-button">
			<button type="button" class="btn-kddi btn btn-primary" id="goToKDDI" disabled="disabled">DO</button>
		</div>
		<div class="col-md-1-button text-left-button margin-left-btn">
			<button type="button" class="btn-log btn btn-primary" id="goToLog" disabled="disabled">Log</button>
		</div>
		<!-- <div class="col-md-1-button">
		</div> -->
		<div class="col-md-1-button vertical-bottom">
			<button type="button" class="btn-edit btn btn-primary" disabled="disabled">Edit</button>
		</div>

		<div class="col-md-1-button vertical-bottom">
			<button type="button" class="btn-download btn btn-primary" disabled="disabled">Download</button>
		</div>
		<!-- <div class="col-md-1-button vertical-bottom">
			<button type="button" class="btn-clear btn btn-primary">Clear</button>
		</div>
		<div class="col-md-1-button">
			<button type="button" class="btn-search btn btn-primary" onclick="doSearch();">Search</button>
		</div> -->
		<div class="col-md-1-button">
			<button type="button" class="btn-close btn btn-primary" onclick="doClose();">Close</button>
		</div>
	</div>
</div>