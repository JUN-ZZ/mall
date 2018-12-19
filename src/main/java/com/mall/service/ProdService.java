package com.mall.service;

import java.util.List;

import com.mall.domain.Prod;

public interface ProdService {

	/**
	 * ??????????????
	 * @param prod
	 * @return true????? false??????
	 */
	boolean addPord(Prod prod);
	/**
	 * ?????????????????
	 * @return
	 */
	List<Prod> listAllProd();
	
	/**
	 * ?????????id?????????
	 * @param pid?????id
	 * @return true ?????? false ??????
	 */
	boolean deleteProdByPid(int pid);
	
	/**
	 * ???????id??????????????
	 * @param pid ???id
	 * @param pnum ???????
	 * @return true ????? false??????
	 */
	boolean updatePnum(int pid, int pnum);


	void  say();
}
