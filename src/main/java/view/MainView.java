package view;

import java.awt.Color;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import viewmodel.MainViewM;

@SuppressWarnings("serial")
public class MainView extends SimpleWindow<MainViewM> {

	public MainView(WindowOwner parent) {
		super(parent, new MainViewM());

	}

	/***************************** panel maestro:label , tablas , buttons, etc ************************/
	@Override
	protected void createFormPanel(Panel mainPanel) {
		// TODO Auto-generated method stub

		this.setTitle("Next-Inversiones");

		mainPanel.setLayout(new VerticalLayout());

		new Label(mainPanel).setText("Bienvenido al Sistema \n")
				.setBackground(Color.WHITE).setWidth(500);

		new Button(mainPanel).setCaption("ABM Empresas")
				.onClick(() -> this.verEmpresas()).setWidth(60).setHeight(80);
		new Button(mainPanel).setCaption("ABM Indicadores")
				.onClick(() -> verAlgo()).setWidth(60).setHeight(80);
		new Button(mainPanel).setCaption("ABM Metodologias")
				.onClick(() -> verAlgo()).setWidth(60).setHeight(80);

		new Label(mainPanel).setText("\n\n\n");

		new Button(mainPanel).setCaption("Cerrar").onClick(() -> cerrar())
				.setHeight(50);
		/*
		 * new
		 * Label(mainPanel).setText("\t\tSeleccione tipo de consulta a realizar: "
		 * ); Selector<String> selectorSemestre = new
		 * Selector<String>(mainPanel).allowNull(false);
		 * selectorSemestre.setWidth(150);
		 * selectorSemestre.bindItemsToProperty("tipoConsultas");
		 * selectorSemestre.bindValueToProperty("consultaSeleccionada");
		 * 
		 * new Label(mainPanel).setText(""); new
		 * Button(mainPanel).setCaption("IR"
		 * ).onClick(this::abrirConsulta).setWidth(140); mainPanel.setWidth(10);
		 */
	}

	/*****************
	 * buttons adicionales: podemos colocarlos horizontales a diferencia de los
	 * demas
	 *************/
	@Override
	protected void addActions(Panel actionsPanel) {
		// TODO Auto-generated method stub

	}

	/************* metodos para los buttons ***************************/
	public void verAlgo() {

		System.out.println("Accediendo para...");
		// Dialog<?> dialog = new ddd(ventanita);
		// dialog.open();
		// dialog.onAccept(() -> {});
	}

	public void cerrar() {
		close();
	}

	public void verEmpresas() {

		System.out.println("Accediendo para...");
		Dialog<?> dialog = new EmpresaView(this);
		dialog.open();
		dialog.onAccept(() -> {
		});

	}
	/*
	 * public void abrirConsulta(){
	 * 
	 * String seleccionado = this.getModelObject().getConsultaSeleccionada();
	 * 
	 * if (seleccionado == "Indicadores") { System.out
	 * .println("aca se abre la ventana de consulta indicadores"); } else { if
	 * (seleccionado == "Metodologias") { System.out
	 * .println("aca se abre la ventana de consulta Metodologias"); } else {
	 * System.out .println("aca se abre la ventana de consulta Graficos"); } } }
	 */
	/*************************************/

}
