public class DesertSceneFactory implements SceneFactory{
    @Override
    public Map createMap() {
        return new DesertMap();
    }

    @Override
    public Weather createWeather() {
        return new DesertWeather();
    }

    @Override
    public Sound createSound() {
        return new DesertSound();
    }
}
