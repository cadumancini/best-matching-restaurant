# Best Matching Restaurant
## How to build and run the tests
From the root folder, run:

>./gradlew build

## How to run the application
From the root folder, run:

>java -jar build/libs/best-matched-restaurants-CM.jar

The command above runs the application that has just been built.

Another JAR file with the application is attached in the e-mail message, so just run from anywhere the JAR file is located:

>java -jar best-matched-restaurants-CM.jar

## GitHub repository
The code is also available on my GitHub profile and can be found [here](https://github.com/cadumancini/best-matching-restaurant). No mentions to AlphaSights have been included, as required.

## Assumptions
### Console application
A console application has been created for this assessment.
In the real world, it would probably be an API, that would expose an endpoint to get requests from any source (e.g., an HTML page), and then return the results with the matched restaurants.
If that were the case, I would build the application using SpringBoot.

### Logs
Were we developing a RESTFUL API, I would add application logs, using SLF4J with any implementation, like `Log4J`, or `Logback`, for example.

### Last sorting order
After sorting the search matches by distance, customer rating, and price, the project description says I can decide the order. I've used the restaurant name (ascending order) for that.