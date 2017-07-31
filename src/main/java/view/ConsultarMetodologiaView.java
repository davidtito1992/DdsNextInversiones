package view;

import indicadoresCondicionados.RankingEmpresa;

import java.awt.Color;

import model.Metodologia;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
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
				"\tMetodologia: "
						+ this.getModelObject().getMetodologia().getNombre())
				.setBackground(Color.WHITE);

		new Label(form).setText("\n\n\n");
		new Label(form).setText("\tSeleccione Empresa");
		Selector<String> selectorNombre = new Selector<String>(form)
				.allowNull(true);
		selectorNombre.setWidth(150);
		selectorNombre.bindItemsToProperty("nombresEmpresas");
		selectorNombre.bindValueToProperty("nombreEmpresaSeleccionado");

		new Label(form).setText("\t");
		new Label(form).setText("\t");
		new Button(form).setCaption("Reiniciar").onClick(this::reiniciar)
				.setWidth(140);

		new Button(form).setCaption("Buscar").onClick(this::buscar)
				.setWidth(140);

		Table<RankingEmpresa> tableRankingEmpresa = new Table<RankingEmpresa>(
				mainPanel, RankingEmpresa.class);

		tableRankingEmpresa.setHeight(4000);
		tableRankingEmpresa.setWidth(1000);

		Column<RankingEmpresa> columnaRankingEmpresa = new Column<RankingEmpresa>(
				tableRankingEmpresa);
		columnaRankingEmpresa.setTitle("Ranking").setWeight(60);
		columnaRankingEmpresa.bindContentsToProperty("ranking");

		Column<RankingEmpresa> columnaNombreEmpresa = new Column<RankingEmpresa>(
				tableRankingEmpresa);
		columnaNombreEmpresa.setTitle("Empresa").setWeight(140);
		columnaNombreEmpresa.bindContentsToProperty("nombre");

		tableRankingEmpresa.bindItemsToProperty("rankingDeEmpresas");
		tableRankingEmpresa.bindValueToProperty("rankingDeEmpresaSeleccionado");

	}

	@Override
	protected void addActions(Panel actions) {

		new Label(actions).setText("\t\t\t\t");
		new Button(actions).setCaption("Salir").onClick(this::cancel)
				.setWidth(140);
	}

	public void reiniciar() {
		getModelObject().reiniciar();
	}

	public void buscar() {
		if (this.getModelObject().getNombreEmpresaSeleccionado() != null) {
			this.getModelObject().buscar();

		} else {
			this.showError("Seleccione algun item por favor");
		}

	}
}
