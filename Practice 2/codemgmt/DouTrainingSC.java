/*=========================================================
*Copyright(c) 2020 CyberLogitec
*@FileName : DouTrainingSC.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.24
*@LastModifier : 
*@LastVersion : 1.0
* 2020.03.17 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.doutraining;

import java.util.List;

import com.clt.apps.opus.dou.doutraining.codemgmt.basic.CodeMgmtBC;
import com.clt.apps.opus.dou.doutraining.codemgmt.basic.CodeMgmtBCImpl;
import com.clt.apps.opus.dou.doutraining.codemgmt.event.DouTrn0002Event;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtDetailVO;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtMasterVO;
import com.clt.apps.opus.dou.doutraining.errmsgmgmt.basic.ErrMsgMgmtBC;
import com.clt.apps.opus.dou.doutraining.errmsgmgmt.basic.ErrMsgMgmtBCImpl;
import com.clt.apps.opus.dou.doutraining.errmsgmgmt.event.DouTrn001Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.dou.doutraining.errmsgmgmt.vo.ErrMsgVO;


/**
 * ALPS-DouTraining Business Logic ServiceCommand - ALPS-DouTraining 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author Duc Quach
 * @see ErrMsgMgmtDBDAO
 * @see CodeMgmtDBDAO
 * @since J2EE 1.6
 */

public class DouTrainingSC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * CodeMgmt system task scenario precedent work
	 * Creating related internal objects when calling a business scenario
	 */
	public void doStart() {
		log.debug("DouTrainingSC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * CodeMgmt system work scenario finishing work
	 * release related internal objects when the work scenario is finished
	 */
	public void doEnd() {
		log.debug("DouTrainingSC 종료");
	}

	/**
	 * Carry out business scenarios for each event
	 * branch processing of all events occurring in ALPS-MoneyMgmt system work
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("DouTrn001Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchErrMsg(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = manageErrMsg(e);
			}
		}
		
		if(e.getEventName().equalsIgnoreCase("DouTrn0002Event")){
			if(e.getFormCommand().isCommand(FormCommand.SEARCH)){
				eventResponse = searchCodeMgmtMaster(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.SEARCH01)){
				eventResponse = searchCodeMgmtDetail(e);
			}
			else if(e.getFormCommand().isCommand(FormCommand.MULTI)){
				eventResponse = manageCodeMgmtMaster(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI01)){
				eventResponse = manageCodeMgmtDetail(e);
			}
		}
		
		return eventResponse;
	}
	
	
	/**
	 * [searchCodeMgmtMaster] to get data for master sheet
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	private EventResponse searchCodeMgmtMaster(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0002Event event = (DouTrn0002Event)e;
		CodeMgmtBC command = new CodeMgmtBCImpl();

		try{
			List<CodeMgmtMasterVO> list = command.searchCodeMgmtMaster(event.getCodeMgmtMasterVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	/**
	 * [searchCodeMgmtDetail] to get data for detail sheet
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	private EventResponse searchCodeMgmtDetail(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0002Event event = (DouTrn0002Event)e;
		CodeMgmtBC command = new CodeMgmtBCImpl();

		try{
			List<CodeMgmtDetailVO> list = command.searchCodeMgmtDetail(event.getCodeMgmtDetailVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	
	/**
	 * DOU_TRN_001 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchErrMsg(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn001Event event = (DouTrn001Event)e;
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
	 * DOU_TRN_001 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse manageErrMsg(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn001Event event = (DouTrn001Event)e;
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
	
	
	/**
	 * [manageCodeMgmtMaster] to manage insert, update, delete for master sheet
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse manageCodeMgmtMaster(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0002Event event = (DouTrn0002Event)e;
		CodeMgmtBC command = new CodeMgmtBCImpl();
		try{
			begin();
			command.manageCodeMgmtMaster(event.getCodeMgmtMasterVOs(),account);
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
	
	/**
	 * [manageCodeMgmtDetail] to manage insert, update, delete for detail sheet
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse manageCodeMgmtDetail(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0002Event event = (DouTrn0002Event)e;
		CodeMgmtBC command = new CodeMgmtBCImpl();
		try{
			begin();
			command.manageCodeMgmtDetail(event.getCodeMgmtDetailVOs(),account);
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