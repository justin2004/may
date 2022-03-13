(ns may.example
  (:require  [may.core :as may]
             [may.core :refer [apl-instance]] ; TODO until i figure out a better way this is necessary
             [libpython-clj2.java-api :as ja]
             [libpython-clj2.require :refer [require-python]]
             [libpython-clj2.python :as py :refer [py. py.. py.-]]))

; this is an example of using may

; start up Dyalog APL
(may/init)

(may/apl-c "⍳5") ; =>  [1 2 3 4 5]

(may/apl-c "∘.=⍨⍳3") ; => [[1 0 0] [0 1 0] [0 0 1]]

(first (may/apl-c "∘.=⍨⍳3")) ; => [1 0 0]
