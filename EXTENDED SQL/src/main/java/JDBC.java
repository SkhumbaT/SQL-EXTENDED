
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import static java.lang.System.out;

public class JDBC {

   static ConnectionManager dbc = new ConnectionManager("localhost","UMUZI","user","pass");

    //Suppression of compiler warnings generated as a result of unchecked type casts.
   @SuppressWarnings("unchecked")
    private static void disableAccessWarnings() {
        try {
            Class unsafeClass = Class.forName("sun.misc.Unsafe");
            Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Object unsafe = field.get(null);

            Method putObjectVolatile = unsafeClass.getDeclaredMethod("putObjectVolatile", Object.class, long.class, Object.class);
            Method staticFieldOffset = unsafeClass.getDeclaredMethod("staticFieldOffset", Field.class);

            Class loggerClass = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field loggerField = loggerClass.getDeclaredField("logger");
            Long offset = (Long) staticFieldOffset.invoke(unsafe, loggerField);
            putObjectVolatile.invoke(unsafe, loggerClass, offset, null);
        } catch (Exception ignored) {
      }
    }

    public static void main(String[] args) {
        disableAccessWarnings();
        selectAllCustomers();
        selectOnlyName();
        selectCustomerWhereIdIsOne();
        updateCustomer();
        deleteCustomer();
        uniqueStatusesFromOrder();
        maximumPayments();
        selectAllCustomerAndSortByCountry();
        productsBetween100and600();
    }

    /**
     * establish JDBC connection
     * execute SQL queries and get the results through ResultSet using Statement.(to Select all entries from customers)
     * get the number of columns of the current ResultSet object
     * using next() method of the ResultSet, move the pointer of the current (ResultSet) object to the next row
     * get the column name and print it with uppercase letters
     * then get and return the name of the specified column in the current ResultSet object.
     * */
    public static void selectAllCustomers() {
        try {
            Connection connection = dbc.getConnection();//
            Statement stmt = connection.createStatement();//
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM customers");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount(); //
            if (resultSet.next ( )) { //
                do {
                    for (int i = 1; i < columnCount; i++) {
                        String columnName = resultSetMetaData.getColumnName (i);  //
                        out.print (columnName.toUpperCase ( ) + ": ");
                        out.print (resultSet.getString (i) + ", " + "\n");
                    }
                    out.println ("---------------");
                } while (resultSet.next ( ));
            }
        }
        catch (SQLException e){
            out.println(e.getMessage());
        }
    }

    /**
     * establish JDBC connection
     * execute SQL queries and get the results through ResultSet using Statement. (to Select all firstnames from customers)
     * use next() method of the ResultSet, move the pointer of the current (ResultSet) object to the next row
     * then get and return the name of the specified column in the current ResultSet object.(firstname)
     * */
    public static void selectOnlyName() {
        try {
            Connection connection = dbc.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM customers");
            if (resultSet.next ( )) {
                do {
                    out.println (resultSet.getString ("first_name"));
                } while (resultSet.next ( ));
            }
            out.println("---------------");
        }
        catch (SQLException e){
            out.println(e.getMessage());
        }
    }

    /**
     * establish JDBC connection
     * execute SQL queries and get the results through ResultSet using Statement. (to Select all firstnames from customers where the ID is 1)
     * then get and return the name of the specified column in the current ResultSet object.
     * */
    public static void selectCustomerWhereIdIsOne() {
        try {
            Connection connection = dbc.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT first_name, last_name FROM customers WHERE customerid=1");
            if (resultSet.next ( )) {
                do {
                    out.println (resultSet.getString ("first_name"));
                    out.println ("---------------");
                } while (resultSet.next ( ));
            }
        }
        catch (SQLException e){
            out.println(e.getMessage());
        }
    }

    /**
     * establish JDBC connection
     * execute SQL queries and get the results through ResultSet using Statement. (to Update firstname and lastname from customers where ID is 1)
     * then get and return the name of the specified column in the current ResultSet object.(firstname)
     * */
    public static void updateCustomer() {
        try {
            Connection connection = dbc.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("UPDATE Customers SET first_name= 'Lerato', last_name= 'Mabitso' WHERE customerid=1");
            if (resultSet.next ( )) {
                do {
                    out.println (resultSet.getString ("first_name"));
                } while (resultSet.next ( ));
            }}
        catch (SQLException e){
            out.println(e.getMessage());
        }
        out.println("---------------");
    }

