package com.mall.service;

import java.util.List;

import com.mall.domain.Prod;

public interface ProdService {

	/**
	 * �����Ʒ��Ϣ�ķ���
	 * @param prod
	 * @return true��ӳɹ� false���ʧ��
	 */
	boolean addPord(Prod prod);
	/**
	 * ��ѯȫ����Ʒ��Ϣ�ķ���
	 * @return
	 */
	List<Prod> listAllProd();
	
	/**
	 * ������Ʒ��idɾ����Ʒ��¼
	 * @param pid��Ʒ��id
	 * @return true ɾ���ɹ� false ɾ��ʧ��
	 */
	boolean deleteProdByPid(int pid);
	
	/**
	 * ������Ʒid�޸���Ʒ���ݵķ���
	 * @param pid ��Ʒid
	 * @param pnum ��Ʒ����
	 * @return true �޸ĳɹ� false�޸�ʧ��
	 */
	boolean updatePnum(int pid, int pnum);
	
}
