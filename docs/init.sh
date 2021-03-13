#!/usr/bin/env bash
clojure -Sdeps '{:deps {seancorfield/clj-new {:mvn/version "0.8.6"}}}' \
  -m clj-new.create \
  https://github.com/clj-python/clj-template@7a2c3a7ece2685fa8dfd57b1a45247fe1d6029eb \
  $1
