package finalproject;

import java.awt.Point;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Move extends Statement {

    public Move(String text) {
        super(text);
    }

    @Override
    public void run() {
        if (pen.isDown()) {
            pen.draw(parsePoint());
        } else {
            pen.setPosition(this.parsePoint());
        }
    }

    @Override
    public Error checkError() {
        Error error = new Error();
        if (!(statementText.startsWith("MOVE(") && statementText.endsWith(")"))) {
            error.errorFound(lineNum, ErrorType.NOT_EXIST);
        } else {
            if (!(statementText.substring(
                    statementText.indexOf("(") + 1, statementText.indexOf(")")).contains(","))) {
                error.errorFound(lineNum, ErrorType.NOT_EXIST);
            } else {
                if ((statementText.indexOf(",") - statementText.indexOf("(") == 1)
                        || (statementText.indexOf(")") - statementText.indexOf(",") == 1)) {
                    error.errorFound(lineNum, ErrorType.INVALID_PARAMETERS);
                } else {
                    Point point = this.parsePoint();
                    if (point == null) {
                        error.errorFound(lineNum, ErrorType.INVALID_PARAMETERS);
                    } else if (point.x > 1000 || point.x < 0 || point.y > 1000 || point.y < 0) {
                        error.errorFound(lineNum, ErrorType.INVALID_PARAMETERS);
                    }
                }
            }
        }
        return error;
    }

    public Point parsePoint() {

        Point point = new Point();

        String firstPar = statementText.substring(statementText.indexOf("(") + 1, statementText.indexOf(","));
        String secondPar = statementText.substring(statementText.indexOf(",") + 1, statementText.indexOf(")"));

        if (firstPar.charAt(0) > 47 && firstPar.charAt(0) < 58) {
            for (int i = 0; i < firstPar.length(); i++) {
                if (!(firstPar.charAt(i) > 47 && firstPar.charAt(i) < 58)) {
                    return null;
                }
            }
            point.x = Integer.parseInt(firstPar);
        } else if ((firstPar.charAt(0) > 64 && firstPar.charAt(0) < 91)
                || (firstPar.charAt(0) > 96 && firstPar.charAt(0) < 123)) {
            if (Statement.getVariable(firstPar) == null) {
                return null;
            } else {
                point.x = Statement.getVariable(firstPar).getValue();
            }
        } else {
            return null;
        }

        if (secondPar.charAt(0) > 47 && secondPar.charAt(0) < 58) {
            for (int i = 0; i < secondPar.length(); i++) {
                if (!(secondPar.charAt(i) > 47 && secondPar.charAt(i) < 58)) {
                    return null;
                }
            }
            point.y = Integer.parseInt(secondPar);
        } else if ((secondPar.charAt(0) > 64 && secondPar.charAt(0) < 91)
                || (secondPar.charAt(0) > 96 && secondPar.charAt(0) < 123)) {
            if (super.getVariable(secondPar) == null) {
                return null;
            } else {
                point.y = super.getVariable(secondPar).getValue();
            }
        } else {
            return null;
        }

        return point;
    }

    @Override
    public void proRun() {
        
    }
}
