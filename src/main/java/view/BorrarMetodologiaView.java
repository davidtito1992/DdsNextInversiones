package view;

import model.Metodologia;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;

import viewmodel.BorrarMetodologiaViewM;

public class BorrarMetodologiaView extends Dialog<BorrarMetodologiaViewM> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	public BorrarMetodologiaView(SimpleWindow owner,
			Metodologia metodologiaABorrar) {
		super(owner, new BorrarMetodologiaViewM(metodologiaABorrar));
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		this.setTitle("Borrar Metodologia");

		new Label(form)
				.setText("Estas seguro que deseas borrar definitivamente la metodologia seleccionada?");

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
			this.showInfo("La Metodologia se elimino exitosamente!");
			this.close();
		} catch (Exception e) {
			this.showError(e.getMessage());
		}

	}
	
}
