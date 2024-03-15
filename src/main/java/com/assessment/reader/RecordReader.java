package com.assessment.reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface RecordReader {
    List<List<String>> loadSourceFromPath(String path) throws IOException, URISyntaxException;
    List<List<String>> getLines(List<List<String>> source);
}
