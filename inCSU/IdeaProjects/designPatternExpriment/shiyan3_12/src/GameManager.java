public class GameManager {
    private Role role;
    private Scene scene;
    private SoundEffect soundEffect;
    private static GameManager instance = null;

    private GameManager(){
        role = new Role();
        scene = new Scene();
        soundEffect = new SoundEffect();
    }

    public void gameSetting(){
        role.display();
        scene.display();
        soundEffect.display();
    }

    public static GameManager getInstance(){
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
}
