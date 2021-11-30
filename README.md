# MiniChat

A small Chat Plugin for Paper to format the chat
using [MiniMessage](https://github.com/KyoriPowered/adventure-text-minimessage)!

### Setup

This Plugin requires at least [Paper](https://papermc.io/) 1.16.5 to function!
This Plugin requires [LuckPerms](https://luckperms.net/) to function!
Place the jarfile into the plugins folder. Use the LuckPerms Meta manager to set the chatformat in
MiniMessage format!

### Configuration

```yaml
minichat:
  format:
    key: minichat.meta # The Meta key used to retrieve the chat format for the user.
    fallback: "<gray><name>: <message></gray>" #If no format is available, this format will be ued.
```

### Meta Examples

```
/lp group admin meta setprefix "<white>[</white><dark_red>ADMIN</dark_red><white>]</white>"
/lp group admin meta set minichat.format "<prefix> <red><name></red>: <white><message></white>"
/lp group default meta set minichat.format "<gray>[Member] <name>: <message></gray>
```


### Default Placeholders

MiniChat provides several placeholders:

- name
- message
- prefix
- suffix
- displayName
- teamDisplayName

### Custom Placeholders

It is possible to define custom placeholders. To do this, add the MiniChat as a dependency to your
plugin and use the Services manager to acquire an instance of the MiniChat interface.

MiniChat is available through GitHub packages.

```xml

<repositories>
  <repository>
    <id>github</id>
    <name>minichat</name>
    <url>https://maven.pkg.github.com/steenooo/minichat</url>
  </repository>
</repositories>
```

```xml

<dependency>
  <groupId>dev.steyn</groupId>
  <artifactId>minichat</artifactId>
  <version>1.0</version>
</dependency>
```

#### Custom Placeholder Example

```java

import dev.steyn.minichat.api.MiniChat;
import dev.steyn.minichat.api.Placeholder;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

class YourPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        RegisteredServiceProvider<MiniChat> service = Bukkit.getServicesManager()
            .getRegistration(MiniChat.class);
        MiniChat miniChat = service.getProvider();
        miniChat.addPlaceholder(this, "team", p -> Component.text("Team1"));
    }
}
```
