/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : CarrierMgmtDBDAO.java
 *@FileTitle : Carrier Management
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.07.04
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.06.24 
 * 1.0 Creation
=========================================================*/

package com.clt.apps.opus.esm.clv.practice4.carriermgmt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.vo.CarrierVO;

public class CarrierMgmtDBDAO extends DBDAOSupport {

	/**
	 * [searchCarrierMgmt] to retrieve data.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<CarrierVO> searchCarrierMgmt(CarrierVO carrierVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (carrierVO != null) {
				Map<String, String> mapVO = carrierVO.getColumnValues();
				List<String> obj_list_no = new ArrayList<>();
				
				if(carrierVO.getJoCrrCd() != null){
								
					String[] crr_cd = carrierVO.getJoCrrCd().split(",");
					if(crr_cd.length > 0){
						for(int i=0;i<crr_cd.length;i++){
							obj_list_no.add(crr_cd[i]);
						}
					}
				}
				
				param.putAll(mapVO);
				param.put("obj_list_no",obj_list_no);
				velParam.putAll(mapVO);
				velParam.put("obj_list_no",obj_list_no);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new CarrierMgmtDBDAOCarrierVORSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, CarrierVO.class);
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
	 * [searchCarrierLane] to get the list of lane for combo box.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<CarrierVO> searchCarrierLane(CarrierVO carrierVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (carrierVO != null) {
				Map<String, String> mapVO = carrierVO.getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new CarrierMgmtDBDAOCarrierLaneRSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, CarrierVO.class);
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
	 * [checkDuplicate] to check duplicate data before insert.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<CarrierVO> checkDuplicate(CarrierVO carrierVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (carrierVO != null) {
				Map<String, String> mapVO = carrierVO.getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new CarrierDBDAOcheckDuplicateRSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, CarrierVO.class);
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
	 * [searchCarrierCombo] to get a list of carrier for combo box.<br>
	 * 
	 *  @param CarrierVO carrierMgmtVO
	 * @return List<CarrierSearchCarrierVO>
	 * @exception DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<CarrierVO> searchCarrierCombo(CarrierVO carrierMgmtVO) throws DAOException {
		DBRowSet dbRowset = null; // save result from db
		List<CarrierVO> list = null; // list VO
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if(carrierMgmtVO != null){
				Map<String, String> mapVO = carrierMgmtVO.getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new CarrierMgmtDBDAOCarrierComboRSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset,CarrierVO.class);
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
	 * [searchCustomer] to get customer data for customer pop-up.<br>
	 * 
	 * @param CarrierVO carrierVO
	 * @return List<CarrierVO>
	 * @exception DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<CarrierVO> searchCustomer(CarrierVO carrierVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierVO> list = null;
		// query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		// velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try {
			if (carrierVO != null) {
				Map<String, String> mapVO = carrierVO.getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate) new CustomerMgmtDBDAOCustomerVORSQL(), param, velParam);
			list = (List) RowSetUtil.rowSetToVOs(dbRowset, CarrierVO.class);
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
	  * [addmanageCarrier] to manage insert a row into database.<br>
	  * 
	  * @param CarrierVO carrierVO
	  * @exception DAOException
	  * @exception Exception
	  */
		public void addmanageCarrier(CarrierVO carrierVO) throws DAOException,Exception {
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();
			try {
				Map<String, String> mapVO = carrierVO .getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				
				SQLExecuter sqlExe = new SQLExecuter("");
				int result = sqlExe.executeUpdate((ISQLTemplate)new CarrierMgmtDBDAOCarrierVOCSQL(), param, velParam);
				if(result == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert SQL");
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
		}
	
		/**
		 * [addmanageCarrierS] to manage insert some rows into database.<br>
		 * 
		 * @param ListList<CarrierVO> carrierVO
		 * @return int[]
		 * @exception DAOException
		 * @exception Exception
		 */
		public int[] addmanageCarrierS(List<CarrierVO> carrierVO) throws DAOException,Exception {
			int insCnt[] = null;
			try {
				SQLExecuter sqlExe = new SQLExecuter("");
				if(carrierVO .size() > 0){
					insCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierMgmtDBDAOCarrierVOCSQL(), carrierVO,null);
					for(int i = 0; i < insCnt.length; i++){
						if(insCnt[i]== Statement.EXECUTE_FAILED)
							throw new DAOException("Fail to insert No"+ i + " SQL");
					}
				}
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return insCnt;
		}
		
		/**
		 * [modifymanageCarrier] to manage update a row in database.<br>
		 * 
		 * @param CarrierVO carrierVO
		 * @return int
		 * @exception DAOException
		 * @exception Exception
		 */
		public int modifymanageCarrier(CarrierVO carrierVO) throws DAOException,Exception {
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();
			
			int result = 0;
			try {
				Map<String, String> mapVO = carrierVO .getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				
				SQLExecuter sqlExe = new SQLExecuter("");
				result = sqlExe.executeUpdate((ISQLTemplate)new CarrierMgmtDBDAOCarrierVOUSQL(), param, velParam);
				if(result == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert SQL");
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return result;
		}
		
		/**
		 * [modifymanageCarrierS] to manage update some rows in database.<br>
		 * 
		 * @param List<CarrierVO> carrierVO
		 * @return int[]
		 * @exception DAOException
		 * @exception Exception
		 */
		public int[] modifymanageCarrierS(List<CarrierVO> carrierVO) throws DAOException,Exception {
			int updCnt[] = null;
			try {
				SQLExecuter sqlExe = new SQLExecuter("");
				if(carrierVO .size() > 0){
					updCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierMgmtDBDAOCarrierVOUSQL(), carrierVO,null);
					for(int i = 0; i < updCnt.length; i++){
						if(updCnt[i]== Statement.EXECUTE_FAILED)
							throw new DAOException("Fail to insert No"+ i + " SQL");
					}
				}
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return updCnt;
		}
		
		/**
		 * [removemanageCarrier] to manage delete a row in database.<br>
		 * 
		 * @param CarrierVO carrierVO
		 * @return int
		 * @exception DAOException
		 * @exception Exception
		 */
		public int removemanageCarrier(CarrierVO carrierVO) throws DAOException,Exception {
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();
			
			int result = 0;
			try {
				Map<String, String> mapVO = carrierVO .getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				
				SQLExecuter sqlExe = new SQLExecuter("");
				result = sqlExe.executeUpdate((ISQLTemplate)new CarrierMgmtDBDAOCarrierVODSQL(), param, velParam);
				if(result == Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert SQL");
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return result;
		}
		
		/**
		 * [removemanageCarrierS] to manage delete some rows in database.<br>
		 * 
		 * @param List<CarrierVO> carrierVO
		 * @return int[]
		 * @exception DAOException
		 * @exception Exception
		 */
		public int[] removemanageCarrierS(List<CarrierVO> carrierVO) throws DAOException,Exception {
			int delCnt[] = null;
			try {
				SQLExecuter sqlExe = new SQLExecuter("");
				if(carrierVO .size() > 0){
					delCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierMgmtDBDAOCarrierVODSQL(), carrierVO,null);
					for(int i = 0; i < delCnt.length; i++){
						if(delCnt[i]== Statement.EXECUTE_FAILED)
							throw new DAOException("Fail to insert No"+ i + " SQL");
					}
				}
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return delCnt;
		}
		
}