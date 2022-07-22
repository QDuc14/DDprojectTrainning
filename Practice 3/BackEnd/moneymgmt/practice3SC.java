/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : practice3SC.java
 *@FileTitle : Money Management
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.07.14
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.07.10 
 * 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice3;

import java.util.List;

import com.clt.apps.opus.esm.clv.practice3.moneymgmt.basic.MoneyMgmtBC;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.basic.MoneyMgmtBCImpl;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.event.ClvPrt0003Event;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.DetailVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.SummaryVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public class practice3SC extends ServiceCommandSupport {
	
	// Login user information
	private SignOnUserAccount account = null;

	/**
	 * MoneyMgmt system task scenario precedent work
	 * Creating related internal objects when calling a business scenario
	 */
	public void doStart() {
		log.debug("Practice3SC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * MoneyMgmt system work scenario finishing work
	 * release related internal objects when the work scenario is finished
	 */
	public void doEnd() {
		log.debug("Practice3SC 종료");
	}
	/**
	 * Carry out business scenarios for each event
	 * branch processing of all events occurring in ALPS-MoneyMgmt system work
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	@Override
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("ClvPrt0003Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchMoneySummaryMgmt(e);
			} else if(e.getFormCommand().isCommand(FormCommand.DEFAULT)){
				eventResponse = initCombo(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH01)){
				eventResponse = searchMoneyDetailMgmt(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH02)){
				eventResponse = laneCombo(e);
			} else if (e.getFormCommand().isCommand(FormCommand.SEARCH03)){
				eventResponse = tradeCombo(e);
			}
				
		}

		return eventResponse;
	}
	/**
	 * [searchMoneySummaryMgmt] to get data for summary sheet
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	private EventResponse searchMoneySummaryMgmt(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse =  new GeneralEventResponse();
		ClvPrt0003Event event = (ClvPrt0003Event)e;
		MoneyMgmtBC command = new MoneyMgmtBCImpl();

		try{
			List<SummaryVO> list = command.searchMoneySummaryMgmt(event.getSummaryVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * [searchMoneyDetailMgmt] to get data for detail sheet
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	private EventResponse searchMoneyDetailMgmt(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPrt0003Event event = (ClvPrt0003Event)e;
		MoneyMgmtBC command = new MoneyMgmtBCImpl();

		try{
			List<DetailVO> list = command.searchMoneyDetailMgmt(event.getDetailVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	/**
	 * [initCombo] to initialize data for partner combo
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	private EventResponse initCombo(Event e) throws EventException{
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPrt0003Event event = (ClvPrt0003Event) e;
		MoneyMgmtBC command = new MoneyMgmtBCImpl();
		try {
			List<SummaryVO> list = command.partnerCombo();
			
			StringBuilder sb = new StringBuilder();
			if(list != null){
				for (int i = 0; i < list.size(); i++) {
					String carriers = list.get(i).getJoCrrCd();
					sb.append(carriers);
					if (i < list.size() - 1) {
						sb.append("|");
					}
				}	
			}
			eventResponse.setETCData("partner", sb.toString());
			
		} catch (EventException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}
	/**
	 * [laneCombo] to get data for lane combo
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	private EventResponse laneCombo(Event e) throws EventException{
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPrt0003Event event = (ClvPrt0003Event) e;
		MoneyMgmtBC command = new MoneyMgmtBCImpl();
		try {
			List<SummaryVO> list = command.laneCombo(event.getSummaryVO());
			
			StringBuilder sb = new StringBuilder();
			if(list != null){
				for (int i = 0; i < list.size(); i++) {
					String lanes = list.get(i).getRlaneCd();
					sb.append(lanes);
					if (i < list.size() - 1) {
						sb.append("|");
					}
				}	
			}
			eventResponse.setETCData("lane", sb.toString());
			
		} catch (EventException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}
	/**
	 * [tradeCombo] to get data for trade combo
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	private EventResponse tradeCombo(Event e) throws EventException{
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPrt0003Event event = (ClvPrt0003Event) e;
		MoneyMgmtBC command = new MoneyMgmtBCImpl();
		try {
			List<SummaryVO> list = command.tradeCombo(event.getSummaryVO());
			
			StringBuilder sb = new StringBuilder();
			if(list != null){
				for (int i = 0; i < list.size(); i++) {
					String trades = list.get(i).getTrdCd();
					sb.append(trades);
					if (i < list.size() - 1) {
						sb.append("|");
					}
				}	
			}
			eventResponse.setETCData("trade", sb.toString());
			
		} catch (EventException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}
	
}
