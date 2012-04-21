package de.mineterra.shop;


import java.util.logging.Logger;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;




public class shop extends JavaPlugin{
	
	public Config config;
	
	private static final Logger log = Logger.getLogger("Minecraft");
	@SuppressWarnings("unused")
	private static Vault vault = null;
	public static Economy econ = null;
	public ConsoleCommandSender console;
    
	
	public void onDisable()
	{
		log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
	}
	
	public void onEnable(){

		 if (!setupEconomy() ) {
	            log.info(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
	            getServer().getPluginManager().disablePlugin(this);
	            return;
	        }

	    }

	    private boolean setupEconomy() {
	        if (getServer().getPluginManager().getPlugin("Vault") == null) {
	            return false;
	        }
	        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
	        if (rsp == null) {
	            return false;
	        }
	        econ = rsp.getProvider();
	        return econ != null;
	    }

	

	
	    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args)
	    {
	      Player p = (Player)sender;

	      if ((cmd.getName().equalsIgnoreCase("shop")) || (cmd.getName().equalsIgnoreCase("s"))) {
	        if (args.length == 0) {
	          p.sendMessage(HelpMenuLayout("/shop", "Öffnet diese Hilfe"));
	          p.sendMessage(HelpMenuLayout("/shop hilfe", "Sagt dir wie man einen Shop erstellt."));
	          p.sendMessage(HelpMenuLayout("/shop paystand", "Zahlt 100Y für einen Markt Stand"));
	          p.sendMessage(HelpMenuLayout("/shop payhaus", "Zahlt 450Y für ein Shop Haus."));
	          return true;
	        }
	        if (args[0].equalsIgnoreCase("hilfe"))
	        {
	          p.sendMessage(ChatColor.GREEN + "Um einen Shop zu erstellen, musst du eine Shop Lizenz kaufen.");
	          p.sendMessage(ChatColor.GREEN + "Eine Markt Lizenz kannst du mit" + ChatColor.YELLOW + " /shop paystand" + ChatColor.GREEN + "  kaufen.");
	          p.sendMessage(ChatColor.GREEN + "Diese Lizenz Kostet " + ChatColor.RED + ">>" + ChatColor.AQUA + " 100Y" + ChatColor.GREEN + ".");
	          p.sendMessage(ChatColor.GREEN + "Eine Shop Haus Lizenz kannst du mit " + ChatColor.YELLOW + "/shop payhaus" + ChatColor.GREEN + " kaufen.");
	          p.sendMessage(ChatColor.GREEN + "Diese Lizenz Kostet " + ChatColor.RED + ">>" + ChatColor.AQUA + " 450Y" + ChatColor.GREEN + ".");

	          
	          return true;
	        }
	        if (args[0].equalsIgnoreCase("paystand"))
	        {
	        	
	        	//TODO Group Promote Einfügen!
	        	
	        	
	        	//Econ System	        	        	
	        	EconomyResponse r = econ.withdrawPlayer(p.getName(), 100.0);
	        	if(r.transactionSuccess()) {
	        		sender.sendMessage(String.format(ChatColor.GREEN + "Die Gebühr in höhe von" + ChatColor.AQUA + " 100Y " + ChatColor.GREEN + "wurde bezahlt!"));
	        		sender.sendMessage(String.format(ChatColor.GREEN + "Du darfst jetzt einen Stand eröffnen!"));
	          return true;
	          
	            } else {
	                sender.sendMessage(String.format(ChatColor.RED + "Du hast nicht genug Geld für einen Markt Stand!", r.errorMessage));
	        }
	        }
	        if (args[0].equalsIgnoreCase("payhaus"))
	        {
	        	
	        	//TODO Group Promote Einfügen!
	        	
	        	
	        	//Econ System
	        	EconomyResponse r = econ.withdrawPlayer(p.getName(), 450.0);
	        	if(r.transactionSuccess()) {
	        		sender.sendMessage(String.format(ChatColor.GREEN + "Die Gebühr in höhe von" + ChatColor.AQUA + " 450Y " + ChatColor.GREEN + "wurde bezahlt!"));
	        		sender.sendMessage(String.format(ChatColor.GREEN + "Du darfst jetzt einen Stand eröffnen!"));
	          return true;
	          
	            } else {
	                sender.sendMessage(String.format(ChatColor.RED + "Du hast nicht genug Geld für einen Markt Stand!", r.errorMessage));
	        }
	        }

	      }

	      return false;
	    }

	    public String HelpMenuLayout(String Input1, String Input2) {
	      String Output = ChatColor.GREEN + Input1 + ChatColor.RED + " = " + ChatColor.GREEN + Input2;
	      return Output;
	    }
	    
}