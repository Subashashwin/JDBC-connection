package org.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Connectivity {
public List<Map<String, String>> firstMethod() {
		
		List<Map<String,String>> list=new ArrayList<Map<String, String>>();
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:xe", "HR", "subash");

			Statement ps = con.createStatement();
			String sql = "select * from Details";
			ResultSet eq = ps.executeQuery(sql);
			
			ResultSetMetaData md = eq.getMetaData();
			java.lang.String cn1 = md.getColumnName(1);
			java.lang.String cn2 = md.getColumnName(2);
			java.lang.String cn3 = md.getColumnName(3);
			java.lang.String cn4 = md.getColumnName(4);
			java.lang.String cn5 = md.getColumnName(5);
			
			while (eq.next()) {
				
				Map<String, String> map = new LinkedHashMap<String, String>();
				    String st1 = eq.getString("FIRST_NAME");
					String st2 = eq.getString("LAST_NAME");
					String st3 = eq.getString("AGE");
					String st4 = eq.getString("GENDER");
					String st5= eq.getString("NATIVE");
										
					map.put(cn1,st1);
					map.put(cn2,st2);
					map.put(cn3,st3);
					map.put(cn4,st4);
					map.put(cn5,st5);
				
				    list.add(map);
				   
						}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return list;
		
   }
	

	public void secondMethod(List<Map<String, String>> result) {

		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:xe", "HR", "subash");

//			String sql1 = "Alter Table Details Add second_name VARCHAR(4000)";
//			PreparedStatement ps1 = con.prepareStatement(sql1);
//	     //  ps1.executeQuery();
	         
			for (Map<String, String> entry : result) {
				Set<Entry<String, String>> ks = entry.entrySet();
				
				for (Entry<String, String> e : ks) {
					String k = e.getKey();
					String v = e.getValue();
					String s=v+k;
					
					if(s.contains("FIRST_NAME")) {
						
						String value = s.replace("FIRST_NAME", "");
						
					    String sql2 = "Update Details Set second_name='" +value+ "' where FIRST_NAME='"+value+"' ";
						String sql3 = "Update Details Set Native='AUSTRALIA' where FIRST_NAME='" +value+ "' ";
						
						PreparedStatement ps2 = con.prepareStatement(sql2);
						PreparedStatement ps3= con.prepareStatement(sql3);
						
					    ps2.executeQuery();
						ps3.executeQuery();
						
								}
					}
				
				}
			            
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	public void thirdMethod(List<Map<String, String>> result) {
		for (Map<String, String> map : result) {
			
			Set<Entry<String, String>> es = map.entrySet();
			
			for (Entry<String, String> entry : es) {
				System.out.println(entry);		
			}
			System.out.println('\n');
		}
				
			}
			 
	public static void main(String[] args) {
		Connectivity sc = new Connectivity();
		List<Map<String, String>> result = sc.firstMethod();
		sc.thirdMethod(result);
		sc.secondMethod(result);

	}

}
