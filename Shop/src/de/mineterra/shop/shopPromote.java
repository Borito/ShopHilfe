package de.mineterra.shop;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class shopPromote {
	public static String activePermissions;
	  public static Plugin pex = Bukkit.getPluginManager().getPlugin("PermissionsEx");
	  public static Plugin gm = Bukkit.getPluginManager().getPlugin("GroupManager");
	  public static Plugin pb = Bukkit.getPluginManager().getPlugin("PermissionsBukkit");

	  public static void getActivePermissions() {
	    if (Bukkit.getPluginManager().isPluginEnabled(pex))
	    {
	      activePermissions = "PermissionsEx";
	    }
	    else
	    {
	      System.out.println("[MasterPromote] No Permissionssystem found!");
	    }
	  }

	  public static void promote(Player player, String Markt)
	  {
	    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	    if (activePermissions.equals("PermissionsEx"))
	    {
	      Bukkit.dispatchCommand(console, "pex user " + player.getName() + " group set " + Markt);
	    }
	    
	  }
	  
}
