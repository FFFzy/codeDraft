package org.csu.mypetstore.persistence.Impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountDAOImpl implements AccountDAO {

    private static final String getAccountByUsernameString = "SELECT" +
            "          SIGNON.USERNAME," +
            "          ACCOUNT.EMAIL," +
            "          ACCOUNT.FIRSTNAME," +
            "          ACCOUNT.LASTNAME," +
            "          ACCOUNT.STATUS," +
            "          ACCOUNT.ADDR1 AS address1," +
            "          ACCOUNT.ADDR2 AS address2," +
            "          ACCOUNT.CITY," +
            "          ACCOUNT.STATE," +
            "          ACCOUNT.ZIP," +
            "          ACCOUNT.COUNTRY," +
            "          ACCOUNT.PHONE," +
            "          PROFILE.LANGPREF AS languagePreference," +
            "          PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "          PROFILE.MYLISTOPT AS listOption," +
            "          PROFILE.BANNEROPT AS bannerOption," +
            "          BANNERDATA.BANNERNAME" +
            "    FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA" +
            "    WHERE ACCOUNT.USERID = ?" +
            "      AND SIGNON.USERNAME = ACCOUNT.USERID" +
            "      AND PROFILE.USERID = ACCOUNT.USERID" +
            "      AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String getAccountByUsernameAndPasswordString = "SELECT \n" +
            "SIGNON.USERNAME, ACCOUNT.EMAIL, ACCOUNT.FIRSTNAME, ACCOUNT.LASTNAME, \n" +
            "ACCOUNT.STATUS, ACCOUNT.ADDR1 AS address1, ACCOUNT.ADDR2 AS address2, ACCOUNT.CITY,  ACCOUNT.STATE, ACCOUNT.ZIP, ACCOUNT.COUNTRY, ACCOUNT.PHONE, \n" +
            "PROFILE.LANGPREF AS languagePreference, PROFILE.FAVCATEGORY AS favouriteCategoryId, PROFILE.MYLISTOPT AS listOption, PROFILE.BANNEROPT AS bannerOption, \n" +
            "BANNERDATA.BANNERNAME \n" +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA \n" +
            "WHERE ACCOUNT.USERID = ?\n" +
            "AND SIGNON.PASSWORD = ?\n" +
            "AND SIGNON.USERNAME = ACCOUNT.USERID \n" +
            "AND PROFILE.USERID = ACCOUNT.USERID \n" +
            "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String insertAccountString = "    INSERT INTO ACCOUNT" +
            "      (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID)" +
            "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
    private static final String insertProfileString = "   INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID) VALUES (?, ?, ?)";
    private static final String insertSignonString = "INSERT INTO SIGNON (PASSWORD,USERNAME) VALUES (?, ?)";
    private static final String updateAccountString = "UPDATE ACCOUNT SET" +
            "      EMAIL = ?," +
            "      FIRSTNAME = ?," +
            "      LASTNAME = ?," +
            "      STATUS = ?," +
            "      ADDR1 = ?," +
            "      ADDR2 = ?," +
            "      CITY = ?," +
            "      STATE = ?," +
            "      ZIP = ?," +
            "      COUNTRY = ?," +
            "      PHONE = ?" +
            "    WHERE USERID = ?";
    private static final String updateProfileString = "UPDATE PROFILE SET LANGPREF = ?, FAVCATEGORY = ? WHERE USERID = ?";
    private static final String updateSignonString = "UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";

    @Override
    public Account getAccountByUsername(String username) {
        Account account = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsernameString);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                account = new Account();
                account.setUsername(resultSet.getString(1));
                account.setEmail(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setState(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                account.setLanguagePreference(resultSet.getString(13));
                account.setFavouriteCategoryId(resultSet.getString(14));
                account.setListOption(resultSet.getBoolean(15));
                account.setBannerOption(resultSet.getBoolean(16));
                account.setBannerName(resultSet.getString(17));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatent(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account account1 = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsernameAndPasswordString);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                account1 = new Account();
                account1.setUsername(resultSet.getString(1));
                account1.setEmail(resultSet.getString(2));
                account1.setFirstName(resultSet.getString(3));
                account1.setLastName(resultSet.getString(4));
                account1.setStatus(resultSet.getString(5));
                account1.setAddress1(resultSet.getString(6));
                account1.setAddress2(resultSet.getString(7));
                account1.setCity(resultSet.getString(8));
                account1.setState(resultSet.getString(9));
                account1.setZip(resultSet.getString(10));
                account1.setCountry(resultSet.getString(11));
                account1.setPhone(resultSet.getString(12));
                account1.setLanguagePreference(resultSet.getString(13));
                account1.setFavouriteCategoryId(resultSet.getString(14));
                account1.setListOption(resultSet.getBoolean(15));
                account1.setBannerOption(resultSet.getBoolean(16));
                account1.setBannerName(resultSet.getString(17));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatent(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account1;
    }

    @Override
    public void insertAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertAccountString);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertProfileString);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSignonString);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateAccountString);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatent(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateProfileString);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatent(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateSignon(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSignonString);
            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatent(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
