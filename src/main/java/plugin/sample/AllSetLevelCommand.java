
package plugin.sample;

import java.util.Iterator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AllSetLevelCommand implements CommandExecutor {
  public AllSetLevelCommand() {
  }

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player player) {
      player.sendMessage("実行できません");
    } else {
      Iterator var6 = sender.getServer().getOnlinePlayers().iterator();

      while(var6.hasNext()) {
        Player player = (Player)var6.next();
        player.setLevel(Integer.parseInt(args[0]));
        System.out.println("プレイヤーのレベルが" + args[0] + "に設定されました。");
      }
    }

    return false;
  }
}
