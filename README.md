# DiscordMusicBotGary
My first attempt at building a bot that can play audio in discord voice channels through the use of JDA and Lavaplayer APIs 

This is the rough skeleton of a music bot, it is not fully functional. The major flaws with this program are as follows:
- Can only play audio if given an exact youtube link
- Fails to register onTrackEnd() and onTrackStart() events

Currently, I have no plans to fix these issues. I made this program primarily so that I could practise using APIs, practise planning/designing/editing an idependant JAVA project, and to try and gain experience properly organizing my code structure in a way that is both intuitive and easy to understand. 

All that being said, this bot has many flaws, and I would not personally recommend implementing it into your own servers in its current state.

If you would still like to try it out, make sure to make the following changes in the code:
- In the BotManagment package, find the MusicBot class, go to line 29, and insert your discord bot's unique token.
- In that same class, go to line 45 and insert your dicord channel's unique ID.

Thanks for checking out the project!
