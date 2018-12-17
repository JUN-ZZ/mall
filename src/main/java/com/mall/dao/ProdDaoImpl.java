package com.mall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mall.domain.Prod;
import com.mall.domain.ProdCategory;
import com.mall.exception.MsgException;
import com.mall.util.JDBCUtils;
import com.mall.util.TransactionManager;

public class ProdDaoImpl implements ProdDao {

	@Override
	public int getCidByCname(String cname) throws MsgException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//con = JDBCUtils.getConnection();
		try {
			con = TransactionManager.getConn();
			ps = con.prepareStatement("select id from prod_category where cname=? ");
			ps.setString(1, cname);
			rs = ps.executeQuery();
			while(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MsgException("查询cid出现异常");
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
		return -1;
	}

	@Override
	public boolean insertProdCategory(ProdCategory pc) {
		Connection con = null;
		PreparedStatement ps = null;
		//con = JDBCUtils.getConnection();
		con = TransactionManager.getConn();
		try {
			String sql = "insert into prod_category values(null,?) ";
			ps = con.prepareStatement(sql);
			ps.setString(1, pc.getCname());
			int len = ps.executeUpdate();
			if(len>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, ps,null);
		}

		return false;
	}

	@Override
	public boolean insertProd(Prod prod) {
		Connection con = null;
		PreparedStatement ps = null;
//		con = JDBCUtils.getConnection();
		con = TransactionManager.getConn();
		try {
			String sql = "insert into prod values(null,?,?,?,?,?,?) ";
			ps = con.prepareStatement(sql);
			ps.setString(1, prod.getName());
			ps.setDouble(2, prod.getPrice());
			ps.setInt(3, prod.getCid());
			ps.setInt(4, prod.getPnum());
			ps.setString(5, prod.getImgurl());
			ps.setString(6, prod.getDescription());
			System.out.println(prod.getImgurl());
			System.out.println(prod.getImgurl().length());

			int len = ps.executeUpdate();
			if(len>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, ps, null);
		}
		return false;
	}
	
	@Override
	public List<Prod> listAllProd() {
		String sql = "select p.*,c.cname from prod p join "
				+ "prod_category c on p.cid=c.id ";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Prod> list = new ArrayList<Prod>();
		try {
			con = JDBCUtils.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Prod prod = new Prod();
				prod.setId(rs.getInt("id"));
				prod.setName(rs.getString("pname"));
				prod.setCid(rs.getInt("cid"));
				prod.setCname(rs.getString("cname"));
				prod.setPrice(rs.getDouble("price"));
				prod.setPnum(rs.getInt("pnum"));
				prod.setImgurl(rs.getString("imgurl"));
				prod.setDescription(rs.getString("description"));
				list.add(prod);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.close(con, ps, rs);
		}
		return list;
	}

	
	@Override
	public boolean deleteProdByPid(int pid) {
		String sql = "delete from prod where id=? ";
		Connection con= null;
		PreparedStatement ps = null;
		try{
			con = TransactionManager.getConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, pid);
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, ps, null);
		}
		return false;
	}

	@Override
	public int getCidByPid(int pid) {
		String sql = "select cid from prod where id=? ";
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = TransactionManager.getConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, pid);
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getInt("cid");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
		return 0;
	}

	@Override
	public int getCountByCid(int cid) {
		//for update-->使用悲观锁解决方案，手动添加排他锁
		String sql = "select count(*) from prod where cid=? for update ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = TransactionManager.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, ps, rs);
		}
		return 0;
	}

	@Override
	public boolean deletePCByCid(int cid) {
		String sql = "delete from prod_category where id=? ";
		Connection con= null;
		PreparedStatement ps = null;
		try{
			con = TransactionManager.getConn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cid);
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.close(null, ps, null);
		}
		return false;
	}

	@Override
	public boolean updatePnumByPid(int pid, int pnum) {
		String sql = "update prod set pnum=? where id=? ";
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pnum);
			ps.setInt(2, pid);
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, ps, null);
		}
		return false;
	}
	
	
}
