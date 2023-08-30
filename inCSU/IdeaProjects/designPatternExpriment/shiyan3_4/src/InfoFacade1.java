public class InfoFacade1 extends AbstractInfoFacade{
    private FileReader fileReader;
    private FormatChange formatChange;
    private StatisticAnalysis statisticAnalysis;
    private Report report;

    public InfoFacade1(){
        fileReader = new FileReader();
        formatChange = new FormatChange();
        statisticAnalysis = new StatisticAnalysis();
        report = new Report();
    }

    @Override
    public void dataProcessing() {
        fileReader.read();
        formatChange.toXML();
        statisticAnalysis.statistic();
        report.display();
    }
}
