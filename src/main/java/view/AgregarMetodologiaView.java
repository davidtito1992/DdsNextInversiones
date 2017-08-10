package view;

import model.SnapshotCondicion;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import viewmodel.AgregarMetodologiaViewM;

public class AgregarMetodologiaView extends Dialog<AgregarMetodologiaViewM> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	public AgregarMetodologiaView(SimpleWindow owner) {
		super(owner, new AgregarMetodologiaViewM());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		this.setTitle("Agregar Metodologia");
		form.setLayout(new ColumnLayout(2));

		new Label(form).setText("\tNombre:");
		new TextBox(form).setWidth(250).bindValueToProperty("nombre");
		new Label(form)
				.setText("\t\t--------------------------------------------");
		new Label(form).setText("--------------------------------------------");

		new Label(form).setText("\tIndicadores: ");
		Selector<String> selectorIndicador = new Selector<String>(form)
				.allowNull(true);
		selectorIndicador.setWidth(150);
		selectorIndicador.bindItemsToProperty("agregarIndicador");
		selectorIndicador.bindValueToProperty("agregarIndicadorSeleccionado");

		new Label(form).setText("\tTipoCondicion: ");
		Selector<String> selectorTipo = new Selector<String>(form)
				.allowNull(true);
		selectorTipo.setWidth(150);
		selectorTipo.bindItemsToProperty("tiposCondiciones");
		selectorTipo.bindValueToProperty("tipoCondicionSeleccionado");

		new Label(form).setText("\tCondicion: ");
		Selector<String> selectorCondicion = new Selector<String>(form)
				.allowNull(true);
		selectorCondicion.setWidth(150);
		selectorCondicion.bindItemsToProperty("agregarCriterio");
		selectorCondicion.bindValueToProperty("agregarCriterioSeleccionado");

		new Label(form).setText("\tPeso/Comparar: ");
		new TextBox(form).setWidth(250).bindValueToProperty("pesoOComparar");

		new Label(form).setText("\tUltimos años: ");
		Selector<String> selectorAnios = new Selector<String>(form)
				.allowNull(true);
		selectorAnios.setWidth(150);
		selectorAnios.bindItemsToProperty("agregarAnios");
		selectorAnios.bindValueToProperty("agregarAniosSeleccionado");

		new Label(form).setText("\t");
		
		new Button(form).setCaption("Agregar").onClick(this::agregarCondicion)
				.setWidth(140);
		new Label(form).setText("\t");
		
		new Button(form).setCaption("Limpiar").onClick(this::limpiar)
				.setWidth(140);

		new Label(form)
				.setText("\t\t--------------------------------------------");
		new Label(form).setText("--------------------------------------------");

		Table<SnapshotCondicion> tableCondicion = new Table<SnapshotCondicion>(
				mainPanel, SnapshotCondicion.class);

		Column<SnapshotCondicion> columnaIndicador = new Column<SnapshotCondicion>(
				tableCondicion);
		columnaIndicador.setTitle("Indicador").setWeight(250);
		columnaIndicador.bindContentsToProperty("indicador");

		Column<SnapshotCondicion> columnaTipoCondicion = new Column<SnapshotCondicion>(
				tableCondicion);
		columnaTipoCondicion.setTitle("TipoCondicion").setWeight(60);
		columnaTipoCondicion.bindContentsToProperty("tipoCondicion");

		Column<SnapshotCondicion> columnaNombreCondicion = new Column<SnapshotCondicion>(
				tableCondicion);
		columnaNombreCondicion.setTitle("Condicion").setWeight(230);
		columnaNombreCondicion.bindContentsToProperty("nombreCondicion");

		Column<SnapshotCondicion> columnaPesoOComparar = new Column<SnapshotCondicion>(
				tableCondicion);
		columnaPesoOComparar.setTitle("PesoOComparar").setWeight(80);
		columnaPesoOComparar.bindContentsToProperty("pesoOComparar");

		Column<SnapshotCondicion> columnaAnios = new Column<SnapshotCondicion>(
				tableCondicion);
		columnaAnios.setTitle("años").setWeight(80);
		columnaAnios.bindContentsToProperty("anios");

		tableCondicion.bindItemsToProperty("snapshotCondiciones");
		tableCondicion.bindValueToProperty("snapshotCondicionSeleccionado");

	}

	@Override
	protected void addActions(Panel actions) {

		new Label(actions).setText("\t\t\t\t\t");

		new Button(actions).setCaption("Guardar").onClick(this::guardar)
				.setAsDefault().setWidth(140);

		new Button(actions).setCaption("Cancelar").onClick(this::cancel)
				.setWidth(140);

	}

	public void agregarCondicion() {
		try{
			this.getModelObject().validar();
			getModelObject().agregarCondicion();
		}catch (RuntimeException e){
			this.showInfo(e.getMessage());
		}
	}

	protected void guardar() {

		try {
			getModelObject().guardarMetodologia();
			this.close();
		} catch (Exception e) {
			this.showError(e.getMessage());
		}

	}

	public void limpiar() {

		this.getModelObject().limpiar();
	}

}
