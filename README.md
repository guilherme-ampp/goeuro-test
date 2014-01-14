goeuro-test
===========

Solution for the GoEuro test.
The application accesses an end-point providing JSON data and transforms the data into a CSV file.

Note:
Unfortunately the end-point given for the test didn't work as of 2014 January 11th:
http://pre.dev.goeuro.de:12345/api/v1/suggest/position/en/name/<STRING>

So I used the the current suggestion end-point in production at GoEuro.com
http://www.goeuro.com/GoEuroAPI/rest/suggest/en?term=<STRING>
