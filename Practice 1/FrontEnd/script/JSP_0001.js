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
  	// global variable
	var tabObjects=new Array();
	var tabCnt=0 ;
	var beforetab=1;
	var sheetObjects=new Array();
	var sheetCnt=0;
	var rowcount=0;
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
     * The function called in the body.onload event adds functionality that needs to be pre-processed after the page finishes loading. <br>
     * Initializes various events of HTML control, IBSheet, IBTab, etc. <br>
     **/
    function loadPage() {
		for(i=0;i<sheetObjects.length;i++){
			// set the basic design of the sheet. this function must be called before sheet initialization. (CoObject.js)
			ComConfigSheet (sheetObjects[i]);
			//sheet initialization
			initSheet(sheetObjects[i],i+1);
			//finish the basic design of the sheet. this function must be called after sheet initialization. (CoObject.js)
			ComEndConfigSheet(sheetObjects[i]);
			// to show the search result on grid.
			doActionIBSheet(sheetObjects[i],document.form,IBSEARCH)
		}
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
        switch(sheetID) {
            case "sheet1":
            	with(sheetObj){
            	// define a string to store head title.
                var HeadTitle="|Del|Msg Cd|Msg Type|Msg level|Message|Description" ;
//                var headCount=ComCountHeadTitle(HeadTitle);
                // SetConfig: configure how to fetch initialized sheet, location of SearchMode and other basic configuration
                SetConfig( { SearchMode:2, MergeSheet:5, Page:10, FrozenCol:0, DataRowMerge:1 } );
                // define header functions such as sort, column movement permission in json format
                var info    = { Sort:1, ColMove:1, HeaderCheck:0, ColResize:1 };
                //define header title and align in json format
                var headers = [ { Text:HeadTitle, Align:"Center"} ];
                //define header title and function
                InitHeaders(headers, info);
                
                var cols = [ {Type:"Status",    Hidden:1, Width:30,   Align:"Center",  ColMerge:0,   SaveName:"ibflag" },
	                     {Type:"DelCheck",  Hidden:0, Width:45,   Align:"Center",  ColMerge:1,   SaveName:"DEL",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
	                     {Type:"Text",      Hidden:0 , Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_msg_cd",  KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
	                     {Type:"Combo",     Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_tp_cd",   KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, ComboText:"Server|UI|Both", ComboCode:"S|U|B" },
	                     {Type:"Combo",     Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_lvl_cd",  KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, ComboText:"ERR|WARNING|INFO", ComboCode:"E|W|I" },
	                     {Type:"Text",      Hidden:0, Width:400,  Align:"Left",    ColMerge:0,   SaveName:"err_msg",     KeyField:1,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1, MultiLineText:1 },
	                     {Type:"Text",      Hidden:0, Width:250,  Align:"Left",    ColMerge:0,   SaveName:"err_desc",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 } 
	                     ];
                  // configure data type, format and functionality of each column.
                InitColumns(cols);
                // set not to display waiting image for processing
                SetWaitImageVisible(0);
                resizeSheet();
                }
            break;
        }
    }
    
    function resizeSheet(){
   	         ComResizeSheet(sheetObjects[0]);
   }
    /**
     * Handles various functions (inquiry, storage, etc.) related to IBSheet. <br>
     * function define transaction between UI and server
     * {@link #processButtonClick} Call this function from a function and use it to refer to IBSheet's function on a button. <br>
     * @param {ibsheet} sheetObj    IBSheet Object
     * @param {form}    formObj     Form Object
     * @param {int}     sAction     Action code to process (for example, IBSEARCH, IBSAVE, IBDELETE, IBDOWNEXCEL, etc., defined in CoObject.js)
     **/
    function doActionIBSheet(sheetObj,formObj,sAction) {
        switch(sAction) {
			case IBSEARCH:      //조회
				if(!validateForm(sheetObj,formObj,sAction)) return
                //ComOpenWait: whether a loading image will appears and lock the srceen 
					//true: lock the screen and show waiting image
					//false: return normal
				ComOpenWait(true);
				// set value for f_cmd in form
				formObj.f_cmd.value=SEARCH;
				// connect to search page to read search XML, and then load XML data internally in IBSheet
 				sheetObj.DoSearch("JSP_0001GS.do", FormQueryString(formObj) );
				break;
			case IBSAVE:        //저장
				if(!validateForm(sheetObj,formObj,sAction))return;
				// set value for f_cmd in form
            	formObj.f_cmd.value=MULTI;
            	//save data based on data transaction status or column
                sheetObj.DoSave("JSP_0001GS.do", FormQueryString(formObj));
				break;
			case IBINSERT:
				//insert the row below the currently selected row
				rowcount=sheetObj.RowCount();
				row=sheetObj.DataInsert();
				break;
			case IBDOWNEXCEL:
				//if sheet null display error, else download sheet on excel format
				if(sheetObj.RowCount() < 1){
					ComShowCodeMessage("COM132501");
				}else{
					sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj), SheetDesign:1, Merge:1});
				}
				break;
        }
    }
    /**
     * Handles the validation process for screen form input values. 
     * @param {ibsheet} sheetObj    IBSheet Object
     * @param {form}    formObj     Form Object
     * @param {int}     sAction     Action code to process (for example, IBSEARCH, IBSAVE, IBDELETE, IBDOWNEXCEL, etc., defined in CoObject.js)
     **/
    function validateForm(sheetObj,formObj,sAction){
    	var regrex = new RegExp("^[A-Z]{3}[0-9]{5}$");
    	for (var i = sheetObj.HeaderRows(); i <= sheetObj.LastRow(); i++){
    		if (sheetObj.GetCellValue(i, "ibflag") == 'I' && !regrex.test(sheetObj.GetCellValue(i,"err_msg_cd"))){
    			ComShowMessage('Invalid ErrMsgCd: ErrMsgCd 8 characters are required, the first 3 characters are uppercase letters, the last 5 characters are numbers.');
    			//ComShowCodeMessage("COM132201", "Msg cd");
    			return false;
    		}
    	}
    	return true;
    }
    
    
    // Check duplicated
     function sheet1_OnChange(sheetObj,Row,Col){
//    	 if(Col == 2){
			var code=sheetObj.GetCellValue(sheetObj.GetSelectRow(), "err_msg_cd");
    	    for(var int=1; int < sheetObj.RowCount(); int++) {
			var orlcode=sheetObj.GetCellValue(int, "err_msg_cd");
			/* If it is null, there is no need to compare it to the self */
				if(code != '' && int != sheetObj.GetSelectRow() && code == orlcode){
    				 //ComShowMessage("동일한 Message Code가 존재합니다.");
    				 ComShowCodeMessage('COM131302',code);
    				 sheetObj.SetCellValue(sheetObj.GetSelectRow(), "err_msg_cd","");
    				 return;
    			 }
    		 }
//    	 }
     }
	/* 개발자 작업  끝 */
     //handling event after search
     function sheet1_OnSearchEnd(sheetObj, Code, Msg, StCode, StMsg) { 
     	ComOpenWait(false);
     }