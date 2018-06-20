package jp.co.cyms.apps.fac001.bl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import jp.co.cyms.apps.fac001.bean.Pac0022Bean;
import jp.co.cyms.apps.fac001.dao.Pac0022Dao;

public class Pac0022BL {

    /**
	 * get all order lease in ORDER_LEASE by orderCd
	 * @param orderId
	 * @return
	 */
	public List<Pac0022Bean> getOrderLease(String orderId) {
		Pac0022Dao dao = new Pac0022Dao();
		List<Pac0022Bean> list = dao.getOrderLease(orderId);
		if (list.size() > 0) {
            return list;
        }
        return null;
	}

	public int doUpdateOrderLease(Pac0022Bean pac0022Bean) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		//set lease status
		Date endDate = StringUtils.isEmpty(pac0022Bean.getEndDt()) ? 
				new Date(0) : sdf.parse(pac0022Bean.getEndDt());
		Date currentDate = new Date();
		
		if (endDate.compareTo(currentDate) < 0) {
			pac0022Bean.setLeaseStatus("ACTIVE"); 
		} else {
			pac0022Bean.setLeaseStatus("EXPIRE");
		}
		
		//set deliver month
		String deliverMth = StringUtils.isEmpty(pac0022Bean.getDeliverDt()) ? 
				StringUtils.EMPTY : pac0022Bean.getDeliverDt();
		pac0022Bean.setDeliverMth(deliverMth);
		
		Pac0022Dao dao = new Pac0022Dao();
		return dao.doUpdateOrderLease(pac0022Bean);
	}

	public int doUpdateOrderDtl(Pac0022Bean pac0022Bean) {
		Pac0022Dao dao = new Pac0022Dao();
		return dao.doUpdateOrderDtl(pac0022Bean);
	}
	
}
