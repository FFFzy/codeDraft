import java.util.ArrayList;

public class Block3D extends Structure3D{
    private ArrayList<Structure3D> blockList = new ArrayList<Structure3D>();
    private String name;

    public Block3D(String name){
        this.name = name;
    }

    @Override
    public void add(Structure3D structure3D) {
        blockList.add(structure3D);
    }

    @Override
    public void fill(Color color) {
        color.getColor();
        System.out.println("填充"+name);

        for(Object obj:blockList){
            ((Structure3D)obj).fill(color);
        }
    }
}
