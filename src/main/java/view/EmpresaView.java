package view;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import model.*;
import viewmodel.*;

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
		//getModelObject().llenarTablas();

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

		Column<SnapshotEmpresa> columnaAño = new Column<SnapshotEmpresa>(
				tableEmpresas);
		columnaAño.setTitle("Año");
		columnaAño.bindContentsToProperty("año");

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

	public void buscar() {
		getModelObject().filtrar();
	}

	public void reiniciar() {
		getModelObject().reiniciar();
	}

}
