package Transactions.AdvancedFeatures;

import Transactions.TransactionFactory;

import java.util.Date;

public class ScheduledTransaction {

    private TransactionFactory transaction;
    private Date scheduledTime;

    public ScheduledTransaction(TransactionFactory transaction, Date scheduledTime) {
        this.transaction = transaction;
        this.scheduledTime = scheduledTime;
    }

    public TransactionFactory getTransaction() {
        return transaction;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

}