    /**
     * establish JDBC connection
     * execute SQL queries and get the results through ResultSet using Statement. (to Delete all customers from customers where ID is 2)
     * then get and return the name of the specified column in the current ResultSet object.(customer id)
     * */
    public static void deleteCustomer() {
        try {
            Connection connection = dbc.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("DELETE FROM customers WHERE customerid=2");
            if (resultSet.next ( )) {
                do {
                    out.println (resultSet.getString ("customerid"));
                } while (resultSet.next ( ));
            }
        }
        catch (SQLException e){
            out.println(e.getMessage());
        }
        out.println("---------------");
    }

    /**
     * establish JDBC connection
     * execute SQL queries and get the results through ResultSet using Statement. (to show all unique statuses from orders)
     * then get and return the name of the specified column in the current ResultSet object.(status)
     * */
    public static void uniqueStatusesFromOrder() {
        try {
            Connection connection = dbc.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT DISTINCT ON (status) status FROM orders");
            if (resultSet.next ( )) {
                do {
                    out.println (resultSet.getString ("status"));
                } while (resultSet.next ( ));
            }
            out.println("---------------");
        }
        catch (SQLException e){
            out.println(e.getMessage());
        }
    }

    /**
     * establish JDBC connection
     * execute SQL queries and get the results through ResultSet using Statement. (to select the maximum amount from payments)
     * then get and return the name of the specified column in the current ResultSet object.(max)
     * */
    public static void maximumPayments() {
        try {
            Connection connection = dbc.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT MAX(amount) FROM payments");
            if (resultSet.next ( )) {
                do {
                    out.println (resultSet.getString ("max"));
                    out.println ("---------------");
                } while (resultSet.next ( ));
            }
        }
        catch (SQLException e){
            out.println(e.getMessage());
        }
    }

    /**
     * establish JDBC connection
     * execute SQL queries and get the results through ResultSet using Statement. (to show all customers from customers and sort by country)
     * get the number of columns of the current ResultSet object
     * use next() method of the ResultSet, move the pointer of the current (ResultSet) object to the next row
     * get the column name and print it with uppercase letters
     * then get and return the name of the specified column in the current ResultSet object.
     * */
    public static void selectAllCustomerAndSortByCountry() {
        try {
            Connection connection = dbc.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM customers ORDER BY country");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            if (resultSet.next ( )) {
                do {
                    for (int i = 1; i < columnCount; i++) {
                        String columnName = resultSetMetaData.getColumnName (i);
                        out.print (columnName.toUpperCase ( ) + ": ");
                        out.print (resultSet.getString (i) + ", " + "\n");
                    }
                    out.println ("---------------");
                } while (resultSet.next ( ));
            }
        }
        catch (SQLException e){
            out.println(e.getMessage());
        }
    }

    /**
     * establish JDBC connection
     * execute SQL queries and get the results through ResultSet using Statement. (to select all products with a buyprice between 100 & 600)
     * get the number of columns of the current ResultSet object
     * use next() method of the ResultSet, move the pointer of the current (ResultSet) object to the next row
     * get the column name and print it with uppercase letters
     * then get and return the name of the specified column in the current ResultSet object.
     * */
    public static void productsBetween100and600() {
        try {
            Connection connection = dbc.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM products WHERE buyprice>100 AND buyprice<600");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            if (resultSet.next ( )) {
                do {
                    for (int i = 1; i < columnCount; i++) {
                        String columnName = resultSetMetaData.getColumnName (i);
                        out.print (columnName.toUpperCase ( ) + ": ");
                        out.print (resultSet.getString (i) + ", ");
                    }
                    out.println ("---------------");
                } while (resultSet.next ( ));
            }
        }
        catch (SQLException e){
            out.println(e.getMessage());
        }
    }
}