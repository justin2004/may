(ns may.scratch
  (:require  [may.core :as may]
             [libpython-clj2.java-api :as ja]
             [libpython-clj2.require :refer [require-python]]
             [libpython-clj2.python :as py :refer [py. py.. py.-]]))


(defn rgb [n]
  (let [r (bit-and 0xff (bit-shift-right n 16))
        g (bit-and 0xff (bit-shift-right n 8))
        b (bit-and 0xff (bit-shift-right n 0))]
    [r g b]))


(defn png->vec [path]
  "returns: [rows col] [rgbs] "
  (let [im (javax.imageio.ImageIO/read (new java.io.File path))
        cols (.getWidth im)
        rows (.getHeight im)
        ; v (-> im .getRaster .getDataBuffer .getData)
        v (.getRGB im 0 0 (.getWidth im) (.getHeight im) nil 0 (.getWidth im))
        a (mapcat rgb v) ; a lazyseq of vecs ;; used to be map
        ; b (may/apl "⍴∆" a)
        ] 
    [[rows cols] a]))


(defn vec->png [rows cols v]
  (let [im (new java.awt.image.BufferedImage
                cols
                rows
                java.awt.image.BufferedImage/TYPE_INT_ARGB)
        _ (.setRGB im 0 0 cols rows (int-array (* cols rows) v) 0 cols)]
    (javax.imageio.ImageIO/write im
                                 "png"
                                 (new java.io.File "/mnt/Sia_out_new.png")))
  "Sia_out_new.png")

(defn vec->rgb [v]
  (let [r (first v)
        g (second v)
        b (nth v 2)]
    (bit-or
     (bit-shift-left 255 24) ; alpha channel
     (bit-shift-left r 16)
     (bit-shift-left g 8)
     (bit-shift-left b 0))))

; (rgb (vec->rgb [7 2 7]))
; (vec->png 100 100 (doall (map vec->rgb (may/apl-c "10000 ⍴ ∆" [255 0 0]))))


; assumes 3 channels (rgb)
(defn to-png [rows cols v]
  "`v` is a vec (with no nesting) of r g b r g b etc. "
  (print (vec->png rows cols (map vec->rgb (partition 3 v)))))


; helper functions
(defn init []
  (may/apl "vecToMat←{ dims←⊃1⌷⍵
           vec←⊃2⌷⍵
           m←1=3|⍳≢vec
           dims ⍴ (m⊂vec)} ⋄ 0")
  (may/apl "matToVec←{⊃,/,⍵} ⋄ 0")) ;good -- it removes structure

; getting the image in APL
; (time (may/apl "a←⊃∆ ⋄ 0" (png->vec "/mnt/twitter_photo1_sm.png")))
(comment (time (may/apl "img←vecToMat(⊃∆) ⋄ 0" (png->vec "/mnt/twitter_photo1_sm.png"))))
(comment (may/apl "⍴img"))
(comment (time (may/apl "img←vecToMat(⊃∆) ⋄ 0" (png->vec "/mnt/Sia.png"))))

; it is faster to have no structure (just numbers)
; getting the image out of APL
(comment (time (to-png 598 500 (may/apl-c "matToVec img")))) ;good
(comment (may/apl "matToVec 3 3 ⍴ ⊂(1 2 3) ")) ; example

; getting the image out of APL
; (time (print (vec->png 304 540 (map vec->rgb (may/apl-c ",imgred")))))
(comment (to-png 100 100 (may/apl-c "matToVec ,?100 100 ⍴ (⊂255 255 255)")))
; (print (vec->png 100 100 (map vec->rgb (may/apl-c ",100 100↑imgred"))))


