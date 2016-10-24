package team.brick.shootem.game.sound;
import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	
	public static final Sound background = new Sound("/sounds/background.au");
	public static final Sound lazer = new Sound("/resources/sounds/lazer-sound.au");
	public static final Sound explosion = new Sound("/resources/sounds/explosion.au");
	public static final Sound victory = new Sound("/resources/sounds/victory.au");
		
	private AudioClip clip;
	
	public Sound(String filename)
	{
		try{
			clip = Applet.newAudioClip(Sound.class.getResource(filename));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void play()
	{
		try{
			new Thread(){
				public void run(){
					clip.loop();
				}
			}.start();			
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
	}
	public void execute()
	{
		try{
			new Thread(){
				public void run(){
					clip.play();
				}
			}.start();			
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
	}
}
