package finalproject;

import java.awt.Point;

public class Up extends Statement {
    
    public Up(String text) {
        super(text);
    }
    
    @Override
    public void run(){
        pen.pickUp();
    }
    
    @Override
    public Error checkError() {
        Error error = new Error();
        if(!this.statementText.equals("UP")){
            error.errorFound(lineNum, ErrorType.NOT_EXIST);
        }
        return error;
    }
    
        @Override
    public void proRun() {
        try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        this.run();
    }
}
