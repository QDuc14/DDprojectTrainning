/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : MoneyMgmtBCImpl.java
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

import com.clt.apps.opus.esm.clv.practice3.moneymgmt.integration.MoneyMgmtDBDAO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.DetailVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.SummaryVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
/**
 * ALPS-moneymgmt Business Logic Command Interface<br>
 * - Interface to business logic for ALPS-moneymgmt<br>
 *
 * @author duc
 * @since J2EE 1.6
 */
public class MoneyMgmtBCImpl extends BasicCommandSupport implements MoneyMgmtBC {
	// Database Access Object
	private transient MoneyMgmtDBDAO dbDao = null;
	/**
	 * MoneyMgmtBCImpl creating object<br>
	 * MoneyMgmtDBDAO Generates.<br>
	 */
	public MoneyMgmtBCImpl(){
		dbDao = new MoneyMgmtDBDAO();
	}
	
	/**
	 * searchMoneySummaryMgmt to retrieve data
	 * @param summaryVO
	 * @return List<SummaryVO>
	 * @throws EventException
	 */
	public List<SummaryVO> searchMoneySummaryMgmt(SummaryVO summaryVO) throws EventException {
		try {
			return dbDao.searchMoneySummaryMgmt(summaryVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	/**
	 * searchMoneyDetailMgmt to retrieve data
	 * @param detailVO
	 * @return List<DetailVO>
	 * @throws EventException
	 */
	public List<DetailVO> searchMoneyDetailMgmt(DetailVO detailVO) throws EventException {
		try {
			return dbDao.searchMoneyDetailMgmt(detailVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	/**
	 * partnerCombo to get data for combo box
	 * @return List<SummaryVO>
	 * @throws EventException
	 */
	public List<SummaryVO> partnerCombo() throws EventException{
		try{
			return dbDao.partnerCombo();
		}catch(DAOException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	/**
	 * laneCombo to get data for combo box
	 * @param summaryVO
	 * @return List<SummaryVO>
	 * @throws EventException
	 */
	public List<SummaryVO> laneCombo(SummaryVO summaryVO) throws EventException{
		try{
			return dbDao.laneCombo(summaryVO);
		}catch(DAOException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	/**
	 * tradeCombo to get data for combo box
	 * @param summaryVO
	 * @return
	 * @throws EventException
	 */
	public List<SummaryVO> tradeCombo(SummaryVO summaryVO) throws EventException{
		try {
			return dbDao.tradeCombo(summaryVO);
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage());
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage());
		}
	}
	
}
