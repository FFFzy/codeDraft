public class InfoFacade2 extends AbstractInfoFacade{
    private FileReader fileReader;
    private StatisticAnalysis statisticAnalysis;
    private Report report;

    public InfoFacade2(){
        fileReader = new FileReader();
        statisticAnalysis = new StatisticAnalysis();
        report = new Report();
    }

    @Override
    public void dataProcessing() {
        fileReader.read();
        statisticAnalysis.statistic();
        report.display();
    }
}
