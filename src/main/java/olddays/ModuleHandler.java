package olddays;

import java.util.ArrayList;

public class ModuleHandler {

	public boolean oldEndermen = true;
    ArrayList<Boolean> modifications =  new ArrayList<Boolean>();
	
    public ModuleHandler()
    {
    	modifications.add(oldEndermen); // 0
    }
    
    public static void getHandler()
    {
    	new ModuleHandler();
    }

}
