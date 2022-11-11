import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import com.mysql.jdbc.PreparedStatement;
import java.util.GregorianCalendar;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Service {
    public static void main(String[] args) {
        int choice;
        while(true)
        {
            System.out.println("Select the option");
            System.out.println("1.Add Customer");
            System.out.println("2.search Customer");
            System.out.println("3.Delete customer");
            System.out.println("4.Update customer");
            System.out.println("5.View all customer");
            System.out.println("6.Generate bill ");
            System.out.println("7.View all bills ");
            System.out.println("8 Top two high bill");
            System.out.println("9.Exit ");
            System.out.println("*****************");
            System.out.println("ENTER YOUR CHOICE:--");
            Scanner sc=new Scanner(System.in);
            choice=sc.nextInt();
            switch(choice)
            {
                case 1:
                    System.out.println("Add customer details...");
                    System.out.println("Enter the customer code");
                    int code = sc.nextInt();
                    System.out.println("Enter the  name:--");
                    String name = sc.next();
                    System.out.println("Enter the Address:--");
                    String address = sc.next();
                    System.out.println("Enter the phone:--");
                    int phone = sc.nextInt();
                    System.out.println("Enter the email:--");
                    String email = sc.next();
                    try{
                        Class.forName("com.mysql.jdbc.Driver");

                        Connection con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/admindb","root","");
                        String sql="INSERT INTO `consumer`(`code`, `name`, `address`, `phno`, `email`) VALUES (?,?,?,?,?)";
                        PreparedStatement stmt= (PreparedStatement) con.prepareStatement((sql));
                        stmt.setInt(1,code);
                        stmt.setString(2,name);
                        stmt.setString(3,address);
                        stmt.setInt(4,phone);
                        stmt.setString(5,email);
                        stmt.executeUpdate();
                        System.out.println("value inserted successfully.........!");
                    }
                    catch (Exception e){
                        System.out.println((e));
                    }
                    break;

                    case 2:
                    System.out.println("Search customer");
                        System.out.println("enter phone number for search :-");
                        phone = sc.nextInt();
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/admindb", "root", "");
                            String sql = "SELECT  `name`, `address`, `phno`, `email` FROM `consumer` WHERE `phno`='" + phone + '"';
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            while (rs.next()) {
                                name = rs.getString("name");
                                address = rs.getString("address");
                                phone = rs.getInt("phno");
                                email = rs.getString("email");
                                System.out.println("name = " + name);
                                System.out.println("address = " + address);
                                System.out.println("phone number = " + phone);
                                System.out.println("Email id = " + email + '\n');
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;






                    case 3:
                    System.out.println("Delete customer");
                    break;
                case 4:
                    System.out.println("Update customer");
                    System.out.println("Add customer details...");
                    System.out.println("Enter the customer code");
                    code=sc.nextInt();
                    System.out.println("Enter the  name to be updated:--");
                    name=sc.next();
                    System.out.println("Enter the Address to be updated:--");
                    address=sc.next();
                    System.out.println("Enter the phone to be updated:--");
                    phone=sc.nextInt();
                    System.out.println("Enter the email to be updated:--");
                    email=sc.next();
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/admindb","root","");
                        String sql="UPDATE `consumer` SET `name`='"+name+"',`address`='"+address+"',`phno`='"+String.valueOf(phone)+"',`email`='"+email+"' WHERE `code`="+String.valueOf(code);
                        Statement stmt=con.createStatement();
                        stmt.executeUpdate(sql);
                        System.out.println("Data updated  successfully.....");
                    }
                    catch (Exception e){
                        System.out.println((e));
                    }
                    break;

                case 5:
                    System.out.println("View all customer");

                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/admindb","root","");
                        String sql = "SELECT `code`, `name`, `address`, `phno`, `email` FROM `consumer`";
                        Statement stmt=con.createStatement();
                        ResultSet rs=stmt.executeQuery(sql);
                        while(rs.next()){
                             code = rs.getInt("code");
                             name = rs.getString("name");
                             address = rs.getString("address");
                             phone = rs.getInt("phno");
                             email = rs.getString("email");
                            System.out.println("code = "+code);
                            System.out.println("name = "+name);
                            System.out.println("address = "+address);
                            System.out.println("phone number = "+phone);
                            System.out.println("Email id = "+email+'\n');
                        }
                    }
                    catch (Exception e){
                        System.out.println((e));
                    }


                    break;


                case 6:
                    System.out.println("Generate Bill");
//getting current month and year
                    GregorianCalendar date = new GregorianCalendar();
                    int currentMonth = date.get(Calendar.MONTH);
                    int currentYear = date.get(Calendar.YEAR);
                    currentMonth = currentMonth+1;
                    try {
                        //
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/admindb", "root", "");
                        String sql = "DELETE FROM `bill` WHERE `month`= '" + currentMonth + "'AND `year`= '" + currentYear + "'";
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(sql);
                        System.out.println("Previous data deleted succesfully");
                        String sql1 = "SELECT `id` FROM `consumer` ";
                        Statement stmt1 = con.createStatement();
                        ResultSet rs = stmt1.executeQuery(sql1);
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String sql2 = "select SUM(`unit`) from usages where month(datetime) = '"+currentMonth+"' AND year(datetime) = '"+currentYear+"' AND `userid` ='"+id+"'";
                            Statement stmt2 = con.createStatement();
                            ResultSet rs1 = stmt2.executeQuery(sql2);
                            while (rs1.next()) {
                                int add = rs1.getInt("SUM(`Unit`)");
                                int status = 0;
                                int totalBill = add * 5;
                                //generating random number for invoice
                                int min = 10000;
                                int max = 99999;
                                int invoice = (int)(Math.random() * (max - min + 1) + min);
                                // String sql3 = "INSERT INTO `bill`(`User_Id`, `month`, `year`, `bill`, `paid status`, `bill date`, `total_unit`) VALUES (%s,%s,%s,%s,%s,now(),%s)";
                                String sql3 = "INSERT INTO `bill`(`userid`, `month`, `year`, `bill`, `paid status`, `billdate`, `totalunit`) VALUES (?,?,?,?,?,now(),?)";
                                PreparedStatement stmt3 = (PreparedStatement) con.prepareStatement(sql3);
                                stmt3.setInt(1, id);
                                stmt3.setInt(2, currentMonth);
                                stmt3.setInt(3, currentYear);
                                stmt3.setInt(4, totalBill);
                                stmt3.setInt(5, 0);
                                stmt3.setInt(6, add);
                                stmt3.executeUpdate();
                            }
                        }

                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                case 7:
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/admindb", "root", "");
                        String sql = "SELECT  c.name,c.address,b.`month`, b.`year`, b.`bill`, b.`paid status`, b.`billdate`,  b.`totalunit` FROM `bill` b JOIN consumer c ON b.userid=c.id";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                            name = rs.getString("c.name");
                            address = rs.getString("c.address");
                            int month1 = rs.getInt("b.month");
                            int year1 = rs.getInt("b.year");
                            int bill = rs.getInt("b.bill");
                            String sta = rs.getString("b.paid status");
                            String date1 = rs.getString("b.billdate");
                            int total = rs.getInt("b.totalunit");
                            System.out.println("name ="+name);
                            System.out.println("address ="+address);
                            System.out.println("month ="+month1);
                            System.out.println("year = "+year1);
                            System.out.println("total bill = "+bill);
                            System.out.println("status="+sta);
                            System.out.println("bill date="+date1);
                            System.out.println("total unit ="+total);
                        }
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;




            }

            }
        }

    }

