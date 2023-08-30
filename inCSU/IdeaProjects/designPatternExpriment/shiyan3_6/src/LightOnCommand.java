public class LightOnCommand extends Command{
    private Light light;

    public LightOnCommand(){
        light = new Light();
    }

    @Override
    public void execute() {
        light.lightOn();
    }
}
