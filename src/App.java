import controller.AppSystem;
import model.Brand;

public class App
{
    public static void main(String[] args)
    {
        AppSystem as = new AppSystem();
        as.pullDB();
    }
}
