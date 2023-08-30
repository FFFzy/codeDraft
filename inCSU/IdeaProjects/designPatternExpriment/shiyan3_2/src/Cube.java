public class Cube extends Structure3D{
    private String name;

    public Cube(String name){
        this.name = name;
    }

    @Override
    public void add(Structure3D structure3D) {
        System.out.println("不支持该方法.");
    }

    @Override
    public void fill(Color color){
        color.getColor();
        System.out.println("填充"+name);
    }
}
