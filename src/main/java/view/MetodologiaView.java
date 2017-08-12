package view;

import model.Metodologia;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import viewmodel.MetodologiaViewM;

@SuppressWarnings("serial")
public class MetodologiaView extends Dialog<MetodologiaViewM> {

	@SuppressWarnings("rawtypes")
	public MetodologiaView(SimpleWindow owner) {
		super(owner, new MetodologiaViewM());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		this.setTitle("Metodologia");
		form.setLayout(new ColumnLayout(4));

		new Button(form).setCaption("Agregar")
				.onClick(this::agregarMetodologia).setWidth(140);
		new Button(form).setCaption("Consultar").onClick(this::consultar)
				.setWidth(140);
		new Button(form).setCaption("Borrar").onClick(this::borrar)
				.setWidth(140);

		Table<Metodologia> tableMetodologias = new Table<Metodologia>(
				mainPanel, Metodologia.class);

		tableMetodologias.setHeight(600);
		tableMetodologias.setWidth(200);

		tableMetodologias.bindItemsToProperty("metodologias");
		tableMetodologias.bindValueToProperty("metodologiaSeleccionada");

		Column<Metodologia> columnaNombre = new Column<Metodologia>(
				tableMetodologias);
		columnaNombre.setTitle("Nombre");
		columnaNombre.bindContentsToProperty("nombre");
	}

	@Override
	protected void addActions(Panel actions) {

		new Button(actions).setCaption("Salir").onClick(this::cancel)
				.setWidth(140);
	}

	@Override
	public void cancel() {
		this.close();
	}

	public void agregarMetodologia() {
		// abrir ventana AgregarMetodologiaView
		try {
			System.out.println("Accediendo para ver las metodologias...");

			Dialog<?> dialog = new AgregarMetodologiaView(this);
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

		if (this.getModelObject().getMetodologiaSeleccionada() != null) {
			System.out.println("Accediendo para consultar las metodologias...");

			Dialog<?> dialog = new ConsultarMetodologiaView(this, this
					.getModelObject().getMetodologiaSeleccionada());
			dialog.open();
			dialog.onAccept(() -> {
			});
		} else {

			this.showError("Seleccione una Metodologia, por favor");
		}
	}

	public void borrar() {
		Metodologia metSelec = this.getModelObject()
				.getMetodologiaSeleccionada();
		if (this.getModelObject().getMetodologiaSeleccionada() != null) {

			Dialog<?> dialog = new BorrarMetodologiaView(this, metSelec);
			dialog.open();
			dialog.onAccept(() -> {
			});

			this.getModelObject().llenarTablas();

		} else {

			this.showError("Seleccione una Metodologia, por favor");
		}

	}
}
