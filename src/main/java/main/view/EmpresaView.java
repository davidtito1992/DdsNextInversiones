package main.view;

import main.viewmodel.EmpresaViewM;
import model.SnapshotEmpresa;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;

@SuppressWarnings("serial")
public class EmpresaView extends Dialog<EmpresaViewM> {

	@SuppressWarnings("rawtypes")
	public EmpresaView(SimpleWindow owner) {

		super(owner, new EmpresaViewM());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		this.setTitle("Empresas");
		form.setLayout(new ColumnLayout(4));
		// getModelObject().llenarTablas();

		// new
		// Label(form).setText("\nFILTROS\n").setBackground(Color.LIGHT_GRAY).setWidth(300);

		new Label(form).setText("\t\tSeleccione Empresa");
		Selector<String> selectorNombre = new Selector<String>(form)
				.allowNull(true);
		selectorNombre.setWidth(150);
		selectorNombre.bindItemsToProperty("nombres");
		selectorNombre.bindValueToProperty("nombreSeleccionado");

		new Label(form).setText("\t\tSeleccione Cuenta");
		Selector<String> selectorCuenta = new Selector<String>(form)
				.allowNull(true);
		selectorCuenta.setWidth(150);

		selectorCuenta.bindItemsToProperty("cuentas");
		selectorCuenta.bindValueToProperty("cuentaSeleccionada");

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

		new Label(form).setText("");
		new Button(form).setCaption("Buscar").onClick(this::buscar)
				.setWidth(140);
		new Label(form).setText("");
		new Button(form).setCaption("Reiniciar").onClick(this::reiniciar)
				.setWidth(140);

		Table<SnapshotEmpresa> tableEmpresas = new Table<SnapshotEmpresa>(
				mainPanel, SnapshotEmpresa.class);

		tableEmpresas.setHeight(600);
		tableEmpresas.setWidth(200);

		tableEmpresas.bindItemsToProperty("snapshotEmpresas");
		tableEmpresas.bindValueToProperty("snapshotEmpresaSeleccionada");

		Column<SnapshotEmpresa> columnaNombre = new Column<SnapshotEmpresa>(
				tableEmpresas);
		columnaNombre.setTitle("Nombre");
		columnaNombre.bindContentsToProperty("nombre");

		Column<SnapshotEmpresa> columnaAnio = new Column<SnapshotEmpresa>(
				tableEmpresas);
		columnaAnio.setTitle("Anio");
		columnaAnio.bindContentsToProperty("anio");

		Column<SnapshotEmpresa> columnaSemestre = new Column<SnapshotEmpresa>(
				tableEmpresas);
		columnaSemestre.setTitle("Semestre");
		columnaSemestre.bindContentsToProperty("semestre");

		Column<SnapshotEmpresa> columnaCuenta = new Column<SnapshotEmpresa>(
				tableEmpresas);
		columnaCuenta.setTitle("Cuenta");
		columnaCuenta.bindContentsToProperty("cuenta");

		Column<SnapshotEmpresa> columnaValor = new Column<SnapshotEmpresa>(
				tableEmpresas);
		columnaValor.setTitle("Valor");
		columnaValor.bindContentsToProperty("valor");

	}

	@Override
	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Salir").onClick(this::cancel)
				.setWidth(140);
	}

	// @Override
	// protected void executeTask() {
	// System.out.println("Que hacemos?:/");
	// super.executeTask();
	// }

	@Override
	public void cancel() {
		this.close();
	}

	public void buscar() {
		getModelObject().filtrar();
	}

	public void reiniciar() {
		getModelObject().reiniciar();
	}

}