package olddays.settings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import olddays.ReflectionHelper;

/**
 * @author Kodehawa
 *
 */

public class GlobalBoolConfig {

	public volatile static GlobalBoolConfig instance = new GlobalBoolConfig();
	private String name;
	private String boolname;
	private String path;
	private File file;
	private boolean bool;
	public boolean newbool;
	
	private GlobalBoolConfig(){}
	
	/**
	 * Set all the values.
	 * @param clazz
	 * @param name
	 * @param bool
	 * @param path
	 */
	public GlobalBoolConfig(String showname, Class clazz, String name, Boolean bool, String path)
	{
		this.name = showname;
		this.boolname = name;
		this.bool = bool;
		this.path = path;
		this.file = new File(Minecraft.getMinecraft().mcDataDir, "/config/" + path);
		this.createFile();
		this.read();
		ReflectionHelper.setStringFieldWW(clazz, null, name, newbool);
	}
	
	public GlobalBoolConfig(String showname, String name, Boolean bool, String path)
	{
		this.name = showname;
		this.boolname = name;
		this.bool = bool;
		this.path = path;
		this.file = new File(Minecraft.getMinecraft().mcDataDir, "/config/"+path);
		this.createFile();
		this.read();
	}
	
	private void createFile(){
		if(!file.exists()){
			file.getParentFile().mkdirs();
			try
			{ 
				file.createNewFile();
				this.create(boolname, bool, path);
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Creates a file
	 * @param n name
	 * @param b value
	 * @param p path
	 */
	private void create(String n, boolean b, String p)
	{
		System.out.println("Creating Boolean config file: "+name);
		try
		{
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
			String s = String.valueOf(b);
			bufferedwriter.write(n+":" + s);
			bufferedwriter.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void read()
	{
		System.out.println("Reading boolean file: "+name);
		FileInputStream imputstream;
		try 
		{
			imputstream = new FileInputStream(file.getAbsolutePath());
			DataInputStream datastream = new DataInputStream(imputstream);
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(datastream));
			String value;
			while((value = bufferedreader.readLine()) != null){
				String line = value.trim();
				String[] values = line.split(":");
				String value1 = values[1];
				try{
					if(value1.equalsIgnoreCase("true") || value1.equalsIgnoreCase("false")){
						newbool = Boolean.parseBoolean(value1);
					}
					else{
						System.out.println("Unable to recognize boolean value from file: "+bool);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			bufferedreader.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
