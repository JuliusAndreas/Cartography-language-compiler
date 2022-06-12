package finalproject;

import java.awt.Point;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class FinalProject extends Application {

    private final double stageWidth = 1000;
    private final double stageHeight = 800;
    private static int counter = 0;
    private File file;
    private String mainText = "";
    private String errorText = "";
    private boolean fileOpened = false;
    private Label compileLabel=new Label();
    private boolean readyToRun = false;
    private int compileBtnCOunter=0;

    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();

        TextArea textArea = new TextArea();
        textArea.setMinSize(500, 400);

        Button browseBtn = new Button("Browse");
        Button saveBtn = new Button("Save");
        Button saveAsBtn = new Button("Save as");
        Button compileBtn = new Button("Compile");
        Button runBtn = new Button("Run");
        Button proRunBtn = new Button("Pro Run");
        
        runBtn.setOnAction(event -> {
        
            String currText=textArea.getText();
            if(!mainText.equals(currText)){
                readyToRun=false;
                mainText=currText;
            }
            
            if(readyToRun){
                
                Stage runStage = new Stage();
                Pane root = new Pane();
                Canvas canvas=new Canvas(stageWidth,stageHeight);
                root.getChildren().add(canvas);
                Pen.getPen().setCanvas(canvas);
                Scene runScene = new Scene(root,stageWidth,stageHeight);
                runStage.setTitle("Run window");
                runStage.setWidth(stageWidth);
                runStage.setHeight(stageHeight);
                runStage.setScene(runScene);
                canvas.getGraphicsContext2D().setFill(Color.WHITE);
                canvas.getGraphicsContext2D().fillRect(0, 0, stageWidth, stageHeight);
                runStage.show();
                
                Pen.getPen().setPosition(new Point(0,0));
                Parser parser=new Parser(mainText);
                Statement[] statements=parser.getStatements();
                for(Statement s:statements){
                    s.run();
                }
                
            }else{
                new Alert(Alert.AlertType.ERROR,"You need to compile the code first").showAndWait();
            }
        });

        compileBtn.setOnAction(event -> {

            compileBtnCOunter++;
            int errorCounter = 0;

            mainText=textArea.getText();

            if(compileBtnCOunter>1){
                for(int i=0;i<Statement.variables.size();i++){
                    Statement.variables.set(i, null);
                }
            }
            Parser parser = new Parser(mainText);
            Statement[] statements = parser.getStatements();
            Compiler compiler = new Compiler(statements);

            for (int i = 0; i < compiler.getErrors().length; i++) {
                if (compiler.getErrors()[i].isDiscovered()) {
                    errorCounter++;
                }
            }
            if (errorCounter != 0) {
                Error[] foundErrors = new Error[errorCounter];

                for (int i = 0 , j = 0; i < compiler.getErrors().length; i++) {
                    
                    if (compiler.getErrors()[i].isDiscovered()) {
                        
                        foundErrors[j] = compiler.getErrors()[i];
                        j++;
                    }
                }
                errorText = "Your code compiled with " + errorCounter + " errors\n errors occured in the following lines :";
                for (int i = 0; i < errorCounter; i++) {

                    errorText += foundErrors[i].toString();
                    if (i != errorCounter - 1) {
                        errorText += ", ";
                    }
                    if (i % 3 == 0) {
                        errorText += "\n";
                    }
                }
                
                compileLabel.setText(errorText);
                compileLabel.setTextFill(Color.RED);
                
            } else {
                readyToRun = true;
                errorText = "Your code compiled succesfully\n";
                compileLabel.setText(errorText);
                compileLabel.setTextFill(Color.GREEN);
            }
            

        });

        browseBtn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser);
            file = fileChooser.showOpenDialog(stage);
            if (file != null && file.canRead()) {
                fileOpened = true;
                mainText = "";
                try {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        mainText = mainText + scanner.nextLine();
                        mainText = mainText + "\n";
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                textArea.setText(mainText);
            }
        });

        saveBtn.setOnAction(event -> {
            if (fileOpened) {
                mainText = textArea.getText();
                DataOutputStream output = null;
                try {
                    output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                    output.writeChars(mainText);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        output.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        saveAsBtn.setOnAction(event -> {
            mainText = textArea.getText();
            FileChooser fileChooser = new FileChooser();
            configureFileChooser(fileChooser);
            File outFile = fileChooser.showSaveDialog(stage);
            File tempFile = new File(outFile.getAbsolutePath() + ".m");
            try {
                tempFile.delete();
                tempFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            DataOutputStream output = null;
            if (tempFile.isFile()) {
                try {
                    output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(tempFile)));
                    output.writeChars(mainText);
                    System.out.println(mainText);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        output.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        
        proRunBtn.setOnAction(event -> {
            
             String currText=textArea.getText();
            if(!mainText.equals(currText)){
                readyToRun=false;
                mainText=currText;
            }
            
            if(readyToRun){
                
                Stage proRunStage = new Stage();
                Pane proRoot = new Pane();
                Canvas proCanvas=new Canvas(stageWidth,stageHeight);
                proRoot.getChildren().add(proCanvas);
                Pen.getPen().setCanvas(proCanvas);
                Scene proRunScene = new Scene(proRoot,stageWidth,stageHeight);
                proCanvas.getGraphicsContext2D().setFill(Color.WHITE);
                proCanvas.getGraphicsContext2D().fillRect(0, 0, stageWidth, stageHeight);
                proRunStage.setTitle("Pro Run window");
                proRunStage.setWidth(stageWidth);
                proRunStage.setHeight(stageHeight);
                proRunStage.setScene(proRunScene);
                proRunStage.show();
                
                Pen.getPen().setPosition(new Point(0,0));
                Parser parser=new Parser(mainText);
                Statement[] statements=parser.getStatements();
                for(Statement s:statements){
                    s.proRun();
                }
                
            }else{
                new Alert(Alert.AlertType.ERROR,"You need to compile the code first").showAndWait();
            }
        });

        HBox hBox = new HBox();
        hBox.getChildren().add(browseBtn);
        hBox.getChildren().add(saveBtn);
        hBox.getChildren().add(saveAsBtn);
        hBox.getChildren().add(compileBtn);
        hBox.getChildren().add(runBtn);
        hBox.getChildren().add(proRunBtn);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);

        grid.add(textArea, 0, 0);
        grid.add(hBox, 0, 1);
        grid.add(compileLabel, 0, 2);
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(false);

        Scene scene = new Scene(grid, stageWidth, stageHeight);
        stage.setTitle("Map simulator");
        stage.setScene(scene);
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void configureFileChooser(
            FileChooser fileChooser) {
        fileChooser.setTitle("Choose File");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("M", "*m")
        );
    }
}
