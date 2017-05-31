package DataManagment;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

public class FileWriter {
	
	private String nombreruta ;
	private String textoViejo ;
	private String textoNuevo ;
	
	public FileWriter(String nombreruta,String textoViejo,String textoNuevo) throws Exception{
	this.nombreruta = nombreruta ;
	this.textoViejo= textoViejo ;
	this.textoNuevo = textoNuevo ;
	this.reemplazarTexto();
	}
	
	private void reemplazarTexto() throws Exception{
	BufferedReader file = new BufferedReader(new FileReader(nombreruta));
    String line;
    String input = "";
    while((line = file.readLine()) != null){
        /* ENCONTRAMOS LA ETIQUETA DE FINALIZACION Y REEMPLAZAMOS STRING */
        if(line.contains(this.textoViejo))
            input += line.replaceAll(this.textoViejo, this.textoNuevo);
        else
            input += line+"\r\n";
    }
    FileOutputStream fileOut = new FileOutputStream(nombreruta);
    fileOut.write(input.getBytes());
    fileOut.close();
    }
//\n"+nuevoIndicadorString +"]\r\n"

}