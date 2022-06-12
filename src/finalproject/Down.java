package finalproject;

public class Down extends Statement {
    
    public Down(String text) {
        super(text);
    }

    @Override
    public void run(){
        pen.putDown();
    }
    
    @Override
    public Error checkError() {
        Error error = new Error();
        if(!this.statementText.equals("DOWN")){
            error.errorFound(lineNum, ErrorType.NOT_EXIST);
        }
        return error;
    }
    
        @Override
    public void proRun() {
        this.run();
    }
}
