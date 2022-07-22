/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierMgmtDBDAOCarrierLaneRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.30
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.30 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice4.carriermgmt.integration ;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Quach Dai Duc
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class CarrierMgmtDBDAOCarrierLaneRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * ssss
	  * </pre>
	  */
	public CarrierMgmtDBDAOCarrierLaneRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.practice4.carriermgmt.integration ").append("\n"); 
		query.append("FileName : CarrierMgmtDBDAOCarrierLaneRSQL").append("\n"); 
		query.append("*/").append("\n"); 
	}
	
	public String getSQL(){
		return query.toString();
	}
	
	public HashMap<String,String[]> getParams() {
		return params;
	}

	/**
	 * Query 생성
	 */
	public void setQuery(){
		query.append("select vsl_slan_cd as rlane_cd" ).append("\n"); 
		query.append("from mdm_rev_lane" ).append("\n"); 
		query.append("WHERE delt_flg = 'N'" ).append("\n"); 

	}
}