public class Adapter extends Encryption implements OADataBase {
//    private Encryption encryption;
//
//    public Adapter(){
//        encryption = new Encryption();
//    }

    public String encrypt(String data){
        super.encrypt(data);
        return data;
    }

    @Override
    public String encryptionUser(String user) {
        return encrypt(user);
    }

    @Override
    public String encryptionPwd(String pwd) {
        return encrypt(pwd);
    }
}
