public class Client {
    public static void main(String args[]){
        OADataBase oaDataBase;
        oaDataBase = (OADataBase)XMLUtil.getBean();
        String user = "ABC";
        String pwd = "123456";
        user = oaDataBase.encryptionUser(user);
        pwd = oaDataBase.encryptionPwd(pwd);
    }
}
