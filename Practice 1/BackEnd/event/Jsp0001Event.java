/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Jsp0001Event.java
*@FileTitle : testPr01
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.13
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.13 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.pratrain1.errmsgmgmt.event;

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.esm.clv.pratrain1.errmsgmgmt.vo.ErrMsgVO;


/**
 * JSP_0001 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  JSP_0001HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Quach Dai Duc
 * @see JSP_0001HTMLAction 참조
 * @since J2EE 1.6
 */

public class Jsp0001Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object Query conditions and single-gun processing  */
	ErrMsgVO errMsgVO = null;
	
	/** Table Value Object Multi Data handling */
	ErrMsgVO[] errMsgVOs = null;

	public Jsp0001Event(){}
	
	public void setErrMsgVO(ErrMsgVO errMsgVO){
		this. errMsgVO = errMsgVO;
	}

	public void setErrMsgVOS(ErrMsgVO[] errMsgVOs){
		this. errMsgVOs = errMsgVOs;
	}

	public ErrMsgVO getErrMsgVO(){
		return errMsgVO;
	}

	public ErrMsgVO[] getErrMsgVOS(){
		return errMsgVOs;
	}

}