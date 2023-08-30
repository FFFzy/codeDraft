import java.io.*;

public class Resume implements Cloneable,Serializable {
    private Picture picture;
    private String name;
    private int age;

    public void setPicture(Picture picture){
        this.picture = picture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Picture getPicture() {
        return (this.picture);
    }

    public String getName() {
        return (this.name);
    }

    public int getAge() {
        return (this.age);
    }

    public Resume clone(){
        Object obj = null;
        try{
            obj = super.clone();
            return (Resume)obj;
        }
        catch (CloneNotSupportedException e){
            System.out.println("不支持复制");
            return null;
        }
    }

    public Resume deepClone() throws IOException,ClassNotFoundException, OptionalDataException{
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Resume)ois.readObject();
    }
}
