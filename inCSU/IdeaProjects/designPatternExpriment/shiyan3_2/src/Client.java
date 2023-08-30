public class Client {
    public static void main(String args[]){
        Structure3D pyramid,cube,cylinder,block3d1,block3d2,block3d3;
        block3d1 = new Block3D("一号3D块");
        block3d2 = new Block3D("二号3D块");
        block3d3 = new Block3D("三号3D块");

        pyramid = new Pyramid("锥体1");
        cube = new Cube("立方体1");
        cylinder = new Cylinder("圆柱体1");

        block3d1.add(pyramid);
        block3d1.add(cylinder);
        block3d3.add(block3d2);

        Color red = new Red();
        Color gray = new Gray();

        cube.fill(red);
        block3d1.fill(gray);
        block3d3.fill(red);
    }
}
