package com.clt.apps.opus.esm.clv.practice4.carriermgmt.event;

import com.clt.apps.opus.esm.clv.practice4.carriermgmt.vo.CarrierVO;
import com.clt.framework.support.layer.event.EventSupport;

public class ClvPrt0004Event extends EventSupport{

	
	private static final long serialVersionUID = 1L;
	
	CarrierVO carrierVO = null;
	CarrierVO[] carrierVOs = null;
	
	public ClvPrt0004Event() {}

	public CarrierVO getCarrierVO() {
		return carrierVO;
	}

	public void setCarrierVO(CarrierVO carrierVO) {
		this.carrierVO = carrierVO;
	}

	public CarrierVO[] getCarrierVOs() {
		return carrierVOs;
	}

	public void setCarrierVOs(CarrierVO[] carrierVOs) {
		this.carrierVOs = carrierVOs;
	}
	
	
}
