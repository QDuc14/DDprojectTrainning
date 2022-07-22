/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CodeMgmtDBDAO.java
*@FileTitle : Code Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.24
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.24 
* 1.0 Creation
=========================================================*/

package com.clt.apps.opus.dou.doutraining.codemgmt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.dou.doutraining.codemgmt.basic.CodeMgmtBCImpl;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtMasterVO;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtDetailVO;

public class CodeMgmtDBDAO extends DBDAOSupport {

	/**
	 * searchCodeMgmtMaster to retrieve data.<br>
	 * 
	 * @param CodeMgmtMasterVO codeMgmtMasterVO
	 * @return List<CodeMgmtMasterVO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<CodeMgmtMasterVO> searchCodeMgmtMaster(CodeMgmtMasterVO codeMgmtMasterVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CodeMgmtMasterVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();

		try{
			if(codeMgmtMasterVO != null){
				Map<String, String> mapVO = codeMgmtMasterVO .getColumnValues();
			
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtMasterRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CodeMgmtMasterVO .class);
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	 
	 	/**
		 * searchCodeMgmtDetail to retrieve data.<br>
		 * 
		 * @param CodeMgmtDetailVO codeMgmtDetailVO
		 * @return List<CodeMgmtDetailVO>
		 * @exception DAOException
		 */
	 @SuppressWarnings("unchecked")
	 public List<CodeMgmtDetailVO> searchCodeMgmtDetail(CodeMgmtDetailVO codeMgmtDetailVO) throws DAOException {
			DBRowSet dbRowset = null;
			List<CodeMgmtDetailVO> list = null;
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();

			try{
				if(codeMgmtDetailVO != null){
					Map<String, String> mapVO = codeMgmtDetailVO .getColumnValues();
				
					param.putAll(mapVO);
					velParam.putAll(mapVO);
				}
				dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtDetailRSQL(), param, velParam);
				list = (List)RowSetUtil.rowSetToVOs(dbRowset, CodeMgmtDetailVO .class);
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return list;
	 }
	 
