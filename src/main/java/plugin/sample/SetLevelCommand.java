
package plugin.sample;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLevelCommand implements CommandExecutor {
  private final Main main;

  public SetLevelCommand(Main main) {
    this.main = main;
  }

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player player) {
      if (args.length == 1) {
        player.setLevel(Integer.parseInt(args[0]));
      } else {
        player.sendMessage(this.main.getConfig().getString("Message"));
      }
    }

    return false;
  }
}
