public class Photograph {
    private String name;
    private FilterStrategy filterStrategy;

    public Photograph(String name){
        this.name = name;
    }

    public void setFilterStrategy(FilterStrategy filterStrategy) {
        this.filterStrategy = filterStrategy;
    }

    public void displayPhoto(){
        filterStrategy.display();
        System.out.println(name);
    }
}
