package Transactions.AdvancedFeatures;

import Transactions.TransactionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduledTransactionManager {

    private List<ScheduledTransaction> scheduledTransactions;

    public ScheduledTransactionManager() {
        this.scheduledTransactions = new ArrayList<>();
    }

    public void scheduleTransaction(TransactionFactory transaction, Date scheduledTime) {
        ScheduledTransaction scheduledTransaction = new ScheduledTransaction(transaction, scheduledTime);
        scheduledTransactions.add(scheduledTransaction);
    }

}
