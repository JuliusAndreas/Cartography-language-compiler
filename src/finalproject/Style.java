package finalproject;

public class Style extends Statement {

    public Style(String text) {
        super(text);
    }

    @Override
    public void run() {
        pen.setStyle(this.parseType());
    }

    @Override
    public Error checkError() {
        Error error = new Error();
        String types;

        if (!this.statementText.startsWith("STYLE(")) {
            error.errorFound(this.lineNum, ErrorType.NOT_EXIST);
        } else if (!this.statementText.endsWith(")")) {
            error.errorFound(this.lineNum, ErrorType.NOT_EXIST);
        }else if(this.parseType()==null){
            error.errorFound(lineNum, ErrorType.INVALID_PARAMETERS);
        }
        return error;
    }
    
    public String parseType(){
        
        String par=statementText.substring(statementText.indexOf("(")+1,statementText.indexOf(")"));
        if(!par.equals("DOTTED") && !par.equals("DASHED") && !par.equals("SOLID")){
            return null;
        }
        return par;
    }
    
        @Override
    public void proRun() {
        
    }
}
