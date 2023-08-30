public class Scene {
    private Rendering rendering;

    public void setRendering(Rendering rendering) {
        this.rendering = rendering;
    }

    public void render(){
        rendering.render();
    }
}
