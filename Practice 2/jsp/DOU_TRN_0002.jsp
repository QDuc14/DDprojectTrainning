<%
/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_0002.jsp
*@FileTitle :
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.26
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.20
* 1.0 Creation
=========================================================*/
%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.clt.framework.component.util.JSPUtil"%>
<%@ page import="com.clt.framework.component.util.DateTime"%>
<%@ page import="com.clt.framework.component.message.ErrorHandler"%>
<%@ page import="com.clt.framework.core.layer.event.GeneralEventResponse"%>
<%@ page import="com.clt.framework.support.controller.html.CommonWebKeys"%>
<%@ page import="com.clt.framework.support.view.signon.SignOnUserAccount"%>
<%@ page import="com.clt.apps.opus.dou.doutraining.codemgmt.event.DouTrn0002Event"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	DouTrn0002Event  event = null;				//PDTO(Data Transfer Object including Parameters)
	Exception serverException   = null;			//서버에서 발생한 에러
	String strErrMsg = "";						//에러메세지
	int rowCount	 = 0;						//DB ResultSet 리스트의 건수

	String successFlag = "";
	String codeList  = "";
	String pageRows  = "100";


	try {
		event = (DouTrn0002Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);

		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}

		// 초기화면 로딩시 서버로부터 가져온 데이터 추출하는 로직추가 ..
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");

	}catch(Exception e) {
		out.println(e.toString());
	}
%>

<script language="javascript">
	function setupPage(){
		var errMessage = "<%=strErrMsg%>";
		if (errMessage.length >= 1) {
			ComShowMessage(errMessage);
		} // end if
		loadPage();
	}
</script>
<form name="form">
<input type="hidden" name="f_cmd">
<input type="hidden" name="pagerows">
	<!-- page_title_area(S) -->
	<div class="page_title_area clear">
		<!-- page_title(S) -->
		<h2 class="page_title"><button type="button"><span id="title">PRACTICE 2</span></button></h2>
		<!-- page_title(E) -->
		<!-- opus_design_btn(S) -->
		<div class="opus_design_btn">
		   <button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button><!--
		   --><button type="button" class="btn_normal" name="btn_Save" id="btn_Save">Save</button><!--
		   -->
		</div>
		<!-- opus_design_btn(E) -->
		<!-- page_location(S) -->
	    <div class="location">
	     	<span id="navigation"></span>
	    </div>
	    <!-- page_location(E) -->
	</div>
	<!-- page_title_area(E) -->
	<!-- wrap_search(S) -->
	<div class="wrap_search">
		<!-- opus_design_inquiry(S) -->
		<div class="opus_design_inquiry wFit">
		    <table>
		        <tbody>
				<tr>
				   <th width="40">Subsystem</th>
				<td width="120"><input type="text" style="width:100px;" class="input" value="" name="s_ownr_sub_sys_cd" id="s_ownr_sub_sys_cd"></td>
				<th width="40">Cd ID</th>
				<td><input type="text" style="width:100px;" class="input" value="" name="s_intg_cd_id" id="s_intg_cd_id"></td>
				</tr> 
				</tbody>
			</table>
		</div>
		<!-- opus_design_inquiry(E) -->
	</div>
	<!-- wrap_search(E) -->
	<!-- wrap_result(S) -->
	<div class="wrap_result">
		<!-- opus_design_grid(S) -->
		<div class="opus_design_grid">
			<h3 class="title_design">Master</h3>
			<!-- opus_design_btn(S) -->
			<div class="opus_design_btn">
				<button type="button" class="btn_normal" name="btn_Add_Master" id="btn_Add_Master">Row Add</button><!-- 
			 --><button type="button" class="btn_normal" name="btn_delete_master" id="btn_delete_master">Row Delete</button>
			</div>
			<!-- opus_design_btn(E) -->
		</div>
		<script language="javascript">ComSheetObject('sheet1');</script>
		<!-- opus_design_grid(E) -->
		<!-- opus_design_grid(S) -->
		<div class="opus_design_grid">
			<h3 class="title_design">Detail</h3>
			<!-- opus_design_btn(S) -->
			<div class="opus_design_btn">
				<button type="button" class="btn_normal" name="btn_Add_Detail" id="btn_Add_Detail">Row Add</button><!--
			 --><button type="button" class="btn_normal" name="btn_delete_detail" id="btn_delete_detail">Row Delete</button>
			</div>
			<!-- opus_design_btn(E) -->
		</div>
		<script language="javascript">ComSheetObject('sheet2');</script>
		<!-- opus_design_grid(E) -->
	</div>
	<!-- wrap_result(E) -->

</form>