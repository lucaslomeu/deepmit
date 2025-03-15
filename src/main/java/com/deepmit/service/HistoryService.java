package com.deepmit.service;

import com.deepmit.command.History;

public class HistoryService {

    public void displayHistory() {
        History.displayHistory();
    }

    public void exportHistory() {
        History.exportHistory();
    }

    public void deleteHistory() {
        History.deleteHistoryFile();
    }

    public void clearHistory() {
        History.clearHistory();
    }
}
