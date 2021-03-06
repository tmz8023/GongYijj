package com.Util;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.Statement;

	public class ConnImpl {
		static private ConnPool conPool=new ConnPool();
		public static void closeAll(Connection con,Statement ss,ResultSet rs){
			try {
				if(con!=null){con.close();con=null;};
				if(ss!=null){ss.close();ss=null;};
				if(rs!=null){rs.close();rs=null;};
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("99"+e.getMessage());
			}
		}
		public static void setParams(PreparedStatement ps,Object[] params){   
			try {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage()+"1");
			}
		}
		public static int executeUpdate(String sql,Object[] params){
			Connection con=conPool.getConnection();
			PreparedStatement ps=null;
			try {
				ps=con.prepareStatement(sql);
				if(params!=null){
					setParams(ps, params);
				}
				return ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage()+"2");
			}finally{
				closeAll(con, ps, null);
			}
			return -1;
		}
		public static ResultSet executeQuery(String sql,Object[]params){
			Connection con=conPool.getConnection();
			ResultSet rs=null;
			PreparedStatement ps=null;
			try {
				ps=con.prepareStatement(sql);
				if(params!=null){
					setParams(ps, params);
				}
				rs=ps.executeQuery();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("0"+e.getMessage());
			}
			return rs;
		}

}
