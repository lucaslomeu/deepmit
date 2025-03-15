package com.deepmit.service;

import com.deepmit.command.History;

public class HistoryService {
    private final ExportService exportService = new ExportService();

    public void displayHistory() {
        History.displayHistory();
    }

    public void exportHistory() {
        History.exportHistory();
    }

    public void deleteHistory() {
        History.deleteHistory();
    }

    public void clearHistory() {
        History.clearHistory();
    }
}
