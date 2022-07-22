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

import java.util.List;

import com.clt.apps.opus.esm.clv.practice4.carriermgmt.vo.CarrierVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * ALPS-Doutraining Business Logic Command Interface<br>
 * - ALPS-Doutraining에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Duc Quach
 * @since J2EE 1.6
 */

public interface CarrierMgmtBC {

	/**
	 * [searchCarrierMgmt] to retrieve data in database.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	public List<CarrierVO> searchCarrierMgmt(CarrierVO carrierVO) throws EventException;
	
	/**
	 * [searchCarrierCombo] to get a list of Carrier for Combo box.<br>
	 * 
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	public List<CarrierVO> searchCarrierCombo(CarrierVO carrierVO) throws EventException;
	
	/**
	 * [searchCarrierLane] to get the list of Lane for combo box.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	public List<CarrierVO> searchCarrierLane(CarrierVO carrierVO) throws EventException;
	
	/**
	 * [searchCustomer] to get customer data for customer pop-up.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	public List<CarrierVO> searchCustomer(CarrierVO carrierVO) throws EventException;
	
	/**
	 * [manageCarrier] to save the changed (add, update, delete) in database.<br>
	 * 
	 * @param CarrierVO[] carrierVO
	 * @param SignOnUserAccount account
	 * @exception EventException
	 */
	public void manageCarrier(CarrierVO[] carrierVO,SignOnUserAccount account) throws EventException;
	
	/**
	 * [checkDuplicate] to check duplicate data before insert.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception EventException
	 */
	public List<CarrierVO> checkDuplicate(CarrierVO carrierVO) throws EventException;
}
