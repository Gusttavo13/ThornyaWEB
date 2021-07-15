package club.thornya.thornyaweb;


import club.thornya.thornyaweb.Driver.DriverSetup;
import club.thornya.thornyaweb.Tick.Runnable;
import club.thornya.thornyaweb.Utils.ConfigT;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ThornyaWeb extends JavaPlugin {

    public static ConfigT config;
    public static ThornyaWeb TW;

    @Override
    public void onEnable() {
        TW = this;
        ThornyaWeb.config = new ConfigT(this);
        carregarconfigs();
        Bukkit.getConsoleSender().sendMessage("Plugin ThornyaWeb Iniciando");
        try {
            DriverSetup.setupWeb();
            Runnable.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§cErro ao iniciar o Whatsapp Web");
        }
        registrarComandos();
    }

    @Override
    public void onLoad() {
        Bukkit.getConsoleSender().sendMessage("Carregando");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("Plugin ThornyaWeb Desligando");
        DriverSetup.shutdown();
    }

    public void registrarComandos(){
        //registrarComando("template", new Thornya(this));
        //Objects.requireNonNull(getCommand("template")).setTabCompleter(new TabPrefeitura(this));
    }

    public void registrarComando(String nome, CommandExecutor comando) {
        Objects.requireNonNull(this.getCommand(nome)).setExecutor(comando);
    }

    public void registrarListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public void carregarconfigs(){
        ConfigT.criarConfig("configuration");
    }
    public void salvarConfig(){
        ConfigT.saveConfig("configuration");
    }
    public void reloadAll(){
        salvarConfig();
        config.reloadConfig("configuration");
    }



}
