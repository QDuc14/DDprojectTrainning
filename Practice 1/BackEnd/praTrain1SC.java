/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : praTrain1SC.java
*@FileTitle : testPr01
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.13
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.13 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.pratrain1;

import java.util.List;
import com.clt.apps.opus.esm.clv.pratrain1.errmsgmgmt.basic.ErrMsgMgmtBC;
import com.clt.apps.opus.esm.clv.pratrain1.errmsgmgmt.basic.ErrMsgMgmtBCImpl;
import com.clt.apps.opus.esm.clv.pratrain1.errmsgmgmt.event.Jsp0001Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.pratrain1.errmsgmgmt.vo.ErrMsgVO;


/**
 * ALPS-praTrain1 Business Logic ServiceCommand - ALPS-praTrain1 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author Quach Dai Duc
 * @see ErrMsgMgmtDBDAO
 * @since J2EE 1.6
 */

public class praTrain1SC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * praTrain1 system Business Scenario Prerequisites<br>
	 * Create relevant internal objects when calling a business scenario<br>
	 */
	public void doStart() {
		log.debug("praTrain1SC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * praTrain1 system Business scenario deadlines<br>
	 * Release of relevant internal objects at the end of a business scenario<br>
	 */
	public void doEnd() {
		log.debug("praTrain1SC 종료");
	}

	/**
	 * Perform the business scenario corresponding to each event<br>
	 * Branching of all events that occur in the ALPS-praTrain1 system business<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("Jsp0001Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchErrMsg(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = manageErrMsg(e);
			}
		}
		return eventResponse;
	}
	/**
	 * JSP_0001 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchErrMsg(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Jsp0001Event event = (Jsp0001Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();

		try{
			List<ErrMsgVO> list = command.searchErrMsg(event.getErrMsgVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * JSP_0001 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse manageErrMsg(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Jsp0001Event event = (Jsp0001Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();
		try{
			begin();
			command.manageErrMsg(event.getErrMsgVOS(),account);
			eventResponse.setUserMessage(new ErrorHandler("XXXXXXXXX").getUserMessage());
			commit();
		} catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
}