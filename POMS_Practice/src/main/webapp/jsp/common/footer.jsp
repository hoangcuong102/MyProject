<%@ taglib uri="/struts-tags" prefix="s"%>
<!-- Modal confirm -->
<div class="modal fade" id="modalConfirm" role="dialog"
	data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content"
			style="margin-right: 50px; margin-left: 70px;">
			<div class="modal-header" style="padding: 0px 10px;">
				<button type="button" class="close" onclick="doCloseConfirm();">&times;</button>
				<h4>
					<span class="glyphicon glyphicon-question-sign"></span>
					Confirmation
				</h4>
			</div>
			<div class="modal-body">
				<form role="form">
					<div class="row">
						<div
							class="col-md-12 text-center form-group required no-padding-right"
							style="padding: 0px 10px 0px 15px;">
							<p style="float: left">
								<img src="<s:url value="/"/>style/imgs/confirm.png" width="30"
									height="30"><span id="contentConfirm"
									style="margin-left: 15px;"></span>
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-4">
							<button type="button"
								class="btn btn-primary center-block btn-block" id="buttonYes"
								data-toggle="modal" data-dismiss="modal">Yes</button>
						</div>
						<div class="col-md-4">
							<button type="button"
								class="btn btn-primary center-block btn-block btn-grey"
								id="buttonNo" onclick="doCloseConfirm();">No</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- Modal confirm end-->

<!-- Modal error -->
<div class="modal fade" id="modalInfo" role="dialog"
	data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content"
			style="margin-right: 100px; margin-left: 100px;">
			<div class="modal-header" style="padding: 0px 10px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 id="titleModal">
					<span class="glyphicon glyphicon-remove-circle"></span>
					Error
				</h4>
				<%-- <h4><span class="glyphicon glyphicon-remove-circle"></span>  Error</h4> --%>
			</div>
			<div class="modal-body">
				<form role="form">
					<div class="row">
						<div class="col-md-12 form-group required no-padding-right">
							<p class="text-center">
								<img src="<s:url value="/"/>style/imgs/errorIcon.png" id="modalInfo_image" width="30" height="30"><span
									id="contentMessage" style="margin-left: 15px;"></span>
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<button type="button"
								class="btn btn-primary center-block btn-block" id="btnOk"
								data-dismiss="modal">OK</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- Modal error end-->

<!-- Modal error2 -->
<div class="modal fade" id="modalInfo2" role="dialog"
	data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content"
			style="margin-right: 100px; margin-left: 100px;">
			<div class="modal-header" style="padding: 0px 10px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 id="titleModal">
					<span class="glyphicon glyphicon-remove-circle"></span>
					Error
				</h4>
				<%-- <h4><span class="glyphicon glyphicon-remove-circle"></span>  Error</h4> --%>
			</div>
			<div class="modal-body">
				<form role="form">
					<div class="row">
						<div class="col-md-12 form-group required no-padding-right">
							<p class="text-center">
								<img src="<s:url value="/"/>style/imgs/errorIcon.png" id="modalInfo_image" width="30" height="30"><span
									id="contentMessage2" style="margin-left: 15px;"></span>
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-4">
							<button type="button"
								class="btn btn-primary center-block btn-block" id="buttonYes2"
								data-toggle="modal" data-dismiss="modal">Yes</button>
						</div>
						<div class="col-md-4">
							<button type="button"
								class="btn btn-primary center-block btn-block btn-grey"
								id="buttonNo2" onclick="">No</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- Modal error end-->

<!-- Modal warning -->
<div class="modal fade" id="modalWarning" role="dialog"
	data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content"
			style="margin-right: 50px; margin-left: 70px;">
			<div class="modal-header" style="padding: 0px 10px;">
				<button type="button" class="close" onclick="doCloseWarning()">&times;</button>
				<h4>
					<span class="glyphicon glyphicon-warning-sign"></span> Warning
				</h4>
			</div>
			<div class="modal-body">
				<form role="form">
					<div class="row">
						<div class="col-md-12 form-group required no-padding-right">
							<p style="float: left">
								<img src="<s:url value="/"/>style/imgs/warning.png" width="30"
									height="30"><span id="contentWarning"
									style="margin-left: 15px;"></span>
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-4">
							<button type="button"
								class="btn btn-primary center-block btn-block" id="buttonYesWar"
								data-toggle="modal" data-dismiss="modal">[Yes]</button>
						</div>
						<div class="col-md-4">
							<button type="button"
								class="btn btn-primary center-block btn-block btn-grey"
								id="buttonNoWar" onclick="doCloseWarning()">[No]</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- Modal warning end-->

