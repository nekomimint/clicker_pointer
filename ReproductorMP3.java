package paquete_temp;
import javazoom.jlgui.basicplayer.BasicPlayer;
import java.io.File;
public class ReproductorMP3 {

	    public BasicPlayer player;

	    public ReproductorMP3() {
	        player = new BasicPlayer();
	    }
	public void coge(String y){

	}
	    public void Play() throws Exception {
	        player.play();
	    }

	    public void AbrirFichero(String ruta) throws Exception {
	        player.open(new File(ruta));
	    }

	    public void Pausa() throws Exception {
	        player.pause();
	    }

	    public void Continuar() throws Exception {
	        player.resume();
	    }

	    public void Stop() throws Exception {
	        player.stop();
	    }
	    
	    public void reproducemp3 () throws Exception{
	       try {
	    	   ReproductorMP3   mi_reproductor = new ReproductorMP3();
	            mi_reproductor.AbrirFichero("C:/Zelda Chill Zelda Chill 2.mp3");
	            mi_reproductor.Play();
	        } catch (Exception ex) {
	            System.out.println("Error: " + ex.getMessage());
	        }
	    }
	    public static void main(String args[]) throws Exception{
	    	ReproductorMP3 y = new ReproductorMP3();
	     y.AbrirFichero("C:/Zelda Chill Zelda Chill 2.mp3");
	     y.Play();
	    }
	
}
