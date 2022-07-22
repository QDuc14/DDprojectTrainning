/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CodeMgmtBC.java
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

import java.util.List;

import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtMasterVO;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtDetailVO;
/**
 * ALPS-Doutraining Business Logic Command Interface<br>
 * - ALPS-Doutraining에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Duc Quach
 * @since J2EE 1.6
 */

public interface CodeMgmtBC {

	/**
	 * searchCodeMgmtMaster to retrieve data in database.<br>
	 * 
	 * @param CodeMgmtMasterVO codeMgmtMasterVO
	 * @return List<CodeMgmtMasterVO>
	 * @exception EventException
	 */
	public List<CodeMgmtMasterVO> searchCodeMgmtMaster(CodeMgmtMasterVO codeMgmtMasterVO) throws EventException;
	
	/**
	 * searchCodeMgmtDetail to retrieve data in database.<br>
	 * 
	 * @param CodeMgmtDetailVO codeMgmtDetailVO
	 * @return List<CodeMgmtDetailVO>
	 * @exception EventException
	 */
	public List<CodeMgmtDetailVO> searchCodeMgmtDetail(CodeMgmtDetailVO codeMgmtDetailVO) throws EventException;
	
	/**
	 * searchCodeMgmtDetail to save the changed (add, update, delete) in database<br>
	 * 
	 * @param CodeMgmtMasterVO[] codeMgmtMasterVO
	 * @param SignOnUserAccount account
	 * @exception EventException
	 */
	public void manageCodeMgmtMaster(CodeMgmtMasterVO[] codeMgmtMasterVO,SignOnUserAccount account) throws EventException;
	
	/**
	 * manageCodeMgmtMaster to save the changed (add, update, delete) in database<br>
	 * 
	 * @param CodeMgmtDetailVO[] codeMgmtDetailVO
	 * @param SignOnUserAccount account
	 * @exception EventException
	 */
	public void manageCodeMgmtDetail(CodeMgmtDetailVO[] codeMgmtDetailVO,SignOnUserAccount account) throws EventException;
	
}