package com.clt.apps.opus.esm.clv.practice4.carriermgmt.event;

import javax.servlet.http.HttpServletRequest;


import com.clt.apps.opus.esm.clv.practice4.carriermgmt.vo.CarrierVO;
//import com.clt.apps.opus.dou.doutraining.codemgmt.event.DouTrn0002Event;
//import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtDetailVO;
//import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtMasterVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
//import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtMasterVO;
//import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtDetailVO;

public class CLV_PRT_0004HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * DOU_TRN_0002HTMLAction 객체를 생성
	 */
	public CLV_PRT_0004HTMLAction() {}

	/**
	 * HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
	 * HttpRequst의 정보를 DouTrainingEvent로 파싱하여 request에 셋팅<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
	 * @exception HTMLActionException
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
	 * HttpRequest의 attribute에 업무시나리오 수행결과 값 저장<br>
	 * ServiceCommand에서 View(JSP)로 실행결과를 전송하는 ResultSet을 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * HttpRequest의 attribute에 HttpRequest 파싱 수행결과 값 저장<br>
	 * HttpRequest 파싱 수행결과 값 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
	
}
