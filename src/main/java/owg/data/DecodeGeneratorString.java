package owg.data;

import com.jpmac26.olddays.world.generatortype.GeneratorType;

public class DecodeGeneratorString 
{
	public static void decode(String generatorString)
	{
		String[] genstring = generatorString.split("#");
		GeneratorType gentype = getGeneratorFromName(genstring[0]);
		
		if(gentype != null) //GENERATOR NAME
		{
			GeneratorType.currentGenerator = gentype;
		}
		if(genstring.length > 1 && genstring[1].length() > 0) //GENERATOR SETTINGS
		{
			String[] settingstring = genstring[1].split("&");
			GeneratorType.currentSettings = new int[settingstring.length];
			for(int i = 0; i < settingstring.length; i++)
			{
				GeneratorType.currentSettings[i] = Integer.parseInt(settingstring[i]);
			}
		}
	}
	
	public static GeneratorType getGeneratorFromName(String name)
	{
		for(int g = 0; g < GeneratorType.generatortypes.length; g++)
		{
			if(GeneratorType.generatortypes[g] != null)
			{
				if(GeneratorType.generatortypes[g].GetName().equals(name))
				{
					return GeneratorType.generatortypes[g];
				}
			}
		}
		return null;
	}

	public static int getGeneratorIDFromName(String name)
	{
		for(int g = 0; g < GeneratorType.generatortypes.length; g++)
		{
			if(GeneratorType.generatortypes[g] != null)
			{
				if(GeneratorType.generatortypes[g].GetName().equals(name))
				{
					return GeneratorType.generatortypes[g].GetID();
				}
			}
		}
		return -1;
	}
}