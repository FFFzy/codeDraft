public abstract class Image {
    protected Filter filter;

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public abstract void parseFile(String fileName);
}
