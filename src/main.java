import CSV.CSVTable;
import Serialization.CSVStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class main {

    private File csvFile;
    private boolean headerFlag;
    private CSVTable table;
    private CSVStream csvStream;

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
        table = CSVTable.parse(csvStream, hasHeader());
    }

    private void setFile(String filename) {
        setCSVFileStream(csvFile = new File(filename));
    }

    private void setCSVFileStream(File file) {
        try {
            csvStream = new CSVStream(new FileInputStream(file));
        } catch(FileNotFoundException e) {
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
        System.out.println(table.toString());
    }
}
