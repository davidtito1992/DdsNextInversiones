package view;

import model.Indicador;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import viewmodel.IndicadorViewM;

@SuppressWarnings("serial")
public class IndicadorView extends Dialog<IndicadorViewM> {

	@SuppressWarnings("rawtypes")
	public IndicadorView(SimpleWindow owner) {
		super(owner, new IndicadorViewM());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		this.setTitle("Indicador");
		form.setLayout(new ColumnLayout(4));
		getModelObject().llenarTablas();

		new Label(form).setText("");
		new Button(form).setCaption("Agregar").onClick(this::agregarIndicador)
				.setWidth(140);
		new Label(form).setText("");
		new Button(form).setCaption("Consultar").onClick(this::consultar)
				.setWidth(140);

		Table<Indicador> tableIndicadores = new Table<Indicador>(
				mainPanel, Indicador.class);

		tableIndicadores.setHeight(600);
		tableIndicadores.setWidth(200);

		tableIndicadores.bindItemsToProperty("indicadores");
		tableIndicadores.bindValueToProperty("indicadorSeleccionado");

		Column<Indicador> columnaNombre = new Column<Indicador>(
				tableIndicadores);
		columnaNombre.setTitle("Nombre");
		columnaNombre.bindContentsToProperty("nombre");

		Column<Indicador> columnaFormula = new Column<Indicador>(
				tableIndicadores);
		columnaFormula.setTitle("Formula");
		columnaFormula.bindContentsToProperty("formula");

	}

	@Override
	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Aceptar").onClick(this::accept)
				.setAsDefault();
		new Button(actions).setCaption("Cancelar").onClick(this::cancel);
	}

	@Override
	protected void executeTask() {
		System.out.println("Que hacemos?:/");
		super.executeTask();
	}

	@Override
	public void cancel() {
		this.close();
	}

	public void agregarIndicador() {
	}

	public void consultar() {
	}

	
}
