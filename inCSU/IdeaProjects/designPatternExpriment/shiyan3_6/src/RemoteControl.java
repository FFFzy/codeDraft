public class RemoteControl {
    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void click(){
        command.execute();
    }
}
