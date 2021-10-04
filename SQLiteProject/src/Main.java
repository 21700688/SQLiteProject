import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			Scanner s = new Scanner(System.in);
			String dbfile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			int id2;
			String sql1 = "Select * from g_artist";
			String sql2 = "insert into g_artist (name, a_type, a_year, debut, regdate)"+"values('방탄소년단','남성','2010년대','2013년',datetime('now','localtime'));";
			String sql3 = "update g_artist set a_year = '2000년대,2010년대,2020년대'"+" where id = 1";
			
			String sql;
			boolean m = true;
			
			for(;;) {
				if(m){System.out.println("\n1. 조회(sql1)\n2. 입력(sql2)\n3. 수정(sql3)\n4. 삭제(sql4)\n5. exit");}
				System.out.print("\n>>");
			sql = s.nextLine();
			if(sql.equals("sql1")) {
						System.out.println("\n***데이터 조회***");
						Statement stat1 = con.createStatement();
						ResultSet rs1 = stat1.executeQuery(sql1);
						while(rs1.next()) {
							String id  = rs1.getString("id");
							String name = rs1.getString("name");
							System.out.println(id+" "+name);}stat1.close();
							m=false;}
			
			
			else if(sql.equals("sql2")) {System.out.println("\n***새데이터 추가***");
						Statement stat2 = con.createStatement();
						int cnt = stat2.executeUpdate(sql2);
						if(cnt>0) System.out.println("새로운 데이터가 추가되었습니다.");
						else System.out.println("[Error] 데이터 추가 오류!");
						m=false;}
			
			else if(sql.equals("sql3")) {System.out.println("\n***데이터 수정***");
						Statement stat3  = con.createStatement();
						int cnt  = stat3.executeUpdate(sql3);
						if(cnt>0) System.out.println("데이터가 수정되었습니다.");
						else System.out.println("[Error] 데이터 수정 오류!");
						m=false;}
			
			else if(sql.equals("sql4")) {System.out.println("\n***데이터 삭제***");
						System.out.printf("id: ");
						id2 = s.nextInt();
						s.nextLine();
						String sql4 = "delete from g_artist where id = "+id2;
						Statement stat4  = con.createStatement();
						int cnt = stat4.executeUpdate(sql4);
						if(cnt>0) System.out.println("데이터가 삭제되었습니다.");
						else System.out.println("[Error] 데이터 삭제 오류!");
						m=false;}
			else if(sql.equals("help")) {m=true;}
			
			else if(sql.equals("exit")) {return;}
			
			else {System.out.println("잘못된 입력입니다!");}
			
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {
				try {
					con.close();
				} catch (Exception e) {}
			}
		}

	}

}
