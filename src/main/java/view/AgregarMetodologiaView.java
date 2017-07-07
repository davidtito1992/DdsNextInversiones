package view;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
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

		new Label(form).setText("\t\t\t\tNombre:");
		new TextBox(form).setWidth(250).bindValueToProperty("nombre");

		new Label(form).setText("Condiciones Taxativas");

		new Label(form).setText("\t\tIndicadores: ");
		Selector<String> selectorIndicador = new Selector<String>(form)
				.allowNull(true);
		selectorIndicador.setWidth(150);
		selectorIndicador.bindItemsToProperty("agregarIndicador");
		selectorIndicador.bindValueToProperty("agregarIndicadorSeleccionado");
		new Label(form).setText("\t\t");

		new Label(form).setText("\t\tTipo: ");
		Selector<String> selectorTipo = new Selector<String>(form)
				.allowNull(true);
		selectorTipo.setWidth(150);
		selectorTipo.bindItemsToProperty("agregarCriterio");
		selectorTipo.bindValueToProperty("agregarCriterioSeleccionado");
		new Label(form).setText("\t\t");

		new Label(form).setText("\t\tComparar con: ");
		Selector<String> selectorNro = new Selector<String>(form)
				.allowNull(true);
		selectorNro.setWidth(150);
		selectorNro.bindItemsToProperty("agregarNro");
		selectorNro.bindValueToProperty("agregarNroSeleccionado");
		new Label(form).setText("\t\t");

		new Label(form).setText("\t\tComparar con: ");
		Selector<String> selectorAnios = new Selector<String>(form)
				.allowNull(true);
		selectorAnios.setWidth(150);
		selectorAnios.bindItemsToProperty("agregarAnios");
		selectorAnios.bindValueToProperty("agregarAniosSeleccionado");
		new Label(form).setText("\t\t");

		new Button(form).setCaption("Agregar Condicion")
				.onClick(this::agregarTaxativa).setWidth(140);

		new Label(form).setText("\t\t\t\tFormula:");
		new TextBox(form).setWidth(250).bindValueToProperty("formula");

		new Label(form).setText("\t");
	}

	@Override
	protected void addActions(Panel actions) {

		new Label(actions).setText("\t\t\t\t\t");

		new Button(actions).setCaption("Guardar").onClick(this::guardar)
				.setAsDefault().setWidth(140);

		new Button(actions).setCaption("Cancelar").onClick(this::cancel)
				.setWidth(140);

	}

	public void agregarTaxativa() {
		getModelObject().agregarCondicionTaxativa();
	}

	protected void guardar() {

		// try {
		// getModelObject().guardarMetodologia();
		// this.close();
		// } catch (Exception e) {
		// this.showError(e.getMessage());
		// }

	}
}
