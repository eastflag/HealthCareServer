package com.healthcare.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

public class DBConnectionManager {

    static private DBConnectionManager instance;
    static private DBConnectionPool pool;

    private DBConnectionManager() {
        init();
    }

    private void init() {
        loadDrivers();
        createPools();
    }

    public void destroy() {
    }

    static synchronized public DBConnectionManager getInstance()     {

        if (instance == null) {
            instance = new DBConnectionManager();
        }
        return instance;
    }

    public void freeConnection(Connection con) {
        pool.freeConnection(con);
    }

    public Connection getConnection() {
        return pool.getConnection();
    }

    public synchronized void release() {
        pool.release();
    }

    private void createPools() {
        
        	// REAL DB
//	   	 String url = "jdbc:mysql://192.169.10.10:3306/healthcare_gwangmyeong?useUnicode=true&characterEncoding=utf-8";
//	   	 String user = "healthcare";
//	   	 String password = "!healthcare";
   		
		// TEST DB 
//   	String url = "jdbc:mysql://192.169.10.10:3306/healthcare_test2?useUnicode=true&characterEncoding=utf-8";
//       String user = "healthtest_user2";
//       String password = "!healthtest2";
	   	 
			//Mtelo TEST DB  //Simli & Dinner ////////////////
	   	String url = "jdbc:mysql://106.245.237.196:3306/healthcare_test2?useUnicode=true&characterEncoding=utf-8";
	    String user = "healthtest_user2";
	    String password = "!healthtest2";

        //DB Conn Info Setting =======GoodSW 테스트DB==========================================================    	
   	  //	Real DB Server Setting
//       	   String url = "jdbc:mysql://210.96.71.161:3306/healthcare_gwangmyeong?useUnicode=true&characterEncoding=utf-8";
//           String user = "health_user";
//           String password = "!healthcare";
    	
			//String user = "scott";
            //String password = "tiger";
            pool =  new DBConnectionPool(url, user, password);
    }

    private void loadDrivers() {
        try {
			    //Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			    //Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
                Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
                System.out.println("Can't register JDBC driver: " + e.getMessage());
        }
    }

//inner class

    class DBConnectionPool {

        private final Vector freeConnections = new Vector();
        private final String url;
        private final String password;
        private final String user;

        public DBConnectionPool(String url, String user, String password){
            this.url = url;
            this.user = user;
            this.password = password;
        }

        public synchronized void freeConnection(Connection con) {
            if (con != null) {
            	freeConnections.addElement(con);
                notifyAll();
            }
        }

        public synchronized Connection getConnection() {
            Connection con=null;
   			if (freeConnections.size() > 0) {
                con = (Connection) freeConnections.firstElement();
            	freeConnections.removeElementAt(0);

    	        try {
                    if (con.isClosed()) {
                        con = getConnection();
                    }
        	    } catch (SQLException e) {
                    con = getConnection();
        	    }

            }else {
                con = newConnection();
            }
            return con;
        }//getconnection

        public synchronized void release() {
            Enumeration allConnections = freeConnections.elements();
            while (allConnections.hasMoreElements()) {
                Connection con = (Connection) allConnections.nextElement();
                try {
                    System.out.println("all connection close...");
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Can't close connection for pool ");
                }
            }
            freeConnections.removeAllElements();
        }

        private Connection newConnection() {

            Connection con = null;

            try {
                 con = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println("Can't create a new connection for " + url);
                return null;
            }
            return con;
        }
    }//inner class dbconnectionpool

}