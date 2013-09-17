package me.carl230690.anticaps;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
  implements Listener
{
  int maxCapsPercentage;
  int minLength;
  static String upperCase;

  public void onEnable()
  {
    getServer().getPluginManager().registerEvents(this, this);
    saveDefaultConfig();
    this.maxCapsPercentage = getConfig().getInt("max-caps-percentage");
    this.minLength = getConfig().getInt("min-message-length");
    upperCase = getConfig().getString("uppercase-characters");
  }

  @EventHandler
  public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
    if ((!event.getPlayer().hasPermission("doragoncaps.bypass")) && 
      (event.getMessage().length() >= this.minLength) && 
      (getUppercasePercentage(event.getMessage()) > this.maxCapsPercentage))
      event.setMessage(event.getMessage().toLowerCase());
  }

  public static boolean isUppercase(String string)
  {
    return upperCase.contains(string);
  }

  public static double getUppercasePercentage(String string) {
    double upperCase = 0.0D;
    for (int i = 0; i < string.length(); i++) {
      if (isUppercase(string.substring(i, i + 1))) {
        upperCase += 1.0D;
      }
    }
    return upperCase / string.length() * 100.0D;
  }
}