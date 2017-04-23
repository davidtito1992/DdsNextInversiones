package view;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MainWindow;
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
		form.setLayout(new VerticalLayout());
		

	}

	@Override
	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Aceptar").onClick(this::accept).setAsDefault();
		new Button(actions).setCaption("Cancelar").onClick(this::cancel);
	}

	@Override
	protected void executeTask() {
		super.executeTask();
	}

	@Override
	public void cancel() {
		this.close();
		
	}


}

