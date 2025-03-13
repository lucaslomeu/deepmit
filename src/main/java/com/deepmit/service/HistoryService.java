package com.deepmit.service;

import com.deepmit.command.History;

public class HistoryService {
    public void displayHistory() {
        History.start();
    }

    public void exportHistory() {
        History.exportHistory();
    }
}
