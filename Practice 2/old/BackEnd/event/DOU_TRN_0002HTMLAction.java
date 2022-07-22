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

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.dou.doutraining 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 DouTrainingSC로 실행요청<br>
 * - DouTrainingSC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * @author Duc Quach
 * @see DouTrainingEvent 참조
 * @since J2EE 1.6
 */

public class DOU_TRN_0002HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * DOU_TRN_0002HTMLAction 객체를 생성
	 */
	public DOU_TRN_0002HTMLAction() {}

	/**
	 * HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
	 * HttpRequst의 정보를 DouTrainingEvent로 파싱하여 request에 셋팅<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
	 * @exception HTMLActionException
	 */
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
