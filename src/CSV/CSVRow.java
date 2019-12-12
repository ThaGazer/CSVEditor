package CSV;

import Serialization.CSVStream;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class CSVRow {
    private LinkedList<String> data;

    public CSVRow() {
        data = new LinkedList<>();
    }

    public CSVRow(List<String> rowData) {
        this();
        setData(rowData);
    }

    public CSVRow(CSVStream stream) throws IOException {
        this(stream.readLine());
    }

    protected void setData(List<String> rowData) {
        data.addAll(Objects.requireNonNull(rowData));
    }

    protected List<String> getData() {
        return data;
    }

    protected boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public String toString() {
        if(isEmpty()) {
            return null;
        }

        return getData().toString();
    }
}
