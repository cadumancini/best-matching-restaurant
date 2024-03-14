package com.assessment.exceptions;

import java.io.IOException;

public class CSVReaderException extends IOException {
    public CSVReaderException(String message) {
        super(message);
    }
}
