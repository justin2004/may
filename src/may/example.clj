(ns may.example
  (:require  [may.core :as may]
             [may.core :refer [apl-instance]])) ; TODO until i figure out a better way this is necessary

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
