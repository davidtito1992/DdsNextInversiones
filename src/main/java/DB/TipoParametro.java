package DB;

public enum TipoParametro { 
	integer(1), string(2), timestamp(3);
	
	private int valor;

	TipoParametro(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}