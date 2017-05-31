package view;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import viewmodel.AgregarIndicadorViewM;


public class AgregarIndicadorView extends Dialog<AgregarIndicadorViewM> {

	@SuppressWarnings("rawtypes")
	public AgregarIndicadorView(SimpleWindow owner) {
		super(owner, new AgregarIndicadorViewM());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		this.setTitle("Agregar Indicador");
		form.setLayout(new ColumnLayout(2));

		new Label(form).setText("\t\t\t\tNombre:");
		new TextBox(form).setWidth(250).bindValueToProperty("nombre");

		new Label(form).setText("\t\t\t\tFormula:");
		new TextBox(form).setWidth(250).bindValueToProperty("formula");

	}

	@Override
	protected void addActions(Panel actions) {

		new Label(actions).setText("\t\t\t\t\t");
		new Button(actions).setCaption("Guardar")
				.onClick(this::guardarIndicador).setAsDefault().setWidth(140);
		new Button(actions).setCaption("Cancelar").onClick(this::cancel)
				.setWidth(140);
	}

	public void guardarIndicador() {
		// guardar el indicador en el archivo y agregarlo en el
		// repositorioIndicadores
	}

	@Override
	public void cancel() {
		this.close();
	}

}
