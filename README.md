# ping

A small [Dropwizard](http://dropwizard.github.io/dropwizard/) web application with an [AngularJS](http://angularjs.org/) and [Bootstrap](http://getbootstrap.com/) UI built using the [AngularJS-Dropwizard Generator](https://github.com/rayokota/generator-angular-dropwizard) for [Yeoman](http://yeoman.io/). Tested on Oracle [JDK 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html) and [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

## Prerequisites

### Required

* [JDK 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html) or [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](http://maven.apache.org/)

### Optional

* [Node.js](http://nodejs.org/) - required for Yeoman install
* [Yeoman](http://yeoman.io/) - if you want to use the angular-dropwizard scaffolding generator
* [AngularJS-Dropwizard Generator](https://github.com/rayokota/generator-angular-dropwizard)

## Build & Start the Web Server

Run ```./start.sh```

Or individual steps via [Maven](http://maven.apache.org/):

```
mvn compile
mvn exec:exec -pl ping-service
```

Successful compilation and startup should look like:

```
INFO  [2014-04-09 22:11:26,873] com.yammer.dropwizard.cli.ServerCommand: Starting ping
        .__                  
______  |__|  ____     ____  
\____ \ |  | /    \   / ___\ 
|  |_> >|  ||   |  \ / /_/  >
|   __/ |__||___|  / \___  / 
|__|             \/ /_____/  


INFO  [2014-04-09 22:11:26,880] org.eclipse.jetty.server.Server: jetty-8.1.10.v20130312
INFO  [2014-04-09 22:11:27,268] com.sun.jersey.server.impl.application.WebApplicationImpl: Initiating Jersey application, version 'Jersey: 1.17.1 02/28/2013 12:47 PM'
INFO  [2014-04-09 22:11:27,530] com.yammer.dropwizard.config.Environment: The following paths were found for the configured resources:

    GET     /ping/{message} (io.bumgardner.ping.resources.PingResource)
    DELETE  /ping/users/{id} (io.bumgardner.ping.resources.UserResource)
    GET     /ping/users (io.bumgardner.ping.resources.UserResource)
    GET     /ping/users/{id} (io.bumgardner.ping.resources.UserResource)
    POST    /ping/users (io.bumgardner.ping.resources.UserResource)
    PUT     /ping/users/{id} (io.bumgardner.ping.resources.UserResource)

INFO  [2014-04-09 22:11:27,532] com.yammer.dropwizard.config.Environment: tasks = 

    POST    /tasks/gc (com.yammer.dropwizard.tasks.GarbageCollectionTask)

INFO  [2014-04-09 22:11:28,327] org.eclipse.jetty.server.AbstractConnector: Started InstrumentedSelectChannelConnector@0.0.0.0:8080
INFO  [2014-04-09 22:11:28,331] org.eclipse.jetty.server.AbstractConnector: Started SocketConnector@0.0.0.0:8081
```

### Optional: Build with Grunt & Bower

If you have installed the latest Node.js run the following which will install Grunt and Bower and any Bower client dependencies. NOTE: for Heroku support the package.json file was renamed (otherwise Heroku thinks this is a Node project not a Java project and does not run Maven), so you will have to rename it back to package.json before running the following steps.

```
npm install
bower install
```

To run a Grunt server task to watch for filechanges and do live reload in the browser run:

```
grunt server
```

And you can connect to [http://localhost:9000](http://localhost:9000) for live reload.

## View in the Browser

Go to [http://localhost:8080](http://localhost:8080)

You can create new users from the [Users](http://localhost:8080/#/users) tab.

## Operational Healthcheck/Metrics:

Go to [http://localhost:8081/](http://localhost:8081/)

This gives the ability to check:

* [Metrics](http://localhost:8081/metrics?pretty=true)
* [Ping](http://localhost:8081/ping)
* [Threads](http://localhost:8081/threads)
* [Healthcheck](http://localhost:8081/healthcheck)

## Heroku Deployment

Be sure to follow the [Update your PATH](https://devcenter.heroku.com/articles/add-java-version-to-an-existing-maven-app#update-your-path) instructions for Java 1.7 support.

It should look like ```heroku config:set PATH=/app/.jdk/bin:/usr/local/bin:/usr/bin:/bin```

## From the Command-Line

### cURL

```
curl http://localhost:8080/ping/hello
Received parameters:
message=hello
```

### Apache Benchmark

For example to issue 10k requests from 100 clients:

```
ab -n 10000 -c 100 http://localhost:8080/ping/hello
This is ApacheBench, Version 2.3 <$Revision: 655654 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking 192.168.33.101 (be patient)
Completed 1000 requests
Completed 2000 requests
Completed 3000 requests
Completed 4000 requests
Completed 5000 requests
Completed 6000 requests
Completed 7000 requests
Completed 8000 requests
Completed 9000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:        
Server Hostname:        192.168.33.101
Server Port:            8080

Document Path:          /ping/hello
Document Length:        35 bytes

Concurrency Level:      100
Time taken for tests:   9.936 seconds
Complete requests:      10000
Failed requests:        0
Write errors:           0
Total transferred:      1230369 bytes
HTML transferred:       350105 bytes
Requests per second:    1006.42 [#/sec] (mean)
Time per request:       99.362 [ms] (mean)
Time per request:       0.994 [ms] (mean, across all concurrent requests)
Transfer rate:          120.92 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    7   7.4      4      32
Processing:    24   92  32.6     85     347
Waiting:       24   91  31.6     84     321
Total:         30   99  31.9     92     349

Percentage of the requests served within a certain time (ms)
  50%     92
  66%    105
  75%    114
  80%    121
  90%    142
  95%    154
  98%    183
  99%    198
 100%    349 (longest request)
```

## Database

Uses [Hibernate](http://hibernate.org/) and [Derby](http://derbyjs.com/) database on filesystem ```/tmp/mydb``` in development. For production uses [PostgreSQL](http://www.postgresql.org/). Development/Production settings are controlled via the different Yaml (.yml) files in ./ping-service/.

To run on Postgres, be sure to do something like the following. As the user 'vagrant', do:

```
sudo su postgres
psql
create user vagrant with password 'vagrant';
create database mydb;
grant all privileges on database mydb to vagrant;
\q
exit
```

You can now connect to psql as the user 'vagrant', and from a client config can set username to 'vagrant' and password to 'vagrant'.
