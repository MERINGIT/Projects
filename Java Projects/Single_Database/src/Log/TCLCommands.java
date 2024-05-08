package Log;

public class TCLCommands implements TCLCommandHandler {
    public void rollback(TransactionLog log){
        log=null;  // Assuming TransactionLog has a method to clear its contents.
    }
}
