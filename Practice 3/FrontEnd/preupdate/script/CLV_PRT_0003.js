/*=========================================================
*Copyright(c) 2022 CyberLogitec. All Rights Reserved.
*@FileName   : CLV_PRT_0003.js
*@FileTitle  : Money Management
*@author     : ducquach
*@version    : 1.0
*@since      : 2022/07/05
=========================================================*/


var sheetObjects = new Array();

var sheetCnt = 0;
var comboObjects = new Array();
// combo box
var comboCnt = 0;
// search option
var searchSummary = "";
var searchDetail = "";
// Tab
var firstLoad = true;
var tabObjects = new Array();
var tabCnt = 0;
// Other
var beforetab = 0;
var MonthNoti = true;
var doubl = false;

document.onclick = processButtonClick;
/**
 * [processButtonClick] to handle button click event
 */
function processButtonClick(){
    /**
	 * *** If there are more than 2 sheets per tab, you can specify additional
	 * sheet variables to use ****
	 */
    var sheetObject1=sheetObjects[0];
    var sheetObject2=sheetObjects[1];
    var currentSheet = getCurrentSheet();
    /** **************************************************** */
    var formObject=document.form;
	try {
		var srcName=ComGetEvent("name");
       switch(srcName) {
       		case "btn_Retrieve":
       			if (MonthNoti)
       				monthCheck(formObject);
       			var Fm = formObject.fm_acct_yrmon.value;
    			var To = formObject.to_acct_yrmon.value;
    			if (!compareDate(Fm, To)){
    				ComShowCodeMessage('COM132401');
    				initCalendar();
    			}
    			// check if any missing mandatory missing on search option
//    			if(!searchBarCheck(formObject))
//    				break;
       			doActionIBSheet(currentSheet,formObject,IBSEARCH);
       			break;
       		case "btn_DownExcel":
       			doActionIBSheet(currentSheet,formObject,IBDOWNEXCEL);
       			break;
       		case "btn_vvd_from_next":
       			ascMonth(formObject.fm_acct_yrmon);
       			break;
       		case "btn_vvd_from_back":
       			descMonth(formObject.fm_acct_yrmon);
       			break;
       		case "btn_vvd_to_next":
       			ascMonth(formObject.to_acct_yrmon);
       			break;
       		case "btn_vvd_to_back":
       			descMonth(formObject.to_acct_yrmon);
       			break;
       		case "btn_New":
       			doActionIBSheet(currentSheet,formObject,IBRESET);
       			break;
       } 
       // end switch
	}catch(e) {
		if( e == "[object Error]") {
			ComShowMessage(OBJECT_ERROR);
		} else {
			ComShowMessage(e);
		}
	}
}

/**
 * [setSheetObject]Register the IBSheet Objects created on the page in the
 * sheetObjects array. <br>
 * The sheetObjects array is defined at the top as a common global variable, and
 * this function is automatically called when the IBSheet Object is created by
 * the {@link CoObject#ComSheetObject} function. <br>
 * 
 * @param {ibsheet}
 *            sheet_obj IBSheet Object
 */
function setSheetObject(sheet_obj){
   sheetObjects[sheetCnt++]=sheet_obj;
}

/**
 * [loadPage] The function called in the body.onload event adds functionality
 * that needs to be pre-processed after the page finishes loading. <br>
 * Initializes various events of HTML control, IBSheet, IBTab, etc. <br>
 */
function loadPage() {
	for(i=0;i<sheetObjects.length;i++){
		ComConfigSheet (sheetObjects[i]);
		initSheet(sheetObjects[i],i+1);
		ComEndConfigSheet(sheetObjects[i]);
	}
	// initializr combobox
	for ( var k = 0; k < comboObjects.length; k++) {
		initCombo(comboObjects[k], k + 1);
	}
	s_jo_crr_cd.SetItemCheck(0, 1, 1);
	s_trd_cd.SetEnable(false);
	s_rlane_cd.SetEnable(false);
	
	// initializing tabobject
	for (k = 0; k < tabObjects.length; k++) {
		initTab(tabObjects[k], k + 1);
		tabObjects[k].SetSelectedIndex(0);
	}
	
	initCalendar();
	
	doActionIBSheet(sheetObjects[0],document.form,IBSEARCH);
//	doActionIBSheet(sheetObjects[1],document.form,IBSEARCH);
}

