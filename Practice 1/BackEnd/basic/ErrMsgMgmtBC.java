/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMgmtBC.java
*@FileTitle : testPr01
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.13
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.13 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.pratrain1.errmsgmgmt.basic;

import java.util.List;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.pratrain1.errmsgmgmt.vo.ErrMsgVO;

/**
 * ALPS-Pratrain1 Business Logic Command Interface<br>
 * - ALPS-Pratrain1에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Quach Dai Duc
 * @since J2EE 1.6
 */

public interface ErrMsgMgmtBC {

	/**
	 * searchErrMsg to get a list of ErrMsgVO.<br>
	 * 
	 * @param ErrMsgVO	errMsgVO
	 * @return List<ErrMsgVO>
	 * @exception EventException
	 */
	public List<ErrMsgVO> searchErrMsg(ErrMsgVO errMsgVO) throws EventException;
	
	/**
	 * manageErrMsg to save the changed (add, update, delete) in database<br>
	 * 
	 * @param ErrMsgVO[] errMsgVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void manageErrMsg(ErrMsgVO[] errMsgVO,SignOnUserAccount account) throws EventException;
}