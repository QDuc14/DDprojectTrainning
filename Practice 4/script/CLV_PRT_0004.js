/*=========================================================
*Copyright(c) 2014 CyberLogitec. All Rights Reserved.
*@FileName   : CLV_PRT_0004.js
*@FileTitle  : CarrierMgmt
*@author     : ducquach
*@version    : 1.0
*@since      : 2022/06/30
=========================================================*/
/****************************************************************************************
  Event Identification Code: [Initialize]INIT=0; [Init input]ADD=1; [inquiry]SEARCH=2; [List Lookup]SEARCHLIST=3;
					[modification]MODIFY=4; [delete]REMOVE=5; [List Deletion]REMOVELIST=6 [Multiprocessing]MULTI=7
					Other extra character constants  COMMAND01=11; ~ COMMAND20=30;
 ***************************************************************************************/
   	/* 개발자 작업	*/
  	// global variable
	var tabObjects=new Array();
	var tabCnt=0 ;
	var beforetab=1;
	var sheetObjects=new Array();
	var sheetCnt=0;
	var rowcount=0;
	
	var comboObjects = new Array();
	var comboCnt = 0;
	var comboValues = "";
	// define an event handler that receive and handle button click events
	document.onclick=processButtonClick;
	
	// function for branching to the correct responding logic when a button is clicked
    function processButtonClick(){
         /***** If there are more than 2 sheets per tab, you can specify additional sheet variables to use *****/
         var sheetObject1=sheetObjects[0];
         /*******************************************************/
         var formObject=document.form;
         // if exception the UI will show an error pop-up
    	try {
    		// get name of object
    		var srcName=ComGetEvent("name");
    		// each obj have its action
            switch(srcName) {
            	case "btn_Add":
            		// To insert a row
            		doActionIBSheet(sheetObject1,formObject,IBINSERT);
            		break;
            	case "btn_Retrieve":
            		// to retrieve the search result and show on sheet 
            		doActionIBSheet(sheetObject1,formObject,IBSEARCH);
            		break;
            	case "btn_Save":
            		// to save changes on grid to server database 
            		doActionIBSheet(sheetObject1,formObject,IBSAVE);
            		break;
            	case "btn_DownExcel":
            		// to download the grid to excel to your computer
            		doActionIBSheet(sheetObject1,formObject,IBDOWNEXCEL);
            		break;
            	case "btn_dt_fr":
            		var cal = new ComCalendar;
            		cal.select(formObject.s_cre_dt_fm, 'yyyy-MM-dd');
            		break;
            	case "btn_dt_to":
            		var cal = new ComCalendar;
            		cal.select(formObject.s_cre_dt_to, 'yyyy-MM-dd');
            		break;
            	case "btn_New":
            		doActionIBSheet(sheetObject1,formObject,IBRESET);
            		break;
            	case "btn_Delete":
            		doActionIBSheet(sheetObject1,formObject,IBSAVE);
            		break;
            } // end switch
    	}catch(e) {
    		// if the exception is object error, pop-up will show a mess through OBJECT_ERROR code
    		//else if the exception not a object error the pop_up will show the the error thrown
    		if( e == "[object Error]") {
    			ComShowMessage(OBJECT_ERROR);
    		} else {
    			ComShowMessage(e);
    		}
    	}
    }
    /**
     * Register the IBSheet Objects created on the page in the sheetObjects array. <br>
     * The sheetObjects array is defined at the top as a common global variable, and this function is automatically called when the IBSheet Object is created by the {@link CoObject#ComSheetObject} function. <br>
     * @param {ibsheet} sheet_obj    IBSheet Object
     **/
    function setSheetObject(sheet_obj){
       sheetObjects[sheetCnt++]=sheet_obj;
    }

    /**
     * [loadPage] The function called in the body.onload event adds functionality
     * that needs to be pre-processed after the page finishes loading. <br>
     * Initializes various events of HTML control, IBSheet, IBTab, etc. <br>
     */
    function loadPage(){
		for(var i = 0; i < sheetObjects.length; i++) {
			ComConfigSheet(sheetObjects[i]);
			initSheet(sheetObjects[i], i + 1);
			ComEndConfigSheet(sheetObjects[i]);
		}
		for ( var k = 0; k < comboObjects.length; k++) {
			initCombo(comboObjects[k], k + 1);
		}
	
		doActionIBSheet(sheetObjects[0],document.form,IBSEARCH);
    	// IBSEARCH
    }
	/**
	 * [initCombo] functions that define the basic properties of the combobox
	 * @param comboObj
	 * @param comboNo
	 */
	function initCombo(comboObj, comboNo) {
		comboObj.SetTitle("ALL");
		comboObj.SetTitleVisible(true);
		comboObj.SetEnableAllCheckBtn(true);
//		comboObj.SetMultiSelect(0);
		var formObj = document.form;
		switch (comboNo) {
		case 1:
			with (comboObj) {
				SetMultiSelect(1);
		        SetDropHeight(200);
		        ValidChar(2,1);
			}
			var comboItems = carrierCombo.split("|");
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
	        for (var i = 0; i < comboItems.length; i++) {
	                comboObj.InsertItem(i, comboItems[i], comboItems[i]);
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
     * functions that define the basic properties of the sheet on the screen
     * 
     * @param sheetObj  IBSheet Object
     * @param sheetNo   Number of IBSheet Object
     */
    function initSheet(sheetObj,sheetNo) {
    	var cnt = 0;
    	switch (sheetNo) {
    		case 1:
    			with(sheetObj){  
    				var HeadTitle="STS|Del|Hidden Carrier|Hidden Rev. Lane|Carrier|Rev. Lane|Vendor Code|Customer Code|Customer Code|Trade|Delete Flag|Create Date|Create User ID|Update Date|Update User ID";
    				
    				SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:1 } );
    				
    				var info    = { Sort:1, ColMove:1, HeaderCheck:0, ColResize:1 };
    				var headers = [ { Text:HeadTitle, Align:"Center"} ];
    				InitHeaders(headers, info);
    				
    				var cols = [ 
    		            {Type:"Status",    Hidden:1, Width:50,  Align:"Center",  SaveName:"ibflag"}, 
    		            {Type:"DelCheck",  Hidden:0, Width:50,  Align:"Center",  SaveName:"del_chk"},
    		            {Type:"Text",      Hidden:1, Width:100, Align:"Center",  SaveName:"jo_crr_cd_hid"},
    			        {Type:"Text",      Hidden:1, Width:100, Align:"Center",  SaveName:"rlane_cd_hid"},
    			        {Type:"PopupEdit", Hidden:0, Width:100, Align:"Center",  SaveName:"jo_crr_cd",     KeyField:1, UpdateEdit:0, InsertEdit:1, AcceptKeys:"E",   InputCaseSensitive:1, EditLen:3},
    			        {Type:"ComboEdit", Hidden:0, Width:100, Align:"Center",  SaveName:"rlane_cd",      KeyField:1, UpdateEdit:0, InsertEdit:1, AcceptKeys:"N|E", InputCaseSensitive:1, EditLen:3, ComboCode:laneCombo, ComboText: laneCombo},
    			        {Type:"PopupEdit", Hidden:0, Width:100, Align:"Center",  SaveName:"vndr_seq",      KeyField:1, UpdateEdit:1, InsertEdit:1, AcceptKeys:"N",   EditLen:6},
    			        {Type:"Popup",     Hidden:0, Width:50,  Align:"Center",  SaveName:"cust_cnt_cd",   KeyField:1, UpdateEdit:1, InsertEdit:1, AcceptKeys:"E",   InputCaseSensitive:1, EditLen:2}, 
    				    {Type:"Popup",     Hidden:0, Width:100, Align:"Center",  SaveName:"cust_seq",      KeyField:1, UpdateEdit:1, InsertEdit:1, AcceptKeys:"N",   EditLen: 6}, 
    				    {Type:"PopupEdit", Hidden:0, Width:100, Align:"Center",  SaveName:"trd_cd",        KeyField:0, UpdateEdit:1, InsertEdit:1, AcceptKeys:"E",   InputCaseSensitive:1, EditLen:3},
    				    {Type:"Combo",     Hidden:0, Width:70,  Align:"Center",  SaveName:"delt_flg",      KeyField:0, UpdateEdit:1, InsertEdit:1, ComboCode:"N|Y",  ComboText:"N|Y"}, 
    				    {Type:"Text",      Hidden:0, Width:300, Align:"Center",  SaveName:"cre_dt",        KeyField:0, UpdateEdit:0, InsertEdit:0}, 
    				    {Type:"Text",      Hidden:0, Width:300, Align:"Left",    SaveName:"cre_usr_id",    KeyField:0, UpdateEdit:0, InsertEdit:0}, 
    				    {Type:"Text",      Hidden:0, Width:300, Align:"Center",  SaveName:"upd_dt",        KeyField:0, UpdateEdit:0, InsertEdit:0}, 
    				    {Type:"Text",      Hidden:0, Width:300, Align:"Left",    SaveName:"upd_usr_id",    KeyField:0, UpdateEdit:0, InsertEdit:0}
    			    ];
    		        InitColumns(cols);
    		        SetEditable(1);
    		        SetWaitImageVisible(0);
    		        resizeSheet();
    			}
    			break;
    	}
    }

    /**
     * Function to set the height according to the display
     */
    function resizeSheet() {
    	ComResizeSheet(sheetObjects[0]);
    }

    /**
     * functions that define transaction logic between UI and server
     * 
     * @param sheetObj  IBSheet Object
     * @param formObj   Form Object
     * @param sAction   Action code
     */ 
    function doActionIBSheet(sheetObj,formObj,sAction) {
    	switch(sAction) {
    		case IBSEARCH:
    			ComOpenWait(true);
    			var creFm = formObj.s_cre_dt_fm.value;
    			var creTo = formObj.s_cre_dt_to.value;
    			if (compareDate(creFm, creTo)){
    				ComShowMessage("invalid day order!");
    				ComOpenWait(false);
    			}
    			formObj.f_cmd.value = SEARCH;
    			sheetObj.DoSearch("CLV_PRT_0004GS.do", FormQueryString(formObj));
    			break;
    		case IBINSERT:
    			rowcount=sheetObj.RowCount();
				row=sheetObj.DataInsert();
    			break;
    		case IBSAVE:
    			formObj.f_cmd.value = MULTI;
                sheetObj.DoSave("CLV_PRT_0004GS.do", FormQueryString(formObj));
    			break;
    		case IBDOWNEXCEL:
    			if(sheetObj.RowCount() < 1){
    				ComShowCodeMessage("COM132501");
    			}
    			else{
    				sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj), SheetDesign:1, Merge:1});
    			}
    			break;
    		case IBRESET:
    			formObj.reset();
    			sheetObj.RemoveAll();
    			doActionIBSheet(sheet1, formObj, IBSEARCH);
    			break;
    	}
    }
    /**
     * [sheet1_OnPopupClick] function that show pop-up on click
     * @param sheetObj
     * @param Row
     * @param Col
     */
    function sheet1_OnPopupClick(sheetObj, Row, Col){
    	var name = sheetObj.ColSaveName(Col);
    	switch(name){
    	case "cust_cnt_cd":
    	case "cust_seq":
    		ComOpenPopup('/opuscntr/Customer.do', 1050, 550, 'setCustCd', '1,0,1,1,1,1', true);
    		break;
    	case "jo_crr_cd":
    		ComOpenPopup('/opuscntr/COM_ENS_0N1.do', 800, 500, 'setCrrCd', '1,0,0,1,1,1,1,1', true);
			break;
    	case "vndr_seq":
    		ComOpenPopup('/opuscntr/COM_COM_0007.do', 900, 520, 'setVndrCd', '1,0,1', true);
			break;
    	case "trd_cd":
    		ComOpenPopup('/opuscntr/COM_COM_0012.do', 800, 500, 'setTrdCd', '1,0,0,1,1,1,1,1', true);
			break;
    	}
    }
    /**
     * [setCustCd] function that set data on to pop-up
     * @param arrPopupData
     */
    function setCustCd (arrPopupData) {
    	console.log(arrPopupData)
    	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "cust_cnt_cd", arrPopupData[0][2]);
    	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "cust_seq", arrPopupData[0][3]);
    }
    /**
     * [setVndrCd] function that set data on to pop-up
     * @param arrPopupData
     */
    function setVndrCd (arrPopupData){
    	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "vndr_seq", arrPopupData[0][2]);
    }
    /**
     * [setTrdCd] function that set data on to pop-up
     * @param arrPopupData
     */
    function setTrdCd (arrPopupData){
    	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "trd_cd", arrPopupData[0][3]);
    }
    /**
     * [setCrrCd] function that set data on to pop-up
     * @param arrPopupData
     */
    function setCrrCd(arrPopupData) {
    	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "jo_crr_cd", arrPopupData[0][3]);
    }
    
    /**
     * [compareDate] funtion that check if date from > date to
     * @param creFm
     * @param creTo
     * @returns {Boolean}
     */
    function compareDate(creFm, creTo){
    	var dateFm = new Date(creFm);
    	var dateTo = new Date(creTo);
    	return creFm > creTo;
    }
    
    /**
     * Handling event after search.
     * 
     * @param sheetObj
     * @param Code
     * @param Msg
     * @param StCode
     * @param StMsg
     */
    function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
    	ComOpenWait(false);
    }
    
    /**
     * Handling event after save.
     * 
     * @param sheetObj
     * @param Code
     * @param Msg
     * @param StCode
     * @param StMsg
     */
    function sheet1_OnSaveEnd(sheetObj, Code, Msg, StCode, StMsg) { 
		if(Code < 0){
			alert(Msg)	;
			ComOpenWait(false);
		} 
		doActionIBSheet(sheetObj,document.form,IBRESET);
		ComOpenWait(false);
    }