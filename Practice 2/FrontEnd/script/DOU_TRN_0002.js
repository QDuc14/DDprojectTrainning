/*=========================================================
*Copyright(c) 2014 CyberLogitec. All Rights Reserved.
*@FileName   : JSP_0001.js
*@FileTitle  : Error Message Management
*@author     : ducquach
*@version    : 1.0
*@since      : 2022/06/13
=========================================================*/
/****************************************************************************************
  Event Identification Code: [Initialize]INIT=0; [Init input]ADD=1; [inquiry]SEARCH=2; [List Lookup]SEARCHLIST=3;
					[modification]MODIFY=4; [delete]REMOVE=5; [List Deletion]REMOVELIST=6 [Multiprocessing]MULTI=7
					Other extra character constants  COMMAND01=11; ~ COMMAND20=30;
 ***************************************************************************************/
   	/* 개발자 작업	*/
  	// 공통전역변수
	var tabObjects=new Array();
	var tabCnt=0 ;
	var beforetab=1;
	var sheetObjects=new Array();
	var sheetCnt=0;
	var rowcount=0;
	
	var cdID="";
	// 버튼클릭이벤트를 받아 처리하는 이벤트핸들러 정의 */
	document.onclick=processButtonClick;
	// 버튼 네임으로 구분하여 프로세스를 분기처리하는 이벤트핸들러 */
    function processButtonClick(){
         /***** If there are more than 2 sheets per tab, you can specify additional sheet variables to use *****/
         var sheetObject1=sheetObjects[0];
         var sheetObject2=sheetObjects[1];
         /*******************************************************/
         var formObject=document.form;
    	try {
    		var srcName=ComGetEvent("name");
            switch(srcName) {
            	case "btn_Add_Master":
            		doActionIBSheet(sheetObject1,formObject,IBINSERT);
            		break;
            	case "btn_Add_Detail":
            		cdID = formObject.s_intg_cd_id.value
            		console.log(cdID);
            		doActionIBSheet(sheetObject2,formObject,IBINSERT);
            		break;
            	case "btn_Retrieve":
            		doActionIBSheet(sheetObject1,formObject,IBSEARCH);
            		break;
            	case "btn_Save":
            		if (sheetObject1.IsDataModified()){
            			doActionIBSheet(sheetObject1,formObject,IBSAVE);
            		}
            		else if (sheetObject2.IsDataModified()){
            			doActionIBSheet(sheetObject2,formObject,IBSAVE);
            		}
            		break;
            	case "btn_delete_master":
            			doActionIBSheet(sheetObject1,formObject,IBDELETE);
            		break;
            	case "btn_delete_detail":
            			doActionIBSheet(sheetObject2,formObject,IBDELETE);
            		break;
            	case "btn_DownExcel":
            		doActionIBSheet(sheetObject1,formObject,IBDOWNEXCEL);
            		break;
            } // end switch
    	}catch(e) {
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
     * The function called in the body.onload event adds functionality that needs to be pre-processed after the page finishes loading. <br>
     * Initializes various events of HTML control, IBSheet, IBTab, etc. <br>
     **/
    function loadPage() {
		for(i=0;i<sheetObjects.length;i++){
			ComConfigSheet (sheetObjects[i]);
			initSheet(sheetObjects[i],i+1);
			ComEndConfigSheet(sheetObjects[i]);
		}doActionIBSheet(sheetObjects[0],document.form,IBSEARCH);
	}
    /**
     * Initializes the IBSheet Object on the page. <br>
     * If there are multiple IBSheet, add as many cases as you can to configure the IBSheet initialization module. <br>
     * {@link #loadPage}The function calls this function to initialize the IBSheet Object.<br>
     * @param {ibsheet} sheetObj    IBSheet Object
     * @param {int}     sheetNo     sheetObjects Sequence number in array
     **/
    function initSheet(sheetObj,sheetNo) {
        var cnt=0;
		var sheetID=sheetObj.id;
        switch(sheetNo) {
        	case 1:
        		with(sheetObj){           
	             	var HeadTitle="STS|SubSystem|Cd ID|Cd Name|Length|Cd Type|Table Name|Description Remark|Flag|Create User|Create Date|Update User|Update Date" ;
	             	var headCount=ComCountHeadTitle(HeadTitle);
	
	             	SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:1 } );
	
	             	var info    = { Sort:1, ColMove:1, HeaderCheck:0, ColResize:1 };
	             	var headers = [ { Text:HeadTitle, Align:"Center"} ];
	             	InitHeaders(headers, info);
	             
	             	var cols = [ 
	             	            {Type:"Status",    Hidden:1,    Width:50,     Align:"Center",    ColMerge:0,    SaveName:"ibflag",             KeyField:0,    CalcLogic:"",    Format:"",       PointCount:0,    UpdateEdit:1,    InsertEdit:1 },
	             	            {Type:"Text",      Hidden:0,    Width:70,     Align:"Center",    ColMerge:0,    SaveName:"ownr_sub_sys_cd",    KeyField:0,    CalcLogic:"",    Format:"",       PointCount:0,    UpdateEdit:1,    InsertEdit:1, AcceptKeys : "E", InputCaseSensitive : 1 },
	             	            {Type:"Text",      Hidden:0,    Width:60,     Align:"Center",    ColMerge:0,    SaveName:"intg_cd_id",         KeyField:1,    CalcLogic:"",    Format:"",       PointCount:0,    UpdateEdit:0,    InsertEdit:1, AcceptKeys : "E", InputCaseSensitive : 1 },
	             	            {Type:"Text",      Hidden:0,    Width:200,    Align:"Left",      ColMerge:0,    SaveName:"intg_cd_nm",         KeyField:0,    CalcLogic:"",    Format:"",       PointCount:0,    UpdateEdit:1,    InsertEdit:1 },
	             	            {Type:"Text",      Hidden:0,    Width:50,     Align:"Center",    ColMerge:0,    SaveName:"intg_cd_len",        KeyField:0,    CalcLogic:"",    Format:"",       PointCount:0,    UpdateEdit:1,    InsertEdit:1, AcceptKeys : "N" },
	             	            {Type:"Combo",     Hidden:0,    Width:100,    Align:"Center",    ColMerge:0,    SaveName:"intg_cd_tp_cd",      KeyField:0,    CalcLogic:"",    Format:"",       PointCount:0,    UpdateEdit:1,    InsertEdit:1, ComboCode:"G", ComboText:"General"},
	             	            {Type:"Text",      Hidden:0,    Width:150,    Align:"Left",      ColMerge:0,    SaveName:"mng_tbl_nm",         KeyField:1,    CalcLogic:"",    Format:"",       PointCount:0,    UpdateEdit:0,    InsertEdit:1 },
	             	            {Type:"Text",      Hidden:0,    Width:350,    Align:"Left",      ColMerge:0,    SaveName:"intg_cd_desc",       KeyField:0,    CalcLogic:"",    Format:"",       PointCount:0,    UpdateEdit:1,    InsertEdit:1 },
	             	            {Type:"Combo",     Hidden:0,    Width:40,     Align:"Center",    ColMerge:0,    SaveName:"intg_cd_use_flg",    KeyField:0,    CalcLogic:"",    Format:"",       PointCount:0,    UpdateEdit:1,    InsertEdit:1, ComboCode:"N|Y", ComboText:"N|Y"},
	             	            {Type:"Text",      Hidden:0,    Width:80,     Align:"Center",    ColMerge:0,    SaveName:"cre_usr_id",         KeyField:0,    CalcLogic:"",    Format:"",       PointCount:0,    UpdateEdit:0,    InsertEdit:0 },
	             	            {Type:"Date",      Hidden:0,    Width:80,     Align:"Center",    ColMerge:0,    SaveName:"cre_dt",             KeyField:0,    CalcLogic:"",    Format:"Ymd",    PointCount:0,    UpdateEdit:0,    InsertEdit:0 },
	             	            {Type:"Text",      Hidden:0,    Width:80,     Align:"Center",    ColMerge:0,    SaveName:"upd_usr_id",         KeyField:0,    CalcLogic:"",    Format:"",       PointCount:0,    UpdateEdit:0,    InsertEdit:0 },
	             	            {Type:"Date",      Hidden:0,    Width:80,     Align:"Center",    ColMerge:0,    SaveName:"upd_dt",             KeyField:0,    CalcLogic:"",    Format:"Ymd",    PointCount:0,    UpdateEdit:0,    InsertEdit:0 } 
	             	            ]; 
	             InitColumns(cols);
	             SetEditable(1);
	             SetWaitImageVisible(0);
		         SetSheetHeight(400);
	         }

	         break;
	         
        	case 2:
	        	with(sheetObj){
	                var HeadTitle="STS|Cd ID|Cd Val|Display Name|Description Remark|Order" ;
	                var headCount=ComCountHeadTitle(HeadTitle);
	                
	                SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:0 } );

	                var info    = { Sort:1, ColMove:1, HeaderCheck:0, ColResize:1 };
	                var headers = [ { Text:HeadTitle, Align:"Center"} ];
	                InitHeaders(headers, info);

	                var cols = [ 
		                {Type:"Status", Hidden:1, Width:50,  Align:"Center", ColMerge:1, SaveName:"ibflag",              KeyField:0, CalcLogic:"", Format:"", PointCount:0, UpdateEdit:1, InsertEdit:1 },
					    {Type:"Text",   Hidden:0, Width:60,  Align:"Center", ColMerge:1, SaveName:"intg_cd_id",          KeyField:1, CalcLogic:"", Format:"", PointCount:0, UpdateEdit:0, InsertEdit:1 },
					    {Type:"Text",   Hidden:0, Width:60,  Align:"Center", ColMerge:0, SaveName:"intg_cd_val_ctnt",    KeyField:1, CalcLogic:"", Format:"", PointCount:0, UpdateEdit:0, InsertEdit:1 },
					    {Type:"Text",   Hidden:0, Width:200, Align:"Center", ColMerge:0, SaveName:"intg_cd_val_dp_desc", KeyField:0, CalcLogic:"", Format:"", PointCount:0, UpdateEdit:1, InsertEdit:1 },
					    {Type:"Text",   Hidden:0, Width:600, Align:"Left",   ColMerge:0, SaveName:"intg_cd_val_desc",    KeyField:0, CalcLogic:"", Format:"", PointCount:0, UpdateEdit:1, InsertEdit:1 },
					    {Type:"Text",   Hidden:0, Width:50,  Align:"Center", ColMerge:0, SaveName:"intg_cd_val_dp_seq",  KeyField:0, CalcLogic:"", Format:"", PointCount:0, UpdateEdit:1, InsertEdit:1 } 
					];
		                 
	                InitColumns(cols);
	                SetEditable(1);
	                SetWaitImageVisible(0);
		            resizeSheet();
	    		}
	            break;
        }
    }
    
    function resizeSheet(){
   	         ComResizeSheet(sheetObjects[1]);
   }
    /**
     * Handles various functions (inquiry, storage, etc.) related to IBSheet. <br>
     * {@link #processButtonClick} Call this function from a function and use it to refer to IBSheet's function on a button. <br>
     * @param {ibsheet} sheetObj    IBSheet Object
     * @param {form}    formObj     Form Object
     * @param {int}     sAction     Action code to process (for example, IBSEARCH, IBSAVE, IBDELETE, IBDOWNEXCEL, etc., defined in CoObject.js)
     **/
    function doActionIBSheet(sheetObj,formObj,sAction) {
        switch(sAction) {
			case IBSEARCH:      //조회
				if (sheetObj.id == "sheet1" ) {
					ComOpenWait(true);
					formObj.f_cmd.value = SEARCH;
		 			sheetObj.DoSearch("DOU_TRN_0002GS.do", FormQueryString(formObj));
				}
				else if (sheetObj.id == "sheet2"){
					//ComOpenWait(true);
					formObj.f_cmd.value = SEARCH01;
					sheetObj.DoSearch("DOU_TRN_0002GS.do", FormQueryString(formObj));
				}
				break;
			case IBSAVE:
                //저장처리
				if (sheetObj.id =="sheet1"){
					formObj.f_cmd.value=MULTI;
	                sheetObj.DoSave("DOU_TRN_0002GS.do", FormQueryString(formObj));
				}
				else if (sheetObj.id == "sheet2"){
					formObj.f_cmd.value=MULTI01;
	                sheetObj.DoSave("DOU_TRN_0002GS.do", FormQueryString(formObj));
				}
				break;
			case IBINSERT:      // 입력
				if (sheetObj.id == "sheet1"){
					rowcount=sheetObj.RowCount();
					row=sheetObj.DataInsert();
				}
				else if(sheetObj.id == "sheet2"){
					rowcount=sheetObj.RowCount();
					row=sheetObj.DataInsert();
					sheet2.SetCellValue(sheet2.GetSelectRow(), "intg_cd_id", cdID);
				}
				break;
			case IBDELETE:
				if(sheetObj.id == "sheet1"){
					sheet1.SetCellValue(sheet1.GetSelectRow(), "ibflag", "D");
					formObj.f_cmd.value=MULTI;
	                sheetObj.DoSave("DOU_TRN_0002GS.do", FormQueryString(formObj));
				}else if(sheetObj.id == "sheet2"){
					sheet2.SetCellValue(sheet2.GetSelectRow(), "ibflag", "D");
					formObj.f_cmd.value=MULTI01;
	                sheetObj.DoSave("DOU_TRN_0002GS.do", FormQueryString(formObj));
				}
				break;
			case IBDOWNEXCEL:	//엑셀다운로드
				if(sheetObj.RowCount() < 1){
					ComShowCodeMessage("COM132501");
				}else{
					sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj), SheetDesign:1, Merge:1});
				}
				break;
        }
    }
    
     
     // handling double click
     function sheet1_OnDblClick(sheetObj, Row) {
    	 //console.log(sheet1.GetCellValue(Row , "intg_cd_id"));
    	 document.form.s_intg_cd_id.value = sheetObjects[0].GetCellValue(Row , "intg_cd_id");
    	 console.log(document.form.s_intg_cd_id.value);
    	 doActionIBSheet(sheetObjects[1], document.form, IBSEARCH);
    }
     //check duplicate sheet 1
     function sheet1_OnChange(sheetObj) {
     	// Get value of error message code at current cell
     	var code = sheetObj.GetCellValue(sheetObj.GetSelectRow(), "intg_cd_id");
 			 for(var i = 0; i < sheetObj.RowCount(); i++) {
 	    		var orlcode = sheetObj.GetCellValue(i, "intg_cd_id");
 	    		if(code != '' && i != sheetObj.GetSelectRow() && code == orlcode){
 	   				 ComShowCodeMessage('COM131302',code);
 	   				 sheetObj.SetCellValue(sheetObj.GetSelectRow(), "intg_cd_id", "");
 	   				 return;
 	    	}
 		}
     }
     //check duplicate sheet 2
     function sheet2_OnChange(sheetObj) {
     	// Get value of error message code at current cell
     	var code = sheetObj.GetCellValue(sheetObj.GetSelectRow(), "intg_cd_val_ctnt");
 			 for(var i = 0; i < sheetObj.RowCount(); i++) {
 	    		var orlcode = sheetObj.GetCellValue(i, "intg_cd_val_ctnt");
 	    		if(code != '' && i != sheetObj.GetSelectRow() && code == orlcode){
 	   				 ComShowCodeMessage('COM131302',code);
 	   				 sheetObj.SetCellValue(sheetObj.GetSelectRow(), "intg_cd_val_ctnt", "");
 	   				 return;
 	    	}
 		}
     }
     
	/* 개발자 작업  끝 */
     
     function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
     	ComOpenWait(false);
     }
     function sheet2_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
     	ComOpenWait(false);
     }
