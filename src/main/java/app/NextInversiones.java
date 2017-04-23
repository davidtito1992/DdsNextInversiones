package app;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import view.MainView;

public class NextInversiones extends Application{

	public static void main(String[] args) {
		
		new NextInversiones().start();
	}
	
	@Override
	protected Window<?> createMainWindow() {
		return new MainView(this);
	}
}
