public class Client {
    public static void main(String args[]){
        SceneFactory factory;
        Map map;
        Weather weather;
        Sound sound;
        factory = (SceneFactory)XMLUtil.getBean();
        map = factory.createMap();
        weather = factory.createWeather();
        sound = factory.createSound();
        map.display();
        weather.display();
        sound.display();
    }
}