<!-- Modal warning -->
<div class="modal fade" id="modalWarning2" role="dialog"
	data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content"
			style="margin-right: 15px; margin-left: 15px;">
			<div class="modal-header" style="padding: 0px 10px;">
				<button type="button" class="close" onclick="doCloseWarning()">&times;</button>
				<h4>
					<span class="glyphicon glyphicon-warning-sign"></span> Warning
				</h4>
			</div>
			<div class="modal-body">
				<form role="form">
					<div class="row">
						<div
							class="col-md-12 text-center form-group required no-padding-right">
							<p style="float: left">
								<img src="<s:url value="/"/>style/imgs/warning.png" width="30"
									height="30"><span id="contentWarning2"
									style="margin-left: 15px;"></span>
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-4">
							<button type="button"
								class="btn btn-primary center-block btn-block"
								id="buttonYesWar2" data-toggle="modal" data-dismiss="modal">Proceed</button>
						</div>
						<div class="col-md-4">
							<button type="button"
								class="btn btn-primary center-block btn-block btn-grey"
								id="buttonNoWar2" onclick="doCloseWarning()">Return</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- Modal warning end-->
<!-- Modal confirm file not found -->
<div class="modal fade" id="modalConfirmFileNotFound" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog modal-sm">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:0px 10px;">
          <button type="button" class="close" onclick="doCloseConfirmFileNotFound();">&times;</button>
          <h4><span class="glyphicon glyphicon-remove-circle"></span>    Error</h4>
        </div>
        <div class="modal-body" >
          <form role="form">
            <div class="row">
            	<div class="col-md-12 text-right form-group required no-padding-right">
              		<p style="float:left"><img src="<s:url value="/"/>style/imgs/errorIcon.png" width="30" height="30"><span id="contentConfirmFileNotFound" style="margin-left: 15px;"></span></p>
              	</div>
            </div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<button type="button" class="btn btn-primary center-block btn-block" id="buttonYesFileNotFound" data-toggle="modal" data-dismiss="modal" onclick="doCloseConfirmFileNotFound();">OK</button>
				</div>
				<div class="col-md-4">
				</div>
			</div>
          </form>
        </div>
      </div>
    </div>
</div>
<!-- Modal confirm end-->

<!-- Modal confirm logout-->
<div class="modal fade" id="modalConfirmLogout" role="dialog"
	data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content"
			style="margin-right: 50px; margin-left: 70px;">
			<div class="modal-header" style="padding: 0px 10px;">
				<button type="button" class="close" onclick="doCloseConfirmLogout();">&times;</button>
				<h4>
					<span class="glyphicon glyphicon-question-sign"></span>
					Confirmation
				</h4>
			</div>
			<div class="modal-body">
				<form role="form">
					<div class="row">
						<div
							class="col-md-12 text-center form-group required no-padding-right"
							style="padding: 0px 10px 0px 15px;">
							<p style="float: left">
								<img src="<s:url value="/"/>style/imgs/confirm.png" width="30"
									height="30"><span id="contentConfirmLogout"
									style="margin-left: 15px;"></span>
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-4">
							<button type="button"
								class="btn btn-primary center-block btn-block" id="buttonYesLogout"
								data-toggle="modal" data-dismiss="modal">Proceed</button>
						</div>
						<div class="col-md-4">
							<button type="button"
								class="btn btn-primary center-block btn-block btn-grey"
								id="buttonNoLogout" onclick="doCloseConfirmLogout();">Return</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- Modal confirm logout end-->