[back to README](/README.md)

Notes:

	1. During run the program will save a file containing your settings, don't delete or modify this.
	2. The program will place a file in your movie directory that contains all the information it gathers (be careful modifying this)
		This allows for accesing your media server with different computers and not being required to do another "Import My Movies"
	3. I have not implemented loading bars so the program may appear to "freeze" while:
		Gathering all your movies and cleaning titles for display
		Gathering information and images from online (takes about 1 minute per 100 movies)
	4. Images downloaded are saved alongside your installation directory. They will need to be downloaded again for different computers (for now)

Features:

	1. Gathers Files and determines if they're a movie / tv series based on filename / file path
	2. Cleans file names for display (does not modify your files) example: (Paddington (2014) BRRiP 1080p x264 DD5.1 EN NL Subs) becomes Paddington
	3. Finds year in filename if it's there
	4. Gathers information from IMDB
		Rating
		Year
		Genres
		Movie Image / Poster
	5. View by specific Genre / All files / Only Movies / Only Series
	6. Sorting options
		Rating
		Title
		Year
		Viewcount* see bugs #9
	7. Search, live UI update while typing
	8. Click Movie Image and UI launches default video player with that file.
	9. Display Features With automatic image spacing adjustments
		Turn on / off:
			Title
			Year
			Rating
			View Count
			Genres* see bugs #12
	10. Resizable movie images that saves set size* see Notes #1
	11. Smooth startup with automatic previous settings / directory with images loaded* see Notes #1
	12. Automatic spacing changes during resize of program (keeps images evenly spaced with wrapping)
