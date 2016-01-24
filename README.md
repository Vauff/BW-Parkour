# BW-Parkour
A Bukkit/Spigot parkour plugin designed for the Block World Server. It isn't hardcoded to Block World though so it should really work anywhere if set up properly.

#Commands
/bwp about - Shows you plugin information

/bwp reload - Reloads the configuration file

/bwp help - Gives you help with different command options

/bwp exit [arenaname] - Allows you to leave a parkour arena

/bwp enter \<arenaname> - Allows you to enter a parkour arena

/bwp tp - Lets you tp to your last checkpoint

#Sign Options
All signs should have "[Parkour]" on the first line. What should be on the rest is as follows.

Checkpoint \<arenaname> \<checkpointname>

CheckpointTP \<arenaname>

Enter \<arenaname>

Exit \<arenaname>

Finish \<arenaname>

#Permissions
bwparkour.about - Gives permission to the about command, shows you plugin information

bwparkour.help - Gives permission to the help command, gives you help with different command options

bwparkour.reload - Gives permission to the reload command, reloads the configuration file

bwparkour.place - Gives permission to place BW-Parkour signs, used for arena interactions

bwparkour.exit - Gives permission to the exit command and sign, allows you to leave a parkour arena

bwparkour.enter - Gives permission to the enter command and sign, allows you to enter a parkour arena

bwparkour.tp - Gives permission to the tp command and sign, lets you tp to your last checkpoint

bwparkour.checkpoint - Gives permission to the checkpoint sign, lets you set a new checkpoint

bwparkour.finish - Gives permission to the finish sign, lets you finish a parkour arena
