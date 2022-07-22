/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : CLV_PRT_0004HTMLAction.java
 *@FileTitle : Carrier Management
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.07.04
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.06.24 
 * 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice4.carriermgmt.event;

import javax.servlet.http.HttpServletRequest;


import com.clt.apps.opus.esm.clv.practice4.carriermgmt.vo.CarrierVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

public class CLV_PRT_0004HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	
	public CLV_PRT_0004HTMLAction() {}

	/**
	 * Parsing the HTML DOM object's value as a java variable
	 * parsing the information of HttpRequest as ClvPrt0004Event and setting it in request
	 * @param request
	 * @return Event
	 * @throws HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		ClvPrt0004Event event = new ClvPrt0004Event();
		
		if(command.isCommand(FormCommand.SEARCH)) {
			CarrierVO carrierVO = new CarrierVO();

			carrierVO.setJoCrrCd(JSPUtil.getParameter(request, "s_carrier", ""));
			carrierVO.setVndrSeq(JSPUtil.getParameter(request, "s_vndr_seq", ""));
			carrierVO.setCreDtFr(JSPUtil.getParameter(request, "s_cre_dt_fm", ""));
			carrierVO.setCreDtTo(JSPUtil.getParameter(request, "s_cre_dt_to", ""));
			event.setCarrierVO(carrierVO);
		}
		else if(command.isCommand(FormCommand.MULTI)) {
			event.setCarrierVOs((CarrierVO[])getVOs(request, CarrierVO.class, ""));
		}
		else if(command.isCommand(FormCommand.SEARCH01)){
			CarrierVO carrierMgmtVO = new CarrierVO();
			carrierMgmtVO.setCustCntCd(JSPUtil.getParameter(request, "s_cust_cnt_cd"));
			carrierMgmtVO.setCustSeq(JSPUtil.getParameter(request, "s_cust_seq"));
    		
    		event.setCarrierVO(carrierMgmtVO);    		
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
