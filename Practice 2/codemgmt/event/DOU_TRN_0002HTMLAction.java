/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_0002HTMLAction.java
*@FileTitle : Code Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.24
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.24 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.doutraining.codemgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtMasterVO;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtDetailVO;


public class DOU_TRN_0002HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * DOU_TRN_0002HTMLAction 객체를 생성
	 */
	public DOU_TRN_0002HTMLAction() {}

	/**
	 * Parsing the HTML DOM object's value as a java variable
	 * parsing the information of HttpRequest as DouTrn0002Event and setting it in request
	 * @param request
	 * @return Event
	 * @throws HTMLActionException
	 */
	@Override
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		DouTrn0002Event event = new DouTrn0002Event();
		
		if(command.isCommand(FormCommand.SEARCH)) {
			CodeMgmtMasterVO codeMgmtMasterVO = new CodeMgmtMasterVO();
			codeMgmtMasterVO.setOwnrSubSysCd(JSPUtil.getParameter(request, "s_ownr_sub_sys_cd", ""));
			codeMgmtMasterVO.setIntgCdId(JSPUtil.getParameter(request, "s_intg_cd_id", ""));
			event.setCodeMgmtMasterVO(codeMgmtMasterVO);
		}
		else if(command.isCommand(FormCommand.SEARCH01)){
			CodeMgmtDetailVO codeMgmtDetailVO = new CodeMgmtDetailVO();
			codeMgmtDetailVO.setIntgCdId(JSPUtil.getParameter(request, "s_intg_cd_id", ""));
			event.setCodeMgmtDetailVO(codeMgmtDetailVO);
		}
		
		else if(command.isCommand(FormCommand.MULTI)) {
			event.setCodeMgmtMasterVOs((CodeMgmtMasterVO[])getVOs(request, CodeMgmtMasterVO.class, ""));		
		}
		else if (command.isCommand(FormCommand.MULTI01)){
			event.setCodeMgmtDetailVOs((CodeMgmtDetailVO[])getVOs(request, CodeMgmtDetailVO.class, ""));
		}
		
		return  event;
	}

	/**
	 * sorting the business scenario execution  result value in the attribute of HttpRequest
	 * Setting the ResultSet that transmit the execution result from ServiceCommand to View
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * Saving the HttpRequest parsing result value in the HttpRequest attribute
	 * HttpRequest parsing result value and set in request
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}
