package com.mall.dao;

import java.util.List;

import com.mall.domain.Prod;
import com.mall.domain.ProdCategory;
import com.mall.exception.MsgException;
import org.springframework.stereotype.Repository;

public interface ProdDao {
	/**
	 * ������Ʒ�������Ʋ�ѯ��Ʒ����id�ķ���
	 * @param cname ��Ʒ����
	 * @return ��Ʒ����id ��-1
	 * @throws MsgException
	 */
	int getCidByCname(String cname) throws MsgException ;
	/**
	 * �����Ʒ����ķ���
	 * @param pc
	 * @return true ��ӳɹ� false���ʧ��
	 */
	boolean insertProdCategory(ProdCategory pc);
	/**
	 * �����Ʒ�ķ���
	 * @param prod Javabean
	 * @return true��ӳɹ� false���ʧ�� 
	 */
	boolean insertProd(Prod prod);
	/**
	 * ��ȡ������Ʒ���ݵķ���
	 * @return ��װ����Ʒ���ݵ�JavaBean�ļ���
	 */
	List<Prod> listAllProd();
	
	/**
	 * ������Ʒ��idɾ����Ʒ��¼
	 * @param pid��Ʒ��id
	 * @return true ɾ���ɹ� false ɾ��ʧ��
	 */
	boolean deleteProdByPid(int pid);
	
	/**
	 * ������Ʒid��ȡ��Ʒ����id
	 * @param pid
	 * @return
	 */
	int getCidByPid(int pid);
	
	/**
	 * ������Ʒ����id��ȡ��Ӧ��Ʒ������
	 * @param cid��Ʒ����id
	 * @return true�ɹ� false ʧ��
	 */
	int getCountByCid(int cid);
	
	/**
	 * ������Ʒ�����idɾ����Ʒ����ļ�¼
	 * @param cid ��Ʒ����id
	 * @return true�ɹ� false ʧ��
	 */
	boolean deletePCByCid(int cid);
	
	/**
	 * ������Ʒid�޸���Ʒ����
	 * @param pid
	 * @param pnum
	 * @return
	 */
	boolean updatePnumByPid(int pid, int pnum);
	
	
}
