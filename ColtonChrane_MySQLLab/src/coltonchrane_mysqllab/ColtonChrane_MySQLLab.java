package coltonchrane_mysqllab;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ColtonChrane_MySQLLab {

    static JFrame access = new JFrame("MySQL Access");
    static JLabel lblName = new JLabel("Name: ");
    static JLabel lblAge = new JLabel("Age: ");
    static JTextField name = new JTextField();
    static JTextField age = new JTextField();
    static JButton add = new JButton("Add");
    static JButton quit = new JButton("Quit");
    static DefaultTableModel data = new DefaultTableModel();
    static JTable table = new JTable(data);
    static Connection con = null;

    void populateTable() throws SQLException {
        String query = "select * from People";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData md = rs.getMetaData();
        //clear current table
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        while (rs.next()) {
            String data[] = new String[md.getColumnCount() + 1];
            for (int i = 1; i < md.getColumnCount(); i++) {
                data[i - 1] = rs.getString(i);
            }
            ((DefaultTableModel) table.getModel()).addRow(new Object[]{data[0], data[1]});
        }
    }

    public static void main(String[] args) {
        String mySQLURL = "jdbc:mysql://cstnt.tstc.edu:3306/chranecsp21";
        String userName = "chranecsp21";
        String password = "temp";

        //UI ELEMENTS      
        //db access
        access.setBounds(50, 50, 700, 400);
        access.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        access.setLayout(null);
        access.setVisible(false);

        lblName.setBounds(20, 10, lblName.getPreferredSize().width, lblName.getPreferredSize().height);
        access.add(lblName);

        lblAge.setBounds(120, 10, lblAge.getPreferredSize().width, lblAge.getPreferredSize().height);
        access.add(lblAge);

        name.setBounds(lblName.getWidth() + 100, 40, 100, 18);
        access.add(name);

        age.setBounds(lblAge.getWidth(), 40, 100, 18);
        access.add(age);

        Dimension d = add.getPreferredSize();
        add.setBounds(access.getWidth() - 650, 100, d.width, d.height);
        access.add(add);

        Dimension c = quit.getPreferredSize();
        quit.setBounds(access.getWidth() - 550, 100, c.width, c.height);
        access.add(quit);

        table.setBounds(300, 50, 300, 350);
        access.add(table);

        //login
        JFrame login = new JFrame("Log In");
        login.setBounds(50, 50, 350, 200);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setLayout(null);

        JLabel label1 = new JLabel("Username: ");
        label1.setBounds(10, 10, label1.getPreferredSize().width, label1.getPreferredSize().height);
        login.add(label1);

        JTextField un = new JTextField();
        un.setBounds(label1.getWidth() + 10, 10, 100, 18);
        login.add(un);

        JLabel label2 = new JLabel("Password: ");
        label2.setBounds(10, 75, label1.getPreferredSize().width, label1.getPreferredSize().height);
        login.add(label2);

        JPasswordField pw = new JPasswordField();
        pw.setBounds(label1.getWidth() + 10, 75, 100, 18);
        login.add(pw);

        JButton b1 = new JButton("Access");
        Dimension f = b1.getPreferredSize();
        b1.setBounds(login.getWidth() - 100, 100, f.width, f.height);
        login.add(b1);

        b1.addActionListener((ActionEvent e) -> {
            //insert code here

            if (!un.getText().equals(userName) || !pw.getText().equals(password)) {
                pw.setText("");
            } else {
                try {
                    con = DriverManager.getConnection(mySQLURL, userName, password);
                    if (!con.isClosed()) {
                        login.setVisible(false);
                        access.setVisible(true);

                        //populateTable();
                        String query = "select * from People";
                        PreparedStatement ps = con.prepareStatement(query);
                        ResultSet rs = ps.executeQuery();
                        ResultSetMetaData md = rs.getMetaData();
                        //clear current table
                        ((DefaultTableModel) table.getModel()).setRowCount(0);
                        while (rs.next()) {
                            String data[] = new String[md.getColumnCount() + 1];
                            for (int i = 1; i < md.getColumnCount()+1; i++) {
                                data[i - 1] = rs.getString(i);
                            }
                            ((DefaultTableModel) table.getModel()).addRow(new Object[]{data[0], data[1]});
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });

        add.addActionListener((ActionEvent e) -> {
            //insert code here
            try {
                String inName = name.getText();
                String inAge = age.getText();
                String Query = "INSERT INTO People(name, age) VALUES('" + inName + "', '" + inAge + "');";
                PreparedStatement ps = con.prepareStatement(Query);
                int updateCount = ps.executeUpdate(Query);
                //populateTable();
                String query = "select * from People";
                PreparedStatement ps1 = con.prepareStatement(query);
                ResultSet rs1 = ps1.executeQuery();
                ResultSetMetaData md1 = rs1.getMetaData();
                //clear current table
                ((DefaultTableModel) table.getModel()).setRowCount(0);
                while (rs1.next()) {
                    String data[] = new String[md1.getColumnCount() + 1];
                    for (int i = 1; i < md1.getColumnCount(); i++) {
                        data[i - 1] = rs1.getString(i);
                    }
                    ((DefaultTableModel) table.getModel()).addRow(new Object[]{data[0], data[1]});
                }

            } catch (Exception q) {
                q.printStackTrace();
            }
        });

        quit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        login.setVisible(true);

    }

}
