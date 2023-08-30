public class SurfaceRenderingAdapter implements Rendering{
    private SurfaceRendering surfaceRendering;

    public SurfaceRenderingAdapter(){
        surfaceRendering = new SurfaceRendering();
    }

    @Override
    public void render() {
        surfaceRendering.render();
    }
}
