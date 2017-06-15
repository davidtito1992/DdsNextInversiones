package view;

import model.RegistroIndicador;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;

import parserIndicador.ParseException;
import viewmodel.ConsultarIndicadorViewM;

public class ConsultarIndicadorView extends Dialog<ConsultarIndicadorViewM> {

	@SuppressWarnings("rawtypes")
	public ConsultarIndicadorView(SimpleWindow owner,RegistroIndicador unIndicador) {
		super(owner, new ConsultarIndicadorViewM(unIndicador));
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		this.setTitle("Consultar Indicador");
		form.setLayout(new ColumnLayout(2));
		//this.getModelObject().llenarTablas();
		
		new Label(form).setText("\t\tSeleccione Empresa");
		Selector<String> selectorNombre = new Selector<String>(form)
				.allowNull(true);
		selectorNombre.setWidth(150);
		selectorNombre.bindItemsToProperty("nombres");
		selectorNombre.bindValueToProperty("nombreSeleccionado");

		new Label(form).setText("\t\tSeleccione Anio");
		Selector<Integer> selectorAnio = new Selector<Integer>(form)
				.allowNull(true);
		selectorAnio.setWidth(150);
		selectorAnio.bindItemsToProperty("anios");
		selectorAnio.bindValueToProperty("anioSeleccionado");

		new Label(form).setText("\t\tSeleccione Semestre");
		Selector<Integer> selectorSemestre = new Selector<Integer>(form)
				.allowNull(true);
		selectorSemestre.setWidth(150);
		selectorSemestre.bindItemsToProperty("semestre");
		selectorSemestre.bindValueToProperty("semestreSeleccionado");

		new Label(form).setText("\t\t");
		new Label(form).setText("\t\t");
		new Label(form).setText("\t\tResultado: \t\t\t\t") ;
		new Label(form).bindValueToProperty("resultado") ;

		new Label(form).setText("\t\t");
		new Label(form).setText("\t\t");
		new Label(form).setText("\t\t");
		new Button(form).setCaption("Consultar").onClick(this::consultar)
		.setWidth(140);
        new Label(form).setText("\t\t");
        new Button(form).setCaption("Reiniciar").onClick(this::reiniciar)
		.setWidth(140);
		new Label(form).setText("\t\t");
        new Button(form).setCaption("Cancelar").onClick(this::cancel)
        .setWidth(140);
	}

	@Override
	protected void addActions(Panel actions) {
	}
	
	public void reiniciar() {
		getModelObject().reiniciar();
	}

	public void consultar() {
		if (this.getModelObject().getNombreSeleccionado()!=null && this.getModelObject().getSemestreSeleccionado() !=null && this.getModelObject().getAnioSeleccionado()!=null){
			try {
				this.getModelObject().consultar();
			} catch (ParseException e) {
				showInfo(e.getMessage());
			} catch (Error e){
				showInfo("Hubo un error al consultar las cuentas en el período deseado, por favor verificar que las cuentas que utiliza el indicador sean validas.");
			}			
		}else {
			this.showError("Seleccione todos los campos, por favor");
		}

	}
	
}
