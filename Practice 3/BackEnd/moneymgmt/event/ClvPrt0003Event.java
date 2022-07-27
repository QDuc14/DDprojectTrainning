/*=========================================================
 *Copyright(c) 2022 CyberLogitec
 *@FileName : ClvPrt0003Event.java
 *@FileTitle : Money Management
 *Open Issues :
 *Change history :
 *@LastModifyDate : 2022.07.14
 *@LastModifier : 
 *@LastVersion : 1.0
 * 2022.07.10 
 * 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice3.moneymgmt.event;

import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.DetailVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.SummaryVO;
import com.clt.framework.support.layer.event.EventSupport;
/**
 * CLV_PRT_0003HTMLAction for PDTO(Data Transfer Object including Parameters)<br>
 * - Created from CLV_PRT_0003HTMLAction<br>
 * - Used as PDTO delivered to ServiceCommand Layer<br>
 *
 * @author duc
 * @see CLV_PRT_0003HTMLAction refer
 * @since J2EE 1.6
 */
public class ClvPrt0003Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	SummaryVO summaryVO = null;
	SummaryVO[] summaryVOs = null;
	
	DetailVO detailVO = null;
	DetailVO[] detailVOs = null;
	/**
	 * ClvPrt0003Event generation
	 */
	public ClvPrt0003Event() {}
	/**
	 * getSummaryVO
	 * @return SummaryVO
	 */
	public SummaryVO getSummaryVO() {
		return summaryVO;
	}
	/**
	 * setSummaryVO
	 * @param summaryVO
	 */
	public void setSummaryVO(SummaryVO summaryVO) {
		this.summaryVO = summaryVO;
	}
	/**
	 * getSummaryVOs
	 * @return SummaryVO[]
	 */
	public SummaryVO[] getSummaryVOs() {
		return summaryVOs;
	}
	/**
	 * setSummaryVOs
	 * @param summaryVOs
	 */
	public void setSummaryVOs(SummaryVO[] summaryVOs) {
		this.summaryVOs = summaryVOs;
	}
	/**
	 * getDetailVO
	 * @return DetailVO
	 */
	public DetailVO getDetailVO() {
		return detailVO;
	}
	/**
	 * setDetailVO
	 * @param detailVO
	 */
	public void setDetailVO(DetailVO detailVO) {
		this.detailVO = detailVO;
	}
	/**
	 * getDetailVOs
	 * @return DetailVO[]
	 */
	public DetailVO[] getDetailVOs() {
		return detailVOs;
	}
	/**
	 * setDetailVOs
	 * @param detailVOs
	 */
	public void setDetailVOs(DetailVO[] detailVOs) {
		this.detailVOs = detailVOs;
	}
}
