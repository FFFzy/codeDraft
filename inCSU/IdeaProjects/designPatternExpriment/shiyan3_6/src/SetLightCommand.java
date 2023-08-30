public class SetLightCommand extends Command{
    private Light light;

    public SetLightCommand(){
        light = new Light();
    }

    @Override
    public void execute() {
        light.setLight();
    }
}
