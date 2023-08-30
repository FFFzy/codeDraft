public class Client {
    public static void main(String args[]){
        RemoteControl rc = new RemoteControl();
        Command command1,command2,command3;
        command1 = (Command)XMLUtil.getBean("setLight");
        command2 = (Command)XMLUtil.getBean("lightOn");
        command3 = (Command)XMLUtil.getBean("lightOff");

        rc.setCommand(command1);
        rc.click();
        rc.setCommand(command2);
        rc.click();
        rc.setCommand(command3);
        rc.click();
    }
}
