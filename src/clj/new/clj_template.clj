(ns clj.new.clj-template
  (:require [clj.new.templates :refer [renderer project-name name-to-path ->files]]))

(def render (renderer "libpython_clj"))

(defn file-map->files [data file-map]
  (apply ->files data (seq file-map)))

(defn libpython-clj-template! [name & {force :force? dir :dir}]
  (let [data         {:name      (project-name name)
                      :base      (clojure.string/replace
                                  (project-name name)
                                  #"(.*?)[.](.*$)"
                                  "$1")
                      :suffix    (clojure.string/replace
                                  (project-name name)
                                  #"(.*?)[.](.*$)"
                                  "$2")
                      :sanitized (name-to-path name)}
        {base :base} data]

    (println (str  "Generating libpython-clj template for "
                   (:name data) " at " (:sanitized data) ".\n\n"
                   "For the latest information, please check out "
                   "https://github.com/clj-python/libpython-clj\n"
                   "or join us for discussion at "
                   "https://clojurians.zulipchat.com/#narrow/stream/215609-libpython-clj-dev.\n"
                   "If you run into problems, file a Github issue on the project homepage\nor connect with us at "
                   "https://clojurians.zulipchat.com/#narrow/stream/215609-libpython-clj-dev/topic/help-wanted"))

    (with-bindings {#'clj.new.templates/*force?* force
                    #'clj.new.templates/*dir*    dir}
      (let [lpc-path ".clj-kondo/configs/libpython-clj"
            tp-path ".clj-kondo/configs/tech.parallel"]
        (file-map->files
         data
         {"deps.edn"                                            (render "deps.edn" data)
          (format  "src/%s/%s.clj" (:base data) (:suffix data)) (render "core.clj" data)
          (format  "src/%s/python.clj" (:base data))            (render "python.clj" data)
          ;; clj-kondo hooks related
          ".clj-kondo/config.edn"
          (render ".clj-kondo/config.edn" data)
          ;; libpython_clj
          (str lpc-path "/config.edn")
          (render (str lpc-path "/config.edn") data)
          (str lpc-path "/hooks/libpython_clj/jna/base/def_pylib_fn.clj")
          (render (str lpc-path "/hooks/libpython_clj/jna/base/def_pylib_fn.clj") data)
          (str lpc-path "/hooks/libpython_clj/require/import_python.clj")
          (render (str lpc-path "/hooks/libpython_clj/require/import_python.clj") data)
          ;; tech.parallel
          (str tp-path "/config.edn")
          (render (str tp-path "/config.edn") data)
          (str tp-path "/hooks/tech/parallel/utils/export_symbols.clj")
          (render (str tp-path "/hooks/tech/parallel/utils/export_symbols.clj") data)})))))


(defn clj-template
  ([name] (libpython-clj-template! name))
  ([name & args] (clj-template name)))

(comment
  (newline)
  (libpython-clj-template!
   "mydomain.myapp"
   :dir "testdir"
   :force? true)
  )
