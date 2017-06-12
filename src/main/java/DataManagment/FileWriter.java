package DataManagment;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileWriter {

	private String nombreruta ;
	private String textoViejo ;
	private String textoNuevo ;

	public FileWriter(String nombreruta,String textoViejo,String textoNuevo) throws Exception{
		this.nombreruta = nombreruta ;
		this.textoViejo= textoViejo ;
		this.textoNuevo = textoNuevo ;

		this.realizarOperacion();

	}


	private void reemplazarTexto() throws Exception{
		BufferedReader file = new BufferedReader(new FileReader(nombreruta));
		String line;
		String input = "";

		while((line = file.readLine())!= null){

			/* ENCONTRAMOS LA ETIQUETA DE FINALIZACION Y REEMPLAZAMOS STRING */
			if(line.contains(this.textoViejo)){
				input += line.replaceAll(this.textoViejo,"},\n"+this.textoNuevo);
			}
			else if (line.contains("[]")){
				input += line.replaceAll("]", this.textoNuevo);
			}else
				input += line+"\r\n";
		}
		this.escribir(input);

	}

	private boolean eliminarTexto()  throws Exception{

		boolean finalArchivo= false ; 
		BufferedReader file = new BufferedReader(new FileReader(nombreruta));
		String line;
		String input = "";
		boolean escribio=false ;
		while((line = file.readLine()) != null){
			if(line.contains("["+ this.textoViejo +"]")){   
				input += line.replace(this.textoViejo,"");
				escribio=true ;
			}else if(line.contains(this.textoViejo+",")){
				input += line.replace(this.textoViejo + ",",this.textoNuevo);
				escribio=true ;	          
			}else if (line.contains(this.textoViejo+"]")){
				finalArchivo = true ;
				input += line.replace(line.toString(),"");
				escribio=true ;

			}else{            
				input += line+"\r\n";
			}
		}
		this.escribir(input);
		if (escribio==false){
			throw new Exception();
		}
		return finalArchivo ;
	}

	private void realizarOperacion()throws Exception{
		if (this.textoNuevo == ""){

			boolean finalArchivo =  this.eliminarTexto() ;

			if (finalArchivo){
				BufferedReader file = new BufferedReader(new FileReader(nombreruta));
				String line;
				String input = "";
				int ultimaLinea = (int) file.lines().count() ;
				int cont=0 ;
				file =  new BufferedReader(new FileReader(nombreruta));
				while((line = file.readLine()) != null){
					cont ++;
					if(cont==ultimaLinea){
						input += line.replaceAll("},", "}]");
			}else
				input += line+"\r\n";

				}
				this.escribir(input);
			}

		}else{
			//agregartexto
			this.reemplazarTexto();	

		}
	}
	private void escribir(String input) throws IOException{
		FileOutputStream fileOut = new FileOutputStream(this.nombreruta);
		fileOut.write(input.getBytes());
		fileOut.close();
	}
} 