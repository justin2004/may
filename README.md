# May

## what

A docker image that has everything you need to run Clojure with the ability to evaluate Dyalog APL expressions.
AND
Some Clojure functions that help you run APL.

Clojure talks to Python via [clj-python/libpython-clj]().
Python talks to Dyalog APL via [Py'n'APL]().

## why

I wanted [April]() but with a mature and optimized APL interpreter.
So this is my attempt to approximate that by gluing some existing things together.
I don't claim that this is efficient.
I am just playing around with it for now.

## how

m2
lein pom && lein jar && lein install

## notes

```
+-----------------------------------------------------------------+
| Dyalog is free for non-commercial use but is not free software. |
| A non-commercial licence can be used for experiments and        |
| proof of concept until the point in time that it is of value.   |
| For further information visit                                   |
| https://www.dyalog.com/prices-and-licences.htm                  |
+-----------------------------------------------------------------+
```
