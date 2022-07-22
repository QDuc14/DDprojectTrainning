/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CodeMgmtBCImpl.java
*@FileTitle : Code Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.24
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.24 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.dou.doutraining.codemgmt.basic;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.dou.doutraining.codemgmt.integration.CodeMgmtDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtMasterVO;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtDetailVO;

/**
 * ALPS-DouTraining Business Logic Command Interface<br>
 * - ALPS-DouTraining에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Duc Quach
 * @since J2EE 1.6
 */
public class CodeMgmtBCImpl extends BasicCommandSupport implements CodeMgmtBC {

	// Database Access Object
	private transient CodeMgmtDBDAO dbDao = null;

	/**
	 * CodeMgmtBCImpl 객체 생성<br>
	 * CodeMgmtDBDAO를 생성한다.<br>
	 */
	public CodeMgmtBCImpl() {
		dbDao = new CodeMgmtDBDAO();
	}
	/**
	 * searchCodeMgmtMaster to retrieve data in database.<br>
	 * 
	 * @param CodeMgmtMasterVO codeMgmtMasterVO
	 * @return List<CodeMgmtMasterVO>
	 * @exception EventException
	 */
	public List<CodeMgmtMasterVO> searchCodeMgmtMaster(CodeMgmtMasterVO codeMgmtMasterVO) throws EventException {
		try {
			return dbDao.searchCodeMgmtMaster(codeMgmtMasterVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * searchCodeMgmtDetail to retrieve data in database.<br>
	 * 
	 * @param CodeMgmtDetailVO codeMgmtDetailVO
	 * @return List<CodeMgmtDetailVO>
	 * @exception EventException
	 */
	public List<CodeMgmtDetailVO> searchCodeMgmtDetail(CodeMgmtDetailVO codeMgmtDetailVO) throws EventException {
		try {
			return dbDao.searchCodeMgmtDetail(codeMgmtDetailVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * checkDuplicate to check duplicate before save changed (add, update, delete) in database.<br>
	 * 
	 * @param CodeMgmtMasterVO codeMgmtMasterVO
	 * @return List<CodeMgmtDetailVO>
	 * @exception EventException
	 */
	public List<CodeMgmtMasterVO> checkDuplicate(CodeMgmtMasterVO codeMgmtMasterVO) throws EventException {
		try {
			return dbDao.checkDuplicateCodeMgmt(codeMgmtMasterVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * manageCodeMgmtMaster to save the changed (add, update, delete) in database.<br>
	 * 
	 * @param CodeMgmtMasterVO[] codeMgmtMasterVO
	 * @param SignOnUserAccount account
	 * @exception EventException
	 */
	public void manageCodeMgmtMaster(CodeMgmtMasterVO[] codeMgmtMasterVO, SignOnUserAccount account) throws EventException{
		try {
			List<CodeMgmtMasterVO> insertVoList = new ArrayList<CodeMgmtMasterVO>();
			List<CodeMgmtMasterVO> updateVoList = new ArrayList<CodeMgmtMasterVO>();
			List<CodeMgmtMasterVO> deleteVoList = new ArrayList<CodeMgmtMasterVO>();
			List<CodeMgmtDetailVO> deleteDetailVoList = new ArrayList<CodeMgmtDetailVO>();
			for ( int i=0; i<codeMgmtMasterVO .length; i++ ) {
				if ( codeMgmtMasterVO[i].getIbflag().equals("I")){
					if( checkDuplicate(codeMgmtMasterVO[i]).size() != 0 ){
						throw new DAOException(new ErrorHandler("Duplicate").getMessage());
					}else{
						codeMgmtMasterVO[i].setCreUsrId(account.getUsr_id());
						insertVoList.add(codeMgmtMasterVO[i]);
					}
				} else if ( codeMgmtMasterVO[i].getIbflag().equals("U")){
					codeMgmtMasterVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(codeMgmtMasterVO[i]);
				} else if ( codeMgmtMasterVO[i].getIbflag().equals("D")){
//					deleteVoList.add(codeMgmtMasterVO[i]);
					CodeMgmtDetailVO detailVO = new CodeMgmtDetailVO();
					detailVO.setIntgCdId(codeMgmtMasterVO[i].getIntgCdId());
					deleteDetailVoList.add(detailVO);
					deleteVoList.add(codeMgmtMasterVO[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addmanageCodeMasterS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymanageCodeMgmtMasterS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemanageCodeMgmtMasterS(deleteVoList);
				dbDao.removemanageCodeMgmtDetailbyCdIDS(deleteDetailVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	/**
	 * manageCodeMgmtDetail to save the changed (add, update, delete) in database.<br>
	 * 
	 * @param CodeMgmtDetailVO[] codeMgmtDetailVO
	 * @param SignOnUserAccount account
	 * @exception EventException
	 */
	public void manageCodeMgmtDetail(CodeMgmtDetailVO[] codeMgmtDetailVO, SignOnUserAccount account) throws EventException{
		try {
			List<CodeMgmtDetailVO> insertVoList = new ArrayList<CodeMgmtDetailVO>();
			List<CodeMgmtDetailVO> updateVoList = new ArrayList<CodeMgmtDetailVO>();
			List<CodeMgmtDetailVO> deleteVoList = new ArrayList<CodeMgmtDetailVO>();
			for ( int i=0; i<codeMgmtDetailVO .length; i++ ) {
				if ( codeMgmtDetailVO[i].getIbflag().equals("I")){
					codeMgmtDetailVO[i].setCreUsrId(account.getUsr_id());
					insertVoList.add(codeMgmtDetailVO[i]);
					
				} else if ( codeMgmtDetailVO[i].getIbflag().equals("U")){
					codeMgmtDetailVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(codeMgmtDetailVO[i]);
					
				} else if ( codeMgmtDetailVO[i].getIbflag().equals("D")){
					deleteVoList.add(codeMgmtDetailVO[i]);
				}
				
			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addmanageCodeDetailS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymanageCodeMgmtDetailS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemanageCodeMgmtDetailS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
}