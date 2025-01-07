package codename.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import codename.Observer;
import javafx.fxml.FXML;

public class GridController implements Observer {

    private static final String FILE_NAME = "database.txt";

    @FXML
    private void initialize() {
        System.out.println("GridController initialized");
    }



    @Override
    public void update() {
    }
}
