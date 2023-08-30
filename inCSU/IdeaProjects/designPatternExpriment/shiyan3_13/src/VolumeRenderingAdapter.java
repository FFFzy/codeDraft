public class VolumeRenderingAdapter implements Rendering{
    private VolumeRendering volumeRendering;

    public VolumeRenderingAdapter(){
        volumeRendering = new VolumeRendering();
    }

    @Override
    public void render() {
        volumeRendering.render();
    }
}
