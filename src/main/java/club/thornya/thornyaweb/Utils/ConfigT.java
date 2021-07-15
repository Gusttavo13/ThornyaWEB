package club.thornya.thornyaweb.Utils;

import club.thornya.thornyaweb.ThornyaWeb;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;


public class ConfigT {

    private static ThornyaWeb pl = null;
    private static File file = null;
    private static FileConfiguration fileC = null;

    public ConfigT(ThornyaWeb main){
        pl = main;
    }

    public static void criarConfig(String nomedoarquivo){
        File fileVerifica = new File(pl.getDataFolder(), nomedoarquivo + ".yml");
        if(!fileVerifica.exists()){ pl.saveResource(nomedoarquivo + ".yml", false);}
    }

    public static FileConfiguration getFile(String nomedoarquivo){
        file = new File(pl.getDataFolder(), nomedoarquivo + ".yml");
        fileC = YamlConfiguration.loadConfiguration(file);

        return fileC;

    }

    public static void saveConfig(String nomedoarquivo){
        try {
            getFile(nomedoarquivo).save(file);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig(String nomedoarquivo){

        if(file == null){
            file = new File(pl.getDataFolder(), nomedoarquivo);
        }
        fileC = YamlConfiguration.loadConfiguration(file);
        if(fileC != null){
            YamlConfiguration defaults = YamlConfiguration.loadConfiguration(file);
            fileC.setDefaults(defaults);
        }
    }

}
