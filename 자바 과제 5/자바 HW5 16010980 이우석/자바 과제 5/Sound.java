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
		soundLength=time;//Ÿ���� ��*100�� �Է� �޴´�.
		start();
	}
	public void run()
	{
		i=0;
        try
        {
            AudioInputStream bgm = AudioSystem.getAudioInputStream(new File(bgmName));
            soundClip = AudioSystem.getClip();//Ŭ���� �Ҵ����ֱ�
            soundClip.open(bgm);//���� ����
            soundClip.start();
	        while(true)
	        {
	        	i++;
	            try{
	                sleep(10);
	                if(i>=soundLength)
	                {
	                	soundClip.stop();//�뷡 ���߱�
	                	soundClip.close();//�뷡 ���� �ݱ�
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
