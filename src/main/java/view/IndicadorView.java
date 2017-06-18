package view;

import model.RegistroIndicador;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
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

		new Button(form).setCaption("Agregar").onClick(this::agregarIndicador)
				.setWidth(140);
		new Button(form).setCaption("Consultar").onClick(this::consultar)
				.setWidth(140);
		new Button(form).setCaption("Borrar").onClick(this::borrar)
				.setWidth(140);

		Table<RegistroIndicador> tableIndicadores = new Table<RegistroIndicador>(
				mainPanel, RegistroIndicador.class);

		tableIndicadores.setHeight(600);
		tableIndicadores.setWidth(200);

		tableIndicadores.bindItemsToProperty("indicadores");
		tableIndicadores.bindValueToProperty("indicadorSeleccionado");

		Column<RegistroIndicador> columnaNombre = new Column<RegistroIndicador>(
				tableIndicadores);
		columnaNombre.setTitle("Nombre");
		columnaNombre.bindContentsToProperty("nombre");

		Column<RegistroIndicador> columnaFormula = new Column<RegistroIndicador>(
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
		// abrir ventana AgregarIndicadorView
		try {
			System.out.println("Accediendo para ver las indicadores...");

			Dialog<?> dialog = new AgregarIndicadorView(this);
			dialog.open();
			dialog.onAccept(() -> {
			});

			this.getModelObject().llenarTablas();
		} catch (Exception e) {
			showInfo(e.getMessage());
		}
	}

	public void consultar() {
		// abrir ventana ConsultarIndicadorView

		if (this.getModelObject().getIndicadorSeleccionado() != null) {
			System.out.println("Accediendo para consultar los indicadores...");

			Dialog<?> dialog = new ConsultarIndicadorView(this, this
					.getModelObject().getIndicadorSeleccionado());
			dialog.open();
			dialog.onAccept(() -> {
			});
		} else {

			this.showError("Seleccione un Indicador, por favor");
		}
	}

	public void borrar() {
		// repositorioIndicadores
		if (this.getModelObject().getIndicadorSeleccionado() != null) {

			Dialog<?> dialog = new BorrarIndicadorView(this, this
					.getModelObject().getIndicadorSeleccionado());
			dialog.open();
			dialog.onAccept(() -> {
			});
			this.getModelObject().llenarTablas();

		} else {

			this.showError("Seleccione un Indicador, por favor");
		}

	}
}