package finalproject;

public class Size extends Statement {

    public Size(String text) {
        super(text);
    }

    @Override
    public void run() {
        pen.setSize(parsePar());
    }

    @Override
    public Error checkError() {
        Error error = new Error();
        String index = "";

        if (!this.statementText.startsWith("SIZE(")) {
            error.errorFound(this.lineNum, ErrorType.NOT_EXIST);
        } else if (!this.statementText.endsWith(")")) {
            error.errorFound(this.lineNum, ErrorType.NOT_EXIST);
        } else if (this.parsePar() == -1) {
            error.errorFound(this.lineNum, ErrorType.INVALID_PARAMETERS);
        }
        return error;
    }

    public int parsePar() {
        String par = statementText.substring(statementText.indexOf("(")+1, statementText.indexOf(")"));
        if (par.charAt(0) > 47 && par.charAt(0) < 58) {
            if (par.length() > 1) {
                return -1;
            } else {
                return Integer.parseInt(par);
            }
        } else {
            Variable var = Statement.getVariable(par);
            if (var == null) {
                return -1;
            } else {
                return var.getValue();
            }
        }
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
