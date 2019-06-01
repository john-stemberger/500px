# 500px
Sample integration with the 500px api

# Api references
 1. [Github](https://github.com/500px/legacy-api-documentation)
 	1. [Photos](https://github.com/500px/legacy-api-documentation/blob/master/endpoints/photo/GET_photos.md)
 	1. [Image Sizes](https://github.com/500px/legacy-api-documentation/blob/master/basics/formats_and_terms.md#image-urls-and-image-sizes)


# Task 1 - Photo Showcase
Showcase Popular photos from 500px. Specifically, it should show photos in our “Popular” feature dynamically obtained from the 500px API. The list should support pagination, allowing users to browse through multiple pages of content. Feel free to choose exactly how you’d like to present the photos based on your own intuition.

### Features
1. collapsing toolbar with an icon, title, and subtitle
1. Grid style layout but the with different image sizes. 
    1. Custom Layout manager would be best but this may take longer than I am willing to do.
    1. images are divided into rows with either 2 or 3 images per row.
    1. A row can be either a `tall`, `medium`, or `short` in height
    	1. `tall` is approximately a 3rd the height of the screen (in portrait)
    	1. `medium` is approximately a 4th the height of the screen
    	1. `small` is approximately a 5th the height of the screen
    1. Widths are extremely variable and don't appear to be cropping images
1. Does not support an offline mode.
1. Filters are available for the popular category. Going to exclude this for this test.
1. popular page seems to have 2 separate pull to refresh layouts. one that lives under the collapsible toolbar and one that lives above it. Looks to be a glitch.

# Task 2 - Photo Details
When user clicks on a photo on the grid, a full screen version of the photo should be displayed along with more detailed information about the photo, such as its title, description, and any other data you think might be useful to display.

### Features
1. Looks to be a separate activity as based on the transition animations and the fact that it is using a separate toolbar.
1. contains a large format image in the center of the screen with a bottom sheet containing the details.
1. coordinator layout transitions the large scale image up as the bottom sheet expands.
1. Bottom sheet
	1. social action buttons
		1. likes are displayed twice in 2 different formats which I am not a fan of. There is some added information in that you include 2 names of people that also liked the image but I have no clue who these people are or why I should care that 2 random ppl liked it. If these were friends or people I follow it would make sense.
	1. title, author, age
	1.  nested scroll view displaying extended information about the camera and a description from the author. 
	1. not sure what pulse is?


# Task 3 - Cosmetics and Testing
This task is fairly open - further polish your web application and show off some of your strengths. Some ideas include:
Use your CSS talent to beautify your UI; improve the style, add animations, etc. Increase the reliability of your application by adding extensive test coverage
Feel free to choose the direction you want take (or even do both if you have extra cycles). Use this opportunity to show us what you’re good at in addition to coding.


# Technologies:
1. Glide for image Loading
1. Paging Library for paging the data
1. Repository structures for obfuscating the data source away. This will allow me to add a persistance storage at a later time to support an offline mode. This implementation will be strictly online for now
1. Activity/Fragment ViewModel to persist data across orientation state changes.
1. LiveData? for repository interactions
1. Retrofit for network requests.
