package com.clt.apps.opus.dou.doutraining.codemgmt.event;

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtMasterVO;
import com.clt.apps.opus.dou.doutraining.codemgmt.vo.CodeMgmtDetailVO;


/**
 * DOU_TRN_0002 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  DOU_TRN_0002HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Duc Quach
 * @see DOU_TRN_0002HTMLAction 참조
 * @since J2EE 1.6
 */

public class DouTrn0002Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table Value Object 조회 조건 및 단건 처리  */
	CodeMgmtMasterVO codeMgmtMasterVO = null;
	CodeMgmtDetailVO codeMgmtDetailVO = null;
	
	/** Table Value Object Multi Data 처리 */
	CodeMgmtMasterVO[] codeMgmtMasterVOs = null;
	CodeMgmtDetailVO[] codeMgmtDetailVOs = null;
	
	
	
	public DouTrn0002Event() {}
	
	
	public CodeMgmtMasterVO getCodeMgmtMasterVO() {
		return codeMgmtMasterVO;
	}
	public void setCodeMgmtMasterVO(CodeMgmtMasterVO codeMgmtMasterVO) {
		this.codeMgmtMasterVO = codeMgmtMasterVO;
	}
	public CodeMgmtDetailVO getCodeMgmtDetailVO() {
		return codeMgmtDetailVO;
	}
	public void setCodeMgmtDetailVO(CodeMgmtDetailVO codeMgmtDetailVO) {
		this.codeMgmtDetailVO = codeMgmtDetailVO;
	}
	public CodeMgmtMasterVO[] getCodeMgmtMasterVOs() {
		return codeMgmtMasterVOs;
	}
	public void setCodeMgmtMasterVOs(CodeMgmtMasterVO[] codeMgmtMasterVOs) {
		this.codeMgmtMasterVOs = codeMgmtMasterVOs;
	}
	public CodeMgmtDetailVO[] getCodeMgmtDetailVOs() {
		return codeMgmtDetailVOs;
	}
	public void setCodeMgmtDetailVOs(CodeMgmtDetailVO[] codeMgmtDetailVOs) {
		this.codeMgmtDetailVOs = codeMgmtDetailVOs;
	}
	
//	public DouTrn001Event(){}
//	
//	public void setErrMsgVO(ErrMsgVO errMsgVO){
//		this. errMsgVO = errMsgVO;
//	}
//
//	public void setErrMsgVOS(ErrMsgVO[] errMsgVOs){
//		this. errMsgVOs = errMsgVOs;
//	}
//
//	public ErrMsgVO getErrMsgVO(){
//		return errMsgVO;
//	}
//
//	public ErrMsgVO[] getErrMsgVOS(){
//		return errMsgVOs;
//	}
	
	

}
