/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : MoneyMgmtDBDAO.java
 *@FileTitle : Money Management
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.07.14
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.07.10 
 * 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice3.moneymgmt.integration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.DetailVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.SummaryVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;

public class MoneyMgmtDBDAO extends DBDAOSupport {

	/**
	 * [searchMoneySummaryMgmt] to retrieve data
	 * @param summaryVO
	 * @return List<SummaryVO>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<SummaryVO> searchMoneySummaryMgmt(SummaryVO summaryVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<SummaryVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if(summaryVO != null){
				Map<String, String> mapVO = summaryVO .getColumnValues();
				List<String> obj_list_no = new ArrayList<>();
				if (null!=summaryVO.getJoCrrCd()){
					String[] partners = summaryVO.getJoCrrCd().split(",");
					for(int i = 0; i < partners.length; i++){
						obj_list_no.add(partners[i]);
					}
					param.putAll(mapVO);
					param.put("obj_list_no", obj_list_no);
					
					velParam.putAll(mapVO);
					velParam.put("obj_list_no", obj_list_no);
				}
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new MoneyMgmtDBDAOSummaryVORSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, SummaryVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	/**
	 * [searchMoneyDetailMgmt] to retrieve data
	 * @param summaryVO
	 * @return List<DetailVO>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<DetailVO> searchMoneyDetailMgmt(DetailVO detailVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<DetailVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (detailVO != null) {
				Map<String, String> mapVO = detailVO.getColumnValues();
				List<String> obj_list_no = new ArrayList<>();
				if(!detailVO.getJoCrrCd().isEmpty()){
					String[] partners = detailVO.getJoCrrCd().split(",");
					if(partners.length > 0){
						for(int i=0; i<partners.length; i++){
							obj_list_no.add(partners[i]);
						}
					}
				}
				param.putAll(mapVO);
				param.put("obj_list_no", obj_list_no);
				
				velParam.putAll(mapVO);
				velParam.put("obj_list_no", obj_list_no);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new MoneyMgmtDBDAODetailVORSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, SummaryVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	/**
	 * [partnerCombo] to get data for combo box
	 * @return List<SummaryVO>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<SummaryVO> partnerCombo() throws DAOException {
		DBRowSet dbRowset = null;
		List<SummaryVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new MoneyMgmtDBDAOPartnerComboRSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, SummaryVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	/**
	 * [laneCombo] to get data for combo box
	 * @param summaryVO
	 * @return List<SummaryVO>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<SummaryVO> laneCombo(SummaryVO summaryVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<SummaryVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		List<String> partnerCodes = new ArrayList<String>();
		
		try {
			if(summaryVO != null){
				
				Map<String, String> mapVO = summaryVO .getColumnValues();
				
				if("All".equalsIgnoreCase(summaryVO.getJoCrrCd())==false){
					partnerCodes=Arrays.asList(summaryVO.getJoCrrCd().split(","));
				}
				
				param.putAll(mapVO);
				param.put("partnerCodes", partnerCodes);
					
				velParam.putAll(mapVO);
				velParam.put("partnerCodes", partnerCodes);
				
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new MoneyMgmtDBDAOLaneComboRSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, SummaryVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	/**
	 * [tradeCombo] to get data for combo box
	 * @param summaryVO
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<SummaryVO> tradeCombo(SummaryVO summaryVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<SummaryVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		List<String> partnerCodes = new ArrayList<String>();
		
		try {
			if(summaryVO != null){
				
				Map<String, String> mapVO = summaryVO .getColumnValues();
				
				if("All".equalsIgnoreCase(summaryVO.getJoCrrCd())==false){
					partnerCodes=Arrays.asList(summaryVO.getJoCrrCd().split(","));
				}
				
				param.putAll(mapVO);
				param.put("partnerCodes", partnerCodes);
					
				velParam.putAll(mapVO);
				velParam.put("partnerCodes", partnerCodes);
				
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new MoneyMgmtDBDAOTradeComboRSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, SummaryVO.class);
		} catch (SQLException se) {
			log.error(se.getMessage(), se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
}
