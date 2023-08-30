public class Client {
    public static void main(String args[]){
        AbstractGroup member1,member2,member3,group1,group2,group3;

        group1 = new Group("第一组");
        group2 = new Group("第二组");
        group3 = new Group("第三组");

        member1 = new Member("成员1");
        member2 = new Member("成员2");
        member3 = new Member("成员3");

        group2.add(member1);
        group2.add(member2);
        group3.add(member3);
        group1.add(group2);
        group1.add(group3);

        group1.share();
    }
}
