import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.text.*;

public class SingleCPUSchedulingGUI03 extends JFrame {

    static private PrintWriter  stdErr = new  PrintWriter(System.err, true);

    static private int WIDTH = 800, HEIGHT = 750;            // the size of the Frame 主面板
    /* 各列表对应的面板规格*/
    /*	对应各名词释义  backupBAK 后备  ready 就绪  suspend 挂起  memory内存  */
    static private int BackupBAK_CELL_SIZE = 250, BackupBAK_LIST_ROWS = 10;	//后备队列
    static private int Suspend_CELL_SIZE = 250, Suspend_LIST_ROWS = 10;	    //挂起队列
    static private int Ready_CELL_SIZE = 200, Ready_LIST_ROWS = 6;	        //就绪队列
    static private int Memory_CELL_SIZE = 200, Memory_LIST_ROWS = 10;       //内存队列
    static private int CPU_ROWS = 12, CPU_COLS = 22;                         //CPU面板
    static private int STATUS_ROWS = 8, STATUS_COLS = 30;                   //系统状态面板
    private int timeslice = 1;                             //设置时间片大小
    private int systemStatus=0;        //设置系统状态 0——系统预备状态，等待开始，1——系统运行状态，2——系统暂停状态
    static private int TOTAL__TEXTFIELD_SIZE = 10;	    // Size total text field 记录各队列元素个数

    private JList backupList, suspendList, readyList, memoryList;  //各队列相对应的数组列表
    //	进程添加框中的"添加至后备队列"，"添加至就绪队列"，"重置"Button
    private JButton addToBAKButton, addToReadyButton, resetButton;
    //就绪队列框中的"挂起"，挂起队列框中的"解挂"，"删除"Button
    private JButton suspendButton, umountButton, removeButton;
    //Status面板中的"启动系统"，"重置系统"Button
    private JButton startButton, pauseButton, resetSyatemButton;
    //优先级和时间片单选钮及时间片显示框
    private JRadioButton priorityJRB, timesliceJRB;
    private JLabel timesliceSizeLabel;
    private JTextField timesliceJtf;
    //后备面板、进程添加面板、挂起面板、内存面板
    private JPanel backupBAKPanel, PCBItemPanel, suspendedPanel, memoryPanel;
    //后备队列、挂起队列元素总数标签
    private JLabel backupTotalLabel, suspendTotalLabel;
    //进程信息标签  进程编号PID,所需运行时间requiredTime,优先级priority,当前状态statues,内存中的基址base,所需内存大小limit
    private JLabel PIDLabel, requiredTimeLabel, priorityLabel, statuesLabel, baseLabel, limitLabel;
    //后备队列、挂起队列元素总数文本框（不可编辑）
    private JTextField backupTotalTextField, suspendTotalTextField;
    //进程信息文本框 PID(可编辑),requiredTime(可编辑),priority(可编辑),status(不可编辑),base(不可编辑),limit(可编辑)
    private JTextField PIDTextField, requiredTimeTextField, priorityTextField, statusTextField, baseTextField, limitTextField;
    //CPU状态显示文本域(不可编辑)，status信息文本域(用于现实程序每一步的操作和影响，不可编辑)
    private JTextArea CPUTextArea, statuesTextArea;
    //后备队列PCB数组,就绪、挂起，——内存（可分分区表）
    PCBRecords backupPCB, readyPCB, suspendedPCB;
    private MemoryRecords memoryItems;

