<%
/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Customer.jsp
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.22
*@LastModifier :
*@LastVersion : 1.0
* 2022.06.30
* 1.0 Creation
=========================================================*/
%>

<script language="javascript">
	function setupPage(){
		loadPage();
	}
</script>

<form name="form">
	<input type="hidden" name="f_cmd">
	<input type="hidden" name="pagerows">
	<!-- layer_popup_title(S) -->
	<div class="layer_popup_title">
		<!-- page_title_area(S) -->
		<div class="page_title_area clear">
			<!-- opus_design_btn(S) -->
			<div class="opus_design_btn">
			   <button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button><!--
			   --><button type="button" class="btn_normal" name="btn_OK" id="btn_Save">OK</button>
			</div>
			<!-- opus_design_btn(E) -->
			<!-- location(S) -->
		    <div class="location">
		     	<span id="navigation"></span>
		    </div>
		    <!-- location(E) -->
		</div>
		<!-- page_title_area(E) -->
	</div>
	<!-- layer_popup_title(E) -->
	<!-- wrap_search(S) -->
	<div class="wrap_search">
		<!-- opus_design_inquiry(S) -->
		<div class="opus_design_inquiry">
		    <table>
		        <tbody>
					<tr>
						<th width="40">Country</th>
						<td width="120">
							<input type="text" style="width:100px;" class="input" value="" name="s_cust_cnt_cd" id="s_cust_cnt_cd">
						</td>
						<th width="40">Sequence</th>
						<td>
							<input type="text" min=0 style="width:100px;" class="input" value="" name="s_cust_seq" id="s_cust_seq">
						</td>
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
			<script language="javascript">
				ComSheetObject('sheet1');
			</script>
		</div>	
		<!-- opus_design_grid(E) -->
	</div>
	<!-- wrap_result(E) -->
</form>