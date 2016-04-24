# IC-Parkour
A custom parkour arena Bukkit plugin designed for the IgneousCraft server. It isn't hardcoded to IgneousCraft though so it should really work anywhere if set up properly.

#Commands
/icp about - Shows you plugin information

/icp reload - Reloads the configuration file

/icp help - Gives you help with different command options

/icp exit [arenaname] - Allows you to leave a parkour arena

/icp enter \<arenaname> - Allows you to enter a parkour arena

/icp tp - Lets you tp to your last checkpoint

#Sign Options
All signs should have "[Parkour]" on the first line. What should be on the rest is as follows.

Checkpoint \<arenaname> \<checkpointname>

CheckpointTP \<arenaname>

Enter \<arenaname>

Exit \<arenaname>

Finish \<arenaname>

#Permissions
icparkour.about - Gives permission to the about command, shows you plugin information

icparkour.help - Gives permission to the help command, gives you help with different command options

icparkour.reload - Gives permission to the reload command, reloads the configuration file

icparkour.place - Gives permission to place BW-Parkour signs, used for arena interactions

icparkour.bypasscmdblock - Gives permission to bypass the command blocking, for when in parkour arenas

icparkour.exit - Gives permission to the exit command and sign, allows you to leave a parkour arena

icparkour.enter - Gives permission to the enter command and sign, allows you to enter a parkour arena

icparkour.tp - Gives permission to the tp command and sign, lets you tp to your last checkpoint

icparkour.checkpoint - Gives permission to the checkpoint sign, lets you set a new checkpoint

icparkour.finish - Gives permission to the finish sign, lets you finish a parkour arena
