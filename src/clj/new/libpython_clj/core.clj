(ns {{base}}.{{suffix}}
    (:require [libpython-clj2.python :as py :refer [py. py.- py.. py* py**]]
              [{{base}}.python]
              [libpython-clj2.require :refer [require-python import-python]]))

(import-python)

(comment
  (require-python 'os)
  (os/getcwd))


