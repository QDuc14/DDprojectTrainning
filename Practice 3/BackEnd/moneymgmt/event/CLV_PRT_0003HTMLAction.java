/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : CLV_PRT_0003HTMLAction.java
 *@FileTitle : Money Management
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.07.14
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.07.10 
 * 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice3.moneymgmt.event;

import javax.servlet.http.HttpServletRequest;






import weblogic.jspc;

import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.DetailVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.SummaryVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
/**
 * HTTP Parser<br>
 * - Parsing the value of the HTML DOM object sent to the server through the com.clt.apps.opus.esm.clv.doutraining.moneymgmt screen as a Java variable<br>
 * - Parsing information is converted into an event, put in a request, and requested to be executed by MoneyMgmtSC<br>
 * - EventResponse that transmits execution result from MoneyMgmtSC to View (JSP) is set in the request<br>
 * @author duc
 * @see moneymgmtEvent 참조
 * @since J2EE 1.6
 */
public class CLV_PRT_0003HTMLAction extends HTMLActionSupport{

	private static final long serialVersionUID = 1L;
	/**
	 * [CLV_PRT_0003HTMLAction] generation
	 */
	public CLV_PRT_0003HTMLAction() {}
	/**
	 * Parsing the HTML DOM object's value as a java variable
	 * parsing the information of HttpRequest as ClvPrt0003Event and setting it in request
	 * @param request
	 * @return Event
	 * @throws HTMLActionException
	 */
	@Override
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		FormCommand command = FormCommand.fromRequest(request);
		ClvPrt0003Event event = new ClvPrt0003Event();
		
		if(command.isCommand(FormCommand.SEARCH)) {
			SummaryVO summaryVO = new SummaryVO();
			summaryVO.setTrdCd(JSPUtil.getParameter(request, "s_trd_cd", ""));
			summaryVO.setRlaneCd(JSPUtil.getParameter(request, "s_rlane_cd", ""));
			summaryVO.setJoCrrCd(JSPUtil.getParameter(request, "s_jo_crr_cd", ""));
			summaryVO.setAcctYrmonFm(JSPUtil.getParameter(request, "fm_acct_yrmon", ""));
			summaryVO.setAcctYrmonTo(JSPUtil.getParameter(request, "to_acct_yrmon", ""));
			event.setSummaryVO(summaryVO);
		}
		else if(command.isCommand(FormCommand.SEARCH01)){
			DetailVO detailVO = new DetailVO();
			detailVO.setTrdCd(JSPUtil.getParameter(request, "s_trd_cd", ""));
			detailVO.setRlaneCd(JSPUtil.getParameter(request, "s_rlane_cd", ""));
			detailVO.setJoCrrCd(JSPUtil.getParameter(request, "s_jo_crr_cd", ""));
			detailVO.setAcctYrmonFm(JSPUtil.getParameter(request, "fm_acct_yrmon", ""));
			detailVO.setAcctYrmonTo(JSPUtil.getParameter(request, "to_acct_yrmon", ""));
			event.setDetailVO(detailVO);
		}
		else if(command.isCommand(FormCommand.SEARCH02)){
			SummaryVO summaryVO = new SummaryVO();
			summaryVO.setJoCrrCd(JSPUtil.getParameter(request, "s_jo_crr_cd", ""));
			event.setSummaryVO(summaryVO);
		}
		else if(command.isCommand(FormCommand.SEARCH03)){
			SummaryVO summaryVO = new SummaryVO();
			summaryVO.setJoCrrCd(JSPUtil.getParameter(request, "s_jo_crr_cd", ""));
			summaryVO.setRlaneCd(JSPUtil.getParameter(request, "s_rlane_cd", ""));
			event.setSummaryVO(summaryVO);
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
