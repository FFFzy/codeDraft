public class Client {
    public static void main(String args[]){
        Resume resume_pre,resume_new1 = null,resume_new2 = null;
        resume_pre = new Resume();
        Picture picture = new Picture();
        resume_pre.setName("ABC");
        resume_pre.setAge(20);
        resume_pre.setPicture(picture);

        resume_new1 = resume_pre.clone();

        try {
            resume_new2 = resume_pre.deepClone();
        }
        catch (Exception e){
            System.err.println("克隆失败");
        }
        System.out.println("浅克隆结果：");
        System.out.println("简历是否一样？ " + (resume_pre == resume_new1));
        System.out.println("照片是否一样？ " + (resume_pre.getPicture() == resume_new1.getPicture()));
//        System.out.println("名字是否一样？ " + (resume_pre.getName() == resume_new1.getName()));

        System.out.println("深克隆结果：");
        System.out.println("简历是否一样？ " + (resume_pre == resume_new2));
        System.out.println("照片是否一样？ " + (resume_pre.getPicture() == resume_new2.getPicture()));
//        System.out.println("名字是否一样？ " + (resume_pre.getName() == resume_new2.getName()));
    }
}
