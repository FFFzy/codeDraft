public class JPGImage extends Image{
    @Override
    public void parseFile(String fileName) {
        filter.addFilter();
        System.out.println("显示图像："+fileName+",图像格式：JPG");
    }
}