/**
 * [initSheet] This function initSheet define the basic properties of the sheet
 * on the screen.
 * 
 * @param sheetObj:
 *            IBSheet Object.
 * @param sheetNo:
 *            Number of IBSheet Object.
 */
function initSheet(sheetObj,sheetNo) {
	var cnt = 0;
	switch (sheetNo) {
		case 1:
			with(sheetObj){    
				var HeadTitle1 = "STS|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider";
				var HeadTitle2 = "STS|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Code|Name";
	
	            SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:1 } );
	
	            var info    = { Sort:0, ColMove:0, HeaderCheck:0, ColResize:1 };
	            var headers = [ { Text: HeadTitle1, Align: "Center"},
	                            { Text: HeadTitle2, Align: "Center"}];
	            InitHeaders(headers, info);
	            
	            var cols = [ 
	       	             { Type: "Status", Hidden: 1, Width: 50,  Align: "Center",   ColMerge: 0, SaveName: "ibflag" },
	       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center",   ColMerge: 0, SaveName: "jo_crr_cd",       KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
	       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center",   ColMerge: 0, SaveName: "rlane_cd",        KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
	       	             { Type: "Text",   Hidden: 0, Width: 150, Align: "Center",   ColMerge: 0, SaveName: "inv_no",          KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
	       	             { Type: "Text",   Hidden: 0, Width: 200, Align: "Center",   ColMerge: 0, SaveName: "csr_no",          KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
	       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center",   ColMerge: 0, SaveName: "apro_flg",        KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0},
	       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center",   ColMerge: 0, SaveName: "locl_curr_cd",    KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0},
	       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center",   ColMerge: 0, SaveName: "inv_rev_act_amt", KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0},
	       	          	 { Type: "Text",   Hidden: 0, Width: 100, Align: "Center",   ColMerge: 0, SaveName: "inv_exp_act_amt", KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0},
	       	          	 { Type: "Text",   Hidden: 0, Width: 100, Align: "Center",   ColMerge: 0, SaveName: "prnr_ref_no",     KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0},
	       	          	 { Type: "Text",   Hidden: 0, Width: 100, Align: "Center",   ColMerge: 0, SaveName: "cust_vndr_eng_nm",KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0}
	       	             ];
	            InitColumns(cols);
				SetEditable(1);
				SetAutoSumPosition(1);
				SetWaitImageVisible(0);
				resizeSheet(); 
			}
			break;
		case 2:
			with(sheetObj){
				var HeadTitle1 = "STS|Partner|Lane|Invoice No|Slip No|Approved|Rev/Exp|Item|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider";
				var HeadTitle2 = "STS|Partner|Lane|Invoice No|Slip No|Approved|Rev/Exp|Item|Curr.|Revenue|Expense|Code|Name";
				
				SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:1 } );
				
	            var info    = { Sort:0, ColMove:0, HeaderCheck:0, ColResize:1 };
	            var headers = [ { Text: HeadTitle1, Align: "Center"},
	                            { Text: HeadTitle2, Align: "Center"}];
	            InitHeaders(headers, info);
	            
	            var cols = [ 
		       	             { Type: "Status", Hidden: 1, Width: 50,  Align: "Center", ColMerge: 0, SaveName: "ibflag" },
		       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "jo_crr_cd",       KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
		       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "rlane_cd",        KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
		       	             { Type: "Text",   Hidden: 0, Width: 150, Align: "Center", ColMerge: 0, SaveName: "inv_no",          KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
		       	             { Type: "Text",   Hidden: 0, Width: 200, Align: "Center", ColMerge: 0, SaveName: "csr_no",          KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0}, 
		       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "apro_flg",        KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0},
		       	             { Type: "Combo",  Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "rev_exp",         KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0, ComboText: "Rev|Exp", ComboCode: "R|E"},
		       	          	 { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "item",        	 KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0},
		       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "locl_curr_cd",    KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0},
		       	             { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "inv_rev_act_amt", KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0},
		       	          	 { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "inv_exp_act_amt", KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0},
		       	          	 { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "prnr_ref_no",     KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0},
		       	          	 { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "cust_vndr_eng_nm",KeyField: 1, Format: "", UpdateEdit: 0, InsertEdit: 0}
		       	             ];
		            InitColumns(cols);
					SetEditable(1);
					SetAutoSumPosition(1);
					SetWaitImageVisible(0);
					SetSheetHeight(500);
					resizeSheet();
			}
			break;
	}
}

