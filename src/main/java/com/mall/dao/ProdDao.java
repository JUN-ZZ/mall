package com.mall.dao;

import java.util.List;

import com.mall.domain.Prod;
import com.mall.domain.ProdCategory;
import com.mall.exception.MsgException;
import org.springframework.stereotype.Repository;

public interface ProdDao {
	/**
	 * 根据商品种类名称查询商品种类id的方法
	 * @param cname 商品名称
	 * @return 商品种类id 或-1
	 * @throws MsgException
	 */
	int getCidByCname(String cname) throws MsgException ;
	/**
	 * 添加商品种类的方法
	 * @param pc
	 * @return true 添加成功 false添加失败
	 */
	boolean insertProdCategory(ProdCategory pc);
	/**
	 * 添加商品的方法
	 * @param prod Javabean
	 * @return true添加成功 false添加失败 
	 */
	boolean insertProd(Prod prod);
	/**
	 * 获取所有商品数据的方法
	 * @return 封装了商品数据的JavaBean的集合
	 */
	List<Prod> listAllProd();
	
	/**
	 * 根据商品的id删除商品记录
	 * @param pid商品的id
	 * @return true 删除成功 false 删除失败
	 */
	boolean deleteProdByPid(int pid);
	
	/**
	 * 根据商品id获取商品种类id
	 * @param pid
	 * @return
	 */
	int getCidByPid(int pid);
	
	/**
	 * 根据商品种类id获取对应商品的数量
	 * @param cid商品种类id
	 * @return true成功 false 失败
	 */
	int getCountByCid(int cid);
	
	/**
	 * 根据商品种类的id删除商品种类的记录
	 * @param cid 商品种类id
	 * @return true成功 false 失败
	 */
	boolean deletePCByCid(int cid);
	
	/**
	 * 根据商品id修改商品数量
	 * @param pid
	 * @param pnum
	 * @return
	 */
	boolean updatePnumByPid(int pid, int pnum);
	
	
}
