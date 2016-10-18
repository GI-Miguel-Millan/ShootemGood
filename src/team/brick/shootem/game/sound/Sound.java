package team.brick.shootem.game.sound;
import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	
	public static final Sound sound1 = new Sound("/sounds/background.au");
		
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
}
