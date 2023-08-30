public class RenderEngineAdapter implements Rendering{
    private RenderEngine renderEngine;

    public RenderEngineAdapter(){
        renderEngine = new RenderEngine();
    }

    @Override
    public void render() {
        renderEngine.render();
    }
}
