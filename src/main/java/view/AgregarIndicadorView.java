package view;

import java.awt.Color;
import java.awt.TextArea;

import org.eclipse.jface.viewers.TextCellEditor;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;

import viewmodel.AgregarIndicadorViewM;
import viewmodel.IndicadorViewM;


public class AgregarIndicadorView extends Dialog<AgregarIndicadorViewM>{
	
	@SuppressWarnings("rawtypes")
	public AgregarIndicadorView(SimpleWindow owner) {
		super(owner, new AgregarIndicadorViewM());
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		this.setTitle("Agregar Indicador");
		form.setLayout(new ColumnLayout(4));
		
		new Label(form).setText("\t\tNombre: ");
//		Selector<String> selectorCuenta = new Selector<String>(form)
//				.allowNull(true);
//		selectorCuenta.setWidth(150);
//		selectorCuenta.bindItemsToProperty("cuentas");
//		selectorCuenta.bindValueToProperty("cuentaSeleccionada");

		new Label(form).setText("\t\tFormula: ");
//		Selector<Integer> selectorAño = new Selector<Integer>(form)
//				.allowNull(true);
//		selectorAño.setWidth(150);
//		selectorAño.bindItemsToProperty("años");
//		selectorAño.bindValueToProperty("añoSeleccionado");
		
		new TextArea();	
		
		
	
		new Button(form).setCaption("Guardar").onClick(this::guardarIndicador)
			.setWidth(140);

	}
	
	public void guardarIndicador(){
		//guardar el indicador en el archivo  y agregarlo en el repositorioIndicadores
	}

}
