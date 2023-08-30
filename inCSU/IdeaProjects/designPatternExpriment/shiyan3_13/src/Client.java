public class Client {
    public static void main(String args[]){
        Scene scene = new Scene();
        Rendering rendering;
        rendering = (Rendering)XMLUtil.getBean();
        scene.setRendering(rendering);
        scene.render();
    }
}