/**
 * [resizeSheet]This function resize sheet, If don't call this functions, it
 * will may make UI breakable.
 */
function resizeSheet() {
	for (var i = 0; i < sheetObjects.length; i++) {
		ComResizeSheet(sheetObjects[i]);
	}
}

/**
 * Handles various functions (inquiry, storage, etc.) related to IBSheet. <br>
 * {@link #processButtonClick} Call this function from a function and use it to
 * refer to IBSheet's function on a button. <br>
 * 
 * @param {ibsheet}
 *            sheetObj IBSheet Object
 * @param {form}
 *            formObj Form Object
 * @param {int}
 *            sAction Action code to process (for example, IBSEARCH, IBSAVE,
 *            IBDELETE, IBDOWNEXCEL, etc., defined in CoObject.js)
 */
function doActionIBSheet(sheetObj,formObj,sAction) {
    switch(sAction) {
		case IBSEARCH:      // 조회
			
			if (sheetObj.id == "sheet1" ) {
				ComOpenWait(true);
				searchSummary = getStringSearch();
				formObj.f_cmd.value = SEARCH;
//	 			sheetObj.DoSearch("CLV_PRT_0003GS.do", FormQueryString(formObj));\
				var xml = sheetObj.GetSearchData("CLV_PRT_0003GS.do", FormQueryString(formObj));
				sheetObj.LoadSearchData(xml,{Sync:1});
			}
			else if (sheetObj.id == "sheet2"){
				ComOpenWait(true);
				searchDetail = getStringSearch();
				formObj.f_cmd.value = SEARCH01;
//				sheetObj.DoSearch("CLV_PRT_0003GS.do", FormQueryString(formObj));
				var xml = sheetObj.GetSearchData("CLV_PRT_0003GS.do", FormQueryString(formObj));
				sheetObj.LoadSearchData(xml,{Sync:1});
			}
			break;
		case IBDOWNEXCEL:	// 엑셀다운로드
			if(sheetObj.RowCount() < 1){
				ComShowCodeMessage("COM132501");
			}else{
				sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj), SheetDesign:1, Merge:1});
			}
			break;
		case IBRESET:
			formObj.reset();
			sheetObj.RemoveAll();
			initCalendar();
			s_jo_crr_cd.SetItemCheck(0, 1, 1);
			doActionIBSheet(sheetObjects[0],document.form,IBSEARCH);
//			doActionIBSheet(sheetObjects[1],document.form,IBSEARCH);
			tab1.SetSelectedIndex(0);
			break;
    }
}

/**
 * [initCalendar] functions that define the basic properties of the Yearmonth on
 * the screen
 */
function initCalendar(){
	var formObj = document.form;
	var ymTo = ComGetNowInfo("ym","-");
	formObj.to_acct_yrmon.value = ymTo;
	var ymFrom = ComGetDateAdd(formObj.to_acct_yrmon.value + "-01","M",-1);
	formObj.fm_acct_yrmon.value = GetDateFormat(ymFrom,"ym");	
}

/**
 * [GetDateFormat] functions that format date by "ym"
 * 
 * @param obj
 * @param sFormat
 * @returns {String}
 */
function GetDateFormat(obj, sFormat){
	var sVal = String(getArgValue(obj));
	sVal = sVal.replace(/\/|\-|\.|\:|\ /g,"");
	if (ComIsEmpty(sVal)) return "";
	
	var retValue = "";
	switch(sFormat){
		case "ym":
			retValue = sVal.substring(0,6);
			break;
	}
	retValue = ComGetMaskedValue(retValue,sFormat);
	return retValue;
}

/**
 * [ascMonth] ascending month when click button next
 * 
 * @param obj
 */
function ascMonth(obj){
	sheetObjects[0].RemoveAll();
	sheetObjects[1].RemoveAll();
	var ymFrom = ComGetDateAdd(obj.value + "-01","M",1);
	obj.value = GetDateFormat(ymFrom,"ym");
}

