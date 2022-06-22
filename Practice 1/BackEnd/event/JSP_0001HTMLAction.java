/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : JSP_0001HTMLAction.java
*@FileTitle : testPr01
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.13
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.13 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.pratrain1.errmsgmgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.apps.opus.esm.clv.pratrain1.errmsgmgmt.vo.ErrMsgVO;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.esm.clv.pratrain1 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 praTrain1SC로 실행요청<br>
 * - praTrain1SC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * @author Quach Dai Duc
 * @see praTrain1Event 참조
 * @since J2EE 1.6
 */

public class JSP_0001HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * JSP_0001HTMLAction Creating an object.
	 */
	public JSP_0001HTMLAction() {}

	/**
	 * Parsing the Value of an HTML DOM object as a Java variable<br>
	 * Parse the information from HttpRequst into praTrain1Event and set it in the request<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return The object that implements the Event Event interface.
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		Jsp0001Event event = new Jsp0001Event();
		
		if(command.isCommand(FormCommand.MULTI)) {
			event.setErrMsgVOS((ErrMsgVO[])getVOs(request, ErrMsgVO .class,""));
		}
		else if(command.isCommand(FormCommand.SEARCH)) {
//			event.setErrMsgVO((ErrMsgVO)getVO(request, ErrMsgVO .class));
			ErrMsgVO errMsg = new ErrMsgVO();
			errMsg.setErrMsgCd(JSPUtil.getParameter(request, "s_err_msg_cd"));
			errMsg.setErrMsg(JSPUtil.getParameter(request, "s_err_msg"));
			event.setErrMsgVO(errMsg);
		}
		request.setAttribute("Event", event);
		return  event;
	}

	/**
	 * Storing the results of business scenarios in the attribute of the HttpRequest<br>
	 * Set a ResultSet to Request that sends the results of execution from ServiceCommand to View (JSP)<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * Performing HttpRequest parsing in the attribute of an HttpRequest stores the resulting value<br>
	 * Perform HttpRequest parsingSetting the result value to the request<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param An object that implements the event event interface.
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}