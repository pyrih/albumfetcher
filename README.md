# albumfetcher 
is a simple project for Spring Boot RESTful application to retrieve music album information from external APIs

#endpoints

http://localhost:8765/api/info

http://localhost:8765/api/info?artist=Eminem&album=Relapse

http://localhost:8765/api/info?mediaType=xml

http://localhost:8765/api/info?mediaType=json (default)

http://localhost:8765/api/info?artist=Eminem&album=Relapse&mediaType=xml


http://localhost:8765/api/doc

http://localhost:8765/api/doc?artist=Eminem&album=Relapse


http://localhost:8765/api/info/page

http://localhost:8765/api/info/page?artist=Eminem&album=Relapse&page=1

http://localhost:8765/api/info/page?artist=Eminem&album=Relapse&page=1&size=5

