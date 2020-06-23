/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanced.music.player;

import java.util.LinkedList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Jursh
 */
public class AdvancedMusicPlayer extends Application{

    static ListView<String> listView;
    static ObservableList<String> names;
    
    @Override
    public void start(Stage stage) {
               
        stage.setTitle("JMAC Music Player");

        names = FXCollections.observableArrayList(
          "Song1.mp3", "Song2.mp3", "Song3.mp3");
        listView = new ListView<>(names);
        Button playButton = new Button("Play");
        playButton.setOnAction(this::play); //method reference
        Button stopButton = new Button("Stop");
                stopButton.setOnAction(this::stop); //method reference
        Button addButton  = new Button("Add Song");
        addButton.setOnAction(this::addSong); //method reference
        Button exportButton = new Button("Export");
                exportButton.setOnAction(this::listExport); //method reference
        Button importButton = new Button("Import");
                importButton.setOnAction(this::listImport); //method reference
        Button sortButton = new Button("Sort");
                sortButton.setOnAction(this::sort); //method reference
        
        FlowPane buttons = new FlowPane();
        buttons.setHgap(5);
        buttons.getChildren().addAll(playButton, stopButton, addButton, sortButton, exportButton, importButton);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        grid.add(buttons, 0, 0);
        grid.add(listView, 0, 1);
        
        //Scene scene = new Scene(grid, 350, 400);
        Scene scene = new Scene(grid, 400, 400);
        scene.setFill(Color.LIGHTBLUE);

        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    
    //button functions
    
    private void play(ActionEvent event){
        //TODO play songs
    }
    
    private void stop(ActionEvent event){
        //TODO stop songs
    }
    
    private void addSong(ActionEvent event){
        //TODO addsongs songs
    }

    private void sort(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void listImport(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void listExport(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
