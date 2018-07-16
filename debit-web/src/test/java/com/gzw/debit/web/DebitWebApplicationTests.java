package com.gzw.debit.web;

import com.gzw.debit.core.manager.DayAliveManager;
import com.gzw.debit.core.manager.MerchantManager;
import com.gzw.debit.core.manager.UserManager;
import com.gzw.debit.core.utils.DateUtil;
import com.gzw.debit.dal.model.ext.MerchantDataDO;
import com.gzw.debit.dal.model.ext.StrIntKeyValue;
import com.gzw.debit.dal.query.ext.AliveDataQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DebitWebApplicationTests {

	@Autowired
	private DayAliveManager dayAliveManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private MerchantManager merchantManager;

	@Test
	public void contextLoads() {

		LocalDateTime endTime = DateUtil.getTodayLastTime();
		LocalDateTime startTime = DateUtil.getBefore30Day(DateUtil.getToday0Time());
		AliveDataQuery query = new AliveDataQuery();
		query.setStartTime(startTime);
		query.setEndTime(endTime);
		List<StrIntKeyValue> aliveDataDOS = dayAliveManager.getAliveData(query);
		if(CollectionUtils.isEmpty(aliveDataDOS)){

		}

	}

	@Test
	public void registerDate() {
		LocalDateTime endTime = DateUtil.getTodayLastTime();
		LocalDateTime startTime = DateUtil.getBefore30Day(DateUtil.getToday0Time());
		AliveDataQuery query = new AliveDataQuery();
		query.setStartTime(startTime);
		query.setEndTime(endTime);
		List<StrIntKeyValue> aliveDataDOS = userManager.getRegisterData(query);
		if(CollectionUtils.isEmpty(aliveDataDOS)){

		}

	}

	@Test
	public void maerchant() {

		List<MerchantDataDO> info = merchantManager.getMerchantRegisterData();
		if(CollectionUtils.isEmpty(info)){

		}
	}

}
