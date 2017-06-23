package view;

import java.awt.Color;

import model.RegistroIndicador;
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
import parserIndicador.ParseException;
import parserIndicador.ParserIndicador;
import viewmodel.ConsultarIndicadorViewM;

public class ConsultarIndicadorView extends Dialog<ConsultarIndicadorViewM> {

	@SuppressWarnings("rawtypes")
	public ConsultarIndicadorView(SimpleWindow owner,
			RegistroIndicador unIndicador) {
		super(owner, new ConsultarIndicadorViewM(unIndicador));
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		this.setTitle("Consultar Indicador");
		form.setLayout(new ColumnLayout(2));

		new Label(form).setText(
				"\tIndicador: "
						+ this.getModelObject().getRegistroIndicadorElegido()
								.getNombre()
						+ "\t\t\tFormula: "
						+ this.getModelObject().getRegistroIndicadorElegido()
								.getFormula()).setBackground(Color.WHITE);

		new Label(form).setText("\n\n\n");
		new Label(form).setText("\tSeleccione Empresa");
		Selector<String> selectorNombre = new Selector<String>(form)
				.allowNull(true);
		selectorNombre.setWidth(150);
		selectorNombre.bindItemsToProperty("nombres");
		selectorNombre.bindValueToProperty("nombreSeleccionado");

		new Label(form).setText("\tSeleccione año");
		Selector<Integer> selectorAnio = new Selector<Integer>(form)
				.allowNull(true);
		selectorAnio.setWidth(150);
		selectorAnio.bindItemsToProperty("anios");
		selectorAnio.bindValueToProperty("anioSeleccionado");

		new Label(form).setText("\tSeleccione Semestre");
		Selector<Integer> selectorSemestre = new Selector<Integer>(form)
				.allowNull(true);
		selectorSemestre.setWidth(150);
		selectorSemestre.bindItemsToProperty("semestre");
		selectorSemestre.bindValueToProperty("semestreSeleccionado");
		new Label(form).setText("\t");
		new Label(form).setText("\t");
		new Button(form).setCaption("Reiniciar").onClick(this::reiniciar)
				.setWidth(140);

		new Button(form).setCaption("Buscar").onClick(this::buscar)
				.setWidth(140);

		this.inicializarTabla();

		Table<SnapshotIndicador> tableIndicador = new Table<SnapshotIndicador>(
				mainPanel, SnapshotIndicador.class);

		tableIndicador.setHeight(4000);
		tableIndicador.setWidth(1000);

		Column<SnapshotIndicador> columnaNombreEmpresa = new Column<SnapshotIndicador>(
				tableIndicador);
		columnaNombreEmpresa.setTitle("Empresa").setWeight(120);
		columnaNombreEmpresa.bindContentsToProperty("nombreEmpresa");

		Column<SnapshotIndicador> columnaAnio = new Column<SnapshotIndicador>(
				tableIndicador);
		columnaAnio.setTitle("Año").setWeight(60);
		columnaAnio.bindContentsToProperty("anio");

		Column<SnapshotIndicador> columnaSemestre = new Column<SnapshotIndicador>(
				tableIndicador);
		columnaSemestre.setTitle("Semestre").setWeight(100);
		columnaSemestre.bindContentsToProperty("semestre");

		Column<SnapshotIndicador> columnaCuenta = new Column<SnapshotIndicador>(
				tableIndicador);
		columnaCuenta.setTitle("Resultado").setWeight(400);
		columnaCuenta.bindContentsToProperty("resultado");

		tableIndicador.bindItemsToProperty("snapshotIndicadores");
		tableIndicador.bindValueToProperty("snapshotIndicadorSeleccionado");

	}

	@Override
	protected void addActions(Panel actions) {

		new Label(actions).setText("\t\t\t\t\t\t\t\t");
		new Button(actions).setCaption("Salir").onClick(this::cancel)
				.setWidth(140);
	}

	public void reiniciar() {
		getModelObject().reiniciar();
	}

	public void buscar() {
		if (this.getModelObject().getNombreSeleccionado() != null
				&& this.getModelObject().getSemestreSeleccionado() != null
				&& this.getModelObject().getAnioSeleccionado() != null) {

			this.getModelObject().buscar();

		} else {
			this.showError("Seleccione todos los campos, por favor");
		}

	}

	public void inicializarTabla() {
		try {
			ParserIndicador preIndicador = new ParserIndicador(this
					.getModelObject().getRegistroIndicadorElegido()
					.getFormula());
			this.getModelObject().setFormulaIndicador(preIndicador.pasear());
		} catch (ParseException e) {

			this.showError("No se pudo parsear correctamente la formula del indicador");
			this.close();
		}

		this.getModelObject().llenarTablas();

	}
}
