public class LightOffCommand extends Command{
    private Light light;

    public LightOffCommand(){
        light = new Light();
    }

    @Override
    public void execute() {
        light.lightOff();
    }
}
