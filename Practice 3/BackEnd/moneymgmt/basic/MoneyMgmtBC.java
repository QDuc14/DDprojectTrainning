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
	 * Searching Summary data method
	 * @param summaryVO
	 * @return
	 * @throws EventException
	 */
	public List<SummaryVO> searchMoneySummaryMgmt(SummaryVO summaryVO) throws EventException;
	/**
	 * Searching Details data
	 * @param searchDetailVO
	 * @return
	 * @throws EventException
	 */
	public List<DetailVO> searchMoneyDetailMgmt(DetailVO detailVO) throws EventException;
	/**
	 * Getting Partner combo
	 * @param summaryVO
	 * @return
	 * @throws EventException
	 */
	public List<SummaryVO> partnerCombo() throws EventException;
	/**
	 * Getting Lane combo
	 * @param summaryVO
	 * @return
	 * @throws EventException
	 */
	public List<SummaryVO> laneCombo(SummaryVO summaryVO) throws EventException;
	/**
	 * getting Trade combo
	 * @param summaryVO
	 * @return
	 * @throws EventException
	 */
	public List<SummaryVO> tradeCombo(SummaryVO summaryVO) throws EventException;
	
}
