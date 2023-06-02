# DiscordMusicBotGary
My first attempt at building a bot that can play audio in discord voice channels through the use of JDA and Lavaplayer APIs 

This is the rough skeleton of a music bot, it is not fully functional. The major flaws with this program are as follows:
- Can only play audio if given an exact youtube link
- Fails to register onTrackEnd() and onTrackStart() events

Currently, I have no plans to fix these issues. I made this program primarily so that I could practise using APIs, practise planning/designing/editing an idependant JAVA project, and to try and gain experience properly organizing my code structure in a way that is both intuitive and easy to understand. 

I feel with this being my first independant project, I bit off more than I could chew and made many mistakes. But, I also learned a lot and gained valuable experience and
insight, and am very proud of what I managed to accomplish here. It is because I am proud of what I managed to accomplish and learn, that I decided to upload this to Github as a way of documenting my progress and efforts.

All that being said, this bot has many flaws, and I would not personally recommend implementing it into your own servers.

If you would still like to try it out, make sure to make the following changes in the code:
- In the BotManagment package, find the MusicBot class, go to line 29, and insert your discord bot's unique token.
- In that same class, go to line 45 and insert your dicord channels unique ID.

Thanks for checking out the project!
