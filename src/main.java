import CSV.CSVTable;
import Serialization.CSVReader;
import Serialization.CSVWriter;

import java.io.*;

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

        printCSV();

        table.moveColumn(4, 14);
        printCSV();
        //writeCSV();
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

    private void writeCSV() {
        setCSVWriter(csvFile);

        try {
            table.write(csvWriter);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void setFile(String filename) {
        setCSVReader(csvFile = new File(filename));
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

    private void printCSV() {
        table.printTable();
    }
}
