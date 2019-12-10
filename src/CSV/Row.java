package CSV;

import Serialization.CSVStream;

import java.io.IOException;
import java.util.LinkedList;

class Row {
    private String title;
    private LinkedList<String> data;

    public Row() {
        data = new LinkedList<>();
    }

    public Row(String rowTitle, LinkedList<String> rowData) {
        this();
        setTitle(rowTitle);
        setData(rowData);
    }

    public Row(CSVStream inputStream) throws IOException {
        while(!inputStream.hitNewLine()) {
            inputStream.next();
        }
    }

    private void setTitle(String rowTitle) {
        title = rowTitle;
    }

    private void setData(LinkedList<String> rowData) {
        data.addAll(rowData);
    }
}
