package up5.l3x2.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class LectureIncoherenteExeption extends IOException{
	
	public LectureIncoherenteExeption(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
	public LectureIncoherenteExeption() {
		// TODO Auto-generated constructor stub
		super("Lecture Incohérente vérifiez le contenu des fichiers");
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
	

}
