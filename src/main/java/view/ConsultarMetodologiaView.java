package view;

import java.awt.Color;

import model.Metodologia;
import model.SnapshotRankingEmpresa;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;

import viewmodel.ConsultarMetodologiaViewM;

public class ConsultarMetodologiaView extends Dialog<ConsultarMetodologiaViewM> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	public ConsultarMetodologiaView(SimpleWindow owner, Metodologia metodologia) {
		super(owner, new ConsultarMetodologiaViewM(metodologia));
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		this.setTitle("Consultar Indicador");
		form.setLayout(new ColumnLayout(2));

		new Label(form).setText(
				"Metodologia: "
						+ this.getModelObject().getMetodologia().getNombre())
				.setBackground(Color.WHITE);

		Table<SnapshotRankingEmpresa> tableRankingEmpresa = new Table<SnapshotRankingEmpresa>(
				mainPanel, SnapshotRankingEmpresa.class);

		tableRankingEmpresa.setHeight(4000);
		tableRankingEmpresa.setWidth(500);

		Column<SnapshotRankingEmpresa> columnaNombreEmpresa = new Column<SnapshotRankingEmpresa>(
				tableRankingEmpresa);
		columnaNombreEmpresa.setTitle("Empresa").setWeight(140);
		columnaNombreEmpresa.bindContentsToProperty("nombreEmpresa");

		Table<SnapshotRankingEmpresa> tableRankingEmpresasFallidas = new Table<SnapshotRankingEmpresa>(
				mainPanel, SnapshotRankingEmpresa.class);

		tableRankingEmpresasFallidas.setHeight(4000);
		tableRankingEmpresasFallidas.setWidth(500);

		Column<SnapshotRankingEmpresa> columnaNombreEmpresasFallidas = new Column<SnapshotRankingEmpresa>(
				tableRankingEmpresasFallidas);
		columnaNombreEmpresasFallidas.setTitle("Empresa que fallaron")
				.setWeight(300);
		columnaNombreEmpresasFallidas.bindContentsToProperty("nombreEmpresa");

		Column<SnapshotRankingEmpresa> columnaObservacionEmpresasFallidas = new Column<SnapshotRankingEmpresa>(
				tableRankingEmpresasFallidas);
		columnaObservacionEmpresasFallidas.setTitle("Observacion").setWeight(
				140);
		columnaObservacionEmpresasFallidas
				.bindContentsToProperty("observacion");


		tableRankingEmpresasFallidas
				.bindItemsToProperty("snapshotRankingEmpresasFallidas");
	}

	@Override
	protected void addActions(Panel actions) {

		new Label(actions).setText("\t\t\t\t");
		new Button(actions).setCaption("Salir").onClick(this::cancel)
				.setWidth(140);
	}

}
