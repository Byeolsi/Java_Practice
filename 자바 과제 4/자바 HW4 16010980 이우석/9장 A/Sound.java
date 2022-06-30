import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound  extends Thread{
	private int soundLength, i;
	private String bgmName;
	private Clip soundClip;
	public Sound(String bgm, int time){
		bgmName=bgm;
		soundLength=time;//타임은 초*100을 입력 받는다.
		start();
	}
	public void run()
	{
		i=0;
        try
        {
            AudioInputStream bgm = AudioSystem.getAudioInputStream(new File(bgmName));
            soundClip = AudioSystem.getClip();//클립에 할당해주기
            soundClip.open(bgm);//파일 열기
            soundClip.start();
	        while(true)
	        {
	        	i++;
	            try{
	                sleep(10);
	                if(i>=soundLength)
	                {
	                	soundClip.stop();//노래 멈추기
	                	soundClip.close();//노래 파일 닫기
	            		break;
	                }
	            }catch(InterruptedException e){}
	        }
        }
        catch (Exception ex)
        {
        	System.out.println("Error!!");
        }
    }
}
