[Back to README](/README.md)

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
	15. Convert IMDB mining to use regex on html instead of substrings
