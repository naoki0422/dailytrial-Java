
package plugin.sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.World;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.plugin.pluginsample.AllSetLevelCommand;
import org.example.plugin.pluginsample.SetLevelCommand;

public final class Main extends JavaPlugin implements Listener {
  private int count;

  public Main() {
  }

  public void onEnable() {
    this.saveDefaultConfig();

    Bukkit.getPluginManager().registerEvents(this, this);
    this.getCommand("setlevel").setExecutor(new SetLevelCommand(this));
    this.getCommand("allSetLevel").setExecutor(new AllSetLevelCommand());
  }

  @EventHandler
  public void onPlayerToggleSneak(PlayerToggleSneakEvent e) throws IOException {
    Player player = e.getPlayer();
    World world = player.getWorld();
    List<Color> colorList = List.of(Color.RED, Color.BLUE, Color.WHITE, Color.BLACK);
    if (this.count % 2 == 0) {
      Iterator var5 = colorList.iterator();

      while(var5.hasNext()) {
        Color color = (Color)var5.next();
        Firework firework = (Firework)world.spawn(player.getLocation(), Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffect(FireworkEffect.builder().withColor(color).with(Type.BALL_LARGE).withFlicker().build());
        fireworkMeta.setPower(1);
        firework.setFireworkMeta(fireworkMeta);
      }

      Path path = Path.of("firework.txt");
      Files.writeString(path, "たーまやー", StandardOpenOption.APPEND);
      player.sendMessage(Files.readString(path));
    }

    ++this.count;
  }

  @EventHandler
  public void onPlayerBedEnter(PlayerBedEnterEvent e) {
    Player player = e.getPlayer();
    ItemStack[] itemStacks = player.getInventory().getContents();
    Arrays.stream(itemStacks).filter((item) -> {
      return !Objects.isNull(item) && item.getMaxStackSize() == 64 && item.getAmount() < 64;
    }).forEachOrdered((item) -> {
      item.setAmount(64);
    });
    player.getInventory().setContents(itemStacks);
  }

  @EventHandler
  public void onPlayerJoinEnter(PlayerJoinEvent e) {
    e.setJoinMessage("なんてこったーマインクラフト");
  }
}
