package view;

import java.awt.Color;
import java.sql.Date;

import model.SnapshotEmpresa;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;

import viewmodel.*;

public class EmpresaView extends Dialog<EmpresaViewM> {

	public EmpresaView(SimpleWindow owner) {
		
			super(owner, new EmpresaViewM());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		this.setTitle("Empresas");
		form.setLayout(new ColumnLayout(4));
		
		new Label(form).setText("Nombre de Empresa");
		new TextBox(form).setWidth(150).bindValueToProperty("nombre");	
		
		new Label(form).setText("\t\t\t\tCuenta");
		new TextBox(form).setWidth(150).bindValueToProperty("cuenta");

		new Label(form).setText("Seleccione Año");
		Selector<Integer> selectorAño = new Selector<Integer>(form).allowNull(true);
		selectorAño.setWidth(150) ;
		selectorAño.bindItemsToProperty("años");
		selectorAño.bindValueToProperty("añoSeleccionado");

		new Label(form).setText("\t\tSeleccione Semestre");
		Selector<Integer> selectorSemestre = new Selector<Integer>(form).allowNull(true);
		selectorSemestre.setWidth(150) ;
		selectorSemestre.bindItemsToProperty("semestre");
		selectorSemestre.bindValueToProperty("semestreSeleccionado");
		
		new Label(form).setText("");
		new Button(form).setCaption("Buscar").onClick(this::buscar).setWidth(140);
		
		Table<SnapshotEmpresa> tableEmpresas = new Table<SnapshotEmpresa>(mainPanel, SnapshotEmpresa.class);

		tableEmpresas.setHeight(600);
		tableEmpresas.setWidth(200);
//
//		tableTarea.bindItemsToProperty("empresa");
//		tableTarea.bindValueToProperty("empresaSeleccionada");

		Column<SnapshotEmpresa> columnaNombre = new Column<SnapshotEmpresa>(tableEmpresas);
		columnaNombre.setTitle("Nombre");
//		columnaId.bindContentsToProperty("nombre");
		

		Column<SnapshotEmpresa> columnaAño = new Column<SnapshotEmpresa>(tableEmpresas);
		columnaAño.setTitle("Año");
//		columnaTitulo.bindContentsToProperty("fecha");
		Column<SnapshotEmpresa> columnaSemestre = new Column<SnapshotEmpresa>(tableEmpresas);
		columnaSemestre.setTitle("Semestre");
		Column<SnapshotEmpresa> columnaEbitda = new Column<SnapshotEmpresa>(tableEmpresas);
		columnaEbitda.setTitle("Ebitda");
//		columnaDescripcion.bindContentsToProperty("ebitda");

		Column<SnapshotEmpresa> columnaFDS = new Column<SnapshotEmpresa>(tableEmpresas);
		columnaFDS.setTitle("FDS") ;
//		columnaDescripcion.bindContentsToProperty("fds");
		
		Column<SnapshotEmpresa> columnaFreeCashFlow = new Column<SnapshotEmpresa>(tableEmpresas);
		columnaFreeCashFlow.setTitle("FreeCashFlow");
//		columnaDescripcion.bindContentsToProperty("freeCashFlow");
		
		Column<SnapshotEmpresa> columnaNetoDiscontinuas = new Column<SnapshotEmpresa>(tableEmpresas);
		columnaNetoDiscontinuas.setTitle("NetoDiscontinuas");
		
		Column<SnapshotEmpresa> columnaNetoContinuas = new Column<SnapshotEmpresa>(tableEmpresas);
		columnaNetoContinuas.setTitle("NetoContinuas");
		
	}

	@Override
	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Aceptar").onClick(this::accept).setAsDefault();
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
	
	public void buscar(){
		
	}


}

