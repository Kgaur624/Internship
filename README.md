Java Project - Tweeting

Requirement
Java 8 JDK
Sign up for Twitter Account 
Create a Twitter development application
Download and set up Intellij Idea
Twitter4j configuration

Task’s
I - Tweeting
Goal:
Retrieve and post to Twitter.

	1	Pass in an argument representing a post to be published on Twitter.
	2	Retrieve latest home timeline from Twitter.
	3	I have passed the information keys through a properties file and remove it from your code.

II - Dependency Management
Goal:
Add Maven for dependency management in the Java application.
	{ Maven is used for software project management, primarily for Java applications. You could think of it as a way to manage dependency and versions of libraries. Maven is part of the Apache Software Foundation. }

	1	I have installed and configure dependency management tool in development environment
	2	I have used Maven for dependency management
	
III - Make it Rest-ful
Goal:
Turn the application into an application that serves RESTful API endpoints.

	1	I have added dropwizard as a dependency.
	2	I have created configuration file to configure dropwizard application to start up.
	3	I have added the following REST endpoints for Posting a tweet and Getting tweet form timeline
			a.Post tweet - Create a POST route to the following url http://localhost:8080/api/1.0/	twitter/tweet. This route should take a single post parameter 'message' which will 		represent the message of the tweet. When called properly, this route will post the 		message to the Twitter account.

		b.Get timeline - Create a GET route to the following url http://localhost:8080/api/1.0/twitter/timeline. This route will retrieve a list of latest tweets from the home timeline.

	4	I have used HTTP Status code for successful requests vs requests that error out.
		example, 200 for successful requests and 500 for requests that error out.

IV - Unit Testing
Unit tests are used to ensure code runs the same in a repeatable way so releases can go out with confidence that things haven't changed.

	1	For unit testing I have used mockito. 
	2	I have added unit tests for the existing services
	3	I have used the mock library to mock data 
	4	I have test other classes that I felt it should be tested


V - Unit Test Coverage
These tools give developers insight into what lines of code is covered by existing unit tests. Coverage related tools will allow us to ensure that test all lines of code and meet requirements as needed.

	1.	I have integrated jacoco (code coverage tools) locally so that I can get code coverage tools.
	2	to ensuring 100% code coverage for all "necessary" code, I have skipped the the DropWizard application runtime class but for that I have included 
	⁃	Relevant Classes
	⁃	Models
	⁃	Configuration related files


VI - Configuration files

I have created Configuration java class.
	a.	Including fields to represent twitter keys/secrets/etc
In this task I have moved twitter keys/secret information to the yml file and remove previous usages of keys/secrets.
	a.	When checking in don't commit the base yml file and label it <filename>-example.yml. I didn’t check in the actual keys and replace them with placeholder text
	b.	When running locally, the dev file is copied to <filename>.yml
	c.	Since the previous keys have been checked into git directly,I may want to go to my twitter app and generate new ones to ensure others don't post directly to our twitter feed in case they get your checked in keys. 










