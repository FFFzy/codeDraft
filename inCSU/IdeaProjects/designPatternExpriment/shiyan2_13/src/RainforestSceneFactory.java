public class RainforestSceneFactory implements SceneFactory{
    @Override
    public Map createMap() {
        return new RainforestMap();
    }

    @Override
    public Weather createWeather() {
        return new RainforestWeather();
    }

    @Override
    public Sound createSound() {
        return new RainforestSound();
    }
}
