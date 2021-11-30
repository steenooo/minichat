# MiniChat
A small Chat Plugin for Paper to format the chat using [MiniMessage](https://github.com/KyoriPowered/adventure-text-minimessage)!

### Setup
This Plugin requires at least [Paper](https://papermc.io/) 1.16.5 to function!
This Plugin requires [LuckPerms](https://luckperms.net/) to function!
Place the jarfile into the plugins folder.
Use the LuckPerms meta manager to set the chatformat in MiniMessage format!

### Examples
/lp group admin meta set minichat.format "<white>[</white><dark_red>ADMIN</dark_red><white>]</white> <red><name></red>: <white><message></white>"

  
### Defined Placeholders
MiniChat defines several placeholders:
  - name
  - message
  - prefix
  - suffix
  - displayName
  - teamDisplayName

### Custom Placeholders
It is possible to define custom placeholders. To do this, add the MiniChat as a dependency to your plugin and use the Services manager to acquire an instance of the MiniChat interface.
  
  
  #WIP
