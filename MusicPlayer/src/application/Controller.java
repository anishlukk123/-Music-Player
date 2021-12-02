package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Controller implements Initializable   {
	
	@FXML
	private AnchorPane pane;
	@FXML
     private Button play;
	@FXML
    private Button pause;
	@FXML
    private Button  reset;
	@FXML
    private Button  previous;
	@FXML
    private Button  next;
	@FXML
    private  ChoiceBox<String> speed;
	@FXML
	private Label songlabel;
	@FXML
	private Slider volome;
	@FXML
	private ProgressBar progress;
	private File directory;
	private File[]files;
	private ArrayList<File>songs;
	private int songnumber;
	private int speeds[] = {25,75,95,100,125,150,175,200};
	private Timer timer;
	private boolean  running;
	private TimerTask task;
	private  Media media;
	private MediaPlayer mediaplayer;
	
 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		songs = new ArrayList<File>();
		
		directory = new File("music");
		
		files = directory.listFiles();
		
		if(files != null) {
			
			for(File file : files) {
				
				songs.add(file);
			}
		}
		
		media = new Media(songs.get(songnumber).toURI().toString());
		mediaplayer = new MediaPlayer(media);
		
		songlabel.setText(songs.get(songnumber).getName());
		
		for(int i = 0; i < speeds.length; i++) {
			
			speed.getItems().add(Integer.toString(speeds[i]));
		}
		
		speed.setOnAction(this::changespeed);
		
		volome.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				
				mediaplayer.setVolume(volome.getValue() * 0.01);			
			}
		});
		
		progress.setStyle("-fx-accent: #00FF00");
	}
		
	
	
	

	public void playmedia()
	{
		timer();
	
	changespeed(null);
	

	 mediaplayer.play();
	

	}
	  

	

	public void pausemedia()
	{
		cancel();
     mediaplayer.pause();
	}

	public void resetmedia()
	{
		progress.setProgress(0);
     mediaplayer.seek(Duration.seconds(0.0));
	}

	public void changespeed(ActionEvent e  )
	{
	if(speed.getValue()==null)
	{
		mediaplayer.setRate(1);
	}
	else
	{
		mediaplayer.setRate(Integer.parseInt(speed.getValue())*0.01);
	}
		 

	}
	public void previoustmedia()
	{
		changespeed(null);
		if(songnumber>0)
		{
			songnumber--;
			mediaplayer.stop();
			
			if(running)
				cancel();
			 Media  media = new Media(songs.get(songnumber).toURI().toString());
			  mediaplayer = new MediaPlayer(media);	
			 songlabel.setText(songs.get(songnumber).toString());
		}
		else
		{
			songnumber=0;
			mediaplayer.stop();
			if(running)
				cancel();
			 Media  media = new Media(songs.get(songnumber).toURI().toString());
			  mediaplayer = new MediaPlayer(media);	
			 songlabel.setText(songs.get(songnumber).toString());
		}
	
	}
	public void nexttmedia()
	{
	

		if(songnumber<songs.size()-1)
		{
			mediaplayer.stop();
			songnumber++;
			if(running)
				cancel();
			 Media  media = new Media(songs.get(songnumber).toURI().toString());
			  mediaplayer = new MediaPlayer(media);	
			 songlabel.setText(songs.get(songnumber).toString());
		}
		else
		{
			mediaplayer.stop();
			songnumber=0;
			if(running)
				cancel();
			 Media  media = new Media(songs.get(songnumber).toURI().toString());
			  mediaplayer = new MediaPlayer(media);	
			 songlabel.setText(songs.get(songnumber).toString());
		}
		
		}
	public void timer()
	{
		 timer = new Timer();
		 task = new TimerTask()
				 {

					@Override
					public void run() {
	     double current =  mediaplayer.getCurrentTime().toSeconds();
	     double end = mediaplayer.getTotalDuration().toSeconds();
	     progress.setProgress(current/end);
	       
	 
					}
					
				 };
				 timer.scheduleAtFixedRate(task, 1000, 1000);
	}

public void cancel()
{
	running = false;
 timer.cancel();	
}






}

		
			
	
		
	

	



