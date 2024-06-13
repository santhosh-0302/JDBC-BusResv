

		import java.sql.*;

		public class JDBCDemo {

			public static void main(String[] args) throws Exception {
				// TODO Auto-generated method stub
				//readRecords();
				//insertRecord();
				//insertVar();
				//insertUsingPst();
				  update();
				   //sp3();
				//commitdemo();
				//batchdemo();
				
			}
			public static void readRecords() throws Exception {
				String url ="jdbc:mysql://localhost:3306/jdbcdemo";
				String username = "root";
				String password = "Santhosh@1459";
				String query ="select * from employee";
				
				
				Connection con =DriverManager.getConnection(url,username,password);//we get connection object
				Statement st= con.createStatement();// we get statement object
				ResultSet rs =st.executeQuery(query);//only for read the records and it return resultset
				
				while(rs.next())
				{
				System.out.println("ID is "+rs.getInt(1));
				System.out.println("Name is "+rs.getString(2));
				System.out.println("Salary is "+rs.getInt(3));
				}
				con.close();
			}
			public static void insertRecord() throws Exception {
				String url ="jdbc:mysql://localhost:3306/jdbcdemo";
				String username = "root";
				String password = "Santhosh@1459";
				String query ="insert into employee values(4,'Ramya',250000)";
				
				Connection con =DriverManager.getConnection(url,username,password);
				Statement st= con.createStatement();
				int row=st.executeUpdate(query);// is only return affected rows so it declare as int
				
				System.out.println("No of rows affected : "+row);
				con.close();
			}
			public static void insertVar() throws Exception {
				String url ="jdbc:mysql://localhost:3306/jdbcdemo";
				String username = "root";
				String password = "Santhosh@1459";
				
				int id=5;
				String name="Sachin";
				int salary=300000;
				
				
				
				String query ="insert into employee values(" + id + " ,'"+name+" ',"+salary+");";
				
				Connection con =DriverManager.getConnection(url,username,password);
				Statement st= con.createStatement();
				int row=st.executeUpdate(query);// is only return affected rows so it declare as int
				
				System.out.println("No of rows affected : "+row);
				con.close();
			}
			//insert with prepared Statement
			public static void insertUsingPst() throws Exception {
				String url ="jdbc:mysql://localhost:3306/jdbcdemo";
				String username = "root";
				String password = "Santhosh@1459";
				
				int id=6;
				String name="Nila";
				int salary=300000;
				
				
				//for alter above passing method 
				String query ="insert into employee values(?,?,?);";
				
				Connection con =DriverManager.getConnection(url,username,password);
				
				PreparedStatement pst=con.prepareStatement(query);
				pst.setInt(1, id);
				pst.setString(2, name);// for above question mark give information
				pst.setInt(3,salary);
				int row=pst.executeUpdate();//not need to pass query because already set the values in pst
				
				
				System.out.println("No of rows affected : "+row);
				con.close();
			}
			//for Delete
			public static void delete() throws Exception {
				String url ="jdbc:mysql://localhost:3306/jdbcdemo";
				String username = "root";
				String password = "Santhosh@1459";
				
				int id=5;
				
				 
				String query ="delete from employee where emp_id = "+id;
				
				Connection con =DriverManager.getConnection(url,username,password);
				Statement st=con.createStatement();
			
				int row=st.executeUpdate(query);
				
				System.out.println("No of rows affected : "+row);
				con.close();
			}
			public static void update() throws Exception {
				String url ="jdbc:mysql://localhost:3306/jdbcdemo";
				String username = "root";
				String password = "Santhosh@1459";

				
				String query ="update employee set salary = 150000 where emp_id=1";
				
				
				Connection con =DriverManager.getConnection(url,username,password);
				Statement st=con.createStatement();
			
				int row=st.executeUpdate(query);
				
				System.out.println("No of rows affected : "+row);
				con.close();
			}
			//Types of statement
			//normal statement
			//prepared statement
			//callable statement------> for stored procedure
			
			//calling simple stored procedure
			public static void sp() throws Exception{
				String url="jdbc:mysql://localhost:3306/jdbcdemo";
				String username="root";
				String password ="Santhosh@1459";
				
				Connection con =DriverManager.getConnection(url,username,password);
				CallableStatement cst=con.prepareCall("{call GetEmp()}");//extra curly brackets
				ResultSet rs =cst.executeQuery();
				
				while(rs.next())
				{
				System.out.println("ID is "+rs.getInt(1));
				System.out.println("Name is "+rs.getString(2));
				System.out.println("Salary is "+rs.getInt(3)); 
				}
			}
			public static void sp2() throws Exception{
				String url="jdbc:mysql://localhost:3306/jdbcdemo";
				String username="root";
				String password ="Santhosh@1459";
				int id=2;
				
				Connection con =DriverManager.getConnection(url,username,password);
				CallableStatement cst=con.prepareCall("{call GetEmpById(?)}");//extra curly brackets
				cst.setInt(1, id);
				ResultSet rs =cst.executeQuery();
				
				
				while(rs.next())
				{
				System.out.println("ID is "+rs.getInt(1));
				System.out.println("Name is "+rs.getString(2));
				System.out.println("Salary is "+rs.getInt(3)); 
				}
				con.close();
				
			}
			//calling stored procedure with in and out paramater
			public static void sp3() throws Exception
			{
			    String url="jdbc:mysql://localhost:3306/jdbcdemo";
			    String username="root";
			    String password ="Santhosh@1459";
			    int id=3;

			    //Class.forName("com.mysql.jdbc.Driver"); // load the driver

			    Connection con=DriverManager.getConnection(url,username,password);
			    CallableStatement cst=con.prepareCall("{call GetNameById(?,?)}");
			    cst.setInt(1, id);
			    cst.registerOutParameter(2,Types.VARCHAR);

			    cst.executeUpdate();

			    System.out.println(cst.getString(2));
			    con.close();
			}
			//commit vs autocommit
			public static void commitdemo() throws Exception
			{
				String url="jdbc:mysql://localhost:3306/jdbcdemo";
				String username="root";
				String password="Santhosh@1459";
				
				int salary=40000;
				int id=2;
				
				String query1="insert into employee(salary,emp_id) values(?,?);";
				String query2="update employee set salary = 400000 where emp_id=2";
				
				Connection con=DriverManager.getConnection(url,username,password);
				PreparedStatement pst=con.prepareStatement(query1);
				pst.setInt(1, salary);
				pst.setInt(2, id);
				
				
				Statement st =con.createStatement();
				
				int rows1=pst.executeUpdate();
				System.out.println("Rows affected"+rows1);
				
				int rows2=st.executeUpdate(query2);
				System.out.println("Rows affected"+rows2);
				
				con.close();
				
			}
			
			public static void commitdemo() throws Exception// in this case previous query also blocked by using auto commit
			{
			    String url="jdbc:mysql://localhost:3306/jdbcdemo";
			    String username="root";
			    String password="Santhosh@1459";

			    int salary=60000;
			    int id=1;

			    String query1="update  employee set salary=? where emp_id=?";
			    String query2="update  employee set salary = 500000 where emp_id=2";

		
			    Connection con=DriverManager.getConnection(url,username,password);
			    con.setAutoCommit(false);

			    PreparedStatement pst=con.prepareStatement(query1);
			    pst.setInt(1, salary);
			    pst.setInt(2, id);
			 
			    Statement st =con.createStatement();

			    int rows1=pst.executeUpdate();
			    System.out.println("Rows affected"+rows1);

			    int rows2=st.executeUpdate(query2);
			    System.out.println("Rows affected"+rows2);
			    
			    if(rows1>0 && rows2>0)  
			    	con.commit();

			    con.close();
			}
			
			public static void batchdemo() throws Exception{  //all query execute at one time by using batch and use block previous error queries by usin autocommit and rollback
				String url="jdbc:mysql://localhost:3306/jdbcdemo";
				String username="root";
				String password="Santhosh@1459";
				
				String query1="update  employee set salary = 400000 where emp_id=1";
				String query2="update  employee set salary = 400000 where emp_id=2";
				String query3="update  employee set salary = 400000 where emp_id=3";
				String query4="update  employee set salary = 400000 where emp_id=4";

				
				Connection con= DriverManager.getConnection(url,username,password);
				con.setAutoCommit(false);
				Statement st=con.createStatement();
				st.addBatch(query1);
				st.addBatch(query2);
				st.addBatch(query3);
				st.addBatch(query4);
				
				int[] res= st.executeBatch();
				for(int i:res)
				{
					if(i>0) {
						continue;
					}
					else
						con.rollback();
				}
				con.commit();
				con.close();
				
			}

				
			


}
