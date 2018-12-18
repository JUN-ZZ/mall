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
	 * �����Ʒ
	 */
	@Override
	public boolean addPord(Prod prod) {
		boolean flag = false;
		try{
			//�ж����ݿ��Ƿ��и���Ʒ����
			int cid = -1;
				//��������
			TransactionManager.startTrans();
			cid = dao.getCidByCname(prod.getCname());			
			//���û�и���Ʒ����-����Ӹ���Ʒ����
			if(cid==-1){
				ProdCategory pc = new ProdCategory();
				pc.setId(-1);
				pc.setCname(prod.getCname());
				boolean flag2 = dao.insertProdCategory(pc);
				if(flag2==false){
					//��Ʒ�������ʧ��
					return false;
				}
				//��ȡ����ӵ���Ʒ����id
				cid = dao.getCidByCname(prod.getCname());
			}
			
			//�����Ʒ
			prod.setCid(cid);
			flag = dao.insertProd(prod);
			//�ύ����
			TransactionManager.commitTrans();
		}catch(Exception e){
			e.printStackTrace();
			//�ع�����
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
		//�����������Ʒ����Ʒ��������һ����Ʒ
		//ɾ����Ʒ��ͬʱ��Ҫɾ������Ʒ����
		boolean flag = true;
		try{
			//��������
			TransactionManager.startTrans();
			//�жϸ���Ʒ�Ƿ����Ʒ���һ����Ʒ
			//1.����pid��ȡ��Ʒ���и���Ʒ�����Ӧ����Ʒ������
			int cid = dao.getCidByPid(pid);
			//2.����cid��ȡ��Ʒ���и���Ʒ�����Ӧ����Ʒ������
			int count = dao.getCountByCid(cid);
			//����ǣ���ɾ����Ʒͬʱɾ����Ʒ����
			if(count==1){
				flag = dao.deleteProdByPid(pid)&&flag;
				flag = dao.deletePCByCid(cid)&&flag;
			}else{
				//������ǣ�ֻɾ����Ʒ
				flag = dao.deleteProdByPid(pid);
			}
			///�ύ����
			TransactionManager.commitTrans();
		}catch(Exception e){
			e.printStackTrace();
			//�ع�����
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