/**
 * [descMonth] descending month when click button back
 * 
 * @param obj
 */
function descMonth(obj){
	sheetObjects[0].RemoveAll();
	sheetObjects[1].RemoveAll();
	var ymFrom = ComGetDateAdd(obj.value + "-01","M",-1);
	obj.value = GetDateFormat(ymFrom,"ym");
}
/**
 * [compareDate] compare month
 * 
 * @param Fm
 * @param To
 * @returns {Boolean}
 */
function compareDate(Fm, To){
	var dateFm = new Date(Fm);
	var dateTo = new Date(To);
	return Fm < To;
}

/**
 * [monthCheck] check when search option is over 3 year
 * 
 * @param formObj
 */
function monthCheck(formObj){
	var month;
	var dateFm = new Date(formObj.fm_acct_yrmon.value);
	var dateTo = new Date(formObj.to_acct_yrmon.value);
	
	month = (dateTo.getFullYear() - dateFm.getFullYear()) * 12;
    month -= dateFm.getMonth();
    month += dateTo.getMonth();
	
	if(month >= 3){
		if(confirm("The search conditon is over 3 months.\n Do you want to continue?"))
			MonthNoti = false;
		else 
			initCalendar();
	}
		
}

/**
 * [initCombo] functions that define the basic properties of the combobox
 * 
 * @param comboObj
 * @param comboNo
 */
function initCombo(comboObj, comboNo) {
	var formObj = document.form;
	switch (comboNo) {
	case 1:
		with (comboObj) {
			SetMultiSelect(1);
	        SetDropHeight(250);
	        ValidChar(2,1);
		}
		// partner from ETCDATA and split "|"
		var comboItems = partnerList.split("|");
		addComboItem(comboObj, comboItems);
		break;
	}
}

/**
 * [addComboItem] functions that add data to the combobox
 * 
 * @param comboObj
 * @param comboItems
 */
function addComboItem(comboObj, comboItems) {
	for (var i=0 ; i < comboItems.length ; i++) {
		var comboItem=comboItems[i].split(",");
		if(comboItem.length == 1){
			comboObj.InsertItem(i, comboItem[0], comboItem[0]);
		}else{
			// insert one by one item into comboObj
			comboObj.InsertItem(i, comboItem[0] + "|" + comboItem[1], comboItem[1]);
		}
		
	}   		
}

/**
 * [setComboObject] to put combo objects in global variable "ComboObjects"
 * 
 * @param combo_obj
 */
function setComboObject(combo_obj) {
	comboObjects[comboCnt++] = combo_obj;
}
/**
 * [s_jo_crr_cd_OnChange] handle event after select a partner combo box item
 * 
 * @param OldText
 * @param OldIndex
 * @param OldCode
 * @param NewText
 * @param NewIndex
 * @param NewCode
 */
function s_jo_crr_cd_OnChange(OldText, OldIndex, OldCode, NewText, NewIndex, NewCode) {
	// handling events when user checks all item partner's combo
	console.log(OldIndex);
	console.log(NewIndex);
	if (OldIndex == 0) {
		s_jo_crr_cd.SetItemCheck(0, 0, 0);
	}
	
	if (NewIndex == -1) {
		s_jo_crr_cd.SetItemCheck(0, 1, 0);
		s_rlane_cd.RemoveAll();
		s_rlane_cd.SetEnable(false);
		s_trd_cd.RemoveAll();
		s_trd_cd.SetEnable(false);
	} else if (OldIndex != 0) {
		var newIndexArr = NewIndex.split(",");
		if (newIndexArr[newIndexArr.length - 1] == 0 && OldIndex != -1) {
			for (var i = 0; i < newIndexArr.length; i++) {
				var itemChk = parseInt(newIndexArr[i]);
				s_jo_crr_cd.SetItemCheck(itemChk, 0, 0);
			}
			s_jo_crr_cd.SetItemCheck(0, 1, 0);
			s_rlane_cd.RemoveAll();
			s_rlane_cd.SetEnable(false);
		}
		s_trd_cd.RemoveAll();
		s_trd_cd.SetEnable(false);
	}
	
	var formObj = document.form;
	formObj.f_cmd.value = SEARCH02;
	var xml = sheetObjects[0].GetSearchData("CLV_PRT_0003GS.do", FormQueryString(formObj));
	console.log(xml);
	var laneList = ComGetEtcData(xml, "lane");
	console.log(laneList);
	if(formObj.s_jo_crr_cd.value != "ALL"){
		s_rlane_cd.SetEnable(true);
		initLaneCombo(laneList);
	}
}
/**
 * [s_rlane_cd_OnChange] handle event after select a lane combo box item
 * 
 * @param OldText
 * @param OldIndex
 * @param OldCode
 * @param NewText
 * @param NewIndex
 * @param NewCode
 */
