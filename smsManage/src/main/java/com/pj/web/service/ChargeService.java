package com.pj.web.service;

import com.pj.core.gereric.GenericService;
import com.pj.web.model.Charge;

public interface ChargeService extends GenericService<Charge, Long> {

	boolean updateBalance(Charge chage);
	
}
