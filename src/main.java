import CSV.CSVTable;
import Serialization.CSVReader;
import Serialization.CSVWriter;

import java.io.*;
import java.util.List;

public class main {

    private File csvFile;
    private boolean headerFlag;
    private CSVTable table;
    private CSVReader csvReader;
    private CSVWriter csvWriter;

    public main() {
        table = new CSVTable();
        headerFlag = false;
        csvFile = null;
    }

    public static void main(String[] args) {
        new main().go(args);
    }

    public void go(String[] args) {
        handleUserArgs(args);

        try {
            readCSV();
        } catch(IOException e) {
            e.printStackTrace();
        }

        listHeaders();

        System.out.println(findAndSet(16, "Access granted", 0, "0"));

        try {
            writeCSV();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void handleUserArgs(String[] args) {
        if(args.length < 1) {
            throw new IllegalArgumentException();
        }

        for(int i = 0; i < args.length; i++) {
            switch(i) {
                case 0:
                    setFile(args[i]);
                    break;
                case 1:
                    setHeader(Boolean.parseBoolean(args[i]));
                    break;
            }
        }
    }

    private void readCSV() throws IOException {
        table = CSVTable.parse(csvReader, hasHeader());
        csvReader.close();
    }

    private void writeCSV() throws IOException {
        setCSVWriter(csvFile);

        try {
            table.write(csvWriter);
        } catch(IOException e) {
            e.printStackTrace();
        }
        csvWriter.close();
    }

    private void printCSV() {
        table.printTable();
    }

    private void listHeaders() {
        int i = 0;
        for(String header : table.getHeaders()) {
            System.out.println(i + " : " + header);
            i++;
        }
        System.out.println();
    }

    private void addColumn(int index) {
        table.addColumn(index);
    }
    private void addColumn(int index, String str) {
        table.addColumn(index, str);
    }

    private void addColumn(int index, String str, String header) {
        table.addColumn(index, str, header);
    }

    private void setCell(int r, int c, String newData) {
        table.setCell(r, c, newData);
    }

    private void setColumn(int c, String newStr) {
        table.setColumn(c, newStr);
    }

    private void setFile(String filename) {
        setCSVReader(csvFile = new File(filename));
    }

    private void moveColumn(int c, int newLoc) {
        table.moveColumn(c, newLoc);
    }

    private List<Integer> findAndSet(int c, String change, int c1, String change1) {
        for(Integer i : table.searchCol(c, change)) {
            table.setCell(i, c1, change1);
        }
        return table.searchCol(c1, change1);
    }

    private void setCSVReader(File file) {
        try {
            csvReader = new CSVReader(file);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void setCSVWriter(File file) {
        try {
            csvWriter = new CSVWriter(file);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void setHeader(boolean headers) {
        headerFlag = headers;
    }

    private boolean hasHeader() {
        return headerFlag;
    }
}
