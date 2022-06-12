package finalproject;

public class Color extends Statement {

    public Color(String text) {
        super(text);
    }

    @Override
    public void run() {
        pen.setColor(parsePar()[0], parsePar()[1], parsePar()[2]);
    }

    @Override
    public Error checkError() {
        Error error = new Error();

        if (!statementText.startsWith("COLOR(")) {
            error.errorFound(lineNum, ErrorType.NOT_EXIST);
        } else if (!statementText.endsWith(")")) {
            error.errorFound(lineNum, ErrorType.NOT_EXIST);
        } else if (!statementText.contains(",")) {
            error.errorFound(lineNum, ErrorType.INVALID_PARAMETERS);
        }else if(parsePar()==null){
            error.errorFound(lineNum, ErrorType.INVALID_PARAMETERS);
        }
        return error;
    }

    public int[] parsePar() {
        int commaCounter = 0;
        String[] pars;
        int[] parameters = new int[3];
        String par = statementText.substring(statementText.indexOf("(")+1, statementText.indexOf(")"));
        pars = par.split(",");
        if (pars.length != 3) {
            return null;
        }
        
        if (pars[0].charAt(0) > 47 && pars[0].charAt(0) < 58) {
            for (int i = 0; i < pars[0].length(); i++) {
                if (!(pars[0].charAt(i) > 47 && pars[0].charAt(i) < 58)) {
                    return null;
                }
            }
            parameters[0] = Integer.parseInt(pars[0]);
        } else {
            Variable var = Statement.getVariable(pars[0]);
            if (var == null) {
                return null;
            }
            parameters[0] = var.getValue();
        }
        
        if (pars[1].charAt(0) > 47 && pars[1].charAt(0) < 58) {
            for (int i = 0; i < pars[1].length(); i++) {
                if (!(pars[1].charAt(i) > 47 && pars[1].charAt(i) < 58)) {
                    return null;
                }
            }
            parameters[1] = Integer.parseInt(pars[1]);
        } else {
            Variable var = super.getVariable(pars[1]);
            if (var == null) {
                return null;
            }
            parameters[1] = var.getValue();
        }
        
        if (pars[2].charAt(0) > 47 && pars[2].charAt(0) < 58) {
            for (int i = 0; i < pars[2].length(); i++) {
                if (!(pars[2].charAt(i) > 47 && pars[2].charAt(i) < 58)) {
                    return null;
                }
            }
            parameters[2] = Integer.parseInt(pars[2]);
        } else {
            Variable var = super.getVariable(pars[2]);
            if (var == null) {
                return null;
            }
            parameters[2] = var.getValue();
        }
        if(parameters[0]>255 || parameters[0]<0 || parameters[1]>255 
                || parameters[1]<0 || parameters[2]>255 || parameters[2]<0){
            return null;
        }
        return parameters;
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
