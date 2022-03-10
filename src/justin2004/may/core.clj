(ns justin2004.may.core
  (:require  [libpython-clj2.java-api :as ja]
             [libpython-clj2.require :refer [require-python]]
             [libpython-clj2.python :as py :refer [py. py.. py.-]]))

(def apl-instance nil) ; TODO

(defn init 
  "start up Dyalog APL"
  []
  (py/run-simple-string "import sys")
  (py/run-simple-string "sys.path.append('/root/pynapl')")
; (py/run-simple-string "from pynapl import APL")
  (require-python '[pynapl.APL :as pnapl])
  ; (def apl-instance (pnapl/APL))
  (eval '(def apl-instance (pnapl/APL))))


(comment (init))

; i couldn't make this a macro then run (type (apl "⍳5"))
;     is laziness involved in that?
(defn apl
  "evaluate `apl-string` in Dyalog APL... i think the result gets copied into a python"
  ([apl-string]
   (py/call-attr apl-instance "eval" apl-string))
  ([apl-string obj]
   (py/call-attr apl-instance "eval" apl-string obj)))
; obj is ∆ in APL
; (py/call-attr apl-instance "eval" "2⌷⊃∆" [4 5 9 0])
; i thought i would need this to convert a jvm obj to a python obj
; (ja/-copyToPy mat )

(defn apl-c
  "evaluate `apl-string` in Dyalog APL... i think the result gets copied into a python.
  and copy to to the jvm. this may be an unnecessary function"
  ([apl-string] (ja/-copyToJVM (apl apl-string)))
  ([apl-string obj] (ja/-copyToJVM (apl apl-string obj))))



; (py/run-simple-string "myapl=APL.APL()")
; (print (py/run-simple-string "foo=myapl.eval('⍳5',raw=False)"))
