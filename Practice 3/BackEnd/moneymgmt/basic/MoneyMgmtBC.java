/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : MoneyMgmtBC.java
 *@FileTitle : Money Management
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.07.14
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.07.10 
 * 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice3.moneymgmt.basic;

import java.util.List;

import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.DetailVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.SummaryVO;
import com.clt.framework.core.layer.event.EventException;
/**
 * ALPS-Moneymgmt Business Logic Command Interface<br>
 * - Interface to business logic for ALPS-Moneymgmt<br>
 *
 * @author duc
 * @since J2EE 1.6
 */
public interface MoneyMgmtBC {
	/**
	 * searchMoneySummaryMgmt to retrieve data
	 * @param summaryVO
	 * @return List<SummaryVO>
	 * @throws EventException
	 */
	public List<SummaryVO> searchMoneySummaryMgmt(SummaryVO summaryVO) throws EventException;
	/**
	 * searchMoneyDetailMgmt to retrieve data
	 * @param detailVO
	 * @return List<DetailVO>
	 * @throws EventException
	 */
	public List<DetailVO> searchMoneyDetailMgmt(DetailVO detailVO) throws EventException;
	/**
	 * partnerCombo to get data for combo box
	 * @return List<SummaryVO>
	 * @throws EventException
	 */
	public List<SummaryVO> partnerCombo() throws EventException;
	/**
	 * laneCombo to get data for combo box
	 * @param summaryVO
	 * @return List<SummaryVO>
	 * @throws EventException
	 */
	public List<SummaryVO> laneCombo(SummaryVO summaryVO) throws EventException;
	/**
	 * tradeCombo to get data for combo box
	 * @param summaryVO
	 * @return List<SummaryVO>
	 * @throws EventException
	 */
	public List<SummaryVO> tradeCombo(SummaryVO summaryVO) throws EventException;
	
}
