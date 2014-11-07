apache-camel-nanpa
==================

This project pulls data from nanpa.com about the telephone exchanges assigned in the US and then provides a simple
REST interface to get that data in JSON format.

You can start the routes by simply running:

```
mvn exec:java
```

In your console you should see some GuiceCamelContext messages followed by a block of messages like this:

```
INFO: Starting the internal [HTTP/1.1] server on port 8000
[ttison.CamelApplication.main()] GuiceCamelContext              INFO  Route: route3 started and consuming from: Endpoint[http://Controls-MacBook-Pro.local:8000/npaNxx/(npa)/(nxx)?restletMethods=GET]
[ttison.CamelApplication.main()] GuiceCamelContext              INFO  Route: route4 started and consuming from: Endpoint[direct://get_npa_nxx]
[ttison.CamelApplication.main()] QuartzComponent                INFO  Starting scheduler.
[ttison.CamelApplication.main()] QuartzScheduler                INFO  Scheduler DefaultQuartzScheduler-camel-1_$_NON_CLUSTERED started.
[ttison.CamelApplication.main()] GuiceCamelContext              INFO  Total 4 routes, of which 4 is started.
[ttison.CamelApplication.main()] GuiceCamelContext              INFO  Apache Camel 2.14.0 (CamelContext: camel-1) started in 0.528 seconds
[1) thread #0 - timer://runOnce] route2                         INFO  Update starting
[1) thread #0 - timer://runOnce] route2                         INFO  Reading file
[1) thread #0 - timer://runOnce] route2                         INFO  Unzipping file
[1) thread #0 - timer://runOnce] route2                         INFO  Processing with Flatpack
[1) thread #0 - timer://runOnce] route2                         INFO  Splitting into lines and processing
[1) thread #0 - timer://runOnce] route2                         INFO  Updating cache
[1) thread #0 - timer://runOnce] route2                         INFO  Updating complete
```

Once `Updating complete` shows up you can run queries against the system.  To look up area code 907 and exchange 200 (907-200-xxxx) you can do this:

```
$ curl http://localhost:8000/npaNxx/907/200
```

You will get a response like this:

```
{"state":"AK","npa":907,"nxx":200,"ocn":"6304","company":"ACS WIRELESS, INC.","rateCenter":"VALDEZ","useCode":"AS","assignedDate":"Feb 21, 2006 12:00:00 AM","initialGrowth":"Initial"}
```

This tells you that 907-200 is in Valdez, Alaska, is owned by ACS Wireless Inc, and was assigned on Feb 21st, 2006.