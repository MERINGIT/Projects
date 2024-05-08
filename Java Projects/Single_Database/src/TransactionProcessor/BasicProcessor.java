package TransactionProcessor;

import Commit.CommitProcessor;
import Log.DDLCommands;
import Log.DMLCommands;
import Log.TCLCommands;
import Log.TransactionLog;

import java.io.IOException;
import java.util.Random;

import static java.lang.System.exit;


public class BasicProcessor {

        Random random=new Random();
        DDLCommands ddl=new DDLCommands();
        DMLCommands dml=new DMLCommands();
        private int transactionNumber=0;
        TransactionLog log=new TransactionLog();
        TCLCommands tcl=new TCLCommands();
        String userName="";


    public BasicProcessor(String[] queries,String username) throws IOException {
        transactionNumber = random.nextInt(100);
        this.userName=username;
        QueryLogProcessing(queries,transactionNumber,username);
    }


    public void QueryLogProcessing(String query[],int tid,String username) throws IOException {
            for (String statement : query) {
                if (statement.startsWith("insert")) {
                    ddl.insertStatement(statement,transactionNumber,log,username);
                }
                else if (statement.startsWith("create")) {
                    ddl.createStatement(statement,transactionNumber,log,username);

                }
                else if (statement.startsWith("select")) {
                    dml.selectStatement(statement,transactionNumber,log,username);

                }
                else if (statement.startsWith("delete")) {
                    dml.deleteStatement(statement,transactionNumber,log,username);

                }
                else if (statement.startsWith("update")) {
                    dml.updateStatement(statement,transactionNumber,log,username);

                }
                else if (statement.startsWith("rollback")) {
                    tcl.rollback(log);

                }
                else if (statement.startsWith("commit")) {
                     log.commit(query,transactionNumber,log);

                }
                else if (statement.startsWith("begin transaction")) {}
                else  {
                   System.out.println("Invalid statement");
                   exit(0);

                }
            }
        }


        private static int generateLogSequenceNumber() {
            return (int) (Math.random() * 9000) + 1000;
        }

    }