function s_rlane_cd_OnChange(OldText, OldIndex, OldCode, NewText, NewIndex, NewCode) {
	var formObj = document.form;
	formObj.f_cmd.value = SEARCH03;
	var xml = sheetObjects[0].GetSearchData("CLV_PRT_0003GS.do", FormQueryString(formObj));
	var tradeList = ComGetEtcData(xml, "trade");
	console.log(tradeList);
	if(formObj.s_rlane_cd.value != ''){
		s_trd_cd.SetEnable(true);
		initTradeCombo(tradeList);
	}
}
/**
 * [initLaneCombo] initialize lane combo box
 * 
 * @param laneList
 */
function initLaneCombo(laneList){
	var formObj = document.form;
	with(comboObjects[1]){
		RemoveAll();
		SetMultiSelect(0);
		SetDropHeight(200);
	}
	var comboItems = laneList.split("|");
	addComboItem(comboObjects[1], comboItems);
}
/**
 * [initTradeCombo] initialize trade combop box
 * 
 * @param tradeList
 */
function initTradeCombo(tradeList){
	var formObj = document.form;
	with(comboObjects[2]){
		RemoveAll();
		SetMultiSelect(0);
		SetDropHeight(200);
	}
	var comboItems = tradeList.split("|");
	addComboItem(comboObjects[2], comboItems);
}

/**
 * [setTabObject] set tab object
 * 
 * @param tab_obj :
 *            tab object
 */
function setTabObject(tab_obj) {
	tabObjects[tabCnt++] = tab_obj;
}

/**
 * [initTab] initialize tab object
 * 
 * @param tab_obj :
 *            tab object
 * @param tabNo
 */
function initTab(tabObj, tabNo) {
	switch (tabNo) {
		case 1:
			with (tabObj) {
				var cnt = 0;
				InsertItem("Summary", "");
				InsertItem("Detail", "");
			}
			break;
	}
}
/**
 * [tab1_OnChange] change tab
 * 
 * @param tabObj
 * @param nItem
 */
function tab1_OnChange(tabObj, nItem)
{
	var objs=document.all.item("tabLayer");
	objs[nItem].style.display="Inline";		
	// --------------- this is important! --------------------------//
	for(var i = 0; i<objs.length; i++){
		  if(i != nItem){
		   objs[i].style.display="none";
		   objs[beforetab].style.zIndex=objs[nItem].style.zIndex - 1 ;
		  }
		}
	// ------------------------------------------------------//
	beforetab=nItem;
	handleTabOnchange();
    resizeSheet();
} 

function getCurrentSheet(){
	return sheetObjects[beforetab];
}
/**
 * [handleTabOnchange] handle event after change tab
 */
function handleTabOnchange(){
	if(firstLoad){
		firstLoad = false;
		return;
	}
	var currentSheet = getCurrentSheet();
	var formQuery = getStringSearch();
	if(searchSummary!=formQuery && searchDetail!=formQuery && !doubl){
		if(confirm(msgs['COM130504'])){
			doActionIBSheet(currentSheet, document.form, IBSEARCH)
		}else{
			return;
		}
	}
	if(currentSheet.id=="sheet1"){
		if(searchSummary!=formQuery){
			console.log('sumaru');
			doActionIBSheet(currentSheet, document.form, IBSEARCH)
			
		}
	}else{
		if(searchDetail!=formQuery && !doubl){
			console.log('detaru');
			doActionIBSheet(currentSheet, document.form, IBSEARCH)
		}
	}
}

/**
 * [sheet1_OnDblClick] event fired when user double click any row on sheet 1
 * 
 * @param sheetObj
 * @param Row
 * @param Col
 */
