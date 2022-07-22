/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : practice4SC.java
 *@FileTitle : Carrier Management
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.07.04
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.06.24 
 * 1.0 Creation
=========================================================*/

package com.clt.apps.opus.esm.clv.practice4.carriermgmt;

import java.util.List;

import com.clt.apps.opus.esm.clv.practice4.carriermgmt.basic.CarrierMgmtBC;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.basic.CarrierMgmtBCImp;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.event.ClvPrt0004Event;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.vo.CarrierVO;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;



public class practice4SC extends ServiceCommandSupport {
	private SignOnUserAccount account = null;

	/**
	 * MoneyMgmt system task scenario precedent work
	 * Creating related internal objects when calling a business scenario
	 */
	public void doStart() {
		log.debug("Practice4SC 시작");
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
		log.debug("DouTrainingSC 종료");
	}

	/**
	 * Carry out business scenarios for each event
	 * branch processing of all events occurring in ALPS-MoneyMgmt system work
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("ClvPrt0004Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchCarrierMgmt(e);
			}
			else if(e.getFormCommand().isCommand(FormCommand.DEFAULT)){
				eventResponse = initCombo(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = manageCarrier(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.SEARCH01)){
				eventResponse = searchCustomer(e);
			}
		}
		
		return eventResponse;
	}
	/**
	 * [searchCarrierMgmt] to get data for sheet
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	private EventResponse searchCarrierMgmt(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPrt0004Event event = (ClvPrt0004Event)e;
		CarrierMgmtBC command = new CarrierMgmtBCImp();

		try{
			List<CarrierVO> list = command.searchCarrierMgmt(event.getCarrierVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	/**
	 * [searchCustomer] to get data for customer pop-up
	 * @param e
	 * @return EventResponse
	 * @throws EventException
	 */
	private EventResponse searchCustomer(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPrt0004Event event = (ClvPrt0004Event)e;
		CarrierMgmtBC command = new CarrierMgmtBCImp();

		try{
			List<CarrierVO> list = command.searchCustomer(event.getCarrierVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	/**
	 *
	 * [initCombo] to get data for carrier combo box and lane com bo box
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse initCombo(Event e) throws EventException{
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPrt0004Event event = (ClvPrt0004Event) e;
		CarrierMgmtBC command = new CarrierMgmtBCImp();
		try {
			List<CarrierVO> list = command.searchCarrierCombo(event.getCarrierVO());
			eventResponse.setRsVoList(list);
			
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
			eventResponse.setETCData("carriers", sb.toString());
			
			List<CarrierVO> listLane = command.searchCarrierLane(event.getCarrierVO());
			eventResponse.setRsVoList(listLane);
			
			StringBuilder SBLane = new StringBuilder();
			
			if(listLane != null){
				for (int i = 0; i < listLane.size(); i++) {
					String lanes = listLane.get(i).getRlaneCd();
					SBLane.append(lanes);
					if (i < listLane.size() - 1) {
						SBLane.append("|");
					}
				}	
			}
			eventResponse.setETCData("lanes", SBLane.toString());
			
		} catch (EventException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		return eventResponse;
	}
	
	/**
	 * [manageCarrier] to manage insert, update, delete
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse manageCarrier(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		ClvPrt0004Event event = (ClvPrt0004Event)e;
		CarrierMgmtBC command = new CarrierMgmtBCImp();
		try{
			begin();
			command.manageCarrier(event.getCarrierVOs(),account);
			eventResponse.setUserMessage(new ErrorHandler("BKG06071").getUserMessage());
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
