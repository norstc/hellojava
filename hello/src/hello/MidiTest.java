package hello;
import javax.sound.midi.*;

public class MidiTest {
	
	public void play(){
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			System.out.println("we got a sequencer");
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			System.out.println("what ever");
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MidiTest mt= new MidiTest();
		mt.play();

	}

}
