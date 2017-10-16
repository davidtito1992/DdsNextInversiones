package main.view;

import java.awt.Color;

import main.viewmodel.MainViewM;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("serial")
public class MainView extends SimpleWindow<MainViewM> {

	public MainView(WindowOwner parent) {
		super(parent, new MainViewM());
	}

	/***************************** panel maestro:label , tablas , buttons, etc ************************/
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Next-Inversiones");

		mainPanel.setLayout(new VerticalLayout());

		new Label(mainPanel).setText("Bienvenido al Sistema \n")
				.setBackground(Color.WHITE).setWidth(500);
		new Button(mainPanel).setCaption("Cargar Empresas")
				.onClick(this::cargarEmpresas)
				.bindVisibleToProperty("empresasSinCargar");
		new Button(mainPanel).setCaption("Cargar Indicadores")
				.onClick(this::cargarIndicadores)
				.bindVisibleToProperty("indicadoresSinCargar");
		new Button(mainPanel).setCaption("Cargar Metodologias")
				.onClick(this::cargarMetodologias)
				.bindVisibleToProperty("metodologiasSinCargar");
		new Button(mainPanel).setCaption("Empresas")
				.onClick(() -> this.verEmpresas()).setWidth(60).setHeight(80);
		new Button(mainPanel).setCaption("Indicadores")
				.onClick(() -> this.verIndicadores()).setWidth(60)
				.setHeight(80);
		new Button(mainPanel).setCaption("Metodologias")
				.onClick(() -> this.verMetodologias()).setWidth(60)
				.setHeight(80);

		new Label(mainPanel).setText("\n\n\n");

		new Button(mainPanel).setCaption("Cerrar").onClick(() -> cerrar())
				.setHeight(50);

	}

	/*****************
	 * buttons adicionales: podemos colocarlos horizontales a diferencia de los
	 * demas
	 *************/
	@Override
	protected void addActions(Panel actionsPanel) {
	}

	/************* metodos para los buttons ***************************/
	public void verAlgo() {
		System.out.println("Accediendo para...");
	}

	public void cerrar() {
		close();
	}

	private void verMetodologias() {

		// if (getModelObject().isMetodologiasSinCargar()) {
		// showInfo("Cargue las metodologias primero.");
		// } else {

		// if (getModelObject().isIndicadoresSinCargar()) {
		// showInfo("No se puede acceder a las metodologias si los indicadores no fueron cargados.");
		// } else {
			Dialog<?> dialog = new MetodologiaView(this);
			dialog.open();
			dialog.onAccept(() -> {
			});
		// }
		// }
	}

	public void verIndicadores() {
		// if (getModelObject().isIndicadoresSinCargar()) {
		// showInfo("Cargue los indicadores primero.");
		// } else {
			Dialog<?> dialog = new IndicadorView(this);
			dialog.open();
			dialog.onAccept(() -> {
			});
		// }
	}

	public void verEmpresas() {
		// if (getModelObject().isEmpresasSinCargar()) {
		// showInfo("Cargue las empresas primero.");
		// } else {
		Dialog<?> dialog = new EmpresaView(this);
		dialog.open();
		dialog.onAccept(() -> {
		});
		// }
	}

	private void cargarEmpresas() {
		try {
			getModelObject().cargarEmpresas();
			getModelObject().setEmpresasSinCargar(false);
		} catch (Exception e) {
			showInfo(e.getMessage());
		}

	}

	private void cargarIndicadores() {
		try {
			getModelObject().cargarIndicadores();
			getModelObject().setIndicadoresSinCargar(false);
		} catch (Exception e) {
			showInfo(e.getMessage());
		}
	}

	private void cargarMetodologias() {
		try {
			getModelObject().cargarMetodologias();
			getModelObject().setMetodologiasSinCargar(false);
		} catch (Exception e) {
			showInfo(e.getMessage());
		}
	}

	/*************************************/

}