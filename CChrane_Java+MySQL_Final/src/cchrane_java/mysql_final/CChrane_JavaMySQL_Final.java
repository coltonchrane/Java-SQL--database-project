
package cchrane_java.mysql_final;
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author colto
 */
public class CChrane_JavaMySQL_Final {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String mySQLURL = "jdbc:mysql://cstnt.tstc.edu:3306/chranecsp21";
        String username = "chranecsp21";
        String password = "temp";
        
        Connection con = null;
        
//        try{
//            con = DriverManager.getConnection(mySQLURL, username, password);
//            String query = "";
//            while(!query.equals("quit"))
//            {
//                System.out.print("Enter Query: ");
//                Scanner scan = new Scanner(System.in);
//                query = scan.nextLine();
//                PreparedStatement ps = con.prepareStatement(query);
//                
//                if(query.startsWith("select")){
//                    ResultSet rs = ps.executeQuery();
//                    ResultSetMetaData md = rs.getMetaData();
//                    
//                    while(rs.next()){
//                        for(int i =1;i<md.getColumnCount()+1;i++)
//                        {
//                            System.out.print(rs.getString(i) + "\t");
//                        }
//                        System.out.println();
//                    }
//                    
//                    
//                }else
//                {
//                    int updateCount = ps.executeUpdate(query);
//                    System.out.println(updateCount + " rows affected");
//                }
//            }
//        }catch(Exception e)
//        {
//            e.printStackTrace();
//        }
        addData add = new addData();
        add.setVisible(true);
    }
    
}
