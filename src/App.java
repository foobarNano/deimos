import controller.AppSystem;
import view.GraphicalView;

public class App
{
    public static void main(String[] args)
    {
        AppSystem sys = new AppSystem();
        GraphicalView view = new GraphicalView(sys);

        sys.pullDB();
        view.run();
    }
}