    private boolean flag = false;
    //main函数
    public static void main(String[] args) throws IOException {
        new SingleCPUSchedulingGUI03().initFrame();
    }
    //初始化Frame
    public void initFrame() {

        backupList = new JList();
        backupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        backupList.setVisibleRowCount(BackupBAK_LIST_ROWS);
        backupList.setFixedCellWidth(BackupBAK_CELL_SIZE);
        suspendList = new JList();
        suspendList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        suspendList.setVisibleRowCount(Suspend_LIST_ROWS);
        suspendList.setFixedCellWidth(Suspend_CELL_SIZE);
        readyList = new JList();
        readyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        readyList.setVisibleRowCount(Ready_LIST_ROWS);
        readyList.setFixedCellWidth(Ready_CELL_SIZE);
        memoryList = new JList();
        memoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        memoryList.setVisibleRowCount(Memory_LIST_ROWS);
        memoryList.setFixedCellWidth(Memory_CELL_SIZE);

        suspendButton = new JButton("挂起");
        addToBAKButton = new JButton("加入后备队列");
        addToReadyButton = new JButton("加入就绪队列");
        resetButton = new JButton("重置");
        umountButton = new JButton("解挂");
        removeButton = new JButton("移除");
        startButton = new JButton("开始调度");
        pauseButton = new JButton("暂停");
        resetSyatemButton = new JButton("重置系统");
        priorityJRB = new JRadioButton("优先级", true);
        timesliceJRB = new JRadioButton("时间片");

        backupTotalLabel = new JLabel("总数:");
        backupTotalTextField = new JTextField("0", TOTAL__TEXTFIELD_SIZE);
        backupTotalTextField.setEditable(false);
        suspendTotalLabel = new JLabel("总数:");
        suspendTotalTextField = new JTextField("0", TOTAL__TEXTFIELD_SIZE);
        suspendTotalTextField.setEditable(false);
        timesliceSizeLabel = new JLabel("时间片");
        timesliceJtf = new JTextField("3", 5);
        timesliceJtf.setEditable(true);

        CPUTextArea = new JTextArea(CPU_ROWS, CPU_COLS);
        CPUTextArea.setEditable(false);
        statuesTextArea = new JTextArea(STATUS_ROWS, STATUS_COLS);
        statuesTextArea.setEditable(false);

        /* north panel*/
        JPanel northPanel = new JPanel(new GridLayout(1, 3));
        // ProcessPCB item information Panel
        PCBItemPanel = new JPanel(new BorderLayout());
        PCBItemPanel.setBorder(
                BorderFactory.createTitledBorder("PCB面板"));

        JPanel PCBItemButtonJPanel = new JPanel(new GridLayout(3, 1));
        PCBItemButtonJPanel.add(addToBAKButton);
        PCBItemButtonJPanel.add(addToReadyButton);
        PCBItemButtonJPanel.add(resetButton);

        PCBItemPanel.add(this.initPCBItemPanel(), BorderLayout.CENTER);
        PCBItemPanel.add(PCBItemButtonJPanel, BorderLayout.SOUTH);

        //backupBAKList Panel
        backupBAKPanel = new JPanel(new BorderLayout());
        backupBAKPanel.setBorder(BorderFactory.createTitledBorder("后备队列"));

        JPanel backupTotalPAnel = new JPanel();
        backupTotalPAnel.add(backupTotalLabel);
        backupTotalPAnel.add(backupTotalTextField);
        backupBAKPanel.add (
                new JScrollPane(backupList,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        backupBAKPanel.add(backupTotalPAnel, BorderLayout.SOUTH);

        // SuspendList Panel
        suspendedPanel = new JPanel(new BorderLayout());
        suspendedPanel.setBorder(BorderFactory.createTitledBorder("挂起队列"));

        JPanel suspendedTotalPAnel = new JPanel();
        suspendedTotalPAnel.add(suspendTotalLabel);
        suspendedTotalPAnel.add(suspendTotalTextField);

        JPanel suspendComponentPanel = new JPanel(new GridLayout(1, 2));
        suspendComponentPanel.add(umountButton);
        suspendComponentPanel.add(removeButton);

        suspendedPanel.add (suspendedTotalPAnel, BorderLayout.NORTH);
        suspendedPanel.add (
                new JScrollPane(suspendList,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        suspendedPanel.add(suspendComponentPanel, BorderLayout.SOUTH);

        northPanel.add(backupBAKPanel);
        northPanel.add(PCBItemPanel);
        northPanel.add(suspendedPanel);

        /* center Panel*/
        JPanel centrelPanel = new JPanel(new GridLayout(1, 3));

        // readyList panel
        JPanel readyListPanel = new JPanel(new BorderLayout());
        readyListPanel.setBorder(BorderFactory.createTitledBorder("就绪队列"));
        readyListPanel.add (
                new JScrollPane(readyList,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        readyListPanel.add (suspendButton, BorderLayout.SOUTH);
        // CPU panel
        JPanel CPUPanel = new JPanel();
        CPUPanel.setBorder(BorderFactory.createTitledBorder("CPU"));
        CPUPanel.add (new JScrollPane(CPUTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        memoryPanel = new JPanel(new BorderLayout());
        memoryPanel.setBorder(BorderFactory.createTitledBorder("内存"));
        memoryPanel.add (
                new JScrollPane(memoryList,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        centrelPanel.add(readyListPanel);
        centrelPanel.add(CPUPanel);
        centrelPanel.add(memoryPanel);
        /*statues panel*/
        JPanel southPanel = new JPanel(new BorderLayout());

        JPanel statuesPanel = new JPanel();
        statuesPanel.setBorder(BorderFactory.createTitledBorder("状态"));
        statuesPanel.add (new JScrollPane(statuesTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        JPanel systemContralButtonPanel = new JPanel(new GridLayout(6, 1));
        systemContralButtonPanel.setBorder(BorderFactory.createTitledBorder("控制面板"));
        ButtonGroup group = new ButtonGroup();
        group.add(priorityJRB);
        group.add(timesliceJRB);
        JPanel porityPanel = new JPanel(new GridLayout(1, 2));
        porityPanel.add(timesliceSizeLabel);
        porityPanel.add(timesliceJtf);
        systemContralButtonPanel.add(priorityJRB);
        systemContralButtonPanel.add(timesliceJRB);
        systemContralButtonPanel.add(porityPanel);
        systemContralButtonPanel.add(startButton);
        systemContralButtonPanel.add(pauseButton);
        systemContralButtonPanel.add(resetSyatemButton);

        southPanel.add(statuesPanel, BorderLayout.CENTER);
        southPanel.add(systemContralButtonPanel, BorderLayout.EAST);

//		 arrange panels in window
        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);
        add(centrelPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);  //statuesPanel

//		 start listening for list and buttons events
        backupList.addListSelectionListener(new DisplayBackupProcessListener());
        suspendList.addListSelectionListener(new DisplaySuspendedProcessListener());
        readyList.addListSelectionListener(new DisplayreadyProcessListener());

        addToBAKButton.addActionListener(new AddToBAKListener());
        addToReadyButton.addActionListener(new AddToReadyListener());
        resetButton.addActionListener(new ResetListener());
        suspendButton.addActionListener(new SuspendListener());
        umountButton.addActionListener(new UmountListener());
        removeButton.addActionListener(new RemoveListener());
        startButton.addActionListener(new StartSystemListener());
        pauseButton.addActionListener(new SystemPauseListener());
        resetSyatemButton.addActionListener(new ResetSystemListener());
        priorityJRB.addActionListener(new priotiryListener());
        timesliceJRB.addActionListener(new timeslicListener());

        backupPCB = new PCBRecords();
        readyPCB = new PCBRecords();
        suspendedPCB = new PCBRecords();
        memoryItems = new MemoryRecords();
        MemoryItem initMemoryItem = new MemoryItem(0,3000);
        memoryItems.addItem(initMemoryItem);
        backupList.setListData(backupPCB.getItemsProperties());
        readyList.setListData(readyPCB.getItemsProperties());
        suspendList.setListData(suspendedPCB.getItemsProperties());
        memoryList.setListData(memoryItems.getItemsProperties());

        this.setTitle("CPUScheduling");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocation(200, 10);
        this.addWindowListener(new MyWindowMonitor());
    }



    public JPanel initPCBItemPanel() {

        JPanel iniPCBItemJPanel = new JPanel(new BorderLayout());
        JPanel iniNamePanel = new JPanel(new GridLayout(6, 1));
        JPanel iniValuePanel = new JPanel(new GridLayout(6, 1));

        PIDLabel = new JLabel("PID:");
        requiredTimeLabel = new JLabel("耗时:");
        priorityLabel = new JLabel("优先级:");
        statuesLabel = new JLabel("状态:");
        baseLabel = new JLabel("起始位置:");
        limitLabel = new JLabel("所需内存:");
        iniNamePanel.add(PIDLabel);
        iniNamePanel.add(requiredTimeLabel);
        iniNamePanel.add(priorityLabel);
        iniNamePanel.add(statuesLabel);
        iniNamePanel.add(baseLabel);
        iniNamePanel.add(limitLabel);

        PIDTextField = new JTextField("", 10);
        PIDTextField.setEditable(true);
        requiredTimeTextField = new JTextField("", 10);
        requiredTimeTextField.setEditable(true);
        priorityTextField = new JTextField("", 10);
        priorityTextField.setEditable(true);
        statusTextField = new JTextField("", 10);
        statusTextField.setEditable(false);
        baseTextField = new JTextField("", 10);
        baseTextField.setEditable(false);
        limitTextField = new JTextField("", 10);
        limitTextField.setEditable(true);
        iniValuePanel.add(PIDTextField);
        iniValuePanel.add(requiredTimeTextField);
        iniValuePanel.add(priorityTextField);
        iniValuePanel.add(statusTextField);
        iniValuePanel.add(baseTextField);
        iniValuePanel.add(limitTextField);

        iniPCBItemJPanel.add(iniNamePanel, BorderLayout.WEST);
        iniPCBItemJPanel.add(iniValuePanel, BorderLayout.CENTER);

        return iniPCBItemJPanel;
    }

    class MyWindowMonitor extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
            setVisible(false);
        }
    }

    public void displayInformation(ProcessPCB pcb) {

        PIDTextField.setText(pcb.getPID());
        requiredTimeTextField.setText(Integer.toString(pcb.getRequiredTime()));
        priorityTextField.setText(Integer.toString(pcb.getPriority()));
        statusTextField.setText(pcb.getStatus());
        baseTextField.setText(Integer.toString(pcb.getMemoryBase()));
        limitTextField.setText(Integer.toString(pcb.getMemoryLimit()));
    }
    //Displays the information of the selected PCBItem
    class DisplayBackupProcessListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent event) {
            if (! backupList.getValueIsAdjusting()) {
                String id = null, selectedBAKItem = null;
                selectedBAKItem = (String) backupList.getSelectedValue();
                if(selectedBAKItem  != null) {
                    StringTokenizer stringtokenizer = new StringTokenizer(selectedBAKItem, "_");
                    id = stringtokenizer.nextToken();
                    ProcessPCB backupItem = backupPCB.getItem(id);
                    if(backupItem != null)
                    {
                        displayInformation(backupItem);
                    }
                    statuesTextArea.append(backupItem.toString() + "ProcessItem " + id
                            + "in backupList has been displayed!\n");
                }
            }
        }
    }

    class DisplaySuspendedProcessListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent event) {
            if (! suspendList.getValueIsAdjusting()) {
//				stdErr.flush();
                String id = "";
                String str = (String) suspendList.getSelectedValue();
                if(str != null) {
                    StringTokenizer strtokenizer = new StringTokenizer(str, "_");
                    id = strtokenizer.nextToken();
                    ProcessPCB selectedSuspendedItem = suspendedPCB.getItem(id);
                    if(selectedSuspendedItem != null)
                    {
                        displayInformation(selectedSuspendedItem);
                    }
                    statuesTextArea.append(selectedSuspendedItem.toString() + "ProcessItem " + id
                            + "in suspendList has been displayed!\n");
                }
            }
        }
    }

    class DisplayreadyProcessListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent event) {
            if (! readyList.getValueIsAdjusting()) {
                String id = null, s = null;
                s = (String) readyList.getSelectedValue();
                if(s != null) {
                    StringTokenizer sttokenizer = new StringTokenizer(s, "_");
                    id = sttokenizer.nextToken();
                    ProcessPCB readyItem = readyPCB.getItem(id);
                    if(readyItem != null)
                    {
                        displayInformation(readyItem);
                    }
                    statuesTextArea.append(readyItem.toString() + "ProcessItem " + id
                            + "in readyList has been displayed!\n");
                }
            }
        }
    }
    //内存可分分区表排序，按可用内存大小降序排列
    public void sortMemoryList() {
        MemoryRecords currentListItems = new MemoryRecords();
        int num = memoryItems.getNumberOfItems();
        if(num>0)  {
            for(int i=num; i>=1; i--) {
                Iterator memoryIterator = memoryItems.iterator();
                MemoryItem currentItem = (MemoryItem) memoryIterator.next();
                for( ; memoryIterator.hasNext(); ) {
                    MemoryItem nowItem = (MemoryItem) memoryIterator.next();
                    if(currentItem.getMemoryLimit() < nowItem.getMemoryLimit()) {
                        currentItem = null;
                        currentItem = nowItem;
                    }
                }
                currentListItems.addItem(currentItem);
                memoryItems.removeItem(currentItem);
            }
            memoryItems = null;
            memoryItems = currentListItems;
        }
        memoryList.setListData(memoryItems.getItemsProperties());
    }
    //判断能否为进程分配内存
    public boolean boolMemoryAllocation(int neededLimit) {
        if(memoryItems.getNumberOfItems()>0) {
            Iterator memoryListIteartor = memoryItems.iterator();
            MemoryItem lagerestItem = (MemoryItem) memoryListIteartor.next();
            if(lagerestItem.getMemoryLimit()>= neededLimit)
                return true;
            else {
                return false;
            }
        } else {
            return false;
        }
    }
    //内存分配，按最先适应算法原则
    public int MemoryAllocation(int neededLimit) {
        int currentBase=-1, currentLimit=-1;
        Iterator memoryListIteartor = memoryItems.iterator();
        MemoryItem lagerestItem = (MemoryItem) memoryListIteartor.next();
        currentBase = lagerestItem.getMemoryBase()+neededLimit;
        currentLimit = lagerestItem.getMemoryLimit()-neededLimit;
        memoryItems.removeItem(lagerestItem);
        if(currentLimit>=0) {
            memoryItems.addItem(new MemoryItem(currentBase, currentLimit));
        }
        sortMemoryList();
        return lagerestItem.getMemoryBase();
    }
    //内存回收
    public void memoryRecall(int nowBase, int nowLimit) {
        MemoryItem recallItem = null;
        int recalledBase = nowBase;
        int recalledLimit = nowLimit;
        if(memoryItems.getNumberOfItems()>0) {
            Iterator memoryListIteartor = memoryItems.iterator();
            for( ;memoryListIteartor.hasNext(); ) {
                MemoryItem existedItem = (MemoryItem) memoryListIteartor.next();
                if(existedItem.getMemoryBase() == (nowBase+nowLimit)) {
                    recalledLimit = recalledLimit+existedItem.getMemoryLimit();
                    memoryItems.removeItem(existedItem);
                    memoryListIteartor = memoryItems.iterator();
                } else if(nowBase == (existedItem.getMemoryBase()+existedItem.getMemoryLimit())) {
                    recalledBase = existedItem.getMemoryBase();
                    recalledLimit = recalledLimit+existedItem.getMemoryLimit();
                    memoryItems.removeItem(existedItem);
                    memoryListIteartor = memoryItems.iterator();
                }
                //memoryListIteartor = memoryItems.iterator();
            }
            //recallItem = new MemoryItem(recalledBase, recalledLimit); //曾产生异常
        }
        recallItem = new MemoryItem(recalledBase, recalledLimit);
        memoryItems.addItem(recallItem);
        sortMemoryList();
    }

    public int readInteger(String s) {
        int num = -1;
        try  {
            num = Integer.parseInt(s);
        }  catch(NumberFormatException numberformatexception) {
            statuesTextArea.append("Please input a positive integer!\n");
            num = -999;
        }
        return num;
    }

    public void addToBackupList(String newID, int s1, int s2, int s3, String s) {
        ProcessPCB item = backupPCB.getItem(newID);
        if(item != null) {
            statuesTextArea.append("The PCB " + newID + " has existed in Backup List!\n"
                    + "you need to modify the PID of the selected PCB!\n");
            while(item != null) {
                newID = s + newID;
                item = backupPCB.getItem(newID);
            }
        }
        ProcessPCB newPCB = new ProcessPCB(newID, s1, s2, "等待", -1, s3);
        backupPCB.addItem(newPCB);
        backupList.setListData(backupPCB.getItemsProperties());
        backupTotalTextField.setText(Integer.toString(backupPCB.getNumberOfItems()));
    }

    public void addToReadyList(String nowID, int s1, int s2, int s3, String s) {
        ProcessPCB item = readyPCB.getItem(nowID);
        if(item != null) {
            statuesTextArea.append("The PCB " + nowID + " has existed in Ready List!\n"
                    + "you need to modify the PID of the selected PCB!\n");
            while(item != null) {
                nowID = s + nowID;
                item = backupPCB.getItem(nowID);
            }
        }
        int s4=MemoryAllocation(s3);
        sortMemoryList();
        ProcessPCB newPCB = new ProcessPCB(nowID, s1, s2, "就绪", s4, s3);
        readyPCB.addItem(newPCB);
        sortReadyPCB();
    }

    class AddToBAKListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String newID = PIDTextField.getText();
            String newTime = requiredTimeTextField.getText();
            String newPriority = priorityTextField.getText();
            String newLimit = limitTextField.getText();
            int s1 = 0, s2 = 0, s3 = 0, tag1=-1, tag2=-1, tag3=-1;

            if(newTime != null)
            {
                s1 = readInteger(newTime);
                if(s1 > 0.0) tag1 = 1;
                else  statuesTextArea.append("The neededTime must be a positive integer.\n");
            }
            if(newPriority != null)
            {
                s2 = readInteger(newPriority);
                if(s1 != -999) tag2 = 1;
                else statuesTextArea.append("The priority must be an integer.\n");
            }
            if(newLimit != null)
            {
                s3 = readInteger(newLimit);
                if(s3 > 0.0) tag3 = 1;
                else statuesTextArea.append("The neededLimit must be a positive integer.\n");
            }
            if(tag1 ==1 && tag2 == 1 && tag3 == 1) {
                if(newID == null)  {
                    statuesTextArea.append("The value of ID mustn't be null!\n");
                } else {
                    addToBackupList(newID, s1, s2, s3, "B");
                    statuesTextArea.append("The PCB recorditem has been added to BackupList!\n");
                    reset();
                }
            }
        }
    }

    class AddToReadyListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            String nowID = PIDTextField.getText();
            String time = requiredTimeTextField.getText();
            String priority = priorityTextField.getText();
            String limit = limitTextField.getText();
            int s1 = 0, s2 = 0, s3 =0;
            int tag1 = -1, tag2 = -1, tag3 = -1;
            if(time != null)
            {
                s1 = readInteger(time);
                if(s1 > 0.0)  tag1=1;
                else statuesTextArea.append("The neededTime must be a positive integer.\n");
            }
            if(priority != null)
            {
                s2 = readInteger(priority);
                if(s2 != -999)  tag2=1;
                else statuesTextArea.append("The priority must be an integer.\n");
            }
            if(limit != null)
            {
                s3 = readInteger(limit);
                if(s3 > 0.0)  tag3=1;
                else statuesTextArea.append("The neededLimit must be a positive integer.\n");
            }
            if(tag1 ==1 && tag2 == 1 && tag3 ==1) {
                if(nowID == null)  {
                    statuesTextArea.append("The value of ID mustn't be null!\n");
                }  else  {
                    //          	  PCBItem item = null;
                    if(readyPCB.getNumberOfItems() < 6)  {
                        if(boolMemoryAllocation(s3)) {
                            addToReadyList(nowID, s1, s2, s3, "R");
                            statuesTextArea.append("The student recorditem has been added to ReadyList!\n");
                        } else {
                            statuesTextArea.append("No memory available! The PCB will be added to backupList!\n");
                            addToBackupList(nowID, s1, s2, s3, "b");
                            statuesTextArea.append("The student recorditem has been added to BackupList!\n");
                        }
                    } else {
                        statuesTextArea.append("The ReadyList was full! The new ProcessPCB will be added to BackupList!\n");
                        addToBackupList(nowID, s1, s2, s3, "b");
                        statuesTextArea.append("The student recorditem has been added to BackupList!\n");
                    }
                    reset();
                }
            }
        }
    }

    public void reset() {
        PIDTextField.setText("");
        requiredTimeTextField.setText("");
        priorityTextField.setText("");
        statusTextField.setText("");
        baseTextField.setText("");
        limitTextField.setText("");

    }

    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            reset();
        }
    }
    // This inner class processes suspendButton events.
    //注：在挂起时，不会触发进程调度，而是在点击"startSystemJButton"时才会出发进程调度
    class SuspendListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String selectedReadyItem = null;
            String pid = "";
            if(readyPCB.getNumberOfItems() == 0)
            {
                statuesTextArea.append("The readyList is empty!\n");
            } else {
                try {
                    selectedReadyItem = (String) readyList.getSelectedValue();
                    if(selectedReadyItem == null)  {
                        statuesTextArea.append("Please select an item from the ready list!\n");
                    } else
                    {
                        StringTokenizer stringtokenizer = new StringTokenizer(selectedReadyItem, "_");
                        pid = stringtokenizer.nextToken();
                        ProcessPCB selectedItem = readyPCB.getItem(pid);
                        if(selectedItem == null)  {
                            statuesTextArea.append("No student recorditem of Num " + pid + " is founded!\n");
                        }  else  {
                            ProcessPCB boolItem = suspendedPCB.getItem(pid);
                            if(boolItem != null) {
                                statuesTextArea.append("The PCB " + pid + " has existed in Suspend List!\n"
                                        + "you need to modify the PID of the selected PCB!\n");
                                while(boolItem != null) {
                                    pid = "S" + pid;
                                    boolItem = suspendedPCB.getItem(pid);
                                }
                            }
                            //注意下一步存在问题！
                            ProcessPCB newPcb = new ProcessPCB(pid, selectedItem.getRequiredTime(),
                                    selectedItem.getPriority(),"挂起", -1, selectedItem.getMemoryLimit());
                            memoryRecall(selectedItem.getMemoryBase(), selectedItem.getMemoryLimit());
                            suspendedPCB.addItem(newPcb);
                            readyPCB.removeItem(selectedItem);
//    	                	if(systemStatues == 1) {
//    	                		PCBSchudling();
//    	                	}
                            sortReadyPCB();
                            suspendList.setListData(suspendedPCB.getItemsProperties());
                            statuesTextArea.append("The product has been suspended!\n");
                            suspendTotalTextField.setText(Integer.toString(suspendedPCB.getNumberOfItems()));
                        }
                    }
                } catch (RuntimeException e1) {
                    // TODO Auto-generated catch block
                }
            }
        }
    }
    //对Ready队列进行优先级排序
    public void sortReadyPCB() {
        PCBRecords currentReadyPCB = new PCBRecords();
        int num = readyPCB.getNumberOfItems();
        if(num > 0) {
            for(int i=num; i>=1; i--) {
                Iterator readyIterator = readyPCB.iterator();
                ProcessPCB currentItem = (ProcessPCB) readyIterator.next();
                for( ; readyIterator.hasNext(); ) {
                    ProcessPCB nowItem = (ProcessPCB) readyIterator.next();
                    if(currentItem.getPriority() > nowItem.getPriority()) {
                        currentItem = null;
                        currentItem = nowItem;
                    }
                }
                currentReadyPCB.addItem(currentItem);
                readyPCB.removeItem(currentItem);
            }
            readyPCB = null;
            readyPCB = currentReadyPCB;
        }
        readyList.setListData(readyPCB.getItemsProperties());
    }
    // This inner class processes umountButton events.
    class UmountListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String selectedSuspendedItem = null;
            String id = "";
            if(suspendedPCB.getNumberOfItems() == 0)
            {
                statuesTextArea.append("The suspendList is empty!\n");
            } else {
                try {
                    selectedSuspendedItem = (String) suspendList.getSelectedValue();
                    if(selectedSuspendedItem == null)  {
                        statuesTextArea.append("Please select an item from the suspend list!\n");
                    } else
                    {
                        StringTokenizer stringtokenizer = new StringTokenizer(selectedSuspendedItem, "_");
                        id = stringtokenizer.nextToken();
                        ProcessPCB selectedItem = suspendedPCB.getItem(id);
                        if(selectedItem == null)  {
                            statuesTextArea.append("No student recorditem of Num " + id + " is founded!\n");
                        }  else  {
                            int s3 = selectedItem.getMemoryLimit();
                            if(readyPCB.getNumberOfItems() < 6) {
                                if(boolMemoryAllocation(s3)) {
                                    addToReadyList(id, selectedItem.getRequiredTime(),
                                            selectedItem.getRequiredTime(), s3, "r");
                                    statuesTextArea.append("The product has been Umounted to Ready List!\n");
                                } else {
                                    statuesTextArea.append("No memory available! The PCB will be Umounted to BackupList!\n");
                                    addToBackupList(id, selectedItem.getRequiredTime(),
                                            selectedItem.getRequiredTime(), s3, "II");
                                    statuesTextArea.append("The product has been Umounted to Backup List!\n");
                                }

                            } else {
                                statuesTextArea.append("The ReadyList was full! The new ProcessPCB will be Umounted to BackupList!\n");
                                addToBackupList(id, selectedItem.getRequiredTime(),
                                        selectedItem.getRequiredTime(), s3, "II");
                                statuesTextArea.append("The product has been Umounted to Backup List!\n");
                            }
                            suspendedPCB.removeItem(selectedItem);
                            suspendList.setListData(suspendedPCB.getItemsProperties());
                            suspendTotalTextField.setText(Integer.toString(suspendedPCB.getNumberOfItems()));
                        }
                    }
                } catch (RuntimeException e1) {
                    // TODO Auto-generated catch block
                }
            }
        }
    }
    /**
     * This inner class processes <code>removeButton</code> events.
     */

    public int getTimeslice() {
        return timeslice;
    }
    class RemoveListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            String selectedRemovsItem = null;
            String id = "";
            if(suspendedPCB.getNumberOfItems() == 0)
            {
                statuesTextArea.append("The record is empty!\n");
            } else
            {
                selectedRemovsItem = (String) suspendList.getSelectedValue();
                if(selectedRemovsItem == null)  {
                    statuesTextArea.append("The value of ID mustn't be null!\n");
                } else {
                    StringTokenizer stringtokenizer = new StringTokenizer(selectedRemovsItem, "_");
                    id = stringtokenizer.nextToken();
                    ProcessPCB suspendedItem = suspendedPCB.getItem(id);
                    if(suspendedItem == null)  {
                        statuesTextArea.append("No student recorditem of Num " + id + " is founded!\n");
                    }  else  {

                        suspendedPCB.removeItem(suspendedItem);
                        suspendList.setListData(suspendedPCB.getItemsProperties());
                        statuesTextArea.append("The product has been removed!\n");
                    }
                    suspendTotalTextField.setText(Integer.toString(suspendedPCB.getNumberOfItems()));
                }
            }
        }
    }
    //内存进程调度
    public void PCBSchudling() {
        while(readyPCB.getNumberOfItems() < 6) {
            if(backupPCB.getNumberOfItems() > 0) {
                Iterator backupPCBIterator = backupPCB.iterator();
                ProcessPCB newItem = (ProcessPCB) backupPCBIterator.next();
                readyPCB.addItem(newItem);
                backupPCB.removeItem(newItem);
                sortReadyPCB();
                readyList.setListData(readyPCB.getItemsProperties());
                backupList.setListData(backupPCB.getItemsProperties());
                backupTotalTextField.setText(Integer.toString(backupPCB.getNumberOfItems()));
            } else break;
        }
    }
    /*
        public void run() {

            PCBSchudling();
            if(readyPCB.getNumberOfItems() > 0) {
                Iterator readyIterator = readyPCB.iterator();
                PCBItem runningItem = (PCBItem) readyIterator.next();
                if(runningItem.getPCB().getRequiredTime()>0) {
                    runningItem.run();
                }
                if(runningItem.getPCB().getRequiredTime() <= 0) {
                    statuesTextArea.append("The work has been finished and the PCB will be moved out from the memory!\n");
                    readyPCB.removeItem(runningItem);
                    runningItem = null;
                    //注意
                    PCBSchudling();
                }
                sortReadyPCB();
                readyList.setListData(readyPCB.getItemsProperties());
            } else {
                statuesTextArea.append("CPU backlssh/race!\n");
            //	System.exit(0);
            }
        }
    */
     class RunThread extends Thread {
        int t, TimeSlice;
        boolean Flag = true;
        public void run(){
            while(Flag) {
                //Warning!!!!!!!!!!!!
                if(getSystemStatus() == 0) {
                    Flag = false;
                    break;
                } else if(getSystemStatus() == 2) {
                    while(getSystemStatus() == 2) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }
                    }
                }

                if(readyPCB.getNumberOfItems() < 6) {
                    if(backupPCB.getNumberOfItems() > 0) {
                        while(readyPCB.getNumberOfItems() < 6) {
                            if(backupPCB.getNumberOfItems() > 0) {
                                Iterator bwackupPCBIterator = backupPCB.iterator();
                                ProcessPCB newItem = (ProcessPCB) bwackupPCBIterator.next();
                                while(bwackupPCBIterator.hasNext() ) {
                                    int s = newItem.getMemoryLimit();
                                    if (boolMemoryAllocation(s)) {
                                        break;
                                    } else
                                        newItem = (ProcessPCB) bwackupPCBIterator.next();
                                    if(newItem == null) break;
                                }
                                if(newItem != null) {
                                    int bbase=0;
                                    int llimit = newItem.getMemoryLimit();
                                    bbase = MemoryAllocation(llimit);
                                    ProcessPCB nowItem  = new ProcessPCB(newItem.getPID(), newItem.getRequiredTime(), newItem.getPriority(),
                                            "就绪", bbase, llimit);
                                    readyPCB.addItem(nowItem);
                                    backupPCB.removeItem(newItem);
                                    backupList.setListData(backupPCB.getItemsProperties());
                                    backupTotalTextField.setText(Integer.toString(backupPCB.getNumberOfItems()));
                                } else {
                                    statuesTextArea.append("No memory available!\n");
                                    break;
                                }
                            } else break;
                        }
                    }
                }
                sortReadyPCB();
                backupList.setListData(backupPCB.getItemsProperties());
                backupTotalTextField.setText(Integer.toString(backupPCB.getNumberOfItems()));
                Iterator readyIterator = readyPCB.iterator();
                ProcessPCB runningItem = null;
                if(readyIterator.hasNext()) {
                    runningItem = (ProcessPCB) readyIterator.next();
                }
                //PCBItem runningItem = (PCBItem) readyIterator.next(); //readyPCB.getNumberOfItems() > 0
                //注意此处有bug结果就出现了java.util.NoSuchElementException的错误提示 ，就是黄色的那一句，多了一个 迭代器的next（）。
                if(runningItem != null) {

                    t = runningItem.getRequiredTime();
                    TimeSlice = getTimeslice();
                    System.out.println(TimeSlice);
                    for(int i=TimeSlice; i>=0; i--) {
                        if(t > 0) {
                            runningItem.run();
                            t--;
                            sortReadyPCB();
                            if(getFlag() == true)
                                CPUTextArea.append("The PCB " + runningItem.getPID() + " is running!\n");
                            try {
                                Thread.sleep(990);
                            } catch (Exception ee) {
                                ee.printStackTrace();
                            }
                        }
                    }

                    if(t <= 0) {
                        Flag = false;
                        statuesTextArea.append("The work has been finished and the PCB will be moved out from the memory!\n");
                        memoryRecall(runningItem.getMemoryBase(), runningItem.getMemoryLimit());
                        readyPCB.removeItem(runningItem);
                        runningItem = null;
                        sortReadyPCB();
                    }
                    if(readyPCB.getNumberOfItems() > 0)
                        Flag = getFlag();
                    else Flag = false;
                } else Flag = false;

                if(getSystemStatus() == 0) {
                    Flag = false;
                    break;
                } else {
                    if(getSystemStatus() == 2) {
                        while(getSystemStatus() == 2) {
                            try {
                                Thread.sleep(1000);
                            } catch (Exception ee) {
                                ee.printStackTrace();
                            }
                        }
                    }
                    if(getSystemStatus() == 1) Flag = true;
                }
            }
        }
    }
    public boolean getFlag() {
        return this.flag;
    }

    public int getSystemStatus() {
        return systemStatus;
    }

    class StartSystemListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if(readyPCB.getNumberOfItems() > 0) {
                if(systemStatus == 0) {
                    Thread runThread = new RunThread();
                    flag = true;
                    runThread.start();
                    systemStatus = 1;
                    statuesTextArea.append("System Start!\n");
                } else {
                    statuesTextArea.append("Operat False!\n");
                }
            } else statuesTextArea.append("The ReadyList is empty! Please add processes\n");

        }
    }

    class SystemPauseListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(systemStatus == 1) {
                flag = false;
                systemStatus = 2;
                pauseButton.setText("继续");
                statuesTextArea.append("System Pause!\n");
            }  else if(systemStatus == 2) {
                flag = true;
                systemStatus = 1;
                pauseButton.setText("暂停");
                statuesTextArea.append("System Continue!\n");
            } else {
                statuesTextArea.append("Operat False!\n");
            }
        }
    }

    class ResetSystemListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if(systemStatus != 0) {
                flag = false;
                systemStatus = 0;
                backupPCB = null;
                readyPCB = null;
                suspendedPCB = null;
                memoryItems = null;
                backupPCB = new PCBRecords();
                readyPCB = new PCBRecords();
                suspendedPCB = new PCBRecords();
                backupList.setListData(backupPCB.getItemsProperties());
                readyList.setListData(readyPCB.getItemsProperties());
                suspendList.setListData(suspendedPCB.getItemsProperties());

                backupTotalTextField.setText("0");
                suspendTotalTextField.setText("0");
                CPUTextArea.setText("");
                statuesTextArea.setText("");
                priorityJRB.doClick();
                timeslice = 1;
                timesliceJtf.setText("3");
                memoryItems = new MemoryRecords();
                memoryList.removeAll();
                MemoryItem newMemoryItem = new MemoryItem(0,3000);
                memoryItems.addItem(newMemoryItem);
                memoryList.setListData(memoryItems.getItemsProperties());
                statuesTextArea.append("System Reset!\n");
            } else {
                statuesTextArea.append("Operat False!\n");
            }
        }
    }

    class priotiryListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            timeslice = 1;
        }
    }
    class timeslicListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            timeslice = getTimesliceText();
            if(timeslice <= 0) {
                statuesTextArea.append("The timeslice must be a positive integer!\n");
                timesliceJtf.setText("1");
                timeslice = 1;
            }
        }
    }

    public int getTimesliceText() {
        int n;
        try  {
        n = Integer.parseInt(timesliceJtf.getText());
        }  catch(NumberFormatException numberformatexception) {
        statuesTextArea.append("The timeslice must be a positive integer!\n");
        n = -999;
        }
        return n;
        }
}
