; TODO need to figure out how to get an instance 
; of `apl-instance` in here
(ns may.core-test
  (:require [clojure.test :refer :all]
            ; [may.core :refer :all]
            [may.core :as may]
            [may.core :refer [apl-instance]]
            ))

(defn prep [f]
  (try
    (do 
      (print "here0")
      (may/init)
      (f))
    (catch Exception e (do
                         (print (str "had ex:" (.getMessage e)))
                         (.printStackTrace e java.lang.System/out)))))

;printStackTrace (System.out)

(use-fixtures :once prep)
; (def not-init (atom ()))
; (reset! not-init true)

(deftest a-test
  (testing "FIXME, good."
    (is (= 1 1))))

; (deftest c-test
;   (testing "FIXME, fail"
;     (is (= 0 1))))

; (deftest b-test
;   (testing "FIXME, I fail."
;     (is (do
;           (if @not-init
;             (do
;               (init)
;               (reset! not-init false)))
;           (apl-c "⍳5")))))
