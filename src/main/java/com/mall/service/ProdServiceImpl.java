package com.mall.service;

import java.util.List;

import com.mall.dao.ProdDao;
import com.mall.domain.Prod;
import com.mall.domain.ProdCategory;
import com.mall.exception.MsgException;
import com.mall.util.BaseFactory;
import com.mall.util.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("prodService")
public class ProdServiceImpl implements ProdService {
	
//	private ProdDao dao = BaseFactory.getFactory().getInstance(ProdDao.class);


	@Autowired
	private ProdDao dao ;

	/**
	 * 添加商品
	 */
	@Override
	public boolean addPord(Prod prod) {
		boolean flag = false;
		try{
			//判断数据库是否有该商品种类
			int cid = -1;
				//开启事务
			TransactionManager.startTrans();
			cid = dao.getCidByCname(prod.getCname());			
			//如果没有该商品种类-先添加该商品种类
			if(cid==-1){
				ProdCategory pc = new ProdCategory();
				pc.setId(-1);
				pc.setCname(prod.getCname());
				boolean flag2 = dao.insertProdCategory(pc);
				if(flag2==false){
					//商品种类添加失败
					return false;
				}
				//获取新添加的商品种类id
				cid = dao.getCidByCname(prod.getCname());
			}
			
			//添加商品
			prod.setCid(cid);
			flag = dao.insertProd(prod);
			//提交事务
			TransactionManager.commitTrans();
		}catch(Exception e){
			e.printStackTrace();
			//回滚事务
			TransactionManager.rollBack();
		}finally{			
			TransactionManager.closeConn();
		}
		return flag;
	}

	@Override
	public List<Prod> listAllProd() {
		return dao.listAllProd();
	}

	@Override
	public boolean deleteProdByPid(int pid) {
		//需求：如果该商品是商品种类的最后一件商品
		//删除商品的同时，要删除该商品种类
		boolean flag = true;
		try{
			//开启事务
			TransactionManager.startTrans();
			//判断该商品是否该商品最后一件商品
			//1.根据pid获取商品表中该商品种类对应的商品的数量
			int cid = dao.getCidByPid(pid);
			//2.根据cid获取商品表中该商品种类对应的商品的数量
			int count = dao.getCountByCid(cid);
			//如果是，则删除商品同时删除商品种类
			if(count==1){
				flag = dao.deleteProdByPid(pid)&&flag;
				flag = dao.deletePCByCid(cid)&&flag;
			}else{
				//如果不是，只删除商品
				flag = dao.deleteProdByPid(pid);
			}
			///提交事务
			TransactionManager.commitTrans();
		}catch(Exception e){
			e.printStackTrace();
			//回滚事务
			TransactionManager.rollBack();
		}finally{
			TransactionManager.closeConn();
		}
		return flag;
	}

	@Override
	public boolean updatePnum(int pid, int pnum) {
		return dao.updatePnumByPid(pid,pnum);
	}

	
	
}
