Project Title
API pour gérer l’équipe de football de Nice en ligue 1. Le directeur sportif du club voudrait répertorier en base de données la liste de ses joueurs et le budget de l’équipe afin de gérer au mieux le marché de transfert à venir.

Prerequisites

Java version : 17 or 21 (17 prefereed)
maven version : apache-maven-3.9.5
Packaging : nicefc-0.0.1-SNAPSHOT.jar

Getting Started

Clone the Repository
git clone https://github.com/younesaa/matawan.git
cd your-repo (wher you cloned the project)

Build the Project
mvn clean install (for windows ./mvnw clean install)


Run the Application

mvn spring-boot:run ( or run it on Intellij by running the main function)


The application will be accessible at http://localhost:8080

API Paths

Endpoint 1: /api/teams
GET: Retrieve a list of teams.

Parameters:
page (default: 0)
size (default: 10)
sortBy (default: "name")

POST: Add a new team.

Request Body Example:
{
  "name": "TeamA",
  "acronym": "TA",
  "budget": 100000,
  "players": [
    {"name": "Player1", "position": "ST"},
    {"name": "Player2", "position": "CM"}
  ]
}
