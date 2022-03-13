# May

## what

A docker image that has everything you need to run Clojure with the ability to evaluate APL expressions with [Dyalog APL](https://www.dyalog.com/).

AND

Some Clojure functions that help you evaluate APL.

Clojure talks to Python via [clj-python/libpython-clj](https://github.com/clj-python/libpython-clj).

Python talks to Dyalog APL via [Py'n'APL](https://github.com/Dyalog/pynapl).

## why

I wanted [April](https://github.com/phantomics/april) but with a mature and optimized APL interpreter.
So this is my attempt to approximate that by gluing some existing things together.
I don't claim that this is efficient (as Python is used as a middleman).
I am just playing around with it for now.

## how

- Have Docker and Make installed
- Use vim with [vim-iced](https://github.com/liquidz/vim-iced) for your Clojure REPL
    - Eventually I might add support for other REPLs (let me know if you want one)
- run:

`make run-for-vim-iced`

- run:

`vim src/may/example.clj`

- in vim attach the REPL:

`:IcedConnect`

- then evaluate some APL and have fun:

```clojure
(ns may.example
  (:require  [may.core :as may]
             [may.core :refer [apl-instance]]))

; this is an example of using may

; start up Dyalog APL
(may/init)

; iota 5
(may/apl-c "⍳5") ; =>  [1 2 3 4 5]

; 3 by 3 identity matrix
(may/apl-c "∘.=⍨⍳3") ; => [[1 0 0] [0 1 0] [0 0 1]]

; interact with the resultant data with Clojure
(first (may/apl-c "∘.=⍨⍳3")) ; => [1 0 0]

; send Clojure data into Dyalog APL 
;  what is the shape (⍴) of the Clojure vector [2 4 6]
(may/apl-c "⍴⊃∆" [2 4 6]) ; => [3]
; with in Dyalog APL, ∆ is the enclosed data you pass in from Clojure

; 3 3 reshape of the Clojure vector [2 4 6]
(may/apl-c "3 3 ⍴ ⊃∆" [2 4 6]) ; => [[2 4 6] [2 4 6] [2 4 6]]
```


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
