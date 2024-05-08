package Log;

/**
 * An interface for handling Transaction Control Language (TCL) commands.
 */
public interface TCLCommandHandler {
    /**
     * Rollbacks a transaction, clearing its contents.
     *
     * @param log The transaction log to be rolled back.
     */
    void rollback(TransactionLog log);
}