	 	/**
		 * searchCodeMgmtDetail to check duplicate before save changed (insert, delete, update) in database.<br>
		 * 
		 * @param CodeMgmtMasterVO codeMgmtMasterVO
		 * @return  List<CodeMgmtMasterVO>
		 * @exception DAOException
		 */
	 public List<CodeMgmtMasterVO> checkDuplicateCodeMgmt(CodeMgmtMasterVO codeMgmtMasterVO) throws DAOException {
			DBRowSet dbRowset = null;
			List<CodeMgmtMasterVO> list = null;
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();

			try{
				if(codeMgmtMasterVO != null){
					Map<String, String> mapVO = codeMgmtMasterVO .getColumnValues();
				
					param.putAll(mapVO);
					velParam.putAll(mapVO);
				}
				dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtMasterDuplicateRSQL(), param, velParam);
				list = (List)RowSetUtil.rowSetToVOs(dbRowset, CodeMgmtMasterVO .class);
			} catch(SQLException se) {
				log.error(se.getMessage(),se);
				throw new DAOException(new ErrorHandler(se).getMessage());
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				throw new DAOException(new ErrorHandler(ex).getMessage());
			}
			return list;
		}
	 
	 /**
	  * addmanageCodeMaster to manage insert a row into database.<br>
	  * 
	  * @param CodeMgmtMasterVO codeMgmtMasterVO
	  * @exception DAOException
	  * @exception Exception
	  */
		public void addmanageCodeMaster(CodeMgmtMasterVO codeMgmtMasterVO) throws DAOException,Exception {
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();
			try {
				Map<String, String> mapVO = codeMgmtMasterVO .getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				
				SQLExecuter sqlExe = new SQLExecuter("");
				int result = sqlExe.executeUpdate((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtMasterCSQL(), param, velParam);
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
		  * addmanageCodeDetail to manage insert a row into database.<br>
		  * 
		  * @param CodeMgmtDetailVO codeMgmtDetailVO
		  * @exception DAOException
		  * @exception Exception
		  */
		public void addmanageCodeDetail(CodeMgmtDetailVO codeMgmtDetailVO) throws DAOException,Exception {
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();
			try {
				Map<String, String> mapVO = codeMgmtDetailVO .getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				
				SQLExecuter sqlExe = new SQLExecuter("");
				int result = sqlExe.executeUpdate((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtDetailCSQL(), param, velParam);
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
		 * addmanageCodeMasterS to manage insert some rows into database.<br>
		 * 
		 * @param List<CodeMgmtMasterVO> codeMgmtMasterVO
		 * @return int[]
		 * @exception DAOException
		 * @exception Exception
		 */
		public int[] addmanageCodeMasterS(List<CodeMgmtMasterVO> codeMgmtMasterVO) throws DAOException,Exception {
			int insCnt[] = null;
			try {
				SQLExecuter sqlExe = new SQLExecuter("");
				if(codeMgmtMasterVO .size() > 0){
					insCnt = sqlExe.executeBatch((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtMasterCSQL(), codeMgmtMasterVO,null);
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
		 * addmanageCodeDetailS to manage insert some rows into database.<br>
		 * 
		 * @param List<CodeMgmtDetailVO> codeMgmtDetailVO
		 * @return int[]
		 * @exception DAOException
		 * @exception Exception
		 */
		public int[] addmanageCodeDetailS(List<CodeMgmtDetailVO> codeMgmtDetailVO) throws DAOException,Exception {
			int insCnt[] = null;
			try {
				SQLExecuter sqlExe = new SQLExecuter("");
				if(codeMgmtDetailVO .size() > 0){
					insCnt = sqlExe.executeBatch((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtDetailCSQL(), codeMgmtDetailVO,null);
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
		 * modifymanageCodeMgmtMaster to manage update a row in database.<br>
		 * 
		 * @param CodeMgmtMasterVO codeMgmtVO
		 * @return int
		 * @exception DAOException
		 * @exception Exception
		 */
		public int modifymanageCodeMgmtMaster(CodeMgmtMasterVO codeMgmtVO) throws DAOException,Exception {
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();
			
			int result = 0;
			try {
				Map<String, String> mapVO = codeMgmtVO .getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				
				SQLExecuter sqlExe = new SQLExecuter("");
				result = sqlExe.executeUpdate((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtMasterUSQL(), param, velParam);
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
		 * modifymanageCodeMgmtDetail to manage update a row in database.<br>
		 * 
		 * @param CodeMgmtDetailVO codeMgmtVO
		 * @return int
		 * @exception DAOException
		 * @exception Exception
		 */
		public int modifymanageCodeMgmtDetail(CodeMgmtDetailVO codeMgmtVO) throws DAOException,Exception {
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();
			
			int result = 0;
			try {
				Map<String, String> mapVO = codeMgmtVO .getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				
				SQLExecuter sqlExe = new SQLExecuter("");
				result = sqlExe.executeUpdate((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtDetailUSQL(), param, velParam);
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
		 * modifymanageCodeMgmtMaster to manage update some rows in database.<br>
		 * 
		 * @param List<CodeMgmtMasterVO> codeMgmtVO
		 * @return int[]
		 * @exception DAOException
		 * @exception Exception
		 */
		public int[] modifymanageCodeMgmtMasterS(List<CodeMgmtMasterVO> codeMgmtVO) throws DAOException,Exception {
			int updCnt[] = null;
			try {
				SQLExecuter sqlExe = new SQLExecuter("");
				if(codeMgmtVO .size() > 0){
					updCnt = sqlExe.executeBatch((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtMasterUSQL(), codeMgmtVO,null);
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
		 * modifymanageCodeMgmtDetail to manage update some rows in database.
		 * 
		 * @param List<CodeMgmtDetailVO> codeMgmtVO
		 * @return int[]
		 * @exception DAOException
		 * @exception Exception
		 */
		public int[] modifymanageCodeMgmtDetailS(List<CodeMgmtDetailVO> codeMgmtVO) throws DAOException,Exception {
			int updCnt[] = null;
			try {
				SQLExecuter sqlExe = new SQLExecuter("");
				if(codeMgmtVO .size() > 0){
					updCnt = sqlExe.executeBatch((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtDetailUSQL(), codeMgmtVO,null);
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
		 * removemanageCodeMgmtMaster to manage delete a row in database.<br>
		 * 
		 * @param CodeMgmtMasterVO codeMgmtVO
		 * @return int
		 * @exception DAOException
		 * @exception Exception
		 */
		public int removemanageCodeMgmtMaster(CodeMgmtMasterVO codeMgmtVO) throws DAOException,Exception {
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();
			
			int result = 0;
			try {
				Map<String, String> mapVO = codeMgmtVO .getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				
				SQLExecuter sqlExe = new SQLExecuter("");
				result = sqlExe.executeUpdate((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtMasterDSQL(), param, velParam);
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
		 * removemanageCodeMgmtDetail to manage delete a row in database.<br>
		 * 
		 * @param CodeMgmtDetailVO codeMgmtVO
		 * @return int
		 * @exception DAOException
		 * @exception Exception
		 */
		public int removemanageCodeMgmtDetail(CodeMgmtDetailVO codeMgmtVO) throws DAOException,Exception {
			//query parameter
			Map<String, Object> param = new HashMap<String, Object>();
			//velocity parameter
			Map<String, Object> velParam = new HashMap<String, Object>();
			
			int result = 0;
			try {
				Map<String, String> mapVO = codeMgmtVO .getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
				
				SQLExecuter sqlExe = new SQLExecuter("");
				result = sqlExe.executeUpdate((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtDetailDSQL(), param, velParam);
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
		 * removemanageCodeMgmtMaster to manage delete some rows in database.<br>
		 * 
		 * @param List<CodeMgmtMasterVO> codeMgmtVO
		 * @return int[]
		 * @exception DAOException
		 * @exception Exception
		 */
		public int[] removemanageCodeMgmtMasterS(List<CodeMgmtMasterVO> codeMgmtVO) throws DAOException,Exception {
			int delCnt[] = null;
			try {
				SQLExecuter sqlExe = new SQLExecuter("");
				if(codeMgmtVO .size() > 0){
					delCnt = sqlExe.executeBatch((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtMasterDSQL(), codeMgmtVO,null);
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
		
		/**
		 * removemanageCodeMgmtDetail to manage delete some rows in database.<br>
		 * 
		 * @param List<CodeMgmtDetailVO> codeMgmtVO
		 * @return int[]
		 * @exception DAOException
		 * @exception Exception
		 */
		public int[] removemanageCodeMgmtDetailS(List<CodeMgmtDetailVO> codeMgmtVO) throws DAOException,Exception {
			int delCnt[] = null;
			try {
				SQLExecuter sqlExe = new SQLExecuter("");
				if(codeMgmtVO .size() > 0){
					delCnt = sqlExe.executeBatch((ISQLTemplate)new CodeMgmtDBDAOCodeMgmtDetailDSQL(), codeMgmtVO,null);
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
		
		/**
		 * removemanageCodeMgmtDetailbyCdIDS to manage delete all rows in with cdID same as master database.<br>
		 * 
		 * @param List<CodeMgmtDetailVO> codeMgmtVO
		 * @return int[]
		 * @exception DAOException
		 * @exception Exception
		 */
		public int[] removemanageCodeMgmtDetailbyCdIDS(List<CodeMgmtDetailVO> codeMgmtVO) throws DAOException,Exception {
			int delCnt[] = null;
			try {
				SQLExecuter sqlExe = new SQLExecuter("");
				if(codeMgmtVO .size() > 0){
					delCnt = sqlExe.executeBatch((ISQLTemplate)new CodeMgmtDBDAORemoveAllDetailDSQL(), codeMgmtVO,null);
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
