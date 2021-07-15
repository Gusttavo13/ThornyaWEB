package club.thornya.thornyaweb.Driver;

import org.bukkit.Bukkit;

import java.util.ArrayList;

public class Triggers {

    public static void triggers() throws InterruptedException {
        if(!DriverSetup.getCommandUsed()) {
            onlines();
            sudo();
            foto();
            arquivo();
        }
    }

    private static void onlines() throws InterruptedException {
        if(DriverSetup.message.equalsIgnoreCase("/onlines") || DriverSetup.message.equalsIgnoreCase("/players")){
            DriverSetup.setCommandUsed(true);
            DriverSetup.lastMessage = DriverSetup.message;

            ArrayList<String> players = new ArrayList<>();
            if(Bukkit.getOnlinePlayers().isEmpty()){
                players.add("*Não há jogadores onlines!*");
            }else{
                Bukkit.getOnlinePlayers().forEach(player -> players.add(player.getName()));
                players.add(" ");
                players.add("Total de *{0}* jogadores!".replace("{0}", String.valueOf(Bukkit.getOnlinePlayers().size())));
            }

            Senders.typeChat(players);
        }
    }

    private static void foto() throws InterruptedException {
        if(DriverSetup.message.equalsIgnoreCase("/foto")){
            DriverSetup.setCommandUsed(true);
            DriverSetup.lastMessage = DriverSetup.message;
            Senders.typePhoto("C:\\Users\\Gusttavo13\\Desktop\\1.png", "Mensagem na foto");
        }
    }
    private static void arquivo() throws InterruptedException {
        if(DriverSetup.message.equalsIgnoreCase("/arquivo")){
            DriverSetup.setCommandUsed(true);
            DriverSetup.lastMessage = DriverSetup.message;
            Senders.typeFile("C:\\Users\\Gusttavo13\\Desktop\\Arquivos\\Thornya.py");
        }
    }
    private static void sudo() throws InterruptedException {
        if(DriverSetup.message.startsWith("/sudo")) {
            DriverSetup.setCommandUsed(true);
            DriverSetup.lastMessage = DriverSetup.message;
            if (DriverSetup.message.contains(" ")) {
                String[] buildMessage = DriverSetup.message.split(" ");

                StringBuilder command = new StringBuilder();

                for (int i = 1; i < buildMessage.length; i++) {
                    command.append(buildMessage[i]);
                    if(!(buildMessage.length - 1 == i)){
                        command.append(" ");
                    }
                }
                DriverSetup.setSudoCommand(command.toString());
                Senders.typeChat("Comando executado: *" + DriverSetup.getSudoCommand() + "*");

            }else {
                Senders.typeChat("*Use /sudo (Comando sem /)*");
            }
        }

    }


}
