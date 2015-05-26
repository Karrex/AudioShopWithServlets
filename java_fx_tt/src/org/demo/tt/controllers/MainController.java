package org.demo.tt.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import org.demo.tt.SceneLauncher;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
	@FXML
	private ListView<File> filesListView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		filesListView.selectionModelProperty().addListener(
				new ChangeListener<MultipleSelectionModel<File>>() {

					@Override
					public void changed(
							ObservableValue<? extends MultipleSelectionModel<File>> arg0,
							MultipleSelectionModel<File> oldValue,
							MultipleSelectionModel<File> newValue) {
						File currentFile = newValue.getSelectedItem();
						if (currentFile.isDirectory()) {
							SceneLauncher
									.showFilesInDirectoryWindow(currentFile);
						}
					}
				});
		
	}
	public void setDirectory(File dir){
		File[] list = dir.listFiles();
		ObservableList<File> listOfFiles = FXCollections
				.observableArrayList(list);
		filesListView.setItems(listOfFiles);
	}
}
