package finalproject;

public class WrongStatement extends Statement {

    public WrongStatement(String text) {
        super(text);
    }

    @Override
    public void run(){
        
    }
    
    @Override
    public Error checkError() {
        Error error = new Error();
        error.errorFound(lineNum, ErrorType.NOT_EXIST);
        return error;
    }

    @Override
    public void proRun() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
