package finalproject;

public class Parser {

    private String text;
    private Statement[] statements;

    public Parser(String text) {
        this.setText(text);
        statements=new Statement[text.split("\n").length];
        parse();
    }

    public void setText(String text) {
        this.text = text;
    }

    public Statement[] getStatements() {
        return this.statements;
    }

    public void parse() {
        String[] statementText = text.split("\n");
        
        /*we splited the given text to several text that each one contains 
        the contatns of a line of given text
        and now we are craeting the suitable object for each one of the splited text
        by analysing the start of the texts and choose the suitable class to crate
        an object*/
        
        for (int i = 0; i < statementText.length; i++) {
            switch (statementText[i].charAt(0)) {
                case 'U':
                    statements[i] = new Up(statementText[i]);
                    statements[i].setLineNum(i + 1);
                    break;
                case 'D':
                    statements[i] = new Down(statementText[i]);
                    statements[i].setLineNum(i + 1);
                    break;
                case 'M':
                    statements[i] = new Move(statementText[i]);
                    statements[i].setLineNum(i + 1);
                    break;
                case 'C':
                    statements[i] = new Color(statementText[i]);
                    statements[i].setLineNum(i + 1);
                    break;
                case 'F':
                    statements[i] = new For(statementText[i],statements.length-(i+1),statements);
                    statements[i].setLineNum(i + 1);
                    break;
                case 'I':
                    statements[i] = new Inc(statementText[i]);
                    statements[i].setLineNum(i + 1);
                    break;
                case 'S':
                    switch (statementText[i].charAt(1)) {
                        case 'I':
                            statements[i] = new Size(statementText[i]);
                            statements[i].setLineNum(i + 1);
                            break;
                        case 'T':
                            statements[i] = new Style(statementText[i]);
                            statements[i].setLineNum(i + 1);
                            break;
                        case 'E':
                            statements[i] = new Set(statementText[i]);
                            statements[i].setLineNum(i + 1);
                            break;
                        default:
                            statements[i] = new WrongStatement(statementText[i]);
                            statements[i].setLineNum(i + 1);
                            break;
                    }
                    break;
                default:
                    statements[i] = new WrongStatement(statementText[i]);
                    statements[i].setLineNum(i + 1);
                    break;
            }
        }
    }
}
