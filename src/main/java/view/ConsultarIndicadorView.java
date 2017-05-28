package view;

import model.SnapshotIndicador;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;

import viewmodel.ConsultarIndicadorViewM;

public class ConsultarIndicadorView extends Dialog<ConsultarIndicadorViewM> {

	@SuppressWarnings("rawtypes")
	public ConsultarIndicadorView(SimpleWindow owner) {
		super(owner, new ConsultarIndicadorViewM());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		this.setTitle("Consultar Indicador");
		form.setLayout(new ColumnLayout(4));
		
		new Label(form).setText("\t\tSeleccione Empresa");
		Selector<String> selectorNombre = new Selector<String>(form)
				.allowNull(true);
		selectorNombre.setWidth(150);
		selectorNombre.bindItemsToProperty("nombres");
		selectorNombre.bindValueToProperty("nombreSeleccionado");

		new Label(form).setText("\t\tSeleccione Año");
		Selector<Integer> selectorAño = new Selector<Integer>(form)
				.allowNull(true);
		selectorAño.setWidth(150);
		selectorAño.bindItemsToProperty("años");
		selectorAño.bindValueToProperty("añoSeleccionado");

		new Label(form).setText("\t\tSeleccione Semestre");
		Selector<Integer> selectorSemestre = new Selector<Integer>(form)
				.allowNull(true);
		selectorSemestre.setWidth(150);
		selectorSemestre.bindItemsToProperty("semestre");
		selectorSemestre.bindValueToProperty("semestreSeleccionado");
		
		new Label(form).setText("");
		new Button(form).setCaption("Reiniciar").onClick(this::reiniciar)
				.setWidth(140);
		
		Table<SnapshotIndicador> tableResultadoIndicador = new Table<SnapshotIndicador>(
				mainPanel, SnapshotIndicador.class);

		tableResultadoIndicador.setHeight(600);
		tableResultadoIndicador.setWidth(200);

		tableResultadoIndicador.bindItemsToProperty("snapshotIndicadores");
		tableResultadoIndicador.bindValueToProperty("snapshotIndicadorSeleccionado");

		Column<SnapshotIndicador> columnaNombre = new Column<SnapshotIndicador>(
				tableResultadoIndicador);
		columnaNombre.setTitle("Nombre");
		columnaNombre.bindContentsToProperty("nombre");

		Column<SnapshotIndicador> columnaAño = new Column<SnapshotIndicador>(
				tableResultadoIndicador);
		columnaAño.setTitle("Año");
		columnaAño.bindContentsToProperty("año");

		Column<SnapshotIndicador> columnaSemestre = new Column<SnapshotIndicador>(
				tableResultadoIndicador);
		columnaSemestre.setTitle("Semestre");
		columnaSemestre.bindContentsToProperty("semestre");

		Column<SnapshotIndicador> columnaValor = new Column<SnapshotIndicador>(
				tableResultadoIndicador);
		columnaValor.setTitle("Resultado");
		columnaValor.bindContentsToProperty("resultado");
	}

	@Override
	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Consultar")
				.onClick(this::consultarIndicador).setAsDefault().setWidth(140);
		new Button(actions).setCaption("Cancelar").onClick(this::cancel)
				.setWidth(140);
	}

	public void consultarIndicador() {
		//hacer las cuentas
		this.getModelObject().llenarTablas();
	}

	@Override
	public void cancel() {
		this.close();
	}
	
	public void reiniciar() {
		getModelObject().reiniciar();
	}

}
