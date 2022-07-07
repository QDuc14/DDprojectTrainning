/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : CarrierMgmtBCImp.java
 *@FileTitle : Carrier Management
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.07.04
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.06.24 
 * 1.0 Creation
=========================================================*/

package com.clt.apps.opus.esm.clv.practice4.carriermgmt.basic;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clv.practice4.carriermgmt.integration.CarrierMgmtDBDAO;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.vo.CarrierVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public class CarrierMgmtBCImp extends BasicCommandSupport implements CarrierMgmtBC {

	// Database Access Object
	private transient CarrierMgmtDBDAO dbDao = null;

	/**
	 * CodeMgmtBCImpl 객체 생성<br>
	 * CodeMgmtDBDAO를 생성한다.<br>
	 */
	public CarrierMgmtBCImp() {
		dbDao = new CarrierMgmtDBDAO();
	}
	/**
	 * [searchCarrierMgmt] to retrieve data in database.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	public List<CarrierVO> searchCarrierMgmt(CarrierVO carrierVO) throws EventException {
		try {
			return dbDao.searchCarrierMgmt(carrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [checkDuplicate] to check duplicate data before insert.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	public List<CarrierVO> checkDuplicate(CarrierVO carrierVO) throws EventException {
		try {
			return dbDao.checkDuplicate(carrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [searchCarrierLane] to get the list of Lane for combo box.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	public List<CarrierVO> searchCarrierLane(CarrierVO carrierVO) throws EventException {
		try {
			return dbDao.searchCarrierLane(carrierVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [searchCarrierCombo] to get a list of Carrier for Combo box.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	public List<CarrierVO> searchCarrierCombo(CarrierVO carrierVO) throws EventException {
		try{
			return dbDao.searchCarrierCombo(carrierVO);
		}
		catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}
	
	/**
	 * [searchCustomer] to get customer data for customer pop-up.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	public List<CarrierVO> searchCustomer(CarrierVO carrierVO) throws EventException {
		try{
			return dbDao.searchCustomer(carrierVO);
		}
		catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
		catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}

	/**
	 * [manageCarrier] to save the changed (add, update, delete) in database.<br>
	 * 
	 * @param CarrierVO[] carrierVO
	 * @param SignOnUserAccount account
	 * @exception EventException
	 */
	public void manageCarrier(CarrierVO[] carrierVO, SignOnUserAccount account) throws EventException{
		try {
			List<CarrierVO> insertVoList = new ArrayList<CarrierVO>();
			List<CarrierVO> updateVoList = new ArrayList<CarrierVO>();
			List<CarrierVO> deleteVoList = new ArrayList<CarrierVO>();
			StringBuilder string = new StringBuilder();
			for ( int i=0; i<carrierVO .length; i++ ) {
				if ( carrierVO[i].getIbflag().equals("I")){
					if(checkDuplicate(carrierVO[i]).size() != 0){
						string.append(carrierVO[i].getJoCrrCd() + " - " + carrierVO[i].getRlaneCd() + "|" );
					}else{
						carrierVO[i].setCreUsrId(account.getUsr_id());
						insertVoList.add(carrierVO[i]);
					}
					
					
				} else if ( carrierVO[i].getIbflag().equals("U")){
					carrierVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(carrierVO[i]);
					
				} else if ( carrierVO[i].getIbflag().equals("D")){
					deleteVoList.add(carrierVO[i]);
				}
				
			}
			
			if(string.length()!=0){
				string.deleteCharAt(string.length()-1);
				throw new EventException(new ErrorHandler("ERR12356", new String[]{string.toString()}).getMessage());
			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addmanageCarrierS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymanageCarrierS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemanageCarrierS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
}