function sheet1_OnDblClick(sheetObj, Row, Col) {
	formObj = document.form;
	doubl = true
	var rowCount = sheetObjects[1].RowCount();
	if(rowCount == 0) {
		doActionIBSheet(sheetObjects[1],formObj,IBSEARCH);
		tab1.SetSelectedIndex(1);
	}
	
	sheetObjects[1].SetSelectRow(getSelectRow(sheetObj, Row, Col));
	doubl = false;
}

/**
 * [getSelectRow] select data row in orther tab
 * 
 * @param sheetObj
 * @param Row
 * @param Col
 * @returns num
 */
function getSelectRow(sheetObj, Row, Col){
	var num = 0;
	if(sheetObj.GetCellValue(Row,"jo_crr_cd") != "") {
		var indexInv = sheetObj.GetCellValue(Row,"csr_no");
    	
    	for (var i = 2; i <= sheetObjects[1].RowCount() + 1; i++) {
    		if (sheetObjects[1].GetCellValue(i, "jo_crr_cd") != "") {
    			var indexInvCompare = sheetObjects[1].GetCellValue(i, "csr_no");
    			if (indexInvCompare === indexInv){
    				tab1.SetSelectedIndex(1);
    				num = i;
    				break;
    			}
    		}
    	}
	} return num;
}

/**
 * [getStringSearch] get current search option
 * 
 * @returns searchOptionString
 */
function getStringSearch(){
	var formObject = document.form;
	var searchOptionString = formObject.fm_acct_yrmon.value + formObject.to_acct_yrmon.value
						   + formObject.s_jo_crr_cd.value + formObject.s_rlane_cd.value 
						   + formObject.s_trd_cd.value;
	return searchOptionString;
}
/**
 * [searchBarCheck] to check missing mandatory
 * @param formObject
 * @returns {Boolean}
 */
function searchBarCheck(formObject){
	if (formObject.s_jo_crr_cd.value != 'ALL'){
		if (formObject.s_rlane_cd.value == ''){
			ComShowCodeMessage('COM130403','Lane Combo');
			return false;
		}else if (formObject.s_trd_cd.value ==''){
			ComShowCodeMessage('COM130403','Trade Combo');
			return false;
		}
	}
	return true;
}

/**
 * [sheet1_OnSearchEnd] handle event after search on sheet 1
 * 
 * @param sheetObj
 * @param Code
 * @param Msg
 * @param StCode
 * @param StMsg
 */
function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
	 console.log("sheet1");
 	ComOpenWait(false);
 	var totalRow = sheetObj.RowCount();
	for (var i = 1; i <= totalRow+1; i++){
		if (sheetObj.GetCellValue(i, "jo_crr_cd") == ''){
			if (sheetObj.GetCellValue(i, "inv_no") !== ''){
				sheetObj.SetRowFontColor(i,"#c55a11");
				sheetObj.SetRangeFontBold(i,1,i,10,1);
				sheetObj.SetCellValue(i,"inv_no","");
			}
			else if (sheetObj.GetCellValue(i,"inv_no") == ''){
				sheetObj.SetRowBackColor(i,"#f7caac");
				sheetObj.SetRangeFontBold(i,1,i,10,1);
			}

		}
	}
 }
/**
 * [sheet1_OnSearchEnd] handle event after search on sheet 2
 * 
 * @param sheetObj
 * @param Code
 * @param Msg
 * @param StCode
 * @param StMsg
 */
 function sheet2_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
	 console.log("shee2");
 	ComOpenWait(false);
 	var totalRow = sheetObj.RowCount();
	for (var i = 1; i <= totalRow+1; i++){
		if (sheetObj.GetCellValue(i, "jo_crr_cd") == ''){
			if (sheetObj.GetCellValue(i, "inv_no") !== ''){
				sheetObj.SetRowFontColor(i,"#c55a11");
				sheetObj.SetRangeFontBold(i,1,i,10,1);
				sheetObj.SetCellValue(i,"inv_no","");
			}
			else if (sheetObj.GetCellValue(i,"inv_no") == ''){
				sheetObj.SetRowBackColor(i,"#f7caac");
				sheetObj.SetRangeFontBold(i,1,i,10,1);
			}

		}
	}
 }
