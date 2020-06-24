/*
 * Programmed by Joshua Macaulay 30008704
 */
package advanced.music.player;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

/**
 *
 * @author Jursh
 */
public class AdvancedMusicPlayer extends Application {

    static LinkedList<Song> songList;
    static ListView<String> listView;
    static ObservableList<String> songNames;
    static Stage mainStage;
    static MediaPlayer player;
    int index;

    @Override
    public void start(Stage stage) {
        Stage loginPage = new Stage();

        loginPage.setTitle("Login");
        GridPane grid = new GridPane();

        Label userLabel = new Label("Username: ");
        Label passLabel = new Label("Password: ");
        TextField username = new TextField();
        TextField password = new TextField();
        Label info = new Label("Please enter credentials.");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String user = username.getText();
                String pass = password.getText();

                boolean accepted = checkCredentials(user, pass);
                if (accepted) {
                    loginPage.close();
                    runMainPage(stage);
                } else {
                    info.setText("Incorrect Credentials");
                }
            }
        });

        grid.add(userLabel, 0, 0);
        grid.add(passLabel, 0, 1);
        grid.add(username, 1, 0);
        grid.add(password, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(info, 1, 3);

        //searchGrid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 25, 10, 40));

        Scene scene = new Scene(grid, 300, 140);
        scene.setFill(Color.LIGHTBLUE);

        loginPage.setScene(scene);
        loginPage.show();

    }

    private boolean checkCredentials(String username, String password) {
        boolean result = false;

        AuthenticationImpl authImpl = new AuthenticationImpl();
        authImpl.setAdmin();
        Authentication auth = authImpl;

        result = auth.authenticate(username, password);

        return result;
    }

    private void runMainPage(Stage stage) {
        mainStage = stage;
        stage.setTitle("JMAC Music Player");

        songList = new LinkedList<>();
        songNames = FXCollections.observableArrayList();
        refreshList();

        listView = new ListView<>(songNames);
        Button playButton = new Button("Play");
        playButton.setOnAction(this::play); //method reference
        Button stopButton = new Button("Stop");
        stopButton.setOnAction(this::stop); //method reference
        Button addButton = new Button("Add Song");
        addButton.setOnAction(this::addSong); //method reference
        Button exportButton = new Button("Export");
        exportButton.setOnAction(this::listExport); //method reference
        Button importButton = new Button("Import");
        importButton.setOnAction(this::listImport); //method reference
        Button sortButton = new Button("Sort");
        sortButton.setOnAction(this::sort); //method reference
        Button searchButton = new Button("Search");
        searchButton.setOnAction(this::search); //method reference

        FlowPane buttons = new FlowPane();
        buttons.setHgap(5);
        buttons.getChildren().addAll(playButton, stopButton, addButton, sortButton, searchButton, exportButton, importButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(buttons, 0, 0);
        grid.add(listView, 0, 1);

        //Scene scene = new Scene(grid, 350, 400);
        Scene scene = new Scene(grid, 420, 400);
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

    private void refreshList() {
        songNames.clear();
        for (Song var : songList) {
            songNames.add(var.getSongName());
        }
    }

    private void playNext() {
        if ((index + 1) < songList.size()) {
            index++;
        } else {
            index = 0;
        }

        //selects the song
        listView.getSelectionModel().select(index);

        Song current = songList.get(index);
        //has to reverse the forward slashes
        Media currentMedia = new Media("file:///" + current.getUri().replace('\\', '/'));

        player = new MediaPlayer(currentMedia);
        player.play();
    }

    //button functions
    private void play(ActionEvent event) {
        index = listView.getSelectionModel().getSelectedIndex();

        //will try to stop music playing if its already going
        try {
            player.stop();
        } catch (Exception f) {
            //stops music playing
        }

        //checks to see if a song is selected to play, else plays first in list
        if (index >= 0) {

            Song current = songList.get(index);
            //has to reverse the forward slashes
            Media currentMedia = new Media("file:///" + current.getUri().replace('\\', '/'));

            player = new MediaPlayer(currentMedia);
            //sets auto play next
            player.setOnEndOfMedia(() -> {
                playNext();
            });
            player.play();
        } else {
            if (!songList.isEmpty()) {
                index = 0;
                listView.getSelectionModel().select(index);
                Song current = songList.get(index);
                //has to reverse the forward slashes
                Media currentMedia = new Media("file:///" + current.getUri().replace('\\', '/'));

                player = new MediaPlayer(currentMedia);
                //sets auto play next
                player.setOnEndOfMedia(() -> {
                    playNext();
                });
                player.play();
            }
        }
    }

    private void stop(ActionEvent event) {
        try {
            player.stop();
        } catch (Exception f) {
            //stops music playing
        }
    }

    private void addSong(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Music File");
        File chosenFile = fileChooser.showOpenDialog(mainStage);
        String filename = chosenFile.getName();
        String uri = chosenFile.getAbsolutePath();

        Song newSong = new Song(filename, uri);

        songList.add(newSong);
        refreshList();

    }

    private void sort(ActionEvent event) {
        SongSort sorter = new SongSort();

        Song[] arr = songList.toArray(new Song[songList.size()]);
        arr = sorter.MergeSortSong(arr);

        songList.clear();

        for (int i = 0; i < arr.length; i++) {
            songList.add(arr[i]);
        }

        refreshList();
    }

    private void listImport(ActionEvent event) {
        try {
            CsvReader reader = new CsvReader("playlist.csv");

            songList.clear();

            reader.readHeaders();
            while (reader.readRecord()) {
                String songName = reader.get("song name");
                String uri = reader.get("uri");

                Song newSong = new Song(songName, uri);
                songList.add(newSong);
            }

            refreshList();
        } catch (Exception f) {

        }
    }

    private void listExport(ActionEvent event) {

        try {
            CsvWriter writer = new CsvWriter(new FileWriter("playlist.csv", true), ',');

            writer.write("song name");
            writer.write("uri");
            writer.endRecord();

            Song[] arr = songList.toArray(new Song[songList.size()]);
            for (int i = 0; i < arr.length; i++) {
                writer.write(arr[i].getSongName());
                writer.write(arr[i].getUri());
                writer.endRecord();
            }
            writer.close();
        } catch (Exception f) {
            System.out.println("Error in exporting: " + f.getMessage());
        }
    }

    private void search(ActionEvent event) {
        Stage searchWindow = new Stage();
        searchWindow.setTitle("Enter Search Term");
        GridPane searchGrid = new GridPane();

        TextField input = new TextField();

        Button searchButton = new Button("Search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String searchTerm = input.getText();

                searchList(searchTerm);
                searchWindow.close();
            }
        });

        searchGrid.add(input, 0, 0);
        searchGrid.add(searchButton, 1, 0);

        System.out.println("HERE");
        //searchGrid.setHgap(10);
        //searchGrid.setVgap(10);
        searchGrid.setPadding(new Insets(10, 25, 10, 40));

        Scene scene = new Scene(searchGrid, 300, 50);
        scene.setFill(Color.LIGHTBLUE);

        searchWindow.setScene(scene);
        searchWindow.show();

    }

    private void searchList(String searchTerm) {
        Search search = new Search();
        Song[] arr = songList.toArray(new Song[songList.size()]);
        Song searchedSong = search.binarySearch(searchTerm, arr);

        if (searchedSong != null) {
            //will try to stop music playing if its already going
            try {
                player.stop();
            } catch (Exception f) {
                //stops music playing
            }
            System.out.println("SearchedSong= " + searchedSong.getSongName());
            Media currentMedia = new Media("file:///" + searchedSong.getUri().replace('\\', '/'));

            player = new MediaPlayer(currentMedia);
            //sets auto play next
            player.setOnEndOfMedia(() -> {
                playNext();
            });
            player.play();
        } else {
            System.out.println("Not found");
        }
    }
}
