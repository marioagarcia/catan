package server.RDBMSPlugin.DAO;

import server.persistence.UserDTO;

import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by christopherbelyeu on 12/8/14.
 */
public class GenericDAO {

    Connection connection;
    String tableName;
    String primaryKeyName;


    GenericDAO(Connection connection, String table_name, String primary_key_name) {
        this.connection = connection;
        this.tableName = table_name;
        this.primaryKeyName = primary_key_name;
    }


    public boolean save(String data, String primary_key_value) {
        try {
            ResultSet result = this.connection.createStatement().executeQuery(
                    "SELECT * FROM " + tableName + " WHERE " + primaryKeyName + "='" + primary_key_value + "';");

            if (!result.isClosed() && result.getRow() != 0)
                return false;

            Statement statement = this.connection.createStatement();
            statement.execute(
                    "INSERT INTO " + tableName + " ('" + primaryKeyName + "', 'value') VALUES( '" + primary_key_value + "', '" + data + "' )");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<String> getAll(int data_index) {
        return this.executeBatchQuery("SELECT * FROM " + tableName, data_index);
    }

    public String getByPrimaryKey(String primary_key_value, int data_index) {
        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "SELECT * FROM " + tableName + " WHERE " + this.primaryKeyName + "='" + primary_key_value + "'");

            if (result.isClosed())
                return null;

            return result.getString(data_index);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(String data, String primary_key_value) {
        try {
            ResultSet result = this.connection.createStatement().executeQuery(
                    "SELECT * FROM " + tableName + " WHERE " + primaryKeyName + "='" + primary_key_value + "';");

            if (result.isClosed() || result.getRow() == 0)
                return false;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return this.executeSQL("UPDATE " + this.tableName + " SET value='" + data + "' WHERE " + this.primaryKeyName + "='" + primary_key_value + "'");
    }

    public boolean delete(String primary_key_value) {
        try {
            Statement statement = this.connection.createStatement();
            statement.execute(
                    "DELETE FROM " + this.tableName + " WHERE " + this.primaryKeyName + "='" + primary_key_value + "'");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<String> executeBatchQuery(String sql, int data_index) {
        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ArrayList<String> objects = new ArrayList<String>();

            while (result.next())
                objects.add(result.getString(data_index));

            return objects;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean executeSQL(String sql) {
        try {
            Statement statement = this.connection.createStatement();
            statement.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
