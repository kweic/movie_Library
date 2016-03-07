This is a personal visual organizer for digitized movies with sorting options and automatic information and image downloading.
See Features for more information.
At this point (3.6.2016) the program still has many updates that I plan to implement.* see Bugs and Planned Updates
Program by Kevin Weichel - KevinJWeichel@gmail.com
Not for Resale


To "install" this program:
	1. place compiled program & noImageAvailable.jpg together in location of your choosing.
	

First time running:
	1. Run program
	2. Click Movie Setup > Set Movie Directory (choose the folder containing your movie files)
	3. Click Movie Setup > Import My Movies (wait while program finds your files, cleans titles, determines movie / tv series)*see notes #3
	4. Click Movie Setup > Download Covers & Info (this step needs an internet connection and patience)*see notes #3

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

Bugs and Planned Updates:
	1. Add loading window with progress bar as first user experience (while images are loaded into program and set to user's preferred size)
	2. Add progress bar window for "Download Covers & Info" - needed as this online operation can take some time
	3. Move UI Logic out of Main
	4. Change longer string operations to use StringBuilder and save memory
	5. Test storing images in movie directory so program can work smoother between different computers & media server
	6. Rename MovieInformation save file so it's not the same name as UI saver file (not too important because they're different locations)
	7. Figure out how to only reprint Covers that are visible to the User during sorting changes and UI resize
	8. Add backup source for movie information (currently parses information from IMDB html, this is bound to break with IMDB html changes)
	9. Find a good way to track real viewcount possibly based on viewtime (timed from inactivity in program)
	10. Implement Editing option for user spelling changes of movie titles(this can currently only be done in the save file)
	11. Implement unused images check with deletion option (unused images can accumulate if titles are fixed)
	12. Implement Genres display option in UI. (currently not an option as this would take too much space in my layout)
	13. Implement Help & About options
	14. Add check for Rating Sort where sort will be aborted if no ratings are gathered (currently attempts to sort but is slow)
	15. 