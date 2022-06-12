package finalproject;

public class Set extends Statement {

    public Set(String text) {
        super(text);
    }

    @Override
    public void run() {
        String[] pars = statementText.substring(statementText.indexOf("(")+1, statementText.indexOf(")")).split(",");
        Variable var = new Variable();
        var.setName(pars[0]);
        var.setValue(Integer.parseInt(pars[1]));
        super.addVariable(var);
    }

    @Override
    public Error checkError() {
        Error error = new Error();
        if (!statementText.startsWith("SET(")) {
            error.errorFound(lineNum, ErrorType.NOT_EXIST);
        } else if (!statementText.endsWith(")")) {
            error.errorFound(lineNum, ErrorType.NOT_EXIST);
        } else if (!parsePar()) {
            error.errorFound(lineNum, ErrorType.INVALID_PARAMETERS);
        }
        if(!error.isDiscovered()){
            this.run();
        }
        return error;
    }

    public boolean parsePar() {
        String[] pars = statementText.substring(statementText.indexOf("(")+1, statementText.indexOf(")")).split(",");
        if (pars.length != 2) {
            return false;
        }
        Variable var = Statement.getVariable(pars[0]);
        if (var != null) {
            return false;
        }
        for (int i = 0; i < pars[1].length(); i++) {
            if (!(pars[1].charAt(0) > 47 && pars[1].charAt(0) < 58)) {
                return false;
            }
        }
        return true;
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
