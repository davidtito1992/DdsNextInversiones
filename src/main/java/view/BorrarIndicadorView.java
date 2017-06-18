package view;

import model.RegistroIndicador;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import viewmodel.BorrarIndicadorViewM;

public class BorrarIndicadorView extends Dialog<BorrarIndicadorViewM> {

	@SuppressWarnings("rawtypes")
	public BorrarIndicadorView(SimpleWindow owner,
			RegistroIndicador indicadorABorrar) {
		super(owner, new BorrarIndicadorViewM(indicadorABorrar));
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		this.setTitle("Borrar Indicador");

		new Label(form)
				.setText("Estas seguro que deseas borrar definitivamente el indicador seleccionado?");

	}

	@Override
	protected void addActions(Panel actions) {

		new Button(actions).setCaption("Borrar").onClick(this::accept)
				.setAsDefault().setWidth(140);

		new Button(actions).setCaption("Cancelar").onClick(this::cancel)
				.setWidth(140);

	}

	@Override
	public void accept() {
		try {
			this.getModelObject().borrar();
			this.showInfo("El indicador se elimino exitosamente!");
			this.close();
		} catch (Exception e) {
			this.showError(e.getMessage());
		}

	}

}